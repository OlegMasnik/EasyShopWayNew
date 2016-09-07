(function () {
    'use strict';
    var dateBirthday;

    var app = angular
        .module('SearchApp', ['ngMaterial', 'ngMessages']);
    app.controller('ContactChipDemoCtrl', DemoCtrl);

    function DemoCtrl($q, $timeout, $http) {
        var self = this;
        var pendingSearch, cancelSearch = angular.noop;
        var cachedQuery, lastSearch;

        self.allProducts = loadProducts();
        self.products = [];

        self.filterSelected = true;

        self.querySearch = querySearch;

        self.sendOnMap = sendOnMap;

        function sendOnMap() {
            console.log(self.products);
        }

        /**
         * Search for contacts; use a random delay to simulate a remote call
         */
        function querySearch(criteria) {
            cachedQuery = cachedQuery || criteria;
            return cachedQuery ? self.allProducts.filter(createFilterFor(cachedQuery)) : [];
        }

        function refreshDebounce() {
            lastSearch = 0;
            pendingSearch = null;
            cancelSearch = angular.noop;
        }

        /**
         * Debounce if querying faster than 300ms
         */
        function debounceSearch() {
            var now = new Date().getMilliseconds();
            lastSearch = lastSearch || now;

            return ((now - lastSearch) < 300);
        }

        /**
         * Create filter function for a query string
         */
        function createFilterFor(query) {
            var lowercaseQuery = angular.lowercase(query);

            return function filterFn(product) {
                return (product._lowername.indexOf(lowercaseQuery) != -1);;
            };

        }

        function loadProducts() {
            var products = [
        'Marina Augustine',
        'Oddr Sarno',
        'Nick Giannopoulos',
        'Narayana Garner',
        'Anita Gros',
        'Megan Smith',
        'Tsvetko Metzger',
        'Hector Simek',
        'Some-guy withalongalastaname'
      ];

            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http
                .get(
                    '/EasyShopWayNew/searchProducts',
                    config)
                .success(
                    function (data, status, headers,
                        config) {
                        console.log(data);
                        products = data;
                    }).error(
                    function (data, status, header, config) {
                        console.log('no products');
                    });

            return products.map(function (p, index) {
                var cParts = p.split(' ');
                var product = {
                    name: p,
                    email: cParts[0][0].toLowerCase() + '.' + cParts[1].toLowerCase() + '@example.com',
                    image: 'images/admin.png'
                };
                product._lowername = product.name.toLowerCase();
                return product;
            });
        }
    }

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

    app
        .controller(
            'LoginCtrl', [
            						'$scope',
            						'$http',
            						'$window',
            						function ($scope, $http, $window) {
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
                            $http
                                .post(
                                    'http://localhost:8080/EasyShopWayNew/login',
                                    data, config)
                                .success(
                                    function (data, status,
                                        headers, config) {
                                        if (data.emailErrMsg == undefined) {
                                            $window.location.href = 'cabinet';
                                        } else {
                                            $scope.error = data.emailErrMsg;
                                        }
                                        console
                                            .log(data.emailErrMsg);
                                    }).error(
                                    function (data, status,
                                        header, config) {
                                        console.log('fail');
                                    });
                        } else {
                            cosole.log("sory");
                        }
                    };
            						}]);

    app
        .controller(
            'SignUpCtrl', [
            						'$scope',
            						'$http',
            						function ($scope, $http) {

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

                        console.log($('#emailR').valid() + " " + $('#fName1').valid() + " " + $('#lName1').valid() + " " + $('#passwordR').valid())
                        if ($('#emailR').valid() && $('#passwordR').valid() && $('#fName1').valid() && $('#lName1').valid()) {

                            $http
                                .post(
                                    'http://localhost:8080/EasyShopWayNew/reg',
                                    data, config)
                                .success(
                                    function (data, status,
                                        headers, config) {
                                        console
                                            .log("QWEER" + data.emailErrMsg);
                                        $scope.error = data.emailErrMsg;
                                        if (data.emailErrMsg == undefined) {
                                            $scope.success = "Check your email";
                                        }
                                        // var esc = $
                                        // .Event(
                                        // "keydown", {
                                        // keyCode: 27
                                        // });
                                        // $("body").trigger(esc);

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

})();