var adminApp = angular.module('MyApp');

adminApp.requires.push('md.data.table');

adminApp.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "template/shared/info.jsp",
        controller: 'InfoCtrl'
    }).when("/edit_map", {
        templateUrl: "template/admin/map.html"
    }).when("/products", {
        templateUrl: "template/admin/products.html",
        controller: 'ProdCtrl'
    }).when("/types", {
    	templateUrl: "template/admin/productsType.html",
    	controller: 'TypeCtrl'
    }).when("/users", {
        templateUrl: "template/admin/users.html",
        controller: 'UsersCtrl1'
    }).when("/statistic", {
        templateUrl: "template/shared/statistic.jsp",
        controller: "ChartCtrl",
    }).otherwise({
    	redirectTo: "/"
    });
});

adminApp.controller('ErrorCtrl', function($location) {
	$location.path("/error");
})

adminApp.controller('AdminCtrl', function ($scope, $http, $mdToast) {
	
	$scope.hello = 'Hello world';

});


// ************************************************* InfoCtrl // *************************************************//

adminApp.controller('InfoCtrl', function ($scope, $http) {
    $scope.qwer = "Hello world";
});

// ************************************************* UserCtrl // *************************************************//

adminApp.controller('UsersCtrl1', ['$http', '$scope', '$location', '$mdToast', '$mdDialog', '$translate', function ($http, $scope, $location, $mdToast, $mdDialog, $translate) {

    var original = {};

    $http({
        method: "GET",
        url: "/EasyShopWayNew/users"
    }).then(function mySucces(response) {
        $scope.data = response.data;
        original.users = $scope.data.users;
        original.count = $scope.data.users.length;
        $scope.datatable = angular.copy(original);
        console.log("Get");
        console.log($scope.data);
    }, function myError(response) {
        console.log(response.statusText);
    });
    $scope.smart = true;

    $scope.autocolumn = [{
    	name: "fn",
        display: lang == 'en' ? "First Name" : "Ім'я"
							}, {
        name: "ln",
        display: lang == 'en' ? "Last Name" : 'Прізвище'
							}, {
        name: "e",
        display: lang == 'en' ? "Email" : 'Пошта'
							}];
    $scope.multisearch = Array();
    $scope.multisearch[0] = {
        id: 0,
        column: "",
        ident: "",
    };

    $scope.addRow = function () {
        $scope.multisearch.push({
            id: $scope.multisearch.length,
            column: "",
            ident: ""
        });
    };

    $scope.deleteRow = function (int) {
        $scope.multisearch.splice(int, 1);
        for (var i = 0; i < $scope.multisearch.length; i++) {
            $scope.multisearch[i].id = i;
        }
        $scope.updateDataTable();
    };

    // Configure Table
    $scope.limitOptions = [5, 10, 15];
    $scope.options = {
        pageSelect: true
    };

    $scope.query = {
        order: 'fn',
        limit: 10,
        page: 1
    };

    $scope.updateDataTable = function () {
        var rowdel = Array();
        var filter = false; // set filter false
        for (var j = 0; j < $scope.multisearch.length; j++) {
            if ($scope.multisearch[j].ident && $scope.multisearch[j].column) {
                filter = true; // if a filter exists
            }
        }
        if (filter) { // if a filter is set
            for (var j = 0; j < $scope.multisearch.length; j++) { // for
                if ($scope.multisearch[j].ident) { // check
                    for (var i = original.users.length - 1; i >= 0; i--) { // for
                        var removeRow = true; // take
                        for (var key in original.users[i]) { // for
                            if (original.users[i]
                                .hasOwnProperty(key)) { // check
                                if (key == $scope.multisearch[j].column) { // check
                                    var op = false; // check
                                    for (var key in operators) {
                                        if ($scope.multisearch[j].ident
                                            .split(" ")[0] == key) {
                                            op = true; // operator
                                            // found
                                        }
                                    }
                                    if (op) { // if
                                        var msray = $scope.multisearch[j].ident
                                            .split(" ");
                                        var operator = $scope.multisearch[j].ident
                                            .split(" ")[0];
                                        msray.splice(0,
                                            1); // extract
                                        var comp = msray
                                            .join(" "); // and
                                        if (operators[operator]
                                            (
                                                original.users[i][$scope.multisearch[j].column],
                                                comp)) {
                                            removeRow = false; // check
                                            break;
                                        }
                                    } else {
                                        if (matchRule(
                                                original.users[i][$scope.multisearch[j].column],
                                                $scope.multisearch[j].ident,
                                                $scope.smart)) {
                                            removeRow = false; // check
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (removeRow) {
                            rowdel.push(i);
                        }
                    }
                }
            }
        }
        var dt = angular.copy(original);
        for (var i = 0; i < rowdel.length; i++) {
            dt.users.splice(rowdel[i], 1); // remove
        }
        dt.count = dt.users.length;
        $scope.datatable = angular.copy(dt);
    };
    $scope.active = function (i) {
        console.log(i.e + " " + i.active);

        var data = $.param({
            email: i.e,
            active: !i.active
        });
        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }
        $http
            .post('/EasyShopWayNew/users', data,
                config).success(
                function (data, status, headers,
                    config) {
                    console.log("OK+");
                }).error(
                function (data, status, header,
                    config) {});
        showUserBlockToast(i.e, i.active);
        $http({
            method: "GET",
            url: "/EasyShopWayNew/users"
        }).then(function mySucces(response) {
            $scope.data = response.data;
            original.users = $scope.data.users;
            original.count = $scope.data.users.length;
            $scope.datatable = angular.copy(original);
            console.log("Get");
            console.log($scope.data);
        }, function myError(response) {
            console.log(response.statusText);
        });
        console.log("Finish");
        
        
    };
    
    $scope.sendLoginData = function () {
    	var emails = [];
    	$('input[name="emails"]:checked').each(function() {
    		console.log(this.value);
    		emails.push(this.value);
    	});
    	for (i = 0; i < emails.length; i++) {
    		console.log(emails[i]);
    	}
    	if (emails.length == 0) {
    		showToast($mdToast, $scope, $translate.instant('ZERO_USERS_SELECTED'));
    	} else {
    		$scope.showMailDialog1(emails);
    	}
    }
    
    $scope.showMailDialog1 = function(item) {
//		ctrl.user = item;
		var users = [];
		for (i = 0; i < item.length; i++) {
			var splitedString = item[i].split(" ");
			var user = {
				username : splitedString[1] + " " + splitedString[2],
				email : splitedString[0]
			}
			console.log(user);
			users.push(user);
		}
//
		var messData = {
				
		};
		
		if (users.length > 1) {
			messData.username = users[0].username + " and " + (users.length - 1) + " more users.";
		} else if (users.length == 1) {
			messData.username = users[0].username;
		}
		console.log(users);

		$mdDialog.show({
			controller : DialogCtrl,
			templateUrl : 'mail.tmpl.html',
			parent : angular.element(document.body),
			clickOutsideToClose : true,
			fullscreen : $scope.customFullscreen,
			locals : {
                message: messData
            }
		}).then(function(answer) {
			 var config = {
			            headers: {
			                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
			            }
			 }
			 var data = $.param({
					emails : JSON.stringify(users),
					header : answer.title,
					body : answer.text
			 });
			 console.log(users);
			 console.log(data.emails);
			 
			 console.log(answer.title);			 
			 console.log(answer);
			 console.log("Data " + data);
			        $http
			            .post('/EasyShopWayNew/spammail', data,
			                config).success(
			                function (data, status, headers,
			                    config) {
			                    console.log("Send mail to " + answer.email);
			                }).error(
			                function (data, status, header,
			                    config) {});
		}, function() {
		});
    }
    function DialogCtrl($scope, message) {
		
		console.log(message);
		$scope.message = message;
		
		console.log($scope.message);
		
		$scope.hide = function() {
			$mdDialog.hide();
		};

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.answer = function(answer) {
			console.log(answer);
			$mdDialog.hide(answer);
		};
	}
    
    function showUserBlockToast(email, active){
    	if(active)
    		showToast($mdToast, $scope, "User " + email + " is blocked")
    	else
    		showToast($mdToast, $scope, "User " + email + " is unlocked")
    }
    
    function sendSelectedValues() {
    	var emails = ""; 
    	$('input[name="emails"]:checked').each(function() {
    		   a += $(this).val() + " ";
    		});
    	var messData = {
				username : "Selected users",
				email : "users emails"
		}
		console.log(messData);

		$mdDialog.show({
			controller : DialogCtrl,
			templateUrl : 'mail.tmpl.html',
			parent : angular.element(document.body),
			clickOutsideToClose : true,
			fullscreen : $scope.customFullscreen,
			locals : {
                message: messData
            }
		}).then(function(answer) {
			 var config = {
			            headers: {
			                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
			            }
			 }
			 var data = $.param({
					emails : emails,
					header : answer.title,
					body : answer.text
			 });
			 
			 console.log(answer);
			 console.log("Data " + data);
			        $http
			            .post('/EasyShopWayNew/sendmail', data,
			                config).success(
			                function (data, status, headers,
			                    config) {
			                    console.log("Send mail to " + answer.email);
			                }).error(
			                function (data, status, header,
			                    config) {});
		}, function() {
		});
    }
    
//    $scope.items = [1,2,3,4,5];
//    $scope.selected = [1];
//    $scope.toggle = function (item, list) {
//      var idx = list.indexOf(item);
//      if (idx > -1) {
//        list.splice(idx, 1);
//      }
//      else {
//        list.push(item);
//      }
//    };
//
//    $scope.exists = function (item, list) {
//      return list.indexOf(item) > -1;
//    };
//
//    $scope.isIndeterminate = function() {
//      return ($scope.selected.length !== 0 &&
//          $scope.selected.length !== $scope.items.length);
//    };
//
//    $scope.isChecked = function() {
//      return $scope.selected.length === $scope.items.length;
//    };
//
//    $scope.toggleAll = function() {
//      if ($scope.selected.length === $scope.items.length) {
//        $scope.selected = [];
//      } else if ($scope.selected.length === 0 || $scope.selected.length > 0) {
//        $scope.selected = $scope.items.slice(0);
//      }
//    };
    
    
    $("#select_all").change(function(){  //"select all" change 
        $(".checkbox").prop('checked', $(this).prop("checked")); //change all ".checkbox" checked status
    });


    $('.checkbox').change(function(){ 
        //uncheck "select all", if one of the listed checkbox item is unchecked
    	console.log("inside uncheck 'select all'");
        if(false == $(this).prop("checked")){ //if this item is unchecked
            $("#select_all").prop('checked', false); //change "select all" checked status to false
        }
        
        //check "select all" if all checkbox items are checked
        if ($('.checkbox:checked').length == $('.checkbox').length ){
            $("#select_all").prop('checked', true);
        }
    });
}]);

// ************************************************* ProdCtrl
// *************************************************//

adminApp.controller('ProdCtrl', ['$http', '$scope', '$location', '$mdDialog', '$mdToast', '$route', '$translate', function ($http, $scope, $location, $mdDialog, $mdToast, $route, $translate) {

    $scope.empty = undefined;
  
    $scope.showPromptProd = function (item) {

        console.log($scope.item);

        $mdDialog.show({
                controller: DialogController,
                templateUrl: 'template/admin/edit.prod.tmpl.html',
                parent: angular.element(document.body),
                resolve: {
                    item: function () {
                        return item;
                    }
                },
                clickOutsideToClose: false,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm
                    // breakpoints.
            })
            .then(function (answer) {
            	$route.reload();
            }, function () {
                $scope.status = 'You cancelled the dialog.';
            });
    };

    function DialogController($scope, $mdDialog, item) {
    	
    	  $http({
    			method: "GET",
    			url: "/EasyShopWayNew/type"
    		}).then(function mySucces(response) {
    			$scope.types = response.data.types;
    			console.log("Get");
    			console.log($scope.types);
    			$scope.selectedType = $scope.types[getIdDatatype(item.ptid)];
    		}, function myError(response) {
    			console.log(response.statusText);
    		});

        console.log("item " + item)
        if (item == undefined) {
            $scope.item = {
                id: 0,
                nen: "",
                nuk: "",
                ptid: 0
            };
        } else {
            $scope.item = item;

        }
        
        function getIdDatatype(pid) {
        	for (var i = 0; i < $scope.types.length; i++)
        		if ($scope.types[i].id == pid)
        			return i;
        }

        $scope.hide = function () {
//        	$route.reload();
            $mdDialog.hide();
        };

        $scope.cancel = function () {
//        	$route.reload();
//            $mdDialog.cancel();
        	$scope.hide();
        };

        $scope.answer = function (item, tid) {
            item.ptid = tid.id

            var data = $.param({
                id: item.id,
                nameUk: item.nuk,
                nameEn: item.nen,
                ptid: item.ptid
            });
            console.log("New ITEM");
            console.log(item);
            $http.put('/EasyShopWayNew/products?' + data)
                .success(function (data, status, headers) {
                    console.log('update');
                })
                .error(function (data, status, header, config) {
                    console.log('failed');
                });
            $mdDialog.hide();
//            $route.reload();
        };
    }

    var originalProd = {};

    $http({
        method: "GET",
        url: "/EasyShopWayNew/products"
    }).then(function mySucces(response) {
        $scope.data = response.data;
        originalProd.prods = $scope.data.prods;
        originalProd.count = $scope.data.prods.length;
        $scope.datatable = angular.copy(originalProd);
        console.log("Get");
        console.log($scope.data);
    }, function myError(response) {
        console.log(response.statusText);
    });


    $scope.smartProd = true;

    $scope.autocolumnProd = [
        {
            name: "tnen",
            display: lang == 'en' ? "Product Type" : "Тип продуку"
        },
        {
            name: "tnuk",
            display:  lang == 'en' ? "Product Type" : "Тип продуку"
        },
        {
            name: "nen",
            display: lang == 'en' ? "Name" : "Назва"
		},
        {
            name: "nuk",
            display: lang == 'en' ? "Name" : "Назва"
		}];
    $scope.multisearchProd = Array();
    $scope.multisearchProd[0] = {
        id: 0,
        column: "",
        ident: "",
    };

    $scope.limitOptions = [5, 10, 15];
    $scope.optionsProd = {
        pageSelect: true
    };

    $scope.queryProd = {
        order: 'nen',
        limit: 15,
        page: 1
    };

    $scope.updateDataTableProd = function () {
        var rowdel = Array();
        var filter = false; // set filter false
        for (var j = 0; j < $scope.multisearchProd.length; j++) {
            if ($scope.multisearchProd[j].ident && $scope.multisearchProd[j].column) {
                filter = true; // if a filter exists
            }
        }
        if (filter) { // if a filter is set
            for (var j = 0; j < $scope.multisearchProd.length; j++) { // for
                if ($scope.multisearchProd[j].ident) { // check
                    for (var i = originalProd.prods.length - 1; i >= 0; i--) { // for
                        var removeRow = true; // take
                        for (var key in originalProd.prods[i]) { // for
                            if (originalProd.prods[i]
                                .hasOwnProperty(key)) { // check
                                if (key == $scope.multisearchProd[j].column) { // check
                                    var op = false; // check
                                    for (var key in operators) {
                                        if ($scope.multisearchProd[j].ident
                                            .split(" ")[0] == key) {
                                            op = true; // operator
                                        }
                                    }
                                    if (op) { // if
                                        var msray = $scope.multisearchProd[j].ident
                                            .split(" ");
                                        var operator = $scope.multisearchProd[j].ident
                                            .split(" ")[0];
                                        msray.splice(0,
                                            1); // extract
                                        var comp = msray
                                            .join(" "); // and
                                        if (operators[operator]
                                            (
                                                originalProd.prods[i][$scope.multisearchProd[j].column],
                                                comp)) {
                                            removeRow = false; // check
                                            break;
                                        }
                                    } else {
                                        if (matchRule(
                                                originalProd.prods[i][$scope.multisearchProd[j].column],
                                                $scope.multisearchProd[j].ident,
                                                $scope.smartProd)) {
                                            removeRow = false; // check
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (removeRow) {
                            rowdel.push(i);
                        }
                    }
                }
            }
        }
        var dt = angular.copy(originalProd);
        for (var i = 0; i < rowdel.length; i++) {
            dt.prods.splice(rowdel[i], 1); // remove
        }
        dt.count = dt.prods.length;
        $scope.datatable = angular.copy(dt);
    };

    $scope.deleteProd = function (row) {

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        $http.delete('/EasyShopWayNew/products?id=' + row.id, config)
            .then(
                function (response) {
                    console.log("success delete prod " + row.id);
//                    showToast($mdToast, $scope, "Products with id " + id + " is deleted");
                    showToast($mdToast, $scope, $translate.instant('PRODUCT') + " " + row.nen+ " " + $translate.instant('SUCCESS_DELETE'));
                    $http({
                        method: "GET",
                        url: "/EasyShopWayNew/products"
                    }).then(function mySucces(response) {
                        $scope.data = response.data;
                        originalProd.prods = $scope.data.prods;
                        originalProd.count = $scope.data.prods.length;
                        $scope.datatable = angular.copy(originalProd);
                        console.log("Get");
                        console.log($scope.data);
                    }, function myError(response) {
                        console.log(response.statusText);
                    });
                },
                function (response) {
                    console.log("failed delete prod " + row.id);
                }
            );
    };

    $scope.active = function (i) {
        console.log(i.e + " " + i.active);

        var data = $.param({
            email: i.e,
            active: !i.active
        });
        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }
        $http({
            method: "GET",
            url: "/EasyShopWayNew/products"
        }).then(function mySucces(response) {
            $scope.data = response.data;
            originalProd.prods = $scope.data.prods;
            originalProd.count = $scope.data.prods.length;
            $scope.datatable = angular.copy(originalProd);
            console.log("Get");
            console.log($scope.data);
        }, function myError(response) {
            console.log(response.statusText);
        });
        console.log("Finish");

    };
}]);

adminApp.controller('TypeCtrl', ['$http', '$scope', '$location', '$mdDialog', '$mdToast', '$translate', function ($http, $scope, $location, $mdDialog, $mdToast, $translate) {
	
	$scope.empty = undefined;
	$scope.showPromptType = function (item) {
		
		console.log(" Type " + item);
		
		$mdDialog.show({
			controller: DialogTypeController,
			templateUrl: 'template/admin/edit.type.tmpl.html',
			parent: angular.element(document.body),
			resolve: {
				item: function () {
					return item;
				}
			},
			clickOutsideToClose: true,
			fullscreen: $scope.customFullscreen // Only for -xs, -sm
			// breakpoints.
		})
		.then(function (answer) {
			$http({
				method: "GET",
				url: "/EasyShopWayNew/type"
			}).then(function mySucces(response) {
				$scope.data = response.data;
				originalType.types = $scope.data.types;
				originalType.count = $scope.data.types.length;
				$scope.datatableType = angular.copy(originalType);
				console.log("Get");
				console.log($scope.data);
			}, function myError(response) {
				console.log(response.statusText);
			});
		}, function () {
			$scope.status = 'You cancelled the dialog.';
		});
	};
	
	function DialogTypeController($scope, $mdDialog, item) {
		
		if (item == undefined) {
			$scope.item = {
					id: 0,
					nen: "",
					nuk: "",
					img: ""
			};
		} else {
			$scope.item = item;
		}
		console.log(item);
		
		$scope.hide = function () {
			$mdDialog.hide();
		};
		
		$scope.cancel = function () {
			console.log("cancel");
			$mdDialog.cancel();
		};
		
		$scope.answer = function (item, file_path) {
			
			console.log('file path ' + file_path);
			
			var data = $.param({
				id: item.id,
				nuk: item.nuk,
				nen: item.nen,
				img: item.img
			});
			
			$http.put('/EasyShopWayNew/type?' + data)
			.success(function (data, status, headers) {
				console.log('update');
				
			})
			.error(function (data, status, header, config) {
				console.log('failed');
			});
			$mdDialog.hide();
		};
	}
	
	var originalType = {};
	
	$scope.deleteType = function (row) {
		
		var config = {
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
				}
		}
		
		$http.delete('/EasyShopWayNew/type?id=' + row.id, config)
		.then(
				function (response) {
					console.log("success delete prod " + row.id);
					showToast($mdToast, $scope, $translate.instant('PRODUCT_TYPE') + " " + row.nen + " " + $translate.instant('SUCCESS_DELETE'));
					$http({
						method: "GET",
						url: "/EasyShopWayNew/type"
					}).then(function mySucces(response) {
						$scope.data = response.data;
						originalType.types = $scope.data.types;
						originalType.count = $scope.data.types.length;
						$scope.datatableType = angular.copy(originalType);
						console.log("Get");
						console.log($scope.data);
					}, function myError(response) {
						console.log(response.statusText);
					});
				},
				function (response) {
					showToast($mdToast, $scope, $translate.instant('PRODUCT_TYPE') + " " + row.id + " " + $translate.instant('FAILED_DELETE'));
				}
		);
	};
	
	
	
	$http({
		method: "GET",
		url: "/EasyShopWayNew/type"
	}).then(function mySucces(response) {
		$scope.data = response.data;
		originalType.types = $scope.data.types;
		originalType.count = $scope.data.types.length;
		$scope.datatableType = angular.copy(originalType);
		console.log("Get");
		console.log($scope.data);
	}, function myError(response) {
		console.log(response.statusText);
	});
	$scope.smartType = true;
	
	$scope.autocolumnType = [
	                         {
	                        	 name: "nen",
	                        	 display: lang == 'en' ? "Eglish name" : "Англійська назва"
	                         },
	                         {
	                        	 name: "nuk",
	                        	 display: lang == 'en' ? "Ukraine name" : "Українська назва"
	                         }];
	$scope.multisearchType = Array();
	$scope.multisearchType[0] = {
			id: 0,
			column: "",
			ident: "",
	};
	
	$scope.limitOptionsType = [5, 10, 15];
	$scope.optionsType = {
			pageSelect: true
	};
	
	$scope.queryType = {
			order: 'nen',
			limit: 15,
			page: 1
	};
	
	$scope.updateDataTableType = function () {
		var rowdel = Array();
		var filter = false; // set filter false
		for (var j = 0; j < $scope.multisearchType.length; j++) {
			if ($scope.multisearchType[j].ident && $scope.multisearchType[j].column) {
				filter = true; // if a filter exists
			}
		}
		if (filter) { // if a filter is set
			for (var j = 0; j < $scope.multisearchType.length; j++) { // for
				if ($scope.multisearchType[j].ident) { // check
					for (var i = originalType.types.length - 1; i >= 0; i--) { // for
						var removeRow = true; // take
						for (var key in originalType.types[i]) { // for
							if (originalType.types[i]
							.hasOwnProperty(key)) { // check
								if (key == $scope.multisearchType[j].column) { // check
									var op = false; // check
									for (var key in operators) {
										if ($scope.multisearchType[j].ident
												.split(" ")[0] == key) {
											op = true; // operator
											// found
										}
									}
									if (op) { // if
										var msray = $scope.multisearchType[j].ident
										.split(" ");
										var operator = $scope.multisearchType[j].ident
										.split(" ")[0];
										msray.splice(0,
												1); // extract
										var comp = msray
										.join(" "); // and
										if (operators[operator]
										(
												originalType.types[i][$scope.multisearchType[j].column],
												comp)) {
											removeRow = false; // check
											break;
										}
									} else {
										if (matchRule(
												originalType.types[i][$scope.multisearchType[j].column],
												$scope.multisearchType[j].ident,
												$scope.smartType)) {
											removeRow = false; // check
											break;
										}
									}
								}
							}
						}
						if (removeRow) {
							rowdel.push(i);
						}
					}
				}
			}
		}
		var dt = angular.copy(originalType);
		for (var i = 0; i < rowdel.length; i++) {
			dt.types.splice(rowdel[i], 1); // remove
		}
		dt.count = dt.types.length;
		$scope.datatableType = angular.copy(dt);
	};
	
	$scope.active = function (i) {
		console.log(i.e + " " + i.active);
		
		var data = $.param({
			email: i.e,
			active: !i.active
		});
		var config = {
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
				}
		}
	};
}]);

var operators = {
    '<': function (a, b) {
        return a < b
    },
    '<=': function (a, b) {
        return a <= b
    },
    '!=': function (a, b) {
        return a != b
    },
    '==': function (a, b) {
        return a == b
    },
    '>=': function (a, b) {
        return a >= b
    },
    '>': function (a, b) {
        return a > b
    },
    '%': function (a, b) {
        return a % b
    }
};

function matchRule(str, rule, smart) {
    str = String(str);
    rule = String(rule);
    if (smart) {
        rule = "*" + rule.toLowerCase() + "*";
        str = str.toLowerCase();
    }
    return new RegExp("^" + rule.split("*").join(".*") + "$").test(str);
};


function showToast($mdToast, $scope, msg) {
	var last = {
		bottom : true,
		top : false,
		left : false,
		right : true
	};
	$scope.toastPosition = angular.extend({}, last);

	$scope.getToastPosition = function() {
		return Object.keys($scope.toastPosition).filter(
				function(pos) {
					return $scope.toastPosition[pos];
				}).join(' ');
	};

	$scope.showSimpleToast = function() {
		var pinTo = $scope.getToastPosition();

		$mdToast.show($mdToast.simple().textContent(msg).position(
				pinTo).hideDelay(4000));
	};
	$scope.showSimpleToast();
}

