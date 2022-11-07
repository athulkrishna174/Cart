<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="none">
		<div class="orderPopup">
			<p class="close_btn">X</P>
			<form action="placeOrder" method="post">
			<div class="container" style="margin-top: 50px; width: 700px">
			
				<div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">Full Name</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control myInput" name="name" placeholder="Full Name" required="required">
				    </div>
				</div>
				
				<div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">Contact No</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control myInput" name="phone" placeholder="Contact Number" required="required">
				    </div>
				</div>
				
				<div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">Email</label>
				    <div class="col-sm-10">
				      <input type="email" class="form-control myInput" name="email" placeholder="Email Address" required="required">
				    </div>
				</div>
				
				<div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">Address</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control myInput" name="address" placeholder="Shipping Address" required="required">
				    </div>
				</div>
				
				<div class="mb-3 row">
				    <label class="col-sm-2 col-form-label">Card No</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control myInput" name="card" placeholder="Debit Card Number" required="required">
				    </div>
				</div>
				
				<div class="mb-3 row" style="margin-top: 70px;">
				    <p class="col-sm-9 col-form-label" style="font-weight: bolder;">Total Amount To Pay: <%=request.getParameter("grandTotal") %></p>
				    <div class="col-sm-2" style="margin-left: 45px;">
				      <input type="submit" class="button text-white" value="Generate Bill">
				    </div>
				</div>
				
			</div>
			</form>
		</div>
		<div class="blur"></div>
	</div>
	
</body>
</html>