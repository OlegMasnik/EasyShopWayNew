var lang;

(function() {
	lang = $('#lang').val() || 'en';
	console.log(lang);
})();

var app = angular.module('MyApp', [ 'ngMaterial', 'ngRoute',
		'pascalprecht.translate' ]);

app.config(function($mdThemingProvider, $provide) {
	$mdThemingProvider.alwaysWatchTheme(true);
	
	  $mdThemingProvider.theme('default')
	    .primaryPalette('cyan');
	  
	  $mdThemingProvider.theme('dark')
	    .primaryPalette('pink').dark();
	  
	  $provide.value('themeProvider', $mdThemingProvider);
	  
	});

app.config(function($translateProvider) {
	$translateProvider.translations('en', {
		PROFILE:'Profile',
		GENERAL_INFORMATION:'General Information',
		UPDATE_YOUR_PHOTO:'Update Your Photo',
		CHANGE_PASSWORD:'Change Password',
		OLD_PASSWORD:'Old password',
		NEW_PASSWORD:'New password',
		LANGUAGE:'Language',
		
		SELECT_MAP:'Select map',
		SEARCH:'Search',
		NO_MAPS_MATCHING:'No maps matching',
		WERE_FOUND:'were found',
		
		
		
		
		HEADLINE : 'Hello there, This is my awesome app!',
		INTRO_TEXT : 'And it has i18n support!',
		ERROR_PAGE_NOT_FOUND : '404 OH, TROUBLE...',

		REGISTRATION : 'SignUp',
		FIRST_NAME : 'First name',
		LAST_NAME : 'Last name',
		EMAIL : 'Email',
		PASSWORD : 'Password',
		SUBMIT : 'Submit',
		SIGN_UP_WITH_SN : 'Or sign up with',
		LOGIN : 'LogIn',
		LOGIN_WITH_SN : 'Or login with',
		FOGOT_PASS : 'Forgot Password',
		LOGOUT:'LogOut',
		
		ERROR_CONFIRMATION_EMAIL:'Error confirmation email',
		SUCCESS_CONF_EMAIL:'Successful confirmation email',
		
		RESET_PASS:'Reset password',
		NEW_PASS:'New password',
		REPEAT_PASS: 'Repeat password',
		
		INVALID_EMAIL:'Invalid email',
		EMAIL_DOES_NOT_EXIST:"This email does not exist",
		PASSWORDS_DONT_MATCH:'Passwords do not match',
		PASSWORDS_INVALID:'Passwords is invalid',
		CHECK_EMAIL:'Please check your email',
		
		USER_CABINET_STATISTIC:'Statistic',
		USER_CABINET_HISTORY:'History',
		
		ADMIN_CABINET_MAP:'Map',
		ADMIN_CABINET_USERS:'Users',
		ADMIN_CABINET_PRODUCTS:'Products',
		ADMIN_CABINET_STATISTIC:'Statistic',
		
		DATE:'Date',
		TIME:'Time',
		FROM:'From',
		TO:'To',
		SHOW:'Show',
		
		SMART_SEARCH:'Smart search',
		SEARCH:'Search',
		OPTION:'Option',
		COLUMN:'Column',
		
		PRODUCT_TYPES:'Products type',
		PRODUCTS:'Products',
		PRODUCT_TYPE:'Product type',
		PRODUCT:'Product',
		EDIT_TYPE:'Edit type',
		IMAGE:'Image',
		ADD_NEW:'Add new',
		WIDTH:'Width',
		HEIGHT:'Height',
		CHANGE_SIZE:'Change size',
		PAY_DESCK:'Paydesck',
		CUP_BOARD:'Cupboard',
		
		CREATE_NEW_CUPBOARD:'Create new cupboard',
		ENGLISH_DESCRIPTION:'English description',
		UKRAINE_DESCRIPTION:'Ukrainian description',
		BOARD_COUNT:'Board count',
		EDIT_CUPBOARD:'Edit cupboard',
		
		CREATE_NEW_MAP:'Create new map',
		
		ENTER:'Enter',
		WALL:'Wall',
		EDIT:'Edit',
		SAVE:'Save',
		CANCEL:'Cancel',
		DELETE:'Delete',
		CLEAR:'Clear',
		CHOOSE_IMAGE:'Choose image',
		
		ENGLISH_NAME:'English name',
		UKRAINE_NAME:'Ukrainian name',
		
		EMAIL_EXIST: 'This email already exists',
		UNCORRECT_PASSWORD: 'Ucorrect password',
		
		SUCCESS_DELETE: 'successfull deleted',
		FAILED_DELETE: 'fail deleting',
		
		DELETE_MAP: 'Would you like to delete this map?',
		YES: 'Yes',
		No: 'No'
	
	
	}).translations('uk', {
		PROFILE:'Профіль',
		GENERAL_INFORMATION:'Загальна інформація',
		UPDATE_YOUR_PHOTO:'Оновити фото',
		CHANGE_PASSWORD:'Змінити пароль',
		OLD_PASSWORD:'Старий пароль',
		NEW_PASSWORD:'Новий пароль',
		LANGUAGE:'Мова',
		
		SELECT_MAP:'Вибрати магазин',
		SEARCH:'Пошук',
		NO_MAPS_MATCHING:'Співпадіння з',
		WERE_FOUND:'не знайдено',
		
		HEADLINE : 'Доброго вам здоров`ячка, це наша прога!',
		INTRO_TEXT : 'І є підтримка і18н!',
		ERROR_PAGE_NOT_FOUND : '404 ОЙ, ДІДЬКО...',

		REGISTRATION : 'Реєстрація',
		FIRST_NAME : "Ім'я",
		LAST_NAME : 'Прізвище',
		EMAIL : 'Електронна пошта',
		PASSWORD : 'Пароль',
		SUBMIT : 'Відправити',
		SIGN_UP_WITH_SN : 'Або зареєструйся з допомогою',
		LOGIN : 'Вхід',
		LOGIN_WITH_SN : 'Або увійти з допомогою',
		FOGOT_PASS : 'Нагадати пароль',
		LOGOUT:'Вихід',
		
		ERROR_CONFIRMATION_EMAIL:'Помилка підтвердження електронної пошти',
		SUCCESS_CONF_EMAIL:'Успішне підтвердження через електронну пошту',
		
		RESET_PASS:'Відновлення паролю',
		NEW_PASS:'Новий пароль',
		REPEAT_PASS:'Повторіть пароль',
		
		INVALID_EMAIL:'Невірна електрона пошта',
		EMAIL_DOES_NOT_EXIST:'Електронна пошта не зареєстрована в системі',
		PASSWORDS_DONT_MATCH:'Паролі не співпадають',
		PASSWORDS_INVALID:'Некоректний пароль',
		CHECK_EMAIL:'Будь ласка, перевірте свою електронну пошту',
		
		USER_CABINET_STATISTIC:'Статистика',
		USER_CABINET_HISTORY:'Історія',
		
		ADMIN_CABINET_MAP:'Карта',
		ADMIN_CABINET_USERS:'Користувачі',
		ADMIN_CABINET_PRODUCTS:'Товари',
		ADMIN_CABINET_STATISTIC:'Статистика',
		
		DATE:'Дата',
		TIME:'Час',
		FROM:'Від',
		TO:'До',
		SHOW:'Показати',
		
		SMART_SEARCH:'Розумний пошук',
		SEARCH:'Пошук',
		OPTION:'Опції',
		PRODUCT_TYPES:'Типи товарів',
		PRODUCTS:'Товари',
		PRODUCT_TYPE:'Тип товару',
		PRODUCT:'Товар',
		EDIT_TYPE:'Редагувати тип',
		COLUMN:'Колонки',
		
		CREATE_NEW_CUPBOARD:'Створити новий стелаж',
		ENGLISH_DESCRIPTION:'Опис англійською',
		UKRAINE_DESCRIPTION:'Опис українською',
		BOARD_COUNT:'Кількість полиць',
		EDIT_CUPBOARD:'Редагувати стелаж',
		
		CREATE_NEW_MAP:'Створити нову карту',
		
		IMAGE:'Картинка',
		ADD_NEW:'Додати нову',
		WIDTH:'Ширина',
		HEIGHT:'Довжина',
		CHANGE_SIZE:'Змінити розмір',
		PAY_DESCK:'Каса',
		CUP_BOARD:'Стелаж',
		ENTER:'Вхід',
		WALL:'Стіна',
		EDIT:'Змінити',
		SAVE:'Зберегти',
		CANCEL:'Відмінити',
		DELETE:'Видалити',
		CLEAR:'Очистити',
		CHOOSE_IMAGE:'Обрати зображення',
		
		ENGLISH_NAME:'Назва англійською',
		UKRAINE_NAME:'Назва українською',
		
		EMAIL_EXIST: 'Така електрона пошта вже використовуєься',
		UNCORRECT_PASSWORD: 'Невірний пароль',
		
		SUCCESS_DELETE: 'успішно видалений',
		FAILED_DELETE: 'помилка видалення',
		
		DELETE_MAP: 'Would you like to delete this map?',
		YES: 'Так',
		No: 'Ні'
	});
	$translateProvider.preferredLanguage(lang);
});

