<%@page import="jakarta.ws.rs.core.MediaType"%>
<%@page import="jakarta.ws.rs.core.GenericType"%>
<%@page import="jakarta.ws.rs.client.ClientBuilder"%>
<%@page import="jakarta.ws.rs.client.Client"%>
<%@page
	import="jakarta.security.auth.message.callback.PrivateKeyCallback.IssuerSerialNumRequest"%>
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
	
	<jsp:include page="navbar.jsp" />
	
	<%
	Client client = ClientBuilder.newClient();
	List<Product> products = client.target("http://localhost:8080/cartrest/webapi/products")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Product>>() { });

	
	boolean success = Boolean.TRUE == session.getAttribute("success");
	session.removeAttribute("success");

	if (success) {
		success = false;
	%>
		<div id="none">
			<div class="popup">
				<p class="close_btn">X</P>
				<img alt="sucess" src="./static/images/icons/success.png"
					class="sucess_icon">
				<h4 class="message"><%=session.getAttribute("pname")%>
					Successfully Added to Cart
				</h4>
			</div>
			<div class="blur"></div>
		</div>
	<%
	}
	%>

	<div class="container">
		<div class="row">
			<%
			for (Product product : products) {
			%>
			<div class="card mycard" style="width: 18rem;">
				<img src="<%=product.getImageUri()%>" class="card-img-top"
					alt="image">
				<div class="card-body">

					<div class="buttons">
						<form action="addCart" method="post">
							<h5 class="card-title cardtitle text-center">
								<%=product.getProductName()%>
								| <span class="cardprice"><%=product.getPrice()%></span>
							</h5>
								<input type="hidden" name="pname" value="<%=product.getProductName()%>"> 
								<input type="hidden" name="price" value="<%=product.getPrice()%>"> 
								<input type="submit"
								class="submitpadding button text-white" value="Add to Cart" />

							<select name="quantity" class="button text-white" id="quantity">
								<option value="1" selected="selected">Qty: 1</option>
								<option value="2">Qty: 2</option>
								<option value="3">Qty: 3</option>
								<option value="4">Qty: 4</option>
							</select>
						</form>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

	<script type="text/javascript" src="./static/js/script.js"></script>
</body>
</html>