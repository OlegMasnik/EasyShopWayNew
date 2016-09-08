/**
 * 
 */

var cabinetApp = angular.module("MyApp");

cabinetApp.controller("UserInfo", [
		"$scope",
		"$scope",
		function($scope, $http) {

			$http.get('/EasyShopWayNew/info', config)
					.success(function(data, status, headers, config) {

						console.log(data.firstName + " " + data.lastName);

						$scope.firstName = data.firstName;
						$scope.lastName = data.lastName;
					}).error(function(data, status, header, config) {
						console.log('fail');
					});
		} ])