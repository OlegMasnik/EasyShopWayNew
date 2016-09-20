<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

    
        <script src='/EasyShopWayNew/js/moment.js'></script>

        <md-toolbar id="header" class="md-hue-2">
            <div class="md-toolbar-tools" layout="row" ng-controller="AppCtrl" md-whiteframe="4">

                <div layout="row">
                    <a flex class="logo" href="/EasyShopWayNew/home"><img alt="EasyShopWay" src="/EasyShopWayNew/images/logo/logo_big.gif"></a>
                </div>
                <input type="text" ng-show="false" id="lang" value="${lang}">
                <span flex></span>



                <md-input-container>
                    <md-select ng-model="language" aria-label=" ">
                        <md-option ng-model="uk" value="uk" ng-click='changeLang(uk)'>Українська</md-option>
                        <md-option ng-model="en" value="en" ng-click='changeLang(en)'>English</md-option>
                    </md-select>
                </md-input-container>
                <c:choose>
                    <c:when test="${user == null}">
                        <md-button class="md-raised" ng-click="showLogInForm($event, $scope, $mdDialog)">
                            {{ 'LOGIN' | translate }} </md-button>
                        <md-button class="md-raised" ng-click="showRegistrationInFrom($event, $scope, $mdDialog)">
                            {{ 'REGISTRATION' | translate }} </md-button>
                    </c:when>
                    <c:otherwise>
                        <md-menu md-position-mode="target-right target">
<!--                         	<div class="profile-img-container"> -->
<%--                             <img ng-click="$mdOpenMenu($event)" ng-src='${ user.image == "" ? "images/admin.png" : user.image}' class="head-profile-img" alt="Contact"> --%>
<!--                             </div> -->

							<div class="profile-img-container" ng-click="$mdOpenMenu($event)" style="background-image: url(${ user.image == '' ? 'images/admin.png' : user.image});">
                            </div>
                            <md-menu-content width="3">
                                <md-menu-item>
                                    <md-button ng-click="do()" href="/EasyShopWayNew/cabinet#/">
<%--                                         <c:out value="${user.firstName } ${user.lastName }"></c:out> --%>
									{{ 'PROFILE' | translate }}
                                    </md-button>
                                </md-menu-item>
                                <md-menu-divider></md-menu-divider>
                                <md-menu-item>

                                    <md-button ng-click="do()" href="/EasyShopWayNew/logout">
                                        {{ 'LOGOUT' | translate }} </md-button>
                                </md-menu-item>
                            </md-menu-content>
                        </md-menu>
                    </c:otherwise>
                </c:choose>
            </div>
        </md-toolbar>