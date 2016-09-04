var adminApp = angular.module('MyApp');

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "template/info.html"
	}).when("/map", {
		templateUrl : "template/map.html"
	}).when("/history", {
		templateUrl : "template/history.html"
	}).when("/products", {
		templateUrl : "template/products.html"
	}).when("/users", {
		templateUrl : "template/users.html"
	}).when("/statistic", {
		templateUrl : "template/statistic.html"
	});
});

adminApp.controller('AdminCtrl', function($scope, $http) {
	$scope.qwer = 'Hello world';
});
