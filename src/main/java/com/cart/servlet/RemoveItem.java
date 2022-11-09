package com.cart.servlet;

import java.io.IOException;

import com.cart.model.User;
import com.cart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/removeItem")
public class RemoveItem extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		CartService cartService = new CartService();
		
		cartService.deleteItem(user.getId(), Integer.parseInt(request.getParameter("item")));
		
		response.sendRedirect("cart.jsp");
	}
}
