package com.cart.servlet;

import java.io.IOException;

import com.cart.model.Item;
import com.cart.model.User;
import com.cart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addQuantity")
public class AddQuantity extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
				
		User user = (User)session.getAttribute("user");
		
		CartService cartService = new CartService();
		
		Item item = cartService.getItem(user.getId(), Integer.parseInt(request.getParameter("item")));
		
		item.setQuantity(item.getQuantity() + 1);
		
		cartService.updateQuantity(user.getId(), item.getId(), item);
		
		response.sendRedirect("cart.jsp");
	}

}
