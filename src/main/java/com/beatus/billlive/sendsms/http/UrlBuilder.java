package com.beatus.billlive.sendsms.http;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UrlBuilder {

	protected String baseUrl;
	protected List<Parameter> parameters = new ArrayList<Parameter>();

    private boolean useFragment = false;

	/**
	 * Create an UrlBuilder with base URL. The baseUrl can't be null.
	 *
	 * @param baseUrl
	 */
	public UrlBuilder(String baseUrl) {
		if (baseUrl == null || baseUrl.length() <= 0)
			throw new IllegalArgumentException("BaseUrl can't be empty");

		this.baseUrl = baseUrl;
	}

    public UrlBuilder(String base, String suffix) {
        this(concatWithOverlap(base, suffix));
    }

	/**
	 * Add a string parameter to the URL
	 * @param name
	 * @param value
	 */
	public UrlBuilder add(String name, String value) {
		parameters.add(new Parameter(name, value));
        return this;
	}

	/**
	 * Add an integer parameter to the URL
	 * @param name
	 * @param value
	 */
	public UrlBuilder add(String name, int value) {
		parameters.add(new Parameter(name, Integer.toString(value)));
        return this;
	}

    /**
     * Add parameters from the map. If the value contains array of strings, multiple entries
     * with the same name will be duplicated in parameter list.
     *
     * @param parameters
     */
    public UrlBuilder add(Map<String, Object> parameters)  {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String[]) {
                for (String v : (String[])value)  {
                    add(entry.getKey(), v);
                }
            } else if (value == null) {
                add(entry.getKey(), null);
            } else {
                add(entry.getKey(), value.toString());
            }
        }
        return this;
    }

	/**
	 * Get the final URL with all the parameters appended.
	 * @return
	 */
	public String getUrl() {
        StringBuilder sb = new StringBuilder(baseUrl);

        char separator;

        if (useFragment) {
            if (baseUrl.indexOf('#') > 0)
                separator = '&';
            else
                separator = '#';

        } else {
            if (baseUrl.indexOf('?') > 0)
                separator = '&';
            else
                separator = '?';
        }

        for (Parameter p : parameters) {
            sb.append(separator);
            try {
                sb.append(URLEncoder.encode(p.name, "UTF-8"));
                sb.append('=');
                if (p.value != null) {
                    sb.append(URLEncoder.encode(p.value, "UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                // Not really possible, throw unchecked exception
                throw new IllegalStateException("No UTF-8");
            }
            separator = '&';
        }

        return sb.toString();
	}

    /**
     * Returns the URL
     * @return encoded URL
     */
	public String toString() {
        return getUrl();
	}

    /**
     * Change useFragment flag
     *
     * @param useFragment
     * @return
     */
    public UrlBuilder setUseFragment(boolean useFragment) {
        this.useFragment = useFragment;
        return this;
    }

    /**
     *
     * Concatenate 2 strings with overlapping part, which is not duplicated
     * in the resulting string.
     * <p>
     * For example,
     * <pre>
     * String s1 = "abcdefg";
     * String s2 = "fghijk";
     * String s3 = concatWithOverlap(s1, s2); // "abcdefghijk"
     *
     * s1 = "http://host/idp/api";
     * s2 = "/api/get_data";
     * s3 = concatWithOverlap(s1, s2); // "http://host/idp/api/get_data"
     * </pre>
     *
     * @param s1 The original string
     * @param s2 The string to append to the original string
     * @return The concatenated string
     */
    public static String concatWithOverlap(String s1, String s2) {

            if (s1 == null)
                    return s2;

            if (s2 == null)
                    return s1;

            int len = Math.min(s1.length(), s2.length());

            // Find the index for the end of overlapping part

            int index = -1;

            for (int i = len; i > 0; i--) {
                    String substring = s2.substring(0, i);
                    if (s1.endsWith(substring)) {
                            index = i;
                            break;
                    }
            }

            StringBuilder sb = new StringBuilder(s1);
            if (index < 0)
                    sb.append(s2);
            else if (index <= s2.length())
                    sb.append(s2.substring(index));

            return sb.toString();
    }




    /**
     * Internal class to store name-value pair.
     *
     * Map is not used because it can't handle duplicate
     * entries.
     */
	private static class Parameter {
		public String name;
		public String value;

		public Parameter(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
}
