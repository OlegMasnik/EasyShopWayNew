/**
 * 
 */

var mailApp = angular.module("MyApp");

mailApp.controller("MailCtrl", function($scope, $http, $mdDialog) {

	var ctrl = this;

	ctrl.showMailDialog = function(item) {

//		ctrl.user = item;
//
		var messData = {
				username : item.fn + " " + item.ln,
				email : item.e
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
					emails : answer.email,
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
	    	showMailDialog1(emails);
	    }
	
	ctrl.showMailDialog1 = function(item) {
//		ctrl.user = item;
		var users = [];
		for (i = 0; i < item.length; i++) {
			var splitedString = item[i].split(" ");
			var user = {
				username : splitedString[1] + " " + splitedString[2],
				email : splitedString[0]
			}
			users.push(user);
		}
//
		var messData;
		if (users.length > 1) {
			messData.username = users[0].username + " and " + (usera.length() - 1) + "more users.";
		} else if (users.length == 1) {
			users[0].username;
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
					userEmails : users,
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
});