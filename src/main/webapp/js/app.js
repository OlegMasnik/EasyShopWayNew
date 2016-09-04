var dateBirthday;
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

app.controller('LoginCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
    $scope.sendLoginData = function () {
        console.log('hello' + $scope.email)
        var data = $.param({
            email: $scope.email,
            password: $scope.password
        });
        console.log('Read ' + data);

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }
        if ($('#emailL').valid() && $('#passwordL').valid()) {
            $http.post('http://localhost:8080/EasyShopWayNew/login', data, config)
                .success(function (data, status,
                    headers, config) {
                    if (data.emailErrMsg == undefined) {
                        $window.location.href = 'cabinet';
                    } else {
                        $scope.error = data.emailErrMsg;
                    }
                    console.log(data.emailErrMsg);
                }).error(function (data, status,
                    header, config) {
                    console.log('fail');
                });
        } else {
            cosole.log("sory");
        }
    };
}]);

app.controller('SignUpCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.sendRegData = function () {
        console.log('hello ' + $scope.email)
        console.log("date " + dateBirthday)
        var data = $.param({
            email: $scope.email,
            password: $scope.password,
            firstName: $scope.firstName,
            lastName: $scope.lastName,
            birthday: dateBirthday
        });
        console.log('Read ' + data);

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        console.log($('#fName1').val());
        console.log($('#lName1').val());

        console.log($('#emailR').valid() +" "+ $('#fName1').valid() +" "+ $('#lName1').valid() +" "+ $('#passwordR').valid())
        if ($('#emailR').valid() && $('#passwordR').valid()) {

            $http.post('http://localhost:8080/EasyShopWayNew/reg', data, config)
                .success(function (data, status, headers, config) {
                    console.log("QWEER" + data.emailErrMsg);
                    $scope.error = data.emailErrMsg;
                    if (data.emailErrMsg == undefined) {
                        $scope.success = "Check your email";
                    }
//                    var esc = $
//                        .Event(
//                            "keydown", {
//                                keyCode: 27
//                            });
//                    $("body").trigger(esc);

                }).error(
                    function (data, status,
                        header, config) {
                        console.log('fail');
                    });
        } else {
            console.log("oq");
        }
    };
}]);

app.controller('DatePickerCtrl', function ($scope) {

    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.dateformat = "MM/dd/yyyy";
    $scope.today();
    $scope.showcalendar = function ($event) {
        $scope.showdp = true;
    };
    $scope.showdp = false;
    $scope.dtmax = new Date();
    dateBirthday = moment($scope.dt).format('YYYY-MM-DD');
});

app.controller('formCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.showInfo = function () {

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        $http.get('http://localhost:8080/EasyShopWayNew/info', config)
            .success(function (data, status, headers,
                config) {
                console.log(data);
                $scope.firstName = data.firstName;
                $scope.lastName = data.lastName;
                $scope.birthday = data.birthday;
                $scope.email = data.email;
                $scope.language = data.language;
            }).error(
                function (data, status, header,
                    config) {
                    console.log('fail');
                });
    }
						}]);

app.controller('DemoCtrl', function ($scope) {
    $scope.user = {
        title: 'Developer',
        email: 'ipsum@lorem.com',
        firstName: '',
        lastName: '',
        company: 'Google',
        address: '1600 Amphitheatre Pkwy',
        city: 'Mountain View',
        state: 'CA',
        biography: 'Loves kittens, snowboarding, and can type at 130 WPM.\n\nAnd rumor has it she bouldered up Castle Craig!',
        postalCode: '94043'
    };

}).config(
    function ($mdThemingProvider) {

        // Configure a dark theme with primary foreground yellow

        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('yellow').dark();

    });

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