app.controller('PageRedirectCtrl', function($window) {
	var ctrl = this;

	ctrl.goToPage = function(way) {
		$window.location.href = way;
	}

});

app.controller("ThemeCtrl", function($scope, $rootScope, $http) {
	
	if ($rootScope.theme == "dark") {
		$scope.themeSwitch = "enabled";
		$scope.message = "enabled";
	} else {
		$scope.themeSwitch = "disabled";
		$scope.message = "disabled";
	}
	
	console.log($scope.themeSwitch);
	
	$scope.onChange = function(cbState) {
		console.log(cbState);
		$scope.message = cbState;
		
		var config = {
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
				}
			}
		
		if (cbState == 'enabled') {
			$rootScope.theme = 'dark';
		} else {
			$rootScope.theme = 'default';
		}
		
		var data = $.param({
			theme : $rootScope.theme
		});
		
		$http
		.post('/EasyShopWayNew/info/theme', data,
				config)
		.success(
				function(data, status,
						headers, config) {
					
				}).error(
				function(data, status,
						header, config) {
					console.log('fail to set theme');
				});
		
	};
});

app.controller('AppCtrl', function ($http, $route, $scope, $rootScope, $mdDialog, $mdMedia, $translate, $mdTheming) {
	
//	$scope.theme = 'default';
	
	var config = {
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
			}
		}

		$http.get('/EasyShopWayNew/info/theme', config)
				.success(
				function(data, status, headers,
						config) {

					console.log(data);
					$rootScope.theme = data.theme;
					$scope.theme = data.theme;
										
//					if (data.theme == "dark") {
//						$scope.themeSwitch = true;
//					} else {
//						$scope.themeSwitch = false;
//					}
//					
					
				}).error(
				function(data, status, header,
						config) {
					console.log('fail');
				});
	
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
    $scope.language = lang;
    $scope.en = 'en';
    $scope.uk = 'uk';
    
    $scope.changeLang = function(lang){
    	$http.put('/EasyShopWayNew/home?lang=' + lang)
        .success(function (data, status, headers) {
            $scope.language = lang;
            console.log(lang + "   chage fsfd")
            $translate.use(lang);
            if ((window.location.href).indexOf("statistic") !== -1){
            	$route.reload();
            }
        })
        .error(function (data, status, header, config) {
            console.log('failed');
        });
	}
});

