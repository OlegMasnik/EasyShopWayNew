/**
 * 
 */

var userHistoryApp = angular.module("MyApp");

userHistoryApp.controller("UserHistoryCtrl", [ '$scope', '$http',
		function($scope, $http) {
			$scope.history = [ {
				date : "11/22/3333",
				list : [ {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'vine'
				}, {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'martini'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : 'images/admin.png',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				}, {
					img : 'images/admin.png',
					type : 'meat',
					name : 'chicken'
				} ]
			} ];
			
		} 
]);