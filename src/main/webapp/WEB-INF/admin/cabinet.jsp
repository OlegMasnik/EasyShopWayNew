
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title><c:out value="${user.firstName }"></c:out></title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/cabinet-style.css">
<link rel="stylesheet" href="css/angular-material.min.css">
</head>

<body ng-app="MyApp" layout="column" ng-controller="AdminCtrl">
	<jsp:include page="../parts/header.jsp"></jsp:include>
	<p>
		<a href="#/">Info</a>
	</p>
	<a href="#map">map</a>
	<a href="#history">history</a>
	<a href="#products">products</a>
	<a href="#users">products</a>
	<a href="#statistic">statistic</a>

	<div ng-view></div>
	<script src="js/jquery.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/app.js'></script>
	<script src='js/admin/app.js'></script>
</body>
</html>