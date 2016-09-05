var userApp = angular.module('MyApp');

userApp.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "template/shared/info.jsp",
    }).when("/history", {
        templateUrl: "template/shared/history.jsp",
        controller : 'UserHistoryCtrl'
    }).when("/statistic", {
        templateUrl: "template/shared/statistic.jsp",
        controller : 'ChartCtrl'
    });
});

userApp.controller('ChartCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.getFoodData = function () {
		alert("asdasd");
      
        var responsik = undefined;

        $http({
        	  method: 'POST',
        	  url: '/EasyShopWayNew/userStat'
        	}).then(function successCallback(response) {
        	   responsik = response;
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
