var userApp = angular.module('MyApp');
      
userApp.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "template/shared/info.jsp"
	}).when("/statistic", {
		templateUrl : "template/shared/statistic.jsp",
		controller : "ChartCtrl", 
	}).when("/history", {
		templateUrl : "template/shared/history.jsp",
		controller : "UserHistoryCtrl"
	});
});
