<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <title>
                EasyShopWay
            </title>
            
            <link rel="stylesheet" href="css/preloader/preloader.css">
            <link rel="stylesheet" href="css/angular-material.min.v1.1.css">
          <link rel="stylesheet" href="css/cabinet-style.css">
          <link rel="stylesheet" href="css/style.css">
          <link rel="shortcut icon" href="favicon.ico">
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        </head>

        <body ng-app="MyApp" layout="column"  md-theme="{{ theme }}">
	<div id="loader-wrapper">
		<div id="loader"></div>
	</div>
	<jsp:include page="../parts/header.jsp"></jsp:include>
            <md-content layout="row" flex>
         
            <md-slidenav class="cabinet-sidenav" layout="column" md-is-locked-open="true"
		layout="column" md-whiteframe="4">
                    <md-content layout="column" flex>
                        <a href="#/">
                            <div class=" md-primary" layot="row" >

<%--                                 <img src='${ user.image == "" ? "images/admin.png" : user.image}' alt="" class="profile-img"> --%>

								<div class="profile-img-container-new">
									<img src='${ user.image == "" ? "images/admin.png" : user.image}' alt="" class="new-profile-img">
								
								</div>
								
<!--                                 <div layout="row" md-whiteframe="3" class="cab-title" layout-align="center center"> -->
<%--                                     <c:out value="${ user.firstName}"></c:out>  --%>
<%--                                     <c:out value="${ user.lastName}"></c:out> --%>
<!--                                 </div> -->
                            </div>
                        </a>
                        <md-button href="#statistic">{{ 'USER_CABINET_STATISTIC' | translate }}</md-button>
                        <md-button href="#history">{{ 'USER_CABINET_HISTORY' | translate }}</md-button>
                    </md-content>
                </md-slidenav>
            <md-content layout="column" class="main-page" flex>
                
                <div class="content" layout="column">
                    <div ng-view onload="getFoodData()"></div>
                    
                </div>
                
            </md-content>
            
            <md-button ng-click="page.goToPage('search')" class="md-fab md-fab-bottom-right" ng-controller="PageRedirectCtrl as page" aria-label="search">
			<md-tooltip md-direction="top">
      		 {{ 'SEARCH' | translate }}   
    		</md-tooltip>
			<md-icon md-svg-src="images/svg/vectorpaint.svg"></md-icon>
   			</md-button>
            
            </md-content>
            <script src="js/jquery-3.1.0.min.js"></script>
            <script src='js/angular.min.js'></script>
            <script src='js/angular-route.min.js'></script>
            <script src='js/angular-aria.js'></script>
            <script src='js/angular-animate.js'></script>
            <script src='js/angular-material.min.js'></script>
            <script src="js/shared/highcharts.js"></script>
			<script src='js/angular-translate.js'></script>
			
            <script src='js/app.js'></script>
            <script src='js/user/app.js'></script>
            
            <script src='js/user/user-history.js'></script>
            <script src="js/shared/statistic.js"></script>
            <script src="js/shared/cabinet.js"></script>
            <script src="js/jquery.validate.min.js"></script>
            
            	<script src="js/preloader/preloader.js"></script>
        </body>

        </html>