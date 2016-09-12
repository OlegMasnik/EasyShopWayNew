<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<script src='/EasyShopWayNew/js/moment.js'></script>

<md-toolbar id="header" class="md-hue-2">
<div class="md-toolbar-tools" layout="row" ng-controller="AppCtrl"
	md-whiteframe="4">

	<div layout="row">
		<a flex class="logo" href="/EasyShopWayNew/home"><img
			alt="EasyShopWay" src="/EasyShopWayNew/images/logo/logo.gif"></a>
	</div>
	<input type="text"  ng-show="false" id="lang" value="${lang}">
	<span flex></span>
	<md-input-container>
	<md-select  style="color: white; !important " ng-model="language">
	<md-option ng-model="ua" value="ua" ng-click='changeLang(ua)'>Ukraine</md-option>
	<md-option ng-model="en" value="en" ng-click='changeLang(en)'>English</md-option>
	</md-select> </md-input-container>
	<c:choose>
		<c:when test="${user == null}">
			<md-button class="md-raised"
				ng-click="showLogInForm($event, $scope, $mdDialog)">
			LogIn </md-button>
			<md-button class="md-raised"
				ng-click="showRegistrationInFrom($event, $scope, $mdDialog)">
			SignUp </md-button>
		</c:when>
		<c:otherwise>
			<md-button class="md-raised" href="/EasyShopWayNew/cabinet#/">
			<c:out value="${user.firstName } ${user.lastName }"></c:out></md-button>
			<md-button class="md-raised" href="/EasyShopWayNew/logout">
			LogOut </md-button>
		</c:otherwise>
	</c:choose>
</div>
</md-toolbar>
