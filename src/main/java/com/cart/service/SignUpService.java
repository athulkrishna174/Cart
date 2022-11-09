package com.cart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cart.database.DatabaseConnection;

public class SignUpService {
	
	private DatabaseConnection connector = new DatabaseConnection();
	
	public boolean addUser(String name, String email, String password){
		
		if(!checkDupicate(email)) return false;
		
		Connection connection = connector.getConnection();
		
		String query = "INSERT INTO users(name, email, password) VALUES (?,?,?)";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, password);
			
			statement.execute();
			
		} catch (Exception e) {
			return false;
		}
			
		return true;
	}
	
	public boolean checkDupicate(String email) {
		Connection connection = connector.getConnection();
		
		String query = "SELECT * FROM users WHERE email = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, email);
			
			ResultSet rs = statement.executeQuery();
			
			if(!rs.next()) return true;
			
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
}
