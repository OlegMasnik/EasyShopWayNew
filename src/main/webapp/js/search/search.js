'use strict';

var dateBirthday;
var serApp = angular
    .module('SearchApp', ['ngMaterial', 'ngMessages']);
serApp.controller('ProductListCtrl', DemoCtrl);



function DemoCtrl($timeout, $q, $log, $scope, $http) {
    var self = this;

    //    Map part

    self.simulateMapQuery = false;
    self.isDisabledMap = false;

    self.maps;

    self.querySearchMap = querySearchMap;
    self.selectedMapItemChange = selectedMapItemChange;
    self.searchMapTextChange = searchTextChange;

    //		Products part
    self.simulateQuery = false;
    self.isDisabled = true;

    self.states;

    self.querySearch = querySearch;
    self.selectedItemChange = selectedItemChange;
    self.searchTextChange = searchTextChange;
    self.sendOnMap = sendOnMap;


    self.toggleChecked = toggleChecked;
    $scope.items = [];

    //    Script part

    var config = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
        }
    }

    $http.get('/EasyShopWayNew/searchMaps', config).success(
        function (data, status, headers, config) {
            console.log(data);
            self.maps = loadAllMaps(data);
            console.log(self.maps);
        }).error(function (data, status, header, config) {
        console.log(data);
        console.log('no products');
    });

    // ****************************** 
    // Internal methods 
    // ****************************** 


    function toggleChecked(item) {
        console.log("Remove " + item);

        remove(item);
    }

    function sendOnMap() {
        console.log($scope.items);

        var ids = [];

        for (var i = 0; i < $scope.items.length; i++) {
            ids[i] = ($scope.items[i].value);
        }

        console.log(ids);
        var send = $.param({
            data: JSON.stringify({
                productIds: ids
            })
        });

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        $http
            .post('/EasyShopWayNew/saveProductList', send,
                config).success(
                function (send, status, headers, config) {
                    console.log("Save to db");
                }).error(
                function (send, status, header,
                    config) {});
    }

    /** 
     * Search for states... use $timeout to simulate 
     * remote dataservice call. 
     */
    function querySearch(query) {
        var results = query ? self.states
            .filter(createFilterFor(query)) : self.states,
            deferred;
        if (self.simulateQuery) {
            deferred = $q.defer();
            $timeout(function () {
                deferred.resolve(results);
            }, Math.random() * 1000, false);
            return deferred.promise;
        } else {
            return results;
        }
    }

    function querySearchMap(query) {
        var results = query ? self.maps
            .filter(createFilterFor(query)) : self.maps,
            deferred;
        if (self.simulateQueryMap) {
            deferred = $q.defer();
            $timeout(function () {
                deferred.resolve(results);
            }, Math.random() * 1000, false);
            return deferred.promise;
        } else {
            return results;
        }
    }

    function searchTextChange(text) {
        $log.info('Text changed to ' + text);
    }

    function selectedItemChange(item, text) {
        if (item != undefined) {
            if (find(item) == -1) {
                $scope.items.push(item);
                //	             self.searchText = undefined; 
            }
        }
        console.log(JSON.stringify(item));
    }

    function selectedMapItemChange(item) {

        var data = $.param({
            mapId: item.value
        });

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        $http.get('/EasyShopWayNew/searchProducts', config).success(
            function (data, status, headers, config) {
                console.log(data);
                self.states = loadAll(data);
                console.log(self.states);
            }).error(function (data, status, header, config) {
            console.log(data);
            console.log('no products');
        });


        console.log(JSON.stringify(item));
    }

    function find(value) {
        console.log($scope.items);
        for (var i = 0; i < $scope.items.length; i++) {
            console.log($scope.items[i].value + " " + value.value);
            if ($scope.items[i].value == value.value) {
                return i;
            }
        }
        return -1;
    }

    function remove(value) {
        console.log($scope.items);
        console.log(value);

        for (var i = 0; i < $scope.items.length; i++) {
            console.log($scope.items[i].value + " " + value.value);
            if ($scope.items[i].value == value.value) {
                $scope.items.splice(i, 1);
                console.log($scope.items);
                return;
            }
        }
    }

    /** 
     * Build `states` list of key/value pairs 
     */
    function loadAll(data) {

        console.log(data.products);
        var products = data.products;

        return products.map(function (product) {
            return {
                value: product.id,
                name_uk: product.name_uk,
                name_en: product.name_en,
                display: product.name_en,
                img: product.img,
                coordinates: product.coordinates
            };
        });
    }
    
    function loadAllMaps(data) {

        console.log(data.maps);
        var maps = data.maps;

        return maps.map(function (it) {
            return {
                value: it.id,
                name_uk: it.name_uk,
                name_en: it.name_en,
                display: it.name_en,
            };
        });
    }

    /** 
     * Create filter function for a query string 
     */
    function createFilterFor(query) {
        var lowercaseQuery = angular.lowercase(query);

        return function filterFn(state) {
            return (state.display.indexOf(lowercaseQuery) === 0);
        };
    }
}


serApp.controller('AppCtrl', function ($scope, $mdDialog, $mdMedia) {
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
        }).then(
            function (answer) {
                $scope.status = 'You said the information was "' + answer + '".';
            },
            function () {
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
        }).then(
            function (answer) {
                $scope.status = 'You said the information was "' + answer + '".';
            },
            function () {
                $scope.status = 'You cancelled the dialog.';
            });
        $scope.$watch(function () {
            return $mdMedia('xs') || $mdMedia('sm');
        }, function (wantsFullScreen) {
            $scope.customFullscreen = (wantsFullScreen === true);
        });
    };
});

serApp
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
                                function (data,
                                    status,
                                    headers,
                                    config) {
                                    if (data.emailErrMsg == undefined) {
                                        $window.location.href = 'cabinet';
                                    } else {
                                        $scope.error = data.emailErrMsg;
                                    }
                                    console
                                        .log(data.emailErrMsg);
                                })
                            .error(
                                function (data,
                                    status,
                                    header,
                                    config) {
                                    console
                                        .log('fail');
                                });
                    } else {
                        cosole.log("sory");
                    }
                };
								}]);

serApp
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
                                function (data,
                                    status,
                                    headers,
                                    config) {
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

                                })
                            .error(
                                function (data,
                                    status,
                                    header,
                                    config) {
                                    console
                                        .log('fail');
                                });
                    } else {
                        console.log("oq");
                    }
                };
								}]);

serApp.controller('DatePickerCtrl', function ($scope) {

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