<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<script src='js/moment.js'></script>

<md-toolbar class="md-hue-2">
<div class="md-toolbar-tools" ng-controller="AppCtrl">

	<md-button href="/EasyShopWayNew/home"> EasyShopWay </md-button>

	<span flex=""></span>

	<c:choose>
		<c:when test="${user == null}">
			<md-button class="md-raised"
				ng-click="showLogInForm($scope, $mdDialog)"> LogIn </md-button>
			<md-button class="md-raised"
				ng-click="showRegistrationInFrom($scope, $mdDialog)">
			SignUp </md-button>
		</c:when>
		<c:otherwise>
			<md-button class="md-raised" href="/EasyShopWayNew/logout">
			LogOut </md-button>
		</c:otherwise>
	</c:choose>
</div>
</md-toolbar>