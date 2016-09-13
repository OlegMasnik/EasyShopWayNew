/**
 * 
 */
var statApp = angular.module('MyApp');

statApp.controller('StatisticDateCtrl', function ($scope) {
//	$scope.myDate = new Date();
//    $scope.today = function () {
//        $scope.endDate = new Date();
//        $scope.startDate = new Date(
//  		      $scope.endDate.getFullYear(),
//  		      $scope.endDate.getMonth(),
//  		      $scope.endDate.getDate() - 5);
//    };
//    $scope.maxDate = new Date(
//    	      $scope.myDate.getFullYear(),
//    	      $scope.myDate.getMonth(),
//    	      $scope.myDate.getDate());
//    $scope.dateformat = "MM/dd/yyyy";
//    $scope.today();
//    $scope.showcalendar = function ($event) {
//        $scope.showdp = true;
//    };
//    $scope.showdp = false;
//    $scope.stMaxDt = new Date();
//    dateBirthday = moment($scope.dt).format('YYYY-MM-DD');
	var date = new Date();
	  $scope.myDate = new Date();

	  $scope.minDate = new Date(
	  $scope.myDate.getFullYear() - 100,
	      $scope.myDate.getMonth(),
	      $scope.myDate.getDate());

	  $scope.maxDate = new Date(
	      $scope.myDate.getFullYear(),
	      $scope.myDate.getMonth(),
	      $scope.myDate.getDate());
	  
	  $scope.checkValidity = function(){
	     if ($scope.myDate > date){
	        return false;
	     } else{
	       return true
	     }
	  };
	  $scope.$watch('myDate', function(newVal, oldVal){
	    
	    if(newVal != oldVal){
	        $scope.checkValidity();
	    }
	  });
	  $scope.$watch('myDate', function(newVal, oldVal){
	    
	    if(newVal != oldVal){
	        $scope.checkValidity();
	    }
	  });
});

function maxDateValue() {
	return new Date();
}