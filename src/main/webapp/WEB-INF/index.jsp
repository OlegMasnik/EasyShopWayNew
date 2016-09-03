<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/angular-material.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body ng-app="MyApp">
	<jsp:include page="parts/header.jsp"></jsp:include>
	<h2>{{2+2}}</h2>
	<i class="material-icons">&#xE84D;</i>

<!-- 	<script src="js/jquery.min.js"></script> -->
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/app.js'></script>

	<script type="text/javascript"
		src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.4.4.min.js"></script>
	<script type="text/javascript"
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
	<%-- 	<script src="${pageContext.request.contextPath}/js/index.js"></script> --%>
</body>
</html>
