<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <html>

    <head>

        <title>EasyShopWay</title>

        <link rel="stylesheet" href="css/preloader/preloader.css">
        <link rel="stylesheet" href="css/icon.css">
        <link rel="stylesheet" href="css/angular-material.min.v1.1.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="shortcut icon" href="images/favicon.ico">



    </head>

    <body ng-app="MyApp" layout="column" ng-controller="AppCtrl" md-theme="{{ theme }}">

        <div id="loader-wrapper">
            <div id="loader"></div>
        </div>

        <jsp:include page="parts/header.jsp"></jsp:include>
        <!-- 	<h2>{{2+2}}</h2> -->

        <!-- 	<h2>{{ 'HEADLINE' | translate }}</h2> -->
        <!-- 	<p>{{ 'INTRO_TEXT' | translate }}</p> -->
        <!-- 	<p>{{pageTitle}}</p> -->
        <!-- 	<i class="material-icons">&#xE84D;</i> -->

        <md-content id="main-content" style="background-image: url('images/index.jpg')" flex>
            <div id="div0" layout="column">
                <div flex layout="column" layout-align="center center">
                    <md-button style="background-color : rgba(233, 30, 99, 0.8); height: 45px; font-size: 30px; color : #fff;" ng-controller="PageRedirectCtrl as page" ng-click="page.goToPage('search')" aria-label="search">{{'CREATE_PRODUCT_LIST_NOW' | translate }}</md-button>
                    <div style="color: black;">{{'OR' | translate }}</div>
                    <md-button class="md-accent" style="height: 45px; font-size: 25px;" id="down_1">{{'SEE_TUTORIAL' | translate }}</md-button>
                </div>
                <div layout="row" class="support-container" layout-align="center center" layout-padding>
                    <div class="support" flex="none" style="color: black;">
                        <div layout="row" layout-align="center center" layout-margin>{{'YOU_CAN_USE_UP' | translate }}<img class="support-img" alt="" src="images/keys/up-button.png">{{'AND_DOWN' | translate }}<img class="support-img down-but" alt="" src="images/keys/up-button.png">{{'KEYS_FO_NAVIGATION' | translate }}</div>
                    </div>
                </div>
            </div>
            <div id="div1" layout="column" md-whiteframe="2" style="background-color: white;">
                <md-button id="up_1" style="color: black;"><i class="material-icons">keyboard_arrow_up</i></md-button>

                <div layout="row" flex style="color: black;">
                    <div layout-padding style="font-size: 35px;"  flex layout="row" layout-align="center center">{{ 'STEP_1' | translate }}</div>
                    
                    <div flex layout="row" layout-align="center center">
                        <div md-whiteframe="4" style="background-color: white;"><img style="width: 100%;" alt="tootorial" src="images/tootorial/1.png"></div>
                    </div>
                </div>

                <md-button id="down_2" style="color: black;"> <i class="material-icons">keyboard_arrow_down</i></md-button>
            </div>
            <div id="div2" layout="column" style="background-color: rgba(255, 255, 255, 0.65);">
                <md-button id="up_2" style="color: black;"> <i class="material-icons">keyboard_arrow_up</i></md-button>

                <div layout="row" flex style="color: black;">
                	<div flex layout="row" layout-align="center center">
                        <div md-whiteframe="4" style="background-color: white;"><img style="width: 100%;" alt="tootorial" src="images/tootorial/2.png"></div>
                    </div>
                    <div layout-padding style="font-size: 35px;"  flex layout="row" layout-align="center center">{{ 'STEP_2' | translate }}</div>

                </div>
                <md-button id="down_3" style="color: black;"> <i class="material-icons">keyboard_arrow_down</i></md-button>
            </div>
            <div id="div3" layout="column" md-whiteframe="2" style="background-color: white;">
                <md-button id="up_3" style="color: black;" style="font-size: 30px;"> <i class="material-icons">keyboard_arrow_up</i></md-button>
                <div layout="row" flex style="color: black;">
                    <div layout-padding style="font-size: 35px;" flex layout="row" layout-align="center center">
                        {{ 'STEP_3' | translate }}
                    </div>
                    <div flex layout="column" layout-align="center center">
                        <div md-whiteframe="4" style="background-color: white;"><img style="width: 100%;" alt="tootorial" src="images/tootorial/3.png"></div>
                    </div>
                   
                </div>
<div layout="column" layout-align="center end">
                    <md-button ng-click="page.goToPage('search')"  class="md-fab" ng-controller="PageRedirectCtrl as page" aria-label="search">
                        <md-tooltip md-direction="top">
                            {{ 'SEARCH' | translate }}
                        </md-tooltip>
                        <md-icon md-svg-src="images/svg/vectorpaint.svg"></md-icon>
                    </md-button>
                    </div>
            </div>
        </md-content>

        <script src="js/jquery-3.1.0.min.js"></script>
        <script src='js/angular.min.js'></script>
        <script src='js/angular-aria.js'></script>
        <script src='js/angular-animate.js'></script>
        <script src='js/angular-material.min.js'></script>
        <script src='js/angular-route.min.js'></script>
        <script src='js/angular-translate.js'></script>
        <script src='js/app.js'></script>
        <script src='js/index.js'></script>

        <script src="js/jquery.validate.min.js"></script>
        <script src="js/preloader/preloader.js"></script>
    </body>
    <script type='text/ng-template' id='info.html'></script>

    </html>