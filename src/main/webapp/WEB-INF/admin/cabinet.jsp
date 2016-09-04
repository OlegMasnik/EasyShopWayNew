
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
	<md-content layout="row" class="main-page" flex> <md-slidenav
		md-is-locked-open="true" layout="row" flex md-whiteframe="4">
	<md-content layout="column" flex layout-padding sticky-box>
	<md-button href="#/" layot="row">
	<div>
		<img src="images/admin.png" alt="" class="profile-img">
		<div flex>
			<c:out value="${user.firstName } ${user.lastName}"></c:out>
		</div>
	</div>
	</md-button> <md-button href="#map">Map</md-button> <md-button href="#products">Products</md-button>
	<md-button href="#users">Users</md-button> <md-button href="#statistic">Statistic</md-button>
	<md-button href="#history">History</md-button> </md-content> </md-slidenav>

	<div flex="80" class="content">

		<div ng-view></div>


	</div>
	</md-content>

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