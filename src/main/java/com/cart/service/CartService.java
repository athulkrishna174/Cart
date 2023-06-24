package com.cart.service;

import java.util.List;

import com.cart.model.Item;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class CartService {
	private static final String PATH = "http://localhost:8080/cartrest/webapi/cartitems";
	
	
	public List<Item> getItems(int userId){
		
		try (Client client = ClientBuilder.newClient()) {
			return client.target(PATH)
						.path(String.valueOf(userId))
						.request(MediaType.APPLICATION_JSON)
						.get(new GenericType<List<Item>>() { });
		}
	}
	
	public Item getItem(int userId, int id){
		
		try (Client client = ClientBuilder.newClient()) {
			return client.target(PATH)
						.path(String.valueOf(userId))
						.path(String.valueOf(id))
						.request(MediaType.APPLICATION_JSON)
						.get(Item.class);
		}
	}
	
	public void inserItem(int userId, Item item){
		try (Client client = ClientBuilder.newClient()) {
			client.target(PATH)
						.path(String.valueOf(userId))
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(item, MediaType.APPLICATION_JSON));
		}
      
        
	}
	
	public void deleteItem(int userId, int id){
		try (Client client = ClientBuilder.newClient()) {
			client.target(PATH)
						.path(String.valueOf(userId))
						.path(String.valueOf(id))
						.request(MediaType.APPLICATION_JSON)
						.delete();
		}
	}
	
	
	
	public void deleteAllItems(int userId) {
		try (Client client = ClientBuilder.newClient()) {
			client.target(PATH)
						.path(String.valueOf(userId))
						.request(MediaType.APPLICATION_JSON)
						.delete();
		}
	}
	
	public void updateMyOrder(int userId){
		
		List<Item> items = getItems(userId);
		
		try (Client client = ClientBuilder.newClient()) {
			client.target(PATH)
						.path(String.valueOf(userId))
						.request(MediaType.APPLICATION_JSON)
						.put(Entity.entity(items, MediaType.APPLICATION_JSON));
		}
	}
	
	public void updateQuantity(int userId, int id, Item item) {
		try (Client client = ClientBuilder.newClient()) {
			client.target(PATH)
						.path(String.valueOf(userId))
						.path(String.valueOf(id))
						.request(MediaType.APPLICATION_JSON)
						.put(Entity.entity(item, MediaType.APPLICATION_JSON));
		}
	}
}
