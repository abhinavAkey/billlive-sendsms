package com.beatus.billlive.sendsms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.beatus.billlive.sendsms.model.Distributor;
import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.LocationAndPrice;
import com.beatus.billlive.sendsms.model.Product;
import com.beatus.billlive.sendsms.model.ProductAndPrice;
import com.beatus.billlive.sendsms.model.ProductWithLocationsAndPricesRequest;

@Service
@Component("smsService")
public class SMSService {

	@Resource(name = "distributorService")
	private DistributorService distributorService;

	@Resource(name = "locationService")
	private LocationService locationService;

	@Resource(name = "productService")
	private ProductService productService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SMSService.class);

	public void getSMSScreen(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
		List<Product> products = productService.getProducts();
		Collections.sort(products);
		model.addAttribute("products", products);
		List<Location> locations = locationService.getLocations();
		Collections.sort(locations);
		model.addAttribute("locations", locations);
		List<Product> productsAndLocations = productService.getProductsAndLocations();

		List<LocationAndPrice> locsAndPrices = new ArrayList<LocationAndPrice>();
		for (int i = 0; i < locations.size(); i++) {
			List<ProductAndPrice> productAndPrices = new ArrayList<ProductAndPrice>();
			for (int j = 0; j < productsAndLocations.size(); j++) {
				if (locations.get(i).getLocationName().equals(productsAndLocations.get(j).getProductLocation())) {
					ProductAndPrice productAndPrice = new ProductAndPrice();
					productAndPrice.setProductName(productsAndLocations.get(j).getProductName());
					productAndPrice.setPrice(productsAndLocations.get(j).getProductPrice());
					productAndPrices.add(productAndPrice);
				}
			}
			Collections.sort(productAndPrices);
			LocationAndPrice locAndPrice = new LocationAndPrice();
			locAndPrice.setLocationName(locations.get(i).getLocationName());
			locAndPrice.setProductAndPrices(productAndPrices);
			locsAndPrices.add(locAndPrice);
		}
		Collections.sort(locsAndPrices);
		model.addAttribute("locsAndPrices", locsAndPrices);
	}

	public void postSMSScreen(HttpServletRequest request, ProductWithLocationsAndPricesRequest productNameLocAndPrice,
			ModelMap model) throws ClassNotFoundException, SQLException {

		if (productNameLocAndPrice != null) {
			if (productNameLocAndPrice.getProductNameLocAndPrice() != null
					&& productNameLocAndPrice.getProductNameLocAndPrice().size() > 0) {
				List<String> productLocAndPrice = productNameLocAndPrice.getProductNameLocAndPrice();
				Map<String, List<ProductAndPrice>> productsUpdated = new HashMap<String, List<ProductAndPrice>>();
				for (int i = 0; i < productLocAndPrice.size(); i++) {
					String[] productLocationValue = productLocAndPrice.get(i).split("##");
					if (productLocationValue != null && productLocationValue.length == 3) {
						List<ProductAndPrice> list = null;
						if (productsUpdated.containsKey(productLocationValue[0])) {
							list = productsUpdated.get(productLocationValue[0]);
						} else {
							list = new ArrayList<ProductAndPrice>();
						}
						ProductAndPrice product = new ProductAndPrice();
						product.setProductName(productLocationValue[1]);
						//product.setPrice(productLocationValue[2]);
						list.add(product);
						productsUpdated.put(productLocationValue[0], list);
					}
				}
				List<Distributor> distributors = distributorService.getDistributors();
				for (Distributor distributor : distributors) {
					if (distributor != null && productsUpdated.containsKey(distributor.getLocationId())) {
						List<ProductAndPrice> products = productsUpdated.get(distributor.getLocationId());
						String productsInfo = "A Message from So And So \n";
						for (ProductAndPrice product : products) {
							if (product != null) {
								productsInfo += "The product " + product.getProductName() + " price changed to "
										+ product.getPrice() + "\n";
							}
						}
						LOGGER.info(productsInfo);
						/*UrlBuilder urlBuilder = new UrlBuilder("");
						Map<String, Object> requestParameters = new HashMap<>();
						requestParameters.put("uname", "");
						requestParameters.put("pass", "");
						requestParameters.put("send", "");
						requestParameters.put("dest", distributor.getDistributorPhone());
						requestParameters.put("msg", productsInfo);

						urlBuilder.add(requestParameters);

						LOGGER.debug("Sending request with params: [{}]", urlBuilder.getUrl());

						String response = "";
						try {
							response = new HttpApiCall(5000, 5000).get(urlBuilder.toString());
							LOGGER.debug("responded with [{}]", response);
						} catch (IOException ioe) {
							LOGGER.error(ioe.toString());
						}*/
					}
				}
			}
		}
	}

}
