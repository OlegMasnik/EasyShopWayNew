<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <html>

    <head>
        <title>Search</title>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/icon.css">
        <link rel="stylesheet" href="css/search-style.css">

    </head>

    <body ng-app="SearchApp" layout="column">
        <jsp:include page="parts/header.jsp"></jsp:include>

        <md-content layout="row" ng-controller="ContactChipDemoCtrl as ctrl" class="main-page" flex>
            <md-slidenav class="search-sidenav" md-is-locked-open="true" layout="column" flex md-whiteframe="4">

                <md-content class="md-padding autocomplete" layout="column">
                    <md-contact-chips 
                    ng-model="ctrl.products" 
                    md-contacts="ctrl.querySearch($query)" 
                    md-contact-name="name" 
                    md-contact-image="image" 
                    md-contact-email="id" 
                    md-require-match="true" 
                    md-highlight-flags="i" 
                    filter-selected="ctrl.filterSelected" 
                    placeholder="Enter product name"
                    ng-click="ctrl.getSelectedChip($event)">
                    </md-contact-chips>
                </md-content>
                <md-button ng-click="ctrl.sendOnMap()">Show</md-button>
            </md-slidenav>


        </md-content>
        <script src="js/jquery-3.1.0.min.js"></script>
        <script src='js/angular.min.js'></script>
        <script src='js/angular-aria.js'></script>
        <script src='js/angular-animate.js'></script>
        <script src='js/angular-material.min.js'></script>
        <script src='js/angular-route.min.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>

        <!--         <script src='js/app.js'></script> -->
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/search/search.js"></script>



    </body>

    </html>