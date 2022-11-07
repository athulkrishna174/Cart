package com.cart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cart.model.Item;
import com.cart.model.User;

@DisplayName("When running GenerateBill Class")
class GenerateBillTest {
	
	@BeforeEach
	void init() {
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		
		item1.setName("Shirt");
		item1.setPrice(500);
		item1.setQuantity(2);
		
		item2.setName("Jeans");
		item2.setPrice(1000);
		item2.setQuantity(1);
		
		item3.setName("Shoes");
		item3.setPrice(2000);
		item3.setQuantity(1);
		
		CartService cartService = new CartService();
		cartService.setItem(item1);
		cartService.setItem(item2);
		cartService.setItem(item3);
	}

	@Test
    @DisplayName("Testing PdfCreate")
    void testPdfCreate() throws Exception{
		GenerateBill generateBill = new GenerateBill();
		
		User user = new User();
		
		user.setName("Athul Krishna");
		user.setPhone("9544985532");
		user.setEmail("ecstraingtest@ecs.com");
		user.setAddress("Rose villa gandhi nagar");
		user.setCardNo("4444555566667777");
		
		generateBill.generateBill(user);
	}

}
