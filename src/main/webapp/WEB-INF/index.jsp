<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link rel="stylesheet" href="css/icon.css">
<link rel="stylesheet" href="css/angular-material.min.css">
<link rel="stylesheet" href="css/style.css">

</head>

<body ng-app="MyApp" layout="column" ng-controller="AppCtrl">
	<jsp:include page="parts/header.jsp"></jsp:include>
	<!-- 	<h2>{{2+2}}</h2> -->

	<!-- 	<h2>{{ 'HEADLINE' | translate }}</h2> -->
	<!-- 	<p>{{ 'INTRO_TEXT' | translate }}</p> -->
	<!-- 	<p>{{pageTitle}}</p> -->
	<!-- 	<i class="material-icons">&#xE84D;</i> -->

	<md-content id="main-content" flex>
	<div id="div0" layout="column">
		<div flex layout="column" layout-align="center center">
			<md-button style="background-color : #E91E63; font-size: 30px; color : #fff;" ng-controller="PageRedirectCtrl as page" ng-click="page.goToPage('search')">Create product list now</md-button>
			<div>or</div>
			<md-button class="md-accent" id="down_1">See tutorial</md-button>
		</div>
	</div>
	<div id="div1" layout="column">
		<md-button id="up_1"><i class="material-icons">keyboard_arrow_up</i></md-button>

		<div layout="row" flex>
			<div flex layout="row" layout-align="center center" md-whiteframe="4"
				layout-margin>Image</div>
			<div flex layout="row" layout-align="center center">Description</div>
		</div>

		<md-button id="down_2"> <i class="material-icons">keyboard_arrow_down</i></md-button>
	</div>
	<div id="div2" layout="column">
		<md-button id="up_2"> <i class="material-icons">keyboard_arrow_up</i></md-button>
		
		<div layout="row" flex>
			<div flex layout="row" layout-align="center center">Description</div>
			<div flex layout="row" layout-align="center center" md-whiteframe="4"
				layout-margin>Image</div>

		</div>
		<md-button id="down_3"> <i class="material-icons">keyboard_arrow_down</i></md-button>
	</div>
	<div id="div3" layout="column">
		<md-button id="up_3" style="font-size: 30px;"> <i
			class="material-icons">keyboard_arrow_up</i></md-button>
		<div layout="row" flex>
			<div flex layout="row" layout-align="center center" md-whiteframe="4"
				layout-margin>Image</div>
			<div flex layout="row" layout-align="center center">
				Description
				<md-button ng-click="page.goToPage('search')" class="md-fab"
					ng-controller="PageRedirectCtrl as page"> <md-icon
					md-svg-src="images/svg/vectorpaint.svg"></md-icon> </md-button>
			</div>
		</div>

	</div>
	</md-content>



	<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src='js/angular-translate.js'></script>

	<script src='js/app.js'></script>
	<script src='js/index.js'></script>
	<script src="js/jquery.validate.min.js"></script>

</body>
<script type='text/ng-template' id='info.html'></script>

</html>