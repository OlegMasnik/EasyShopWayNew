<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link rel="stylesheet" href="css/icon.css">
<link rel="stylesheet" href="css/angular-material.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="favicon.ico">

	<script src='js/app.js'></script>
<title>Error confirmation email</title>
</head>

<body ng-app="MyApp" ng-controller="AppCtrl">
	<jsp:include page="/WEB-INF/parts/header.jsp"></jsp:include>
	
	<div layout="row">
	
	<div flex="30"></div>
	
	<h2 flex> {{ 'ERROR_CONFIRMATION_EMAIL' | translate }}</h2>
	
	</div>
	
	<md-button ng-click="page.goToPage('search')" class="md-fab md-fab-bottom-right" ng-controller="PageRedirectCtrl as page" aria-label="search">
	<md-tooltip md-direction="top">
       {{ 'SEARCH' | translate }}   
    </md-tooltip>
	<md-icon md-svg-src="images/svg/vectorpaint.svg"></md-icon>
    </md-button>
<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src='js/angular-translate.js'></script>
	<script src="js/jquery.validate.min.js"></script>



</body>
<script type='text/ng-template' id='info.html'>
        
    </script>

</html>