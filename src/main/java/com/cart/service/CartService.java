package com.cart.service;

import java.util.ArrayList;
import java.util.List;

import com.cart.model.Item;

public class CartService {
	
	static List<Item> items = new ArrayList<>();
	
	public void setItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	public List<Item> getItems(){
		return items;
	}
}
