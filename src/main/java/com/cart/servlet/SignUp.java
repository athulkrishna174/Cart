package com.cart.servlet;

import java.io.IOException;

import com.cart.service.SignUpService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signup")
public class SignUp extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignUpService signUpService = new SignUpService();
		
		HttpSession session = request.getSession();
		
		if(signUpService.addUser(request.getParameter("name"), request.getParameter("email"), 
				request.getParameter("password"))) {
			 session.setAttribute("signup", true);
			 session.setAttribute("message", "Signup Successful! Please Log in");
			 
			 response.sendRedirect("login.jsp");
		}
		
		else {
			session.setAttribute("signupfail", true);
			session.setAttribute("message", "Failed to Signup Please Try Again");
			 
			response.sendRedirect("signup.jsp");
		}
	}
}
