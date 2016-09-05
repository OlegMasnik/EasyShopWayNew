/**
 * 
 */

var userHistoryApp = angular.module("MyApp");

userHistoryApp.controller("UserHistoryCtrl", [ '$scope', '$http',
		function($scope, $http) {
			$scope.history = [ {
				date : "11/22/3333",
				list : [ {
					img : '1.jpeg',
					type : 'alcohol',
					name : 'vine'
				}, {
					img : '2.jpeg',
					type : 'alcohol',
					name : 'martini'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : '3.jpeg',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : '3.jpeg',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : '3.jpeg',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : '3.jpeg',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : '3.jpeg',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				} ]
			}, {
				date : "22/22/3333",
				list : [ {
					img : '3.jpeg',
					type : 'alcohol',
					name : 'martini'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				}, {
					img : '4.jpeg',
					type : 'meat',
					name : 'chicken'
				} ]
			} ];
			
		} 
]);