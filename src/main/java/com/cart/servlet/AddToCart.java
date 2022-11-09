package com.cart.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.cart.model.Item;
import com.cart.model.Product;
import com.cart.model.User;
import com.cart.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@WebServlet("/addCart")
public class AddToCart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Client client = ClientBuilder.newClient();
		Product product = client.target("http://localhost:8080/cartrest/webapi/products")
				.path(request.getParameter("pid")).request(MediaType.APPLICATION_JSON).get(Product.class);

		Item item = new Item();

		item.setPid(product.getPid());
		item.setName(product.getProductName());
		item.setPrice(Integer.parseInt(product.getPrice()));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));

		CartService cartService = new CartService();

		User user = (User) session.getAttribute("user");

		try {
			cartService.inserItem(user.getId(), item);

			session.setAttribute("message", item.getName() + "Successfully Added to Cart");
			session.setAttribute("success", true);

			response.sendRedirect("home.jsp");
		} catch (SQLException e) {
		}
	}

}
