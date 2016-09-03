<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="css/style.css">
<link rel="stylesheet"
	href="css/angular-material.min.css">
</head>
<body ng-app="MyApp">
	<jsp:include page="parts/header.jsp"></jsp:include>
	<h2>{{2+2}}</h2>
	
	<script src="js/jquery.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script
		src='js/angular-material.min.js'></script>
	<script src='js/app.js'></script>

<%-- 	<script src="${pageContext.request.contextPath}/js/index.js"></script> --%>
</body>
</html>
