<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="block">
			<div class="popup">
				<p class="close_btn">X</P>
				<img alt="sucess" src="./static/images/icons/success.png"
					class="sucess_icon">
				<h4 class="message"><%=session.getAttribute("message")%></h4>
			</div>
			<div class="blur"></div>
		</div>
</body>
</html>