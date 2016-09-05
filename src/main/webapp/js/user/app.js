var userApp = angular.module('MyApp');

userApp.controller('ChartCtrl', function($scope, $http) {
	$scope.getFoodData = function () {
		alert("asdasd");
      
        var responsik = undefined;

        $http({
        	  method: 'POST',
        	  url: '/EasyShopWay/userStat'
        	}).then(function successCallback(response) {
        	   responsik = data;
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
   

            // Build the chart
//            $('#container').highcharts({
//                chart: {
//                    plotBackgroundColor: null,
//                    plotBorderWidth: null,
//                    plotShadow: false,
//                    type: 'pie'
//                },
//                title: {
//                    text: 'Food'
//                },
//                tooltip: {
//                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//                },
//                plotOptions: {
//                    pie: {
//                        allowPointSelect: true,
//                        cursor: 'pointer',
//                        dataLabels: {
//                            enabled: false
//                            
//                        },
//                        showInLegend: true
//                    }
//                },
//                series: [{
//                	colorByPoint: true,
//                    name: 'Brands',
//                    data: [{
//                        name: 'Microsoft Internet Explorer',
//                        y: 56.33
//                    }, {
//                        name: 'Chrome',
//                        y: 24.03,
//                       
//                    },
//                    {
//                        name: 'Chrome',
//                        y: 24.03,
//                        sliced: true,
//                        selected: true
//                        
//                    },
//                     {
//                        name: 'Firefox',
//                        y: 10.38
//                    }, {
//                        name: 'Safari',
//                        y: 4.77
//                    }, {
//                        name: 'Opera',
//                        y: 0.91
//                    }, {
//                        name: 'Proprietary or Undetectable',
//                        y: 0.2
//                    }]
//                }]
//            });
       
      
        
    };
}]);
