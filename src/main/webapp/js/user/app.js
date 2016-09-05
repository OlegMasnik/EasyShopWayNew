var userApp = angular.module('MyApp');
      
userApp.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "template/shared/info.jsp"
	}).when("/statistic", {
		templateUrl : "template/shared/statistic.jsp",
		controller : "ChartCtrl"
	}).when("/history", {
		templateUrl : "template/shared/history.jsp",
		controller : "UserHistoryCtrl"
	});
});

userApp.controller('ChartCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.getFoodData = function () {
		var responsik = undefined;
		
		//Build the chart
        $http({
        	  method: 'POST',
        	  url: '/EasyShopWayNew/userStat'
        	}).then(function successCallback(response) {
        	   responsik = response.data;
        	   console.log(responsik);
        	   $('#container').highcharts({
        		      chart: {
        		          plotBackgroundColor: null,
        		          plotBorderWidth: null,
        		          plotShadow: false,
        		          type: 'pie'
        		      },
        		      title: responsik.title,
        		      tooltip: {
        		          pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        		      },
        		      plotOptions: {
        		          pie: {
        		              allowPointSelect: true,
        		              cursor: 'pointer',
        		              dataLabels: {
        		                  enabled: false
        		                  
        		              },
        		              showInLegend: true
        		          }
        		      },
        		      series: responsik.series
        	      });
        	  }, function errorCallback(response) {
        	    console.log("fail");
        	  });
    };
}]);
