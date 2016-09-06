<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <html>

    <head>
        <title>Search</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/icon.css">
        <link rel="stylesheet" href="css/search-style.css">
        <link rel="stylesheet" href="css/angular-material.min.css">

    </head>

    <body ng-app="MyApp" layout="column">
        <jsp:include page="parts/header.jsp"></jsp:include>

        <md-content layout="row" ng-controller="ContactChipDemoCtrl as ctrl" class="main-page" flex>
            <md-slidenav class="search-sidenav" md-is-locked-open="true" layout="column" flex md-whiteframe="4">

                <md-content class="md-padding autocomplete" layout="column">
                    <md-contact-chips ng-model="ctrl.contacts" md-contacts="ctrl.querySearch($query)" md-contact-name="name" md-contact-image="image" md-contact-email="email" md-require-match="true" md-highlight-flags="i" filter-selected="ctrl.filterSelected" placeholder="To">
                    </md-contact-chips>

                    <md-list class="fixedRows">
                        <md-subheader class="md-no-sticky">Contacts</md-subheader>
                        <md-list-item class="md-2-line contact-item" ng-repeat="(index, contact) in ctrl.allContacts" ng-if="ctrl.contacts.indexOf(contact) < 0">
                            <img ng-src="{{contact.image}}" class="md-avatar" alt="{{contact.name}}">
                            <div class="md-list-item-text compact">
                                <h3>{{contact.name}}</h3>
                                <p>{{contact.email}}</p>
                            </div>
                        </md-list-item>
                        <md-list-item class="md-2-line contact-item selected" ng-repeat="(index, contact) in ctrl.contacts">
                            <img ng-src="{{contact.image}}" class="md-avatar" alt="{{contact.name}}">
                            <div class="md-list-item-text compact">
                                <h3>{{contact.name}}</h3>
                                <p>{{contact.email}}</p>
                            </div>
                        </md-list-item>
                    </md-list>

                    <br>
                    <h2 class="md-title">Searching asynchronously.</h2>
                    <md-contact-chips ng-model="ctrl.asyncContacts" md-contacts="ctrl.delayedQuerySearch($query)" md-contact-name="name" md-contact-image="image" md-contact-email="email" md-require-match="true" md-highlight-flags="i" filter-selected="ctrl.filterSelected" placeholder="To">
                    </md-contact-chips>
                </md-content>

            </md-slidenav>


        </md-content>
        <script src="js/jquery-3.1.0.min.js"></script>
        <script src='js/angular.min.js'></script>
        <script src='js/angular-aria.js'></script>
        <script src='js/angular-animate.js'></script>
        <script src='js/angular-material.min.js'></script>
        <script src='js/angular-route.min.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>

        <script src='js/app.js'></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/search/search.js"></script>



    </body>
    <script type='text/ng-template' id='info.html'>
        <!-- List -->
        <p>Choose an Item</p>
    </script>

    </html>