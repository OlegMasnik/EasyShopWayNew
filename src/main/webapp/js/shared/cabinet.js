/**
 * 
 */

var cabinetApp = angular.material("MyApp");

cabinetApp.controller("UserInfo", [
		"$scope",
		"$scope",
		function($scope, $http) {

			$http.get('http://localhost:8080/EasyShopWayNew/info', config)
					.success(function(data, status, headers, config) {

						console.log(data.firstName + " " + data.lastName);

						$scope.firstName = data.firstName;
						$scope.lastName = data.lastName;
					}).error(function(data, status, header, config) {
						console.log('fail');
					});
		} ])