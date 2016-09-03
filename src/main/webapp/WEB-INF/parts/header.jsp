<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.js'></script>
<md-toolbar class="md-hue-2">
<div class="md-toolbar-tools" ng-controller="AppCtrl">
	<md-button aria-label="Go Back"> Go Back </md-button>
	<h2>
		<span>EasyShopWay</span>
	</h2>
	<span flex=""></span>
	<md-button class="md-raised"
		ng-click="showLogInForm($scope, $mdDialog)"> LogIn </md-button>
	<md-button class="md-raised"
		ng-click="showRegistrationInFrom($scope, $mdDialog)">
	SignUp </md-button>
</div>
</md-toolbar>