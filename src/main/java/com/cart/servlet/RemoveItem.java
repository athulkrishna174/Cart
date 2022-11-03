package com.cart.servlet;

import java.io.IOException;

import com.cart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/removeItem")
public class RemoveItem extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CartService cartService = new CartService();
		
		cartService.removeItem(Integer.parseInt(request.getParameter("item")));
		
		response.sendRedirect("cart.jsp");
	}
}
