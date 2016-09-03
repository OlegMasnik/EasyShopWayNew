var app = angular.module('MyApp', ['ngMaterial']);

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
});

app.controller('LoginCtrl', ['$scope', function ($scope) {
    $scope.sendLoginData = function () {
        console.log('hello ' + $scope.email)
        var req = {
            method: 'POST',
            url: 'http://localhost:8080/EasyShopWayNew/login',
            headers: {
                'Content-Type': undefined
            },
            data: {
                email: $scope.email,
                pass: $scope.password
            }
        }

        //        $http(req).then(function () {...
        //        }, function () {...
        //        });
    };
}]);

function DialogController($scope, $mdDialog) {
    $scope.hide = function () {
        $mdDialog.hide();
    };

    $scope.cancel = function () {
        $mdDialog.cancel();
    };

    $scope.answer = function (answer) {
        $mdDialog.hide(answer);
    };
}