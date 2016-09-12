<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link rel="stylesheet" href="css/icon.css">
<link rel="stylesheet" href="css/angular-material.min.css">
<link rel="stylesheet" href="css/style.css">

</head>

<body ng-app="MyApp" ng-controller="AppCtrl">
	<jsp:include page="parts/header.jsp"></jsp:include>
	<h2>{{2+2}}</h2>

	<h2>{{ 'HEADLINE' | translate }}</h2>
	<p>{{ 'INTRO_TEXT' | translate }}</p>
	<p>{{pageTitle}}</p>
	<i class="material-icons">&#xE84D;</i>


	<md-button ng-click="page.goToPage('search')"
		class="md-fab md-fab-bottom-right"
		ng-controller="PageRedirectCtrl as page"> <md-icon
		md-svg-src="images/svg/vectorpaint.svg"></md-icon> </md-button>

	<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src='js/angular-translate.js'></script>

	<script src='js/app.js'></script>
	<script src="js/jquery.validate.min.js"></script>



</body>
<script type='text/ng-template' id='info.html'>
        
    </script>

</html>