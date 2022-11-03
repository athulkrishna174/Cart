package com.cart.servlet;

import java.io.IOException;

import com.cart.model.Item;
import com.cart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addCart")
public class AddToCart extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Item item = new Item();
		
		item.setName(request.getParameter("pname"));
		item.setPrice(Integer.parseInt(request.getParameter("price")));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		
		CartService cartService = new CartService();
		
		cartService.setItem(item);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("pname", item.getName());
		session.setAttribute("success", true);
		
		response.sendRedirect("home.jsp");
	}

}
