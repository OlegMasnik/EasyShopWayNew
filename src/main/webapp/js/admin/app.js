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

adminApp.controller('UsersCtrl', function($scope, $http) {
	$http({
		method : "GET",
		url : "/EasyShopWayNew/users"
	}).then(function mySucces(response) {
		$scope.users = response.data.users;
		console.log($scope.users);
	}, function myError(response) {
		console.log(response.statusText);
	});
});

app
		.controller(
				'UsersCtrl1',
				[
						'$scope',
						function($scope) {
							'use strict';
							// some var
							var original = {
								"count" : 12,
								"data" : [
										{
											"name" : "Frozen yogurt",
											"type" : "Ice cream",
											"calories" : 159.0,
											"fat" : 6.0,
											"carbs" : 24.0,
											"protein" : 4.0,
											"sodium" : 87.0,
											"calcium" : 14.0,
											"iron" : 1.0,
											"comment" : "Not as good as the real thing."
										}, {
											"name" : "Ice cream sandwich",
											"type" : "Ice cream",
											"calories" : 237.0,
											"fat" : 9.0,
											"carbs" : 37.0,
											"protein" : 4.3,
											"sodium" : 129.0,
											"calcium" : 8.0,
											"iron" : 1.0,
											"comment" : "Very smooth."
										}, {
											"name" : "Eclair",
											"type" : "Pastry",
											"calories" : 262.0,
											"fat" : 16.0,
											"carbs" : 24.0,
											"protein" : 6.0,
											"sodium" : 337.0,
											"calcium" : 6.0,
											"iron" : 7.0
										}, {
											"name" : "Cupcake",
											"type" : "Pastry",
											"calories" : 305.0,
											"fat" : 3.7,
											"carbs" : 67.0,
											"protein" : 4.3,
											"sodium" : 413.0,
											"calcium" : 3.0,
											"iron" : 8.0,
											"comment" : "Very tasty."
										}, {
											"name" : "Magnum",
											"type" : "Ice cream",
											"calories" : 227.0,
											"fat" : 19.0,
											"carbs" : 27.2,
											"protein" : 4.3,
											"sodium" : 109.0,
											"calcium" : 18.0,
											"iron" : 2.0
										}, {
											"name" : "Jelly bean",
											"type" : "Candy",
											"calories" : 375.0,
											"fat" : 0.0,
											"carbs" : 94.0,
											"protein" : 0.0,
											"sodium" : 50.0,
											"calcium" : 0.0,
											"iron" : 0.0,
											"comment" : "I like it very much."
										}, {
											"name" : "Mars",
											"type" : "Candy",
											"calories" : 528.0,
											"fat" : 22.0,
											"carbs" : 55.0,
											"protein" : 1.2,
											"sodium" : 5.0,
											"calcium" : 2.0,
											"iron" : 16.0
										}, {
											"name" : "Lollipop",
											"type" : "Candy",
											"calories" : 392.0,
											"fat" : 0.2,
											"carbs" : 98.0,
											"protein" : 0.0,
											"sodium" : 38.0,
											"calcium" : 0.0,
											"iron" : 2.0,
											"comment" : "Just for kids."
										}, {
											"name" : "Honeycomb",
											"type" : "Other",
											"calories" : 408.0,
											"fat" : 3.2,
											"carbs" : 87.0,
											"protein" : 6.5,
											"sodium" : 562.0,
											"calcium" : 0.0,
											"iron" : 45.0
										}, {
											"name" : "Donut",
											"type" : "Pastry",
											"calories" : 452.0,
											"fat" : 25.0,
											"carbs" : 51.0,
											"protein" : 4.9,
											"sodium" : 326.0,
											"calcium" : 2.0,
											"iron" : 22.0,
											"comment" : "Very smooth."
										}, {
											"name" : "Brownie",
											"type" : "Pastry",
											"calories" : 752.0,
											"fat" : 35.0,
											"carbs" : 51.2,
											"protein" : 5.9,
											"sodium" : 326.0,
											"calcium" : 5.0,
											"iron" : 2.0
										}, {
											"name" : "KitKat",
											"type" : "Candy",
											"calories" : 518.0,
											"fat" : 26.0,
											"carbs" : 65.0,
											"protein" : 7.0,
											"sodium" : 54.0,
											"calcium" : 12.0,
											"iron" : 6.0,
											"comment" : "Just the original."
										} ]
							};
							$scope.datatable = angular.copy(original);
							$scope.smart = true;

							// multisearch
							// multisearch
							$scope.autocolumn = [ {
								name : "name",
								display : "Name"
							}, {
								name : "type",
								display : "Type"
							}, {
								name : "calories",
								display : "Calories"
							}, {
								name : "fat",
								display : "Fat"
							}, {
								name : "carbs",
								display : "Carbs"
							}, {
								name : "protein",
								display : "Protein"
							}, {
								name : "sodium",
								display : "Sodium"
							}, {
								name : "calcium",
								display : "Calcium"
							}, {
								name : "iron",
								display : "Iron"
							}, {
								name : "comment",
								display : "Comment"
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
								order : 'name',
								limit : 15,
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
																							// every
																							// filter
										if ($scope.multisearch[j].ident) { // check
																			// if
																			// filter
																			// exists
											for (var i = original.data.length - 1; i >= 0; i--) { // for
																									// every
																									// row
												var removeRow = true; // take
																		// a
																		// guess
												for ( var key in original.data[i]) { // for
																						// every
																						// property
													if (original.data[i]
															.hasOwnProperty(key)) { // check
																					// if
																					// propery
																					// exitst
														if (key == $scope.multisearch[j].column) { // check
																									// if
																									// filter
																									// is
																									// current
																									// column
															var op = false; // check
																			// for
																			// operators
															for ( var key in operators) {
																if ($scope.multisearch[j].ident
																		.split(" ")[0] == key) {
																	op = true; // operator
																				// found
																}
															}
															if (op) { // if
																		// operator
																		// was
																		// found
																var msray = $scope.multisearch[j].ident
																		.split(" ");
																var operator = $scope.multisearch[j].ident
																		.split(" ")[0];
																msray.splice(0,
																		1); // extract
																			// operator
																var comp = msray
																		.join(" "); // and
																					// rest
																if (operators[operator]
																		(
																				original.data[i][$scope.multisearch[j].column],
																				comp)) {
																	removeRow = false; // check
																						// operator
																						// condition
																	break;
																}
															} else {
																if (matchRule(
																		original.data[i][$scope.multisearch[j].column],
																		$scope.multisearch[j].ident,
																		$scope.smart)) {
																	removeRow = false; // check
																						// string
																						// and
																						// filter
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
									dt.data.splice(rowdel[i], 1); // remove
																	// the row
								}
								dt.count = dt.data.length;
								$scope.datatable = angular.copy(dt);
							};
						} ]);

// some other functions
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

