var adminApp = angular.module('MyApp');

adminApp.requires.push('md.data.table');

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "template/shared/info.jsp",
		controller : 'InfoCtrl'
	}).when("/map", {
		templateUrl : "template/admin/map.html"
	}).when("/history", {
		templateUrl : "template/admin/history.html"
	}).when("/products", {
		templateUrl : "template/admin/products.html"
	}).when("/users", {
		templateUrl : "template/admin/users.html",
		controller : 'UsersCtrl1'
	}).when("/statistic", {
		templateUrl : "template/admin/statistics.html"
	});
});

adminApp.controller('AdminCtrl', function($scope, $http) {

});

adminApp.controller('InfoCtrl', function($scope, $http) {
	$scope.qwer = "Hello world";
});

adminApp
		.controller(
				'UsersCtrl1',				[
						'$http',
						'$scope', '$location',
						function($http, $scope, $location) {

							var original = {};

							$http({
								method : "GET",
								url : "/EasyShopWayNew/users"
							}).then(function mySucces(response) {
								$scope.data = response.data;
								original.users = $scope.data.users;
								original.count = $scope.data.users.length;
								$scope.datatable = angular.copy(original);
								console.log("Get");
								console.log($scope.data);
							}, function myError(response) {
								console.log(response.statusText);
							});
							$scope.smart = true;

							$scope.autocolumn = [ {
								name : "fn",
								display : "First Name"
							}, {
								name : "ln",
								display : "Last Name"
							}, {
								name : "e",
								display : "Email"
							} ];
							$scope.multisearch = Array();
							$scope.multisearch[0] = {
								id : 0,
								column : "",
								ident : "",
							};

							$scope.addRow = function() {
								$scope.multisearch.push({
									id : $scope.multisearch.length,
									column : "",
									ident : ""
								});
							};

							$scope.deleteRow = function(int) {
								$scope.multisearch.splice(int, 1);
								for (var i = 0; i < $scope.multisearch.length; i++) {
									$scope.multisearch[i].id = i;
								}
								$scope.updateDataTable();
							};

							// Configure Table
							$scope.limitOptions = [ 5, 10, 15 ];
							$scope.options = {
								pageSelect : true
							};

							$scope.query = {
								order : 'fn',
								limit : 5,
								page : 1
							};

							$scope.updateDataTable = function() {
								var rowdel = Array();
								var filter = false; // set filter false
								for (var j = 0; j < $scope.multisearch.length; j++) {
									if ($scope.multisearch[j].ident
											&& $scope.multisearch[j].column) {
										filter = true; // if a filter exists
									}
								}
								if (filter) { // if a filter is set
									for (var j = 0; j < $scope.multisearch.length; j++) { // for
										if ($scope.multisearch[j].ident) { // check
											for (var i = original.users.length - 1; i >= 0; i--) { // for
												var removeRow = true; // take
												for ( var key in original.users[i]) { // for
													if (original.users[i]
															.hasOwnProperty(key)) { // check
														if (key == $scope.multisearch[j].column) { // check
															var op = false; // check
															for ( var key in operators) {
																if ($scope.multisearch[j].ident
																		.split(" ")[0] == key) {
																	op = true; // operator
																	// found
																}
															}
															if (op) { // if
																var msray = $scope.multisearch[j].ident
																		.split(" ");
																var operator = $scope.multisearch[j].ident
																		.split(" ")[0];
																msray.splice(0,
																		1); // extract
																var comp = msray
																		.join(" "); // and
																if (operators[operator]
																		(
																				original.users[i][$scope.multisearch[j].column],
																				comp)) {
																	removeRow = false; // check
																	break;
																}
															} else {
																if (matchRule(
																		original.users[i][$scope.multisearch[j].column],
																		$scope.multisearch[j].ident,
																		$scope.smart)) {
																	removeRow = false; // check
																	break;
																}
															}
														}
													}
												}
												if (removeRow) {
													rowdel.push(i);
												}
											}
										}
									}
								}
								var dt = angular.copy(original);
								for (var i = 0; i < rowdel.length; i++) {
									dt.users.splice(rowdel[i], 1); // remove
								}
								dt.count = dt.users.length;
								$scope.datatable = angular.copy(dt);
							};
							$scope.active = function(i) {
								console.log(i.e + " " + i.active);
								
								var data = $.param({
									email : i.e,
									active : !i.active
								});
								var config = {
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
									}
								}
								$http
										.post('/EasyShopWayNew/users', data,
												config).success(
												function(data, status, headers,
														config) {
													console.log("OK+");
												}).error(
												function(data, status, header,
														config) {
												});
								$http({
									method : "GET",	
									url : "/EasyShopWayNew/users"
								}).then(function mySucces(response) {
									$scope.data = response.data;
									original.users = $scope.data.users;
									original.count = $scope.data.users.length;
									$scope.datatable = angular.copy(original);
									console.log("Get");
									console.log($scope.data);
								}, function myError(response) {
									console.log(response.statusText);
								});
								console.log("Finish");

							};
						} ]);

var operators = {
	'<' : function(a, b) {
		return a < b
	},
	'<=' : function(a, b) {
		return a <= b
	},
	'!=' : function(a, b) {
		return a != b
	},
	'==' : function(a, b) {
		return a == b
	},
	'>=' : function(a, b) {
		return a >= b
	},
	'>' : function(a, b) {
		return a > b
	},
	'%' : function(a, b) {
		return a % b
	}
};


function matchRule(str, rule, smart) {
	str = String(str);
	rule = String(rule);
	if (smart) {
		rule = "*" + rule.toLowerCase() + "*";
		str = str.toLowerCase();
	}
	return new RegExp("^" + rule.split("*").join(".*") + "$").test(str);
};