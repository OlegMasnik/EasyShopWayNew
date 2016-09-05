/**
 * 
 */
var statApp = angular.module('MyApp');

statApp.controller('StatisticDateCtrl', function ($scope) {

    $scope.today = function () {
        $scope.endDate = new Date();
        $scope.startDate = new Date(
  		      $scope.endDate.getFullYear(),
  		      $scope.endDate.getMonth(),
  		      $scope.endDate.getDate() - 5);
    };
    $scope.dateformat = "MM/dd/yyyy";
    $scope.today();
    $scope.showcalendar = function ($event) {
        $scope.showdp = true;
    };
    $scope.showdp = false;
    $scope.stMaxDt = new Date();
    dateBirthday = moment($scope.dt).format('YYYY-MM-DD');
});