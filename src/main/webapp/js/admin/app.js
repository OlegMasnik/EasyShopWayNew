var adminApp = angular.module('MyApp');

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "template/admin/info.jsp",
		controller : 'InfoCtrl'
	}).when("/map", {
		templateUrl : "template/admin/map.html"
	}).when("/history", {
		templateUrl : "template/admin/history.html"
	}).when("/products", {
		templateUrl : "template/admin/products.html"
	}).when("/users", {
		templateUrl : "template/admin/users.html"
	}).when("/statistic", {
		templateUrl : "template/admin/statistics.html"
	});
});

adminApp.controller('AdminCtrl', function($scope, $http) {
	
});

adminApp.controller('InfoCtrl', function($scope, $http) {
	$scope.qwer = "Hello world";
});

