<%@page import="com.cart.service.CartService"%>
<%@page import="com.cart.model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Items</title>
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
	CartService cartService = new CartService();
	List<Item> items = cartService.getItems();
	
	int grandTotal = 0;
	int count = 1;
	%>

	<table class="table mytable">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">Item</th>
				<th scope="col">Price</th>
				<th scope="col">Quantity</th>
				<th scope="col">Total</th>	
				<th scope="col">Delete</th>	
			</tr>
		</thead>
		<tbody>
		<% for (Item item : items) {
		%>
			<tr>
				<th scope="row"><%=count %></th>
				<td><%=item.getName() %></td>
				<td><%=item.getPrice() %></td>
				<td><%=item.getQuantity() %></td>
				<td><%=item.getTotal() %></td>
				<td>
					<form action="removeItem" method="get">
						<input type="hidden" name="item" value="<%=items.indexOf(item)%>">
						<input type="submit" class="deletebtn" value="Remove"/>
					</form>
				</td>
				<%
				count++;
				grandTotal += item.getTotal(); 
				%>
			</tr>
		<%} %>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<th>Grand Total:</th>
			<td><%=grandTotal %></td>
			<td></td>
		</tr>
		</tbody>
	</table>
</body>
</html>