app
		.controller(
				'LoginCtrl',
				[
						'$scope',
						'$http',
						'$window',
						'$mdToast',
						'$translate',
						function($scope, $http, $window, $mdToast, $translate) {
							$scope.sendLoginData = function() {
								console.log('hello' + $scope.email)
								var data = $.param({
									email : $scope.email,
									password : $scope.password
								});
								console.log('Read ' + data);

								var config = {
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
									}
								}
								if ($('#emailL').valid()
										&& $('#passwordL').valid()) {
									console.log("Valid data");
									$http
											.post('/EasyShopWayNew/login',
													data, config)
											.success(
													function(data, status,
															headers, config) {
														
														if (data.emailErrMsg == undefined) {
															if (data.passwordErrMsg == undefined) {
																$window.location.href = 'cabinet';
															} else {
																showToast($mdToast, $scope, $translate.instant('UNCORRECT_PASSWORD'));
															}
														} else {
															showToast($mdToast, $scope, $translate.instant('EMAIL_DOES_NOT_EXIST'));
														}
														console
																.log("Error: "
																		+ data.emailErrMsg
																		+ " "
																		+ data.passwordErrMsg);
													}).error(
													function(data, status,
															header, config) {
														console.log('fail');
													});
								} else {
									console.log("sory");
								}
							};
						} ]);

