package com.cart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cart.database.DatabaseConnection;
import com.cart.model.Item;

public class CartService {
	private DatabaseConnection connector = new DatabaseConnection();
	
	public List<Item> getItems(int userId) throws SQLException{
		Connection connection = connector.getConnection();
        
        String query = "SELECT * FROM cartitems WHERE uid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            
            ResultSet rs = statement.executeQuery();
      
            return getItemList(rs);
        }
	}
	
	private List<Item> getItemList(ResultSet rs) throws SQLException{
        
        List<Item> items = new ArrayList<>();
        
        while(rs.next()) {
            
            Item item = new Item();
            
            item.setId(rs.getInt("id"));
            item.setPid(rs.getInt("pid"));
            item.setName(rs.getString("name"));
            item.setQuantity(rs.getInt("quantity"));
            item.setPrice(rs.getInt("price"));
            
            items.add(item);
        }
        
        return items;
    }
	
	public void inserItem(int userId, Item item) throws SQLException {
		Connection connection = connector.getConnection();
        
        String query = "INSERT INTO cartitems (uid, pid, name, quantity, price, total)"
        		+ "VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, item.getPid());
            statement.setString(3, item.getName());
            statement.setInt(4, item.getQuantity());
            statement.setInt(5, item.getPrice());
            statement.setInt(6, item.getTotal());
            
            statement.execute();
      
        }
	}
	
	public void deleteItem(int userId, int id) {
		Connection connection = connector.getConnection();
        
        String query = "DELETE FROM cartitems WHERE uid = ? and id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, id);
            
            statement.execute();
        }catch (Exception e) {
        }
	}
	
	
	public void deleteAllItems(int userId) {
		Connection connection = connector.getConnection();
        
        String query = "DELETE FROM cartitems WHERE uid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            
            statement.execute();
        }catch (Exception e) {
        }
	}
	
	public void updateMyOrder(int userId) throws SQLException {
		
		List<Item> items = getItems(userId);
		
		Connection connection = connector.getConnection();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date();
		
		for(Item item: items) {
			String query = "INSERT INTO orders (uid, pid, name,  price, quantity, total, date)"
					+ "VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(query); 
	            statement.setInt(1, userId);
	            statement.setInt(2, item.getId());
	            statement.setString(3, item.getName());
	            statement.setInt(4, item.getPrice());
	            statement.setInt(5, item.getQuantity());
	            statement.setInt(6, item.getTotal());
	            statement.setString(7, formatter.format(date));
	            
	            statement.execute();
		}
	}
}
