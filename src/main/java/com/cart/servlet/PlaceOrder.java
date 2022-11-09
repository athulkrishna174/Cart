package com.cart.servlet;

import java.io.IOException;

import com.cart.model.User;
import com.cart.service.GenerateBill;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrder extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		User user = (User)session.getAttribute("user");
		
		user.setName(request.getParameter("name"));
		user.setPhone(request.getParameter("phone"));	
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address")); 	
		user.setCardNo(request.getParameter("card"));
		
		GenerateBill generateBill = new GenerateBill();
		try {
			generateBill.generateBill(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.setAttribute("message", "Order Placed and Bill Gereated");
		session.setAttribute("billSuccess", true);
		response.sendRedirect("home.jsp");
	}

}
