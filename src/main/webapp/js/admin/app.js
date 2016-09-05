var adminApp = angular.module('MyApp');

adminApp.requires.push('datatables');

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
		controller : 'UsersCtrl'

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