<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./static/css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<%
	boolean signup = Boolean.TRUE == session.getAttribute("signup");
	session.removeAttribute("signup");
	
	boolean loginFail = Boolean.TRUE == session.getAttribute("loginFail");
	session.removeAttribute("loginFail");
	
	if (signup) {
		signup = false;
	%>
	
	<jsp:include page="alert.jsp"></jsp:include>
	<%
	session.removeAttribute("message");
	}
	
	if (loginFail) {
		loginFail = false;
	%>
	
	<jsp:include page="failAlert.jsp"></jsp:include>
	<%
	session.removeAttribute("message");
	}
	%>

	<div class="container mycontainer">
		<div class="row align-items-center d-flex justify-content-center">
			<div class="col-4 align-self-center">
				<form action="login" method="post">
					<!-- Email input -->
					<div class="form-outline mb-4">
						<input type="email" name="email" class="form-control myInput" required="required"/> <label
							class="form-label">Email address</label>
					</div>

					<!-- Password input -->
					<div class="form-outline mb-4">
						<input type="password" name="password" class="form-control myInput" required="required"/>
						<label class="form-label">Password</label>
					</div>
					<!-- Submit button -->
					<div class="d-grid">
						<button type="submit" class="btn button text-white"><i class="fa fa-sign-in" aria-hidden="true"></i> Log In</button>
					</div>

					<!-- Register buttons -->
					<div class="text-center">
						<p>
							Not a member? <a href="signup.jsp" class="link">Register</a>
						</p>
					</div>
				</form>

			</div>
		</div>

	</div>
	
	<script type="text/javascript" src="./static/js/script.js"></script>

</body>
</html>