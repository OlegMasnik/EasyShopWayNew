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