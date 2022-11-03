<%@page import="com.cart.service.GetProducts"%>
<%@page import="com.cart.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./static/css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<%
		GetProducts getProducts = new GetProducts();
		List<Product> products = getProducts.getProducts();
	%>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="row">
			<%
			for (Product product : products) {
			%>
			<div class="card mycard" style="width: 18rem;">
				<img src="<%=product.getImageUri()%>" class="card-img-top"
					alt="image">
				<div class="card-body">
					<h5 class="card-title cardtitle text-center">
						<%=product.getProductName()%>
						| <span class="cardprice"><%=product.getPrice()%></span>
					</h5>
					<div class="buttons">
						<a href="#" class="button text-white">Add to Cart</a> <select
							class="button text-white" id="quantity">
							<option value="1" selected="selected">Qty: 1</option>
							<option value="2">Qty: 2</option>
							<option value="3">Qty: 3</option>
							<option value="4">Qty: 4</option>
						</select>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

</body>
</html>