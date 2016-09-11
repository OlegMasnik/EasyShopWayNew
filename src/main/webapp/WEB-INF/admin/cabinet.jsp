<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title><c:out value="${user.firstName }"></c:out></title>
<link rel="stylesheet" href="css/angular-material.min.css">
<link rel="stylesheet" href="css/cabinet-style.css">
<link rel="stylesheet" href="css/style.css">
<link href="css/icon.css" rel="stylesheet">

<link rel="stylesheet" href="css/datatable/md-data-table.css">

</head>

<body ng-app="MyApp" layout="column" ng-controller="AdminCtrl">

	<jsp:include page="../parts/header.jsp"></jsp:include>
	<md-content layout="row" flex> <span style="width: 250px;"></span>
	<md-slidenav class="cabinet-sidenav" md-is-locked-open="true"
		layout="column" flex md-whiteframe="4"> <md-content
		layout="column" flex> <a href="#/">
		<div class="cab-user-logo" layot="row" layout-padding>

			<img src="${user.image }" alt="" class="profile-img">

			<div>
				<c:out value="${ user.firstName}"></c:out>
				<c:out value="${ user.lastName}"></c:out>
			</div>
		</div>
	</a> <md-button href="#edit_map">Map</md-button> <md-button
		href="#products">Products</md-button> <md-button href="#users">Users</md-button>
	<md-button href="#statistic">Statistic</md-button> </md-content> </md-slidenav> <md-content
		layout="row" class="main-page" flex>

	<div flex class="content" layout="column">
		<div ng-view onload="getFoodData()"></div>
	</div>
	</md-content> </md-content>

<md-button ng-click="page.goToPage('search')" class="md-fab md-fab-bottom-right" ng-controller="PageRedirectCtrl as page">
	<md-icon md-svg-src="images/svg/vectorpaint.svg"></md-icon>
    </md-button>

	<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src="js/datatable/md-data-table.js"></script>
	<script src='js/angular-translate.js'></script>
	
	<script src="js/shared/highcharts.js"></script>
	<script src="js/shared/exporting.js"></script>

	<script src='js/app.js'></script>
	<script src='js/admin/app.js'></script>
	<script src='js/admin/map.js'></script>
	<script src='js/user/app.js'></script>
	<script src="js/shared/statistic.js"></script>
	<script src="js/shared/cabinet.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src='js/admin/mail.js'></script>

</body>

</html>