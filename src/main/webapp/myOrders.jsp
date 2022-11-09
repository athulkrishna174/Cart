<%@page import="jakarta.ws.rs.core.GenericType"%>
<%@page import="jakarta.ws.rs.core.MediaType"%>
<%@page import="com.cart.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="jakarta.ws.rs.client.ClientBuilder"%>
<%@page import="jakarta.ws.rs.client.Client"%>
<%@page import="com.cart.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>

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
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if(session.getAttribute("user")==null){
		response.sendRedirect("login.jsp");
	}
	
	try{
		User user = (User) session.getAttribute("user");
	
		Client client = ClientBuilder.newClient();
		List<Order> orders = client.target("http://localhost:8080/cartrest/webapi/orders")
					.path(String.valueOf(user.getId()))
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Order>>() { });
	
	int count = 1;
	%>
	
	<table class="table mytable" >
	<caption></caption>
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">Item</th>
				<th scope="col">Price</th>
				<th scope="col">Quantity</th>
				<th scope="col">Total</th>	
				<th scope="col">Order Date</th>	
			</tr>
		</thead>
		<tbody>
		<% for (Order order : orders) {
		%>
			<tr>
				<th scope="row"><%=count++ %></th>
				<td><%=order.getName() %></td>
				<td><%=order.getPrice() %></td>
				<td><%=order.getQuantity() %></td>
				<td><%=order.getTotal() %></td>
				<td><%=order.getDate() %></td>
				<td></td>
			</tr>
		<%} %>
		</tbody>
	</table>
	<%
	}catch(Exception e){}	
	%>

</body>
</html>