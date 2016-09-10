<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link rel="stylesheet" href="css/icon.css">
<link rel="stylesheet" href="css/angular-material.min.css">
<link rel="stylesheet" href="css/style.css">
<title>Successfull confirmation email</title>
</head>

<body ng-app="MyApp">
	<jsp:include page="/WEB-INF/parts/header.jsp"></jsp:include>
	
	
	<h2>Successfull confirmation email</h2>
	
	<md-button ng-click="page.goToPage('search')" class="md-fab md-fab-bottom-right" ng-controller="PageRedirectCtrl as page">
	<md-icon md-svg-src="images/svg/vectorpaint.svg"></md-icon>
    </md-button>

	<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/angular-route.min.js'></script>

	<script src='js/app.js'></script>
	<script src="js/jquery.validate.min.js"></script>



</body>
<script type='text/ng-template' id='info.html'>
        
    </script>

</html>