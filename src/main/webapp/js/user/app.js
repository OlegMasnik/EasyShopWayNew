//var userApp = angular.module('MyApp');
//      
//userApp.config(function($routeProvider) {
//	$routeProvider.when("/", {
//		templateUrl : "template/shared/info.jsp"
//	}).when("/statistic", {
//		templateUrl : "template/shared/statistic.jsp",
//		controller : "ChartCtrl", 
//	}).when("/history", {
//		templateUrl : "template/shared/history.jsp",
//		controller : "UserHistoryCtrl"
//	});
//});
//
//userApp.controller('ChartCtrl', ['$scope', '$http', function($scope, $http) {
//	
//	$scope.date = new Date();
//	
//	$scope.startDate = new Date(
//		      $scope.date.getFullYear(),
//		      $scope.date.getMonth() - 1,
//		      $scope.date.getDate());
//	$scope.endDate = new Date();
//	  
//	//alert($scope.startDate);
//	$scope.getFoodData = function () {
//		
//		var startDate = moment($scope.startDate).format('YYYY-MM-DD');
//		var endDate = moment($scope.endDate).format('YYYY-MM-DD');
//		 var data = $.param({
//	            startDate: startDate,
//	            endDate: endDate  
//	     });
//		 
//		 var config = {
//		            headers: {
//		                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
//		            }
//		        }
//		 
//		 $http.post('/EasyShopWayNew/stat', data, config)
//		 		.success(function (data, status, headers, config) {
//		 			   response = data;
//		        	   console.log(response);
//		        	   $('#pieContainer').highcharts({
//		        		      chart: {
//		        		          plotBackgroundColor: null,
//		        		          plotBorderWidth: null,
//		        		          plotShadow: false,
//		        		          type: 'pie'
//		        		      },
//		        		      title: response.pie.title,
//		        		      tooltip: {
//		        		          pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//		        		      },
//		        		      plotOptions: {
//		        		          pie: {
//		        		              allowPointSelect: true,
//		        		              cursor: 'pointer',
//		        		              dataLabels: {
//		        		                  enabled: false
//		        		                  
//		        		              },
//		        		              showInLegend: true
//		        		          }
//		        		      },
//		        		      series: response.pie.series
//		        	      });
//	
//	         }).error(
//	             function (data, status, header, config) {
//	                 console.log('fail');
//	             });
//    };
//}]);


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

userApp.controller('ChartCtrl', ['$scope', '$http', '$route', function($scope, $http, $route) {
	
	$scope.date = new Date();
	
	$scope.startDate = new Date(
		      $scope.date.getFullYear(),
		      $scope.date.getMonth() - 1,
		      $scope.date.getDate());
	$scope.endDate = new Date();
	  
	//alert($scope.startDate);
	$scope.getFoodData = function () {
		
		var startDate = moment($scope.startDate).format('YYYY-MM-DD');
		var endDate = moment($scope.endDate).format('YYYY-MM-DD');
		 var data = $.param({
	            startDate: startDate,
	            endDate: endDate  
	     });
		 
		 var config = {
		            headers: {
		                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
		            }
		        }

		 $http.post('/EasyShopWayNew/stat', data, config)
		 		.success(function (data, status, headers, config) {
		 			   response = data;
		        	   console.log(response);
		        	    console.log(response);
			$('#pieContainer').highcharts({
			    chart: {
			        plotBackgroundColor: null,
			        plotBorderWidth: null,
			        plotShadow: false,
			        type: 'pie'
			    },
			    title: response.pie.title,
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
			    series: response.pie.series
			});
		        	   
    	   $('#columnContainer').highcharts({
    	        chart: {
    	            type: 'column'
    	        },
    	        title: response.column.title,
    	        xAxis: response.column.xAxis,
    	        yAxis: response.column.yAxis,
    	        legend: {
    	            enabled: false
    	        },
    	        plotOptions: {
    	            series: {
    	                borderWidth: 0,
    	                dataLabels: {
    	                    enabled: true,
    	                    format: '{point.y:.1f}%'
    	                }
    	            }
    	        },

    	        tooltip: {
    	            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    	            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b><br/>'
    	        },

    	        series: response.column.series
    	    });
		        //$route.reload();
		       }).error(
	             function (data, status, header, config) {
	                 console.log('fail');
	             });
    };
}]);
