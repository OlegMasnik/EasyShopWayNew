<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<title>Search</title>
<link rel="stylesheet" href="css/angular-material.min.v1.1.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/icon.css">
<link rel="stylesheet" href="css/search-style.css">
<link rel="shortcut icon" href="favicon.ico">
	<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src="js/angular-messages.min.js"></script>
</head>

<body ng-app="SearchApp" layout="column">
	<script src='js/moment.js'></script>

	<%-- <md-toolbar class="md-hue-2">
	<div class="md-toolbar-tools" layout="row" ng-controller="AppCtrl"
		md-whiteframe="4">

		<div layout="row">
			<a flex class="logo" href="/EasyShopWayNew/home"><img
				alt="EasyShopWay" src="images/logo/logo.gif"></a>
		</div>
		<span flex></span>

		<c:choose>
			<c:when test="${user == null}">
				<md-button class="md-raised"
					ng-click="showLogInForm($scope, $mdDialog)"> LogIn </md-button>
				<md-button class="md-raised"
					ng-click="showRegistrationInFrom($scope, $mdDialog)">
				SignUp </md-button>
			</c:when>
			<c:otherwise>
				<md-button class="md-raised" href="/EasyShopWayNew/cabinet">
				<c:out value="${user.firstName } ${user.lastName }"></c:out> </md-button>
				<md-button class="md-raised" href="/EasyShopWayNew/logout">
				LogOut </md-button>
			</c:otherwise>
		</c:choose>
	</div>
	</md-toolbar> --%>
	<jsp:include page="parts/header.jsp"></jsp:include>

	<md-content layout="row" ng-controller="ProductListCtrl as ctrl"
		class="main-page" flex> <md-slidenav
		class="search-sidenav" md-is-locked-open="true" layout="column" flex
		md-whiteframe="4">

	<div class="autocompletedemoBasicUsage" layout="column" flex
		ng-cloak="">
		<md-content class="md-padding" layout="column" flex>
		<form ng-submit="$event.preventDefault()">
			<md-autocomplete ng-model="searchModel"
				ng-disabled="ctrl.isDisabledMap"
				md-selected-item="ctrl.selectedMapItem"
				md-search-text-change="ctrl.searchTextChange(ctrl.searchMapText)"
				md-search-text="ctrl.searchMapText"
				md-selected-item-change="ctrl.selectedMapItemChange(item)"
				md-items="item in ctrl.querySearch(ctrl.searchMapText)"
				md-item-text="item.display" md-min-length="0"
				placeholder="Enter map name"> <md-item-template>
			<span md-highlight-text="ctrl.searchMapText" md-highlight-flags="^i">{{item.display}}</span>
			</md-item-template> <md-not-found> No maps matching
			"{{ctrl.searchMapText}}" were found. </md-not-found> </md-autocomplete>
			<br>
		</form>
		<md-content flex>
		<div class="chips" flex="none" ng-repeat="item in items">
			<div class="chip-precontext" layout="row">
				<span class="chip-context" flex> <span>
						{{item.display}}</span>
				</span> <span> <a style="cursor: pointer;"
					class="md-fab md-mini close-but"
					ng-click="ctrl.toggleChecked(item)">&#10006;</a>
				</span>
			</div>
		</div>
		</md-content> </md-content>
	</div>
	<md-button ng-click="ctrl.sendOnMap()"
		style="background-color : #E91E63; color : #fff;">Show</md-button> </md-slidenav></md-content>

	<!-- 	<script src='js/app.js'></script> -->
	<!-- 	<script src='js/admin/map.js'></script> -->
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/search/search.js"></script>


</body>

</html>