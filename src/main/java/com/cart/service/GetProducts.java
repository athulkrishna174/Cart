package com.cart.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cart.database.DatabaseConnection;
import com.cart.model.Product;

public class GetProducts {

	private DatabaseConnection connector = new DatabaseConnection();

	public List<Product> getProducts() throws SQLException {

		Connection connection = connector.getConnection();

		String query = "SELECT * FROM products";
		try (Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(query);

			return getProductList(rs);
		}
	}

	private List<Product> getProductList(ResultSet rs) throws SQLException {

		List<Product> products = new ArrayList<>();

		while (rs.next()) {
			Product product = new Product();

			product.setProductName(rs.getString("pname"));
			product.setPrice(rs.getString("price"));
			product.setImageUri(rs.getString("image"));

			products.add(product);
		}

		return products;
	}
}
