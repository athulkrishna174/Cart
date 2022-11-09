package com.cart.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cart.database.DatabaseConnection;
import com.cart.model.User;
import com.cart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LogIn extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private DatabaseConnection connector = new DatabaseConnection();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Connection connection = connector.getConnection();
		
		String query = "SELECT * FROM users WHERE email = ? and password = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, request.getParameter("email"));
			statement.setString(2, request.getParameter("password"));
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				
				session.setAttribute("user", user);
				session.setAttribute("cartService", new CartService());
				
				response.sendRedirect("home.jsp");
			}
			else {
				session.setAttribute("loginFail", true);
				session.setAttribute("message", "Invalid Credentials!");
				response.sendRedirect("login.jsp");
			}
		}catch (Exception e) {
			session.setAttribute("loginFail", true);
			session.setAttribute("message", "Error!!!");
			response.sendRedirect("login.jsp");
		}
		
	}
}