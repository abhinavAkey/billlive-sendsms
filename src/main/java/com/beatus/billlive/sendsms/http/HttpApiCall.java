package com.beatus.billlive.sendsms.http;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.security.InvalidParameterException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;


/**
 *
 * Http-based API Calls
 *
 * Make HTTP calls using Google's client API library.
 *
 */
public class HttpApiCall {

    // application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5


    public static class ResponseWithStatus {
        private final int statusCode;
        private final String response;

        public ResponseWithStatus(int statusCode, String response) {
            this.statusCode = statusCode;
            this.response = response;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getResponse() {
            return response;
        }
    }

    private final HttpClient HTTP_CLIENT;
    private final HttpTransport TRANSPORT;
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApiCall.class);
    private static final int DEFAULT_HTTP_TIMEOUT = 5000;
    public static final String KEYSTORE_FILENAME = "keystore-filename";
    public static final String KEYSTORE_PASSWORD = "keystore-password";
    public static final String TRUSTSTORE_FILENAME = "trustore-filename";
    public static final String TRUSTSTORE_PASSWORD = "truststore-password";
    public static final String ENABLED_PROTOCOLS = "enabled-protocols";

    //-------------------------------------------------------------
    // Configurable parameters
    //-------------------------------------------------------------

    private int connectTimeout;
    private int readTimeout;
    private int maxTotal = 200;
    private int maxTotalPerRoute = 20;

    public HttpApiCall() {
        connectTimeout = readTimeout = DEFAULT_HTTP_TIMEOUT;

        HTTP_CLIENT = createHttpClient();
        TRANSPORT = new ApacheHttpTransport(HTTP_CLIENT);
    }

    public HttpApiCall(int connectTimeout, int readTimeout) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;

