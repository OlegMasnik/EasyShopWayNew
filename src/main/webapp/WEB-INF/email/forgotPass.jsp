<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
 <link rel="stylesheet" href="css/preloader/preloader.css">
<link rel="stylesheet" href="css/icon.css">
<link rel="stylesheet" href="css/angular-material.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="favicon.ico">
	
<title>Reset password</title>
</head>

<body ng-app="MyApp">
<div id="loader-wrapper">
		<div id="loader"></div>
	</div>
	<jsp:include page="/WEB-INF/parts/header.jsp"></jsp:include>

	<div layout="row">
		<div flex="30"></div>
		<div flex>
			<form name="logForm" layout="column" action="/forgetpassword"
				method="post" ng-controller="ResetPassCtrl">
				<h2>{{ 'RESET_PASS' | translate }}</h2>
				<label style="color: red;">{{status}}</label>
				<md-input-container class="md-block"
					style="margin-top: 0; margin-bottom: 10px;" flex layout="column">
				<label>{{ 'EMAIL' | translate }}</label> <input type="email" ng-model="e"
					name="email" required minlength="10" maxlength="100"> </md-input-container>
				<md-input-container class="md-block"
					style="margin-top: 0; margin-bottom: 10px;" layout="column" flex>
				<label>{{ 'NEW_PASS' | translate }}</label> <input type="password" ng-model="p"
					name="password" required minlength="6" maxlength="25"> </md-input-container>
				<md-input-container class="md-block"
					style="margin-top: 0; margin-bottom: 10px;" layout="column" flex>
				<label>{{ 'REPEAT_PASS' | translate }}</label> <input type="password" ng-model="pr"
					name="passwordR" required minlength="6" maxlength="25"> </md-input-container>
				<div layout="row">
					<div flex="70"></div>
					<md-button flex ng-click='sendResetData()'
						class="md-raised md-primary">{{ 'SUBMIT' | translate }}</md-button>
				</div>
			</form>
		</div>

		<div flex="30"></div>
	</div>

	<md-button ng-click="page.goToPage('search')"
		class="md-fab md-fab-bottom-right"
		ng-controller="PageRedirectCtrl as page" aria-label="search">
		<md-tooltip md-direction="top">
       {{ 'SEARCH' | translate }}   
    </md-tooltip> <md-icon md-svg-src="images/svg/vectorpaint.svg"></md-icon> </md-button>
		
	<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src='js/angular-translate.js'></script>

	<script src='js/app.js'></script>
	<script src="js/jquery.validate.min.js"></script>

	<script type="text/javascript">
		angular
				.module('MyApp')
				.controller(
						'ResetPassCtrl',
						function($scope, $http, $mdToast, $translate) {

							$scope.sendResetData = function() {

								$http(
										{
											method : "PUT",
											url : "/EasyShopWayNew/forgotpassword?email="
													+ $scope.e
										})
										.then(
												function mySucces(response) {
													
													var isContains = response.data;
													if (typeof ($scope.e) == 'undefined')
														showToast($mdToast,	$scope, $translate.instant('INVALID_EMAIL'));
// 														$scope.status = "Email is invalid";
													else if (isContains == "false")
														showToast($mdToast,	$scope, $translate.instant('EMAIL_DOES_NOT_EXIST'));
// 														$scope.status = "This email does not exist";
													else if (($scope.p != $scope.pr))
														showToast($mdToast,	$scope, $translate.instant('PASSWORDS_DONT_MATCH'));
// 														$scope.status = "Passwords do not match";
													else if (typeof ($scope.p) == 'undefined'
															|| typeof ($scope.pr) == 'undefined')
														showToast($mdToast,	$scope, $translate.instant('PASSWORDS_INVALID'));
// 														$scope.status = "Passwords is invalid";
													else {
														var data = $.param({
															email : $scope.e,
															password : $scope.p
														});
														console.log(data);
														var config = {
															headers : {
																'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
															}
														}
														$http
																.post(
																		'/EasyShopWayNew/forgotpassword',
																		data,
																		config)
																.success(
																		function(
																				response,
																				status,
																				headers) {
																			
																			showToast(
																					$mdToast,
																					$scope, $translate.instant('CHECK_EMAIL'));
																		})
																.error(
																		function(
																				data,
																				status,
																				header,
																				config) {
																			console
																					.log('failed');
																		});
													}
												},
												function myError(response) {
													console
															.log(response.statusText);
												});

							}
						});

		function showToast($mdToast, $scope, msg) {
			var last = {
				bottom : true,
				top : false,
				left : false,
				right : true
			};
			$scope.toastPosition = angular.extend({}, last);

			$scope.getToastPosition = function() {
				return Object.keys($scope.toastPosition).filter(function(pos) {
					return $scope.toastPosition[pos];
				}).join(' ');
			};

			$scope.showSimpleToast = function() {
				var pinTo = $scope.getToastPosition();

				$mdToast.show($mdToast.simple().textContent(msg)
						.position(pinTo).hideDelay(4000));
			};
			$scope.showSimpleToast();
		}
	</script>

<script src="js/preloader/preloader.js"></script>

</body>
<script type='text/ng-template' id='info.html'>
    </script>
</html>