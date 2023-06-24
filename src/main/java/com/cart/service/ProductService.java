package com.cart.service;

import java.util.List;

import com.cart.model.Product;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class ProductService {

	private static final String PATH = "http://localhost:8080/cartrest/webapi/products";
	
	public Product getProduct(String productId) {
		try (Client client = ClientBuilder.newClient()) {
			return client.target(PATH)
					.path(productId).request(MediaType.APPLICATION_JSON).get(Product.class);
		}
	}
	
	public List<Product> getAllProducts(){
		try (Client client = ClientBuilder.newClient()) {
			return client.target(PATH)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Product>>() { });
		}
	}
}
