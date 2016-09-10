var dateBirthday;
var app = angular.module('MyApp', ['ngMaterial', 'ngRoute']);

app.controller('PageRedirectCtrl', function($window) {
	var ctrl = this;
	
	ctrl.goToPage = function(way) {
		$window.location.href = way;	
	}
	
});

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
                    	console.log("Valid data");
                        $http
                            .post(
                                '/EasyShopWayNew/login',
                                data, config)
                            .success(
                                function (data, status,
                                    headers, config) {
                                    if (data.emailErrMsg == undefined) {
                                    	if (data.passwordErrMsg == undefined) {
                                    		$window.location.href = 'cabinet';
                                    	} else {
                                    		$scope.error = data.passwordErrMsg;
                                    	}
                                    } else {
                                        $scope.error = data.emailErrMsg;
                                    }
                                    console
                                        .log("Error: " + data.emailErrMsg + " " + data.passwordErrMsg);
                                }).error(
                                function (data, status,
                                    header, config) {
                                    console.log('fail');
                                });
                    } else {
                        console.log("sory");
                    }
                };
						}]);

app
    .controller(
        'SignUpCtrl', [
						'$scope',
						'$http',
						function ($scope, $http) {
							$scope.sendRegData = function() {
								console.log('hello ' + $scope.email)
								console.log("date " + dateBirthday)
								var data = $.param({
									email : $scope.email,
									password : $scope.password,
									firstName : $scope.firstName,
									lastName : $scope.lastName,
									birthday : dateBirthday
								});
								console.log('Read ' + data);

								var config = {
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
									}
								}
								var firstNameDefined = $('#fName1').val();
								var lastNameDefined = $('#lName1').val();
								console.log('Reg');
								if ($('#emailR').valid() && $('#passwordR').valid()
										&& firstNameDefined != "" && lastNameDefined != "") {
									console.log('Before reg');
									$http
											.post('/EasyShopWayNew/reg', data, config)
											.success(
													function(data, status,
															headers, config) {
														console
																.log("QWEER"
																		+ data.emailErrMsg);
														$scope.error = data.emailErrMsg;
														if (data.emailErrMsg == undefined) {
															$scope.success = "Check your email";
														}
													}).error(
													function(data, status,
															header, config) {
														console.log('fail');
													});
								} else {
									console.log("oq");
									$scope.error = "Please fill all required fields.";
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

app
    .controller(
        'formCtrl', [
						'$scope',
						'$http',
						function ($scope, $http) {
                $scope.showInfo = function () {

                    dateBirthday = moment($scope.birthday).format('YYYY-MM-DD');

                    $scope.languages = [{
                        sName: 'uk',
                        name: "Українська"
                    }, {
                        sName: 'en',
                        name: "English"
                    }];

                    var config = {
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                        }
                    }

                    $http
                        .get(
                            '/EasyShopWayNew/info',
                            config)
                        .success(
                            function (data, status, headers,
                                config) {

                                var date = new Date(data.birthday);
                                var birthday = new Date(
                                    date.getFullYear(),
                                    date.getMonth(),
                                    date.getDate());

                                console.log(data);
                                $scope.firstName = data.firstName;
                                $scope.lastName = data.lastName;
                                $scope.birthday = birthday;
                                $scope.email = data.email;
                                $scope.language = data.language;
                                $scope.img = data.img;
                                $scope.id = data.id;
                            }).error(
                            function (data, status, header,
                                config) {
                                console.log('fail');
                            });
                };

                $scope.saveInfo = function () {

                    console.log($scope.birthday);
                    console.log($scope.birthday.getFullYear());
                    var date = new Date($scope.birthday);
                    
                    var birthday = '' + date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate();

                    console.log(birthday);

                    var data = $.param({
                        firstName: $scope.firstName,
                        lastName: $scope.lastName,
                        birthday: birthday,
                        email: $scope.email,
                        language: $scope.language,
                        changes: true
                    });

                    console.log(data);

                    var config = {
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                        }
                    }

                    // Validate data

                    // End validate

                    $http.post('/EasyShopWayNew/info', data,
                        config).success(function (data, status,
                        headers, config) {

                        console.log(data);

                    }).error(function (data, status,
                        headers, config) {

                        alert("error");

                    });

                }
						}]);

app.controller('changePassCtrl',  ['$scope', '$http', function ($scope, $http) {
	$scope.changePass = function(){

		if ($('#newPass').valid() && $('#oldPass').valid()){
			 var data = $.param({
	             oldPass: $scope.user.oldPass,
	             newPass: $scope.user.newPass
	         });
			 
			 var config = {
			            headers: {
			                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
			            }
			        }

			 
			 $http.post('/EasyShopWayNew/pass', data, config)
		 		.success(function (data, status, headers, config) {
		 			$scope.message = data.msg;
		 		}).error(
	          function (data, status, header, config) {
	        	  $scope.message = 'Changing failed';
	          });
		}	
	}
	
	$scope.cancel = function() {
	    $scope.user = {};
	    
	    var defaultForm = {
		    	   oldPass: "",
		    	   newPass: ""
		    	}
	    $scope.changePassForm.$setPristine();
		$scope.user = angular.copy(defaultForm);
	    $scope.changePassForm.oldPass.$touched = false;
	    $scope.changePassForm.newPass.$touched = false;
	    $scope.message = undefined;
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