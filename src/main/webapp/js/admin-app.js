/**
 * 
 */

var app = angular.module('AdminApp', ['ngMaterial']);

app.controller('AppCtrl', function ($scope, $mdDialog, $mdMedia) {
    $scope.status = '  ';
    $scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');

    $scope.showLogInForm = function (ev) {
        var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;

        $mdDialog.show({
            controller: DialogController,
            templateUrl: 'login.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            fullscreen: useFullScreen
        }).then(function (answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function () {
            $scope.status = 'You cancelled the dialog.';
        });

        $scope.$watch(function () {
            return $mdMedia('xs') || $mdMedia('sm');
        }, function (wantsFullScreen) {
            $scope.customFullscreen = (wantsFullScreen === true);
        });

    };
    $scope.showRegistrationInFrom = function (ev) {
        var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;

        $mdDialog.show({
            controller: DialogController,
            templateUrl: 'signup.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            fullscreen: useFullScreen
        }).then(function (answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function () {
            $scope.status = 'You cancelled the dialog.';
        });

        $scope.$watch(function () {
            return $mdMedia('xs') || $mdMedia('sm');
        }, function (wantsFullScreen) {
            $scope.customFullscreen = (wantsFullScreen === true);
        });

    };
});

app.controller('DialogController', function($scope, $mdDialog) {
	$scope.status = '  ';
	$scope.customFullscreen = false;
	$scope.showLoginDialog = function(ev) {
		$mdDialog.show({
			controller : DialogController,
			templateUrl : 'login.tmpl.html',
			parent : angular.element(document.body),
			targetEvent : ev,
			clickOutsideToClose : true
		}).then(
				function(answer) {
					$scope.status = 'You said the information was "'
							+ answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
	};
	$scope.showSignUpDialog = function(ev) {
		$mdDialog.show({
			controller : DialogController,
			templateUrl : 'signup.tmpl.html',
			parent : angular.element(document.body),
			targetEvent : ev,
			clickOutsideToClose : true
		}).then(
				function(answer) {
					$scope.status = 'You said the information was "'
							+ answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
	};
	$scope.openCabinet = function(){
		location.href = '/EasyShopWay/Cabinet.do';
	};

	function DialogController($scope, $mdDialog) {
		$scope.hide = function() {
			$mdDialog.hide();
		};
		$scope.cancel = function() {
			$mdDialog.cancel();
		};
		$scope.answer = function(answer) {
			$mdDialog.hide(answer);
		};
	}
});