app
		.controller(
				'SignUpCtrl',
				[
						'$scope',
						'$http',
						'$mdToast',
						'$translate',
						function($scope, $http, $mdToast, $translate) {
							$scope.sendRegData = function() {
								console.log('hello ' + $scope.email)
								var data = $.param({
									email : $scope.email,
									password : $scope.password,
									firstName : $scope.firstName,
									lastName : $scope.lastName,
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
								if ($('#emailR').valid()
										&& $('#passwordR').valid()
										&& firstNameDefined != ""
										&& lastNameDefined != "") {
									$http
											.post('/EasyShopWayNew/reg', data,
													config)
											.success(
													function(data, status,
															headers, config) {
														console
																.log("QWEER"
																		+ data.emailErrMsg);
														if (data.emailSuccMsg != undefined) 
															showToast($mdToast, $scope, $translate.instant('CHECK_EMAIL'));
														if (data.emailErrMsg != undefined) 
																showToast($mdToast, $scope, $translate.instant('EMAIL_EXIST'));
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
						} ]);



app
		.controller(
				'formCtrl',
				[
						'$scope',
						'$http',
						'$mdToast',
						'$route',
						function($scope, $http, $mdToast, $route) {
							
							(function() {
								
								console.log(" First s" + lang);
								lang = $('#lang').val() || 'en';
								console.log(lang);
							})();
							
							$scope.showInfo = function() {

								dateBirthday = moment($scope.birthday).format(
										'YYYY-MM-DD');

								$scope.languages = [ {
									sName : 'uk',
									name : "Українська"
								}, {
									sName : 'en',
									name : "English"
								} ];

								var config = {
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
									}
								}

								$http
										.get('/EasyShopWayNew/info', config)
										.success(
												function(data, status, headers,
														config) {

													var date = new Date(
															data.birthday);
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
												function(data, status, header,
														config) {
													console.log('fail');
												});
							};

							$scope.saveInfo = function() {

								console.log($scope.birthday);
								console.log($scope.birthday.getFullYear());
								var date = new Date($scope.birthday);

								var birthday = '' + date.getFullYear() + '-'
										+ date.getMonth() + '-'
										+ date.getDate();

								console.log(birthday);

								var data = $.param({
									firstName : $scope.firstName,
									lastName : $scope.lastName,
									birthday : birthday,
									email : $scope.email,
									language : $scope.language,
									changes : true
								});

								console.log(data);

								var config = {
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
									}
								}
								$http
										.post('/EasyShopWayNew/info', data,
												config)
										.success(
												function(data, status, headers,
														config) {
													showToast($mdToast, $scope,
															"Your information is success updated");
													console.log(data);

												}).error(
												function(data, status, headers,
														config) {
													showToast($mdToast, $scope,
															data);
												});

							}
							
							console.log("adad");
						} ]);

app
		.controller(
				'changePassCtrl',
				[
						'$scope',
						'$http',
						'$route',
						'$mdToast',
						function($scope, $http, $route, $mdToast) {
							$scope.changePass = function() {

								if ($('#newPass').valid()
										&& $('#oldPass').valid()) {
									var data = $.param({
										oldPass : $scope.user.oldPass,
										newPass : $scope.user.newPass
									});

									var config = {
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
										}
									}

									$http.post('/EasyShopWayNew/pass', data,
											config).success(
											function(data, status, headers,
													config) {
												showToast($mdToast, $scope,
														data.msg);
											}).error(
											function(data, status, header,
													config) {
												showToast($mdToast, $scope,
														"Changing failed");
											});
								}
							}

							$scope.cancel = function() {
								$scope.changePassForm.$setPristine();
								$scope.changePassForm.oldPass.$touched = false;
								$scope.changePassForm.newPass.$touched = false;
								$scope.message = undefined;
							};
						} ]);

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

app.controller('UploadImageCtrl', [ '$scope', '$http', '$mdToast', '$route',
		function($scope, $http, $mdToast, $route) {
			$scope.status = "Validation success";

			$scope.sendImg = function() {

				var file = document.getElementById('file');

				var ext = file.value.match(/\.([^\.]+)$/)[1];
				switch (ext) {
				case 'jpg':
				case 'bmp':
				case 'png':
				case 'gif':
					showToast($mdToast, $scope, "File type is allowed");
					break;
				default:
					console.log('ne ok');
					showToast($mdToast, $scope, "File type not allowed");
					this.value = '';
				}
			}

		} ]);
function showToast($mdToast, $scope, msg) {
	var last = {
		bottom : true,
		top : false,
		left : true,
		right : false
	};
	$scope.toastPosition = angular.extend({}, last);

	$scope.getToastPosition = function() {
		return Object.keys($scope.toastPosition).filter(function(pos) {
			return $scope.toastPosition[pos];
		}).join(' ');
	};

	$scope.showSimpleToast = function() {
		var pinTo = $scope.getToastPosition();

		$mdToast.show($mdToast.simple().textContent(msg).position(pinTo)
				.hideDelay(4000));
	};
	$scope.showSimpleToast();
}