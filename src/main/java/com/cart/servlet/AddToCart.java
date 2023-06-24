package com.cart.servlet;

import java.io.IOException;

import com.cart.model.Item;
import com.cart.model.Product;
import com.cart.model.User;
import com.cart.service.CartService;
import com.cart.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addCart")
public class AddToCart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		ProductService productService = new ProductService();
		Product product = productService.getProduct(request.getParameter("pid"));

		Item item = new Item();

		item.setPid(product.getPid());
		item.setName(product.getProductName());
		item.setPrice(Integer.parseInt(product.getPrice()));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));

		CartService cartService = new CartService();

		User user = (User) session.getAttribute("user");

		cartService.inserItem(user.getId(), item);

		session.setAttribute("message", item.getName() + "Successfully Added to Cart");
		session.setAttribute("success", true);

		response.sendRedirect("home.jsp");
		
	}

}