        HTTP_CLIENT = createHttpClient();
        TRANSPORT = new ApacheHttpTransport(HTTP_CLIENT);
    }

    public HttpApiCall (int connectTimeout, int readTimeout, Map<String,String> options) {
       this(connectTimeout, readTimeout, 20, 200, options);
    }

    public HttpApiCall (int connectTimeout, int readTimeout,
                        int maxTotal, int maxTotalPerRoute, Map<String,String> options) {

        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.maxTotal = maxTotal;
        this.maxTotalPerRoute = maxTotalPerRoute;
        boolean useOptions = false;

        if (options != null && ! options.isEmpty()) {
            for (String values : options.values()) {
                if (! values.isEmpty()) {
                    useOptions = true;
                    break;
                }
            }
        }

        if (useOptions) {
            LOGGER.info("using options map {}", options);
            HTTP_CLIENT = createHttpClient(options);
        } else {
            LOGGER.info("not using options map, empty");
            HTTP_CLIENT = createHttpClient();
        }

        TRANSPORT = new ApacheHttpTransport(HTTP_CLIENT);
    }


    //-------------------------------------------------------------
    // HTTP GET
    //-------------------------------------------------------------

    /**
     * Do a HTTP GET and return responses as string.
     * 
     * @param url URL to fetch the content
     * @return The content as string
     * @throws IOException
     */
    public String get(String url) throws IOException {
        return get(url, null);
    }


    /**
     * Do a HTTP GET and return the status code + response as string.
     *
     * @param url URL to fetch the content
     * @return The content as string
     * @throws IOException
     */
    public ResponseWithStatus getWithStatus(String url) throws IOException {
        return getWithStatus(url, null);
    }

    /**
     * Do a HTTP GET with optional headers and return response as string.
     *
     * @param url URL to fetch content
     * @param headers
     * @return
     * @throws IOException
     */
    public String get(String url, Map<String, String> headers) throws IOException {
        return getWithStatus(url, headers).getResponse();
    }

    /**
     * Do a HTTP GET with optional headers and return the status code + response as string.
     *
     * @param url URL to fetch content
     * @param headers
     * @return
     * @throws IOException
     */
    public ResponseWithStatus getWithStatus(String url, Map<String, String> headers) throws IOException {

        HttpRequestFactory factory = createFactory(null, headers);
        HttpRequest request = factory.buildGetRequest(new GenericUrl(url));
        HttpResponse response = request.execute();
        return new ResponseWithStatus(response.getStatusCode(), response.parseAsString());
    }

    /**
     * Do a HTTP GET and return JSON response in a Java class
     *
     * @param url URL to fetch the content
     * @param jsonClass The class for the object
     * @return The Java object
     * @throws IOException
     */
    public <T> T getJson(String url, Class<T> jsonClass) throws IOException {
        return getJson(url, null, jsonClass);
    }

    /**
     * Do a HTTP GET with optional headers and return JSON response in a Java class
     *
     * @param url URL to fetch the content
     * @param headers Extra headers to send
     * @param jsonClass The class representing the JSON object
     * @param <T> The type for jsonClass
     * @return The Java object
     * @throws IOException
     */
    public <T> T getJson(String url, Map<String, String> headers,
            Class<T> jsonClass) throws IOException {

        HttpRequestFactory factory = createFactory("text/javascript, application/json, */*", headers);

        HttpRequest request = factory.buildGetRequest(new GenericUrl(url));
        request.setParser(new JsonObjectParser(JSON_FACTORY)); // This adds default application/json
        HttpResponse response = request.execute();

        return response.parseAs(jsonClass);
    }

    //-------------------------------------------------------------
    // HTTP POST
    //-------------------------------------------------------------

    /**
     * Send HTTP request as a form POST and return data as string
     *
     * @param url The URL of server to post to
     * @param params The form parameters
     * @return The response as string
     * @throws IOException
     */
    public String post(String url, Map<String, String> params) throws IOException {
        return post(url, params, null);
    }

    /**
     * Send HTTP request as a form POST and return data as string
     * 
     * @param url The URL of server to post to
     * @param params The form parameters
     * @param headers Extra HTTP headers to send with the request
     * @return The response as string
     * @throws IOException
     */
    public String post(String url, Map<String, String> params,
        Map<String, String> headers) throws IOException {

        HttpRequestFactory factory = createFactory(null, headers);

        UrlEncodedContent content = new UrlEncodedContent(params);
        HttpRequest request = factory.buildPostRequest(new GenericUrl(url), content);
        HttpResponse response = request.execute();
        return response.parseAsString();

    }

    /**
     * Send HTTP request by posting data with specified content type
     *
     * @param url The URL of server to post to
     * @param data The data to post
     * @param contentType The content type of the data
     * @return The response as string
     * @throws IOException
     */
    public String post(String url, String data, String contentType) throws IOException {
        return post(url, data, contentType, null);
    }

    /**
     * Send HTTP request by posting data with specified content type and extra headers
     *
     * @param url The URL of server to post to
     * @param data The data to post
     * @param contentType The content type of the data
     * @param headers Extra headers to add to the request
     * @return The response as string
     * @throws IOException
     */
    public String post(String url, String data, String contentType,
        final Map<String, String> headers) throws IOException {

        HttpRequestFactory factory = createFactory(contentType, headers);

        HttpContent content = ByteArrayContent.fromString(contentType, data);
        HttpRequest request = factory.buildPostRequest(new GenericUrl(url), content);
        HttpResponse response = request.execute();

        return response.parseAsString();
    }

    /**
     * Send HTTP request by posting parameters (form post) and return result in
     * an object representing response JSON.
     * <p />
     * <code>GenericJson</code> can be used for output to return data as map/list.
     *
     * @param url The URL To post to
     * @param params The parameters
     * @param responseClass The class to decode response into
     * @param headers Extra headers
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T postJson(String url, Map<String, String> params,
            Class<T> responseClass, Map<String, String> headers) throws IOException {

        HttpRequestFactory factory = createFactory("text/javascript, application/json", headers);

        UrlEncodedContent content = new UrlEncodedContent(params);

        HttpRequest request = factory.buildPostRequest(new GenericUrl(url), content);

        request.setParser(new JsonObjectParser(JSON_FACTORY));

        HttpResponse response = request.execute();
        return response.parseAs(responseClass);
    }

    /**
     * Send HTTP request by deleting an object representing JSON and return result in
     * another object representing response JSON.
     * <p />
     * <code>GenericJson</code> can be used for output to return data as map/list.
     *
     * @param url The URL to post to
     * @param responseClass The class for the object representing the response JSON
     * @param <T> The class used as response
     * @return The Java object representing the response XML
     * @throws IOException
     */
    public <T> T deleteJson(String url, Map<String, String> headers,
                         Class<T> responseClass) throws IOException {


        HttpRequestFactory factory = createFactory("text/javascript, application/json", headers);
        HttpRequest request = factory.buildDeleteRequest(new GenericUrl(url));
        request.setParser(new JsonObjectParser(JSON_FACTORY));
        HttpResponse response = request.execute();
        return response.parseAs(responseClass);
    }


    /**
     * Send HTTP request by putting an object representing JSON and return result in
     * another object representing response JSON.
     * <p />
     * <code>GenericJson</code> can be used for output to return data as map/list.
     *
     * @param url The URL to post to
     * @param requestData The Java object representing the request JSON
     * @param responseClass The class for the object representing the response JSON
     * @param <T> The class used as response
     * @return The Java object representing the response XML
     * @throws IOException
     */
    public <T> T putJson(String url, Object requestData, Map<String, String> headers,
                          Class<T> responseClass) throws IOException {


        HttpRequestFactory factory = createFactory("text/javascript, application/json", headers);

        JsonHttpContent content = new JsonHttpContent(JSON_FACTORY, requestData);

        HttpRequest request = factory.buildPutRequest(new GenericUrl(url), content);

        request.setParser(new JsonObjectParser(JSON_FACTORY));

        HttpResponse response = request.execute();
        return response.parseAs(responseClass);
    }

    /**
     * Send HTTP request by posting an object representing JSON and return result in
     * another object representing response JSON.
     * <p />
     * <code>GenericJson</code> can be used for output to return data as map/list.
     *
     * @param url The URL to post to
     * @param requestData The Java object representing the request JSON
     * @param responseClass The class for the object representing the response JSON
     * @param <T> The class used as response
     * @return The Java object representing the response XML
     * @throws IOException
     */
    public <T> T postJson(String url, Object requestData, Map<String, String> headers,
            Class<T> responseClass) throws IOException {


        HttpRequestFactory factory = createFactory("text/javascript, application/json", headers);

        JsonHttpContent content = new JsonHttpContent(JSON_FACTORY, requestData);

        HttpRequest request = factory.buildPostRequest(new GenericUrl(url), content);

        request.setParser(new JsonObjectParser(JSON_FACTORY));

        HttpResponse response = request.execute();
        return response.parseAs(responseClass);
    }

    public boolean ping (String url) throws IOException {
        HttpRequestFactory factory = createFactory(null, null);
        HttpRequest request = factory.buildHeadRequest(new GenericUrl(url));
        HttpResponse response = request.execute();

        return response.getStatusCode() == 200;
    }

    //-------------------------------------------------------------
    // Utility methods
    //-------------------------------------------------------------

    public static Map<String, String> getAuthHeader(String username, String password) {
        try {
            String auth = Base64.encodeBase64String((username + ':' + password).getBytes("UTF-8"));
            Map<String, String> headers = new TreeMap<String, String>();
            headers.put("Authorization", "Basic " + auth);
            return headers;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 missing");
        }
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxTotalPerRoute() {
        return maxTotalPerRoute;
    }

    public void setMaxTotalPerRoute(int maxTotalPerRoute) {
        this.maxTotalPerRoute = maxTotalPerRoute;
    }

    //-------------------------------------------------------------
    // Internal methods
    //-------------------------------------------------------------

    public HttpRequestFactory createFactory(final String contentType,
            final Map<String, String> headers) {

        HttpRequestFactory factory = TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) {
                HttpHeaders httpHeaders = request.getHeaders();
                if (contentType != null && contentType.length() >0) {
                    httpHeaders.setAccept("text/html, text/plain, " + contentType);
                }

                if (headers != null && !headers.isEmpty()) {
                    for(Map.Entry<String, String> entry : headers.entrySet()) {
                        httpHeaders.set(entry.getKey(), Arrays.asList(entry.getValue()));
                    }
                }

                request.setConnectTimeout(connectTimeout);
                request.setReadTimeout(readTimeout);
            }

        });

        return factory;
    }

    public HttpClient createHttpClient() {

        LOGGER.info("using backward compatible HttpClient");

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        registry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(registry);
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxTotalPerRoute);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(connectionManager);
        defaultHttpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(1, false));

        return defaultHttpClient;
    }

    public HttpClient createHttpClient(Map<String,String> options) {

        String[] enabledProtocols = null;
        if (containsOption(ENABLED_PROTOCOLS, options)) {
             enabledProtocols = options.get(ENABLED_PROTOCOLS).split(",");
        }

        KeyManagerFactory keyManagerFactory = null;
        if (containsOption(KEYSTORE_FILENAME, options) && containsOption(KEYSTORE_PASSWORD, options)) {
            try {
                LOGGER.info("loading keystore ...");
                KeyStore keyStore = KeyStore.getInstance("JKS");
                keyStore.load(
                        new FileInputStream(options.get(KEYSTORE_FILENAME)),
                        options.get(KEYSTORE_PASSWORD).toCharArray());
                keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
                keyManagerFactory.init(keyStore, options.get(KEYSTORE_PASSWORD).toCharArray());
            } catch (Exception e) {
                e.printStackTrace();
                throw new InvalidParameterException(e.getMessage());
            }
        }

        TrustManagerFactory trustManagerFactory = null;
        if (containsOption(TRUSTSTORE_FILENAME, options) && containsOption(TRUSTSTORE_PASSWORD, options)) {
            try {
                LOGGER.info("loading truststore ..");
                KeyStore keyStore = KeyStore.getInstance("JKS");
                keyStore.load(
                        new FileInputStream(options.get(TRUSTSTORE_FILENAME)),
                        options.get(TRUSTSTORE_PASSWORD).toCharArray());
                trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
                trustManagerFactory.init(keyStore);
            } catch (Exception e) {
                e.printStackTrace();
                throw new InvalidParameterException(e.getMessage());
            }
        }

        return createHttpClient(enabledProtocols,
                trustManagerFactory != null ? trustManagerFactory.getTrustManagers() : null,
                keyManagerFactory != null ? keyManagerFactory.getKeyManagers() : null);

    }

    public boolean containsOption (String key, Map<String,String> options) {
       return options.containsKey(key) && ! options.get(key).trim().isEmpty();
    }

    public HttpClient createHttpClient(String[] enabledProtocols,
                                       TrustManager[] trustManagers, KeyManager[] keyManagers){

        /*
         * This is the 4.5 impl -- which breaks ApacheHttpTransport because getParams() method is gone starting
         * in 4.3
         * https://github.com/google/google-api-java-client/issues/928
         *

        LOGGER.info("using new HttpClient wiht protocols: {}", enabledProtocols);

        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        //new String[]{"TLSv1.2"}
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                enabledProtocols, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory)
                .build();

        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        clientConnectionManager.setMaxTotal(200);
        clientConnectionManager.setDefaultMaxPerRoute(20);

        return HttpClients.custom().setConnectionManager(clientConnectionManager).build(); */

        SSLContext sslContext = null;

        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagers, trustManagers, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParameterException(e.getMessage());
        }

        SSLSocketFactory sf = new CustomizedSSLSocketFactory(sslContext, null, enabledProtocols);

        Scheme httpsScheme = new Scheme("https", 443, sf);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(httpsScheme);
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);

        LOGGER.info("using maxTotal={}, maxTotalPerRoute={}", maxTotal, maxTotalPerRoute);

        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxTotalPerRoute);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(connectionManager);
        defaultHttpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(1, false));
        //defaultHttpClient.setKeepAliveStrategy();
        return defaultHttpClient;
    }

    public class CustomizedSSLSocketFactory extends SSLSocketFactory {

        private String[] tlsProtocols;

        public CustomizedSSLSocketFactory(SSLContext sslContext,
                                          X509HostnameVerifier hostnameVerifier,
                                          String[] tlsProtocols) {
            super(sslContext, hostnameVerifier);
            this.tlsProtocols = tlsProtocols;
        }

        /*static final int LIMIT_MAX_VALUE = 65535;
            static final int LIMIT_MIN_VALUE = 0;
            static final ProtocolVersion NONE = new ProtocolVersion(-1, "NONE");
            static final ProtocolVersion SSL20Hello = new ProtocolVersion(2, "SSLv2Hello");
            static final ProtocolVersion SSL30 = new ProtocolVersion(768, "SSLv3");
            static final ProtocolVersion TLS10 = new ProtocolVersion(769, "TLSv1");
            static final ProtocolVersion TLS11 = new ProtocolVersion(770, "TLSv1.1");
            static final ProtocolVersion TLS12 = new ProtocolVersion(771, "TLSv1.2");
            */

        @Override
        protected void prepareSocket(SSLSocket socket) {
            // Enforce client-specified protocols
            if(tlsProtocols != null) {
                HttpApiCall.LOGGER.debug("enabling protocols {} on socket", Arrays.asList(tlsProtocols));
                socket.setEnabledProtocols(tlsProtocols);
                try {
                    socket.setTcpNoDelay(true);
                } catch (SocketException e) {
                    HttpApiCall.LOGGER.warn("setting no delay on socket failed {}", e);
                }
            }
        }
    }

}
