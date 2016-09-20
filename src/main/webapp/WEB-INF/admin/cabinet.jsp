<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title><c:out value="${user.firstName }"></c:out></title>

<link rel="stylesheet" href="css/preloader/preloader.css">
<link rel="stylesheet" href="css/angular-material.min.v1.1.css">
<link rel="stylesheet" href="css/cabinet-style.css">
<link rel="stylesheet" href="css/style.css">
<link href="css/icon.css" rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico">

<link rel="stylesheet" href="css/datatable/md-data-table.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>

<body ng-app="MyApp" layout="column" ng-controller="AdminCtrl" md-theme="{{ theme }}">

<div id="loader-wrapper">
    <div id="loader"></div>
</div>

	<jsp:include page="../parts/header.jsp"></jsp:include>
	<md-content layout="row" flex> 
	
<!-- 	<span style="width: 250px;"></span> -->
	
	<md-slidenav class="cabinet-sidenav" layout="column" md-is-locked-open="true"
		layout="column" md-whiteframe="4"> <md-content  md-whiteframe="4"
		layout="column" flex> <a href="#/">
		<div class="cab-user-logo" layot="row" layout-padding>

			<img src='${ user.image == "" ? "images/admin.png" : user.image}' alt="" class="profile-img">

			<div ng-style="theme === 'dark' ? { 'color':'white' } : { 'color': 'black' }">
				<c:out value="${ user.firstName}"></c:out>
				<c:out value="${ user.lastName}"></c:out>
			</div>
		</div>
	</a> <md-button href="#edit_map">{{ 'ADMIN_CABINET_MAP' | translate }}</md-button> 
	<md-button href="#products">{{ 'ADMIN_CABINET_PRODUCTS' | translate }}</md-button> 
	<md-button href="#types">{{ 'PRODUCT_TYPE' | translate }}</md-button> 
		<md-button href="#users">{{ 'ADMIN_CABINET_USERS' | translate }}</md-button>
	<md-button href="#statistic">{{ 'ADMIN_CABINET_STATISTIC' | translate }}</md-button> </md-content> </md-slidenav> <md-content
		layout="row" flex class="main-page">

	<div flex class="content" layout="column" >
		<div ng-view onload="getFoodData()"></div>
	</div>

	</md-content> </md-content>

<md-button ng-click="page.goToPage('search')" class="md-fab md-fab-bottom-right" ng-controller="PageRedirectCtrl as page" aria-label="search">
	<md-tooltip md-direction="top">
       {{ 'SEARCH' | translate }}   
    </md-tooltip>
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
	<script src='js/app.js'></script>
	<script src='js/admin/app.js'></script>
	<script src='js/admin/map.js'></script>
	<script src='js/user/app.js'></script>

	<script src="js/shared/statistic.js"></script>
	<script src="js/shared/cabinet.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src='js/admin/mail.js'></script>
	
	<script src="js/preloader/preloader.js"></script>

</body>

</html>