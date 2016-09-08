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

</head>

<body ng-app="SearchApp" layout="column">
	<script src='js/moment.js'></script>

	<md-toolbar class="md-hue-2">
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
				<c:out value="${user.firstName } ${user.lastName }"></c:out></md-button>
				<md-button class="md-raised" href="/EasyShopWayNew/logout">
				LogOut </md-button>
			</c:otherwise>
		</c:choose>
	</div>
	</md-toolbar>

	<md-content layout="row" ng-controller="ProductListCtrl as ctrl"
		class="main-page" flex> <md-slidenav
		class="search-sidenav" md-is-locked-open="true" layout="column" flex
		md-whiteframe="4">

	<div class="autocompletedemoBasicUsage" layout="column" flex
		ng-cloak="">
		<md-content class="md-padding" layout="column" flex>
		<form ng-submit="$event.preventDefault()">
			<md-autocomplete ng-model="searchModel" ng-disabled="ctrl.isDisabled"
				md-no-cache="ctrl.noCache" md-selected-item="ctrl.selectedItem"
				md-search-text-change="ctrl.searchTextChange(ctrl.searchText)"
				md-search-text="ctrl.searchText"
				md-selected-item-change="ctrl.selectedItemChange(item, ctrl.searchText)"
				md-items="item in ctrl.querySearch(ctrl.searchText)"
				md-item-text="item.display" md-min-length="0"
				placeholder="What are You looking for?"> <md-item-template>
			<span md-highlight-text="ctrl.searchText" md-highlight-flags="^i">{{item.display}}</span>
			</md-item-template> <md-not-found> No products matching
			"{{ctrl.searchText}}" were found. </md-not-found> </md-autocomplete>
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
		style="background-color : #E91E63; color : #fff;">Show</md-button> </md-slidenav> </md-content>
	<script src="js/jquery-3.1.0.min.js"></script>
	<script src='js/angular.min.js'></script>
	<script src='js/angular-aria.js'></script>
	<script src='js/angular-animate.js'></script>
	<script src='js/angular-material.min.js'></script>
	<script src='js/angular-route.min.js'></script>
	<script src="js/angular-messages.min.js"></script>

	<!--         <script src='js/app.js'></script> -->
	<script src="js/jquery.validate.min.js"></script>
	<!--         <script src="js/search/search.js"></script> -->

	<script>
		'use strict';
		var serApp = angular
				.module('SearchApp', [ 'ngMaterial', 'ngMessages' ]);
		serApp.controller('ProductListCtrl', DemoCtrl);

		function DemoCtrl($timeout, $q, $log, $scope, $http) {
			var self = this;

			self.simulateQuery = false;
			self.isDisabled = false;

			// list of `state` value/display objects
			// 			self.states = loadAll();

			var config = {
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
				}
			}

			$http.get('/EasyShopWayNew/searchProducts', config).success(
					function(data, status, headers, config) {
						console.log(data);
						self.states = loadAll(data);
						console.log(self.states);
					}).error(function(data, status, header, config) {
				console.log(data);
				console.log('no products');
			});

			self.querySearch = querySearch;
			self.selectedItemChange = selectedItemChange;
			self.searchTextChange = searchTextChange;

			self.newState = newState;

			self.toggleChecked = function(item) {
				console.log("Remove " + item);

				remove(item);
			}

			self.sendOnMap = function() {
				console.log($scope.items);
			}

			$scope.items = [];

			function newState(state) {
				alert("Sorry! You'll need to create a Constitution for "
						+ state + " first!");
			}

			// ******************************
			// Internal methods
			// ******************************

			/**
			 * Search for states... use $timeout to simulate
			 * remote dataservice call.
			 */
			function querySearch(query) {
				var results = query ? self.states
						.filter(createFilterFor(query)) : self.states, deferred;
				if (self.simulateQuery) {
					deferred = $q.defer();
					$timeout(function() {
						deferred.resolve(results);
					}, Math.random() * 1000, false);
					return deferred.promise;
				} else {
					return results;
				}
			}

			function searchTextChange(text) {
				$log.info('Text changed to ' + text);
			}

			function selectedItemChange(item, text) {
				if (item != undefined) {
					if (find(item) == -1) {
						$scope.items.push(item);
						self.searchText = undefined;
					}
				}

				console.log(JSON.stringify(item));
			}

			function find(value) {
				console.log($scope.items);
				for (var i = 0; i < $scope.items.length; i++) {
					console.log($scope.items[i].value + " " + value.value);
					if ($scope.items[i].value == value.value) {
						return i;
					}
				}
				return -1;
			}

			function remove(value) {
				console.log($scope.items);
				console.log(value);

				for (var i = 0; i < $scope.items.length; i++) {
					console.log($scope.items[i].value + " " + value.value);
					if ($scope.items[i].value == value.value) {
						$scope.items.splice(i, 1);
						console.log($scope.items);
						return;
					}
				}
			}

			/**
			 * Build `states` list of key/value pairs
			 */
			function loadAll(data) {

				console.log(data.products);
				var products = data.products;

				return products.map(function(product) {
					return {
						value : product.id,
						name_uk : product.name_uk,
						name_en : product.name_en,
						display : product.name_en,
						img : product.img,
						coordinates : product.coordinates
					};
				});
			}

			/**
			 * Create filter function for a query string
			 */
			function createFilterFor(query) {
				var lowercaseQuery = angular.lowercase(query);

				return function filterFn(state) {
					return (state.display.indexOf(lowercaseQuery) === 0);
				};
			}
		}

		serApp.controller('AppCtrl', function($scope, $mdDialog, $mdMedia) {
			$scope.status = '  ';
			$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
			$scope.showLogInForm = function(ev) {
				var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
						&& $scope.customFullscreen;
				$mdDialog.show({
					controller : DialogController,
					templateUrl : 'login.tmpl.html',
					parent : angular.element(document.body),
					targetEvent : ev,
					clickOutsideToClose : true,
					fullscreen : useFullScreen
				}).then(
						function(answer) {
							$scope.status = 'You said the information was "'
									+ answer + '".';
						}, function() {
							$scope.status = 'You cancelled the dialog.';
						});
				$scope.$watch(function() {
					return $mdMedia('xs') || $mdMedia('sm');
				}, function(wantsFullScreen) {
					$scope.customFullscreen = (wantsFullScreen === true);
				});
			};
			$scope.showRegistrationInFrom = function(ev) {
				var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
						&& $scope.customFullscreen;
				$mdDialog.show({
					controller : DialogController,
					templateUrl : 'signup.tmpl.html',
					parent : angular.element(document.body),
					targetEvent : ev,
					clickOutsideToClose : true,
					fullscreen : useFullScreen
				}).then(
						function(answer) {
							$scope.status = 'You said the information was "'
									+ answer + '".';
						}, function() {
							$scope.status = 'You cancelled the dialog.';
						});
				$scope.$watch(function() {
					return $mdMedia('xs') || $mdMedia('sm');
				}, function(wantsFullScreen) {
					$scope.customFullscreen = (wantsFullScreen === true);
				});
			};
		});

		serApp
				.controller(
						'LoginCtrl',
						[
								'$scope',
								'$http',
								'$window',
								function($scope, $http, $window) {
									$scope.sendLoginData = function() {
										console.log('hello' + $scope.email)
										var data = $.param({
											email : $scope.email,
											password : $scope.password
										});
										console.log('Read ' + data);

										var config = {
											headers : {
												'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
											}
										}
										if ($('#emailL').valid()
												&& $('#passwordL').valid()) {
											$http
													.post(
															'http://localhost:8080/EasyShopWayNew/login',
															data, config)
													.success(
															function(data,
																	status,
																	headers,
																	config) {
																if (data.emailErrMsg == undefined) {
																	$window.location.href = 'cabinet';
																} else {
																	$scope.error = data.emailErrMsg;
																}
																console
																		.log(data.emailErrMsg);
															})
													.error(
															function(data,
																	status,
																	header,
																	config) {
																console
																		.log('fail');
															});
										} else {
											cosole.log("sory");
										}
									};
								} ]);

		serApp
				.controller(
						'SignUpCtrl',
						[
								'$scope',
								'$http',
								function($scope, $http) {

									$scope.sendRegData = function() {
										console.log('hello ' + $scope.email)
										console.log("date " + dateBirthday)
										var data = $.param({
											email : $scope.email,
											password : $scope.password,
											firstName : $scope.firstName,
											lastName : $scope.lastName,
											birthday : dateBirthday
										});
										console.log('Read ' + data);

										var config = {
											headers : {
												'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
											}
										}

										console.log($('#fName1').val());
										console.log($('#lName1').val());

										console.log($('#emailR').valid() + " "
												+ $('#fName1').valid() + " "
												+ $('#lName1').valid() + " "
												+ $('#passwordR').valid())
										if ($('#emailR').valid()
												&& $('#passwordR').valid()
												&& $('#fName1').valid()
												&& $('#lName1').valid()) {

											$http
													.post(
															'http://localhost:8080/EasyShopWayNew/reg',
															data, config)
													.success(
															function(data,
																	status,
																	headers,
																	config) {
																console
																		.log("QWEER"
																				+ data.emailErrMsg);
																$scope.error = data.emailErrMsg;
																if (data.emailErrMsg == undefined) {
																	$scope.success = "Check your email";
																}
																// var esc = $
																// .Event(
																// "keydown", {
																// keyCode: 27
																// });
																// $("body").trigger(esc);

															})
													.error(
															function(data,
																	status,
																	header,
																	config) {
																console
																		.log('fail');
															});
										} else {
											console.log("oq");
										}
									};
								} ]);

		serApp.controller('DatePickerCtrl', function($scope) {

			$scope.today = function() {
				$scope.dt = new Date();
			};
			$scope.dateformat = "MM/dd/yyyy";
			$scope.today();
			$scope.showcalendar = function($event) {
				$scope.showdp = true;
			};
			$scope.showdp = false;
			$scope.dtmax = new Date();
			dateBirthday = moment($scope.dt).format('YYYY-MM-DD');
		});

		function DialogController($scope, $mdDialog) {
			$scope.hide = function() {
				$mdDialog.hide();
			};
			$scope.cancel = function() {
				$mdDialog.cancel();
			};
			$scope.answer = function(answer) {
				$mdDialog.hide(answer);
			};
		}
	</script>

</body>

</html>