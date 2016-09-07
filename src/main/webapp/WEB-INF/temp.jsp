<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Document</title>

        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
		<link rel="stylesheet" href="css/search/search-style.css">
       

        <script src="js/angular.min.js"></script>
        <script src="js/angular-animate.js"></script>
        <script src="js/angular-aria.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>

        <!-- Angular Material Library -->
        <script src="js/angular-material.min.js"></script>

        <script src="js/search/search.js"></script>

    </head>

    <body ng-app="SearchApp" layout="column">
        <jsp:include page="parts/header.jsp"></jsp:include>
        <div flex class="chipsdemoContactChips" ng-controller="ContactChipDemoCtrl as ctrl" layout="column" ng-cloak="">

            <md-content class="md-padding autocomplete" layout="column">
                <h2 class="md-title">Searching asynchronously.</h2>
                <md-contact-chips ng-model="ctrl.asyncContacts" md-contacts="ctrl.delayedQuerySearch($query)" md-contact-name="name" md-contact-image="image" md-contact-email="email" md-require-match="true" md-highlight-flags="i" filter-selected="ctrl.filterSelected" placeholder="To">
                </md-contact-chips>
            </md-content>
        </div>

    </body>

    </html>