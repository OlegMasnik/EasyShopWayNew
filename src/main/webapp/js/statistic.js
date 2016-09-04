var app = angular.module('MyApp');

app.controller('ChartCtrl', function($scope) {
	$scope.getFoodData = function () {
//        console.log('hello' + $scope.email)
//        
//
//        var config = {
//            headers: {
//                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
//            }
//        }
//        if ($('#emailL').valid() && $('#passwordL').valid()) {
//            $http.post('http://localhost:8080/EasyShopWayNew/login', data, config)
//                .success(function (data, status,
//                    headers, config) {
//                    if (data.emailErrMsg == undefined) {
//                        $window.location.href = 'cabinet';
//                    } else {
//                        $scope.error = data.emailErrMsg;
//                    }
//                    console.log(data.emailErrMsg);
//                }).error(function (data, status,
//                    header, config) {
//                    console.log('fail');
//                });
//        } else {
//            cosole.log("sory");
//        }
//        
        $(document).ready(function () {

            // Build the chart
            $('#container').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: 'Browser market shares January, 2015 to May, 2015'
                },
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
                series: [{
                    name: 'Brands',
                    colorByPoint: true,
                    data: [{
                        name: 'Microsoft Internet Explorer',
                        y: 56.33
                    }, {
                        name: 'Chrome',
                        y: 24.03,
                       
                    },
                    {
                        name: 'Chrome',
                        y: 24.03,
                        sliced: true,
                        selected: true
                        
                    },
                     {
                        name: 'Firefox',
                        y: 10.38
                    }, {
                        name: 'Safari',
                        y: 4.77
                    }, {
                        name: 'Opera',
                        y: 0.91
                    }, {
                        name: 'Proprietary or Undetectable',
                        y: 0.2
                    }]
                }]
            });
        });
        
        
    };
});
