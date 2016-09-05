<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<h3 class="cab-title">
	Profile <span ng-bind="qwer"></span>
</h3>

<md-content class="cab-content" layout-padding md-whiteframe="4">
<div class="cab-category">General information</div>
<form name="userForm" ng-controller="formCtrl" data-ng-init="showInfo()">
	<md-input-container class="md-block" flex-gt-sm="">
	<label> <span><md-icon
				md-svg-src="images/icons/person.svg" class="name"></md-icon></span> First
		Name
	</label> <input ng-model="firstName"> </md-input-container>
	<md-input-container class="md-block" flex-gt-sm="">
	<label> <span><md-icon
				md-svg-src="images/icons/person.svg" class="name"></md-icon></span> Last
		Name
	</label> <input ng-model="lastName"> </md-input-container>
	<md-input-container class="md-block" flex-gt-sm="">
	<label> <span><md-icon
				md-svg-src="images/icons/birth.svg" class="name"></md-icon></span> Birth
		Day
	</label> <input ng-model="birthday"> </md-input-container>
	<md-input-container class="md-block" flex-gt-sm="">
	<label> <span><md-icon
				md-svg-src="images/icons/email.svg" class="name"></md-icon></span> Email
	</label> <input ng-model="email"> </md-input-container>
	<md-input-container class="md-block" flex-gt-sm="">
	<label> <span><md-icon
				md-svg-src="images/icons/lang.svg" class="name"></md-icon></span> Preferred
		language
	</label> <input ng-model="language"> </md-input-container>
</form>
</md-content>