<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navbar</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bgcolor">
		<div class="container-fluid">
			<a class="navbar-brand fw-bold" href="#">CART</a>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active" href="home.jsp"><em class="fa fa-home"> Home</em></a></li>
					<li class="nav-item"><a class="nav-link" href="myOrders.jsp"><em class="fa fa-inbox"> My Orders</em></a></li>
					<li class="nav-item"><a class="nav-link" href="cart.jsp"><em class="fa fa-shopping-cart"> Cart</em></a></li>
					<li class="nav-item"><a class="nav-link" href="logout"><em class="fa fa-sign-out"> Log Out</em></a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>