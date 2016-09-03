<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
  <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
      <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
      <html>

      <head>
          <title>
              <c:out value="${user.firstName }"></c:out>
          </title>
          <link rel="stylesheet" href="css/style.css">
          <link rel="stylesheet" href="css/cabinet-style.css">
          <link rel="stylesheet" href="css/angular-material.min.css"> </head>

<<<<<<< HEAD
      <body ng-app="MyApp" layout="column">
          <jsp:include page="../parts/header.jsp"></jsp:include>
          <div layout="row" flex>
              <md-slidenav md-is-locked-open="true" layout="row" flex md-whiteframe="4">
                  <md-content layout="column" flex layout-margin>
                      <div> <img src="images/admin.png" alt="" class="profile-img">
                          <c:out value="${user.firstName }"></c:out>
                      </div>
                      <md-button>Information</md-button>
                      <md-button>Statistic</md-button>
                      <md-button>History</md-button>
                  </md-content>
              </md-slidenav>
              <md-content flex="70">
                  <form name="userForm" ng-controller="formCtrl" data-ng-init="showInfo()">
                      <md-input-container class="md-block" flex-gt-sm="">
                          <label> <span><md-icon md-svg-src="images/icons/person.svg" class="name"></md-icon></span> First Name</label>
                          <input ng-model="firstName"> </md-input-container>
                      <md-input-container class="md-block" flex-gt-sm="">
                          <label> <span><md-icon md-svg-src="images/icons/person.svg" class="name"></md-icon></span> Last Name</label>
                          <input ng-model="lastName"> </md-input-container>
                      <md-input-container class="md-block" flex-gt-sm="">
                          <label> <span><md-icon md-svg-src="images/icons/birth.svg" class="name"></md-icon></span> Birth Day</label>
                          <input ng-model="birthday"> </md-input-container>
                      <md-input-container class="md-block" flex-gt-sm="">
                          <label> <span><md-icon md-svg-src="images/icons/email.svg" class="name"></md-icon></span> Email</label>
                          <input ng-model="email"> </md-input-container>
                      <md-input-container class="md-block" flex-gt-sm="">
                          <label> <span><md-icon md-svg-src="images/icons/lang.svg" class="name"></md-icon></span> Preferred language</label>
                          <input ng-model="language"> </md-input-container>
                    </form>
              </md-content>
<script src="js/jquery.min.js"></script>
<script src='js/angular.min.js'></script>
<script src='js/angular-aria.js'></script>
<script src='js/angular-animate.js'></script>
<script src='js/angular-material.min.js'></script>
<script src='js/app.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-route.min.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js'></script>
<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-114/svg-assets-cache.js'></script>
<script src='https://cdn.gitcdn.link/cdn/angular/bower-material/v1.1.0/angular-material.js'></script>
  </body>
</html>
=======
    <body ng-app="MyApp" layout="column">
        <jsp:include page="../parts/header.jsp"></jsp:include>

        <div layout="row" flex>
            <md-slidenav md-is-locked-open="true" layout="row" flex md-whiteframe="4">
                <md-content layout="column" flex layout-margin>
                    <div>
                        <img src="images/admin.png" alt="" class="profile-img"><c:out value="${user.firstName }"></c:out>
                    </div>
                    <md-button>Edit map</md-button>
                    <md-button>Statistic</md-button>
                    <md-button>Users</md-button>
                    <md-button>Products</md-button>
                </md-content>
            </md-slidenav>
            <md-content flex="70"> Content </md-content>
        </div>
        
        

        <script src="js/jquery.min.js"></script>
        <script src='js/angular.min.js'></script>
        <script src='js/angular-aria.js'></script>
        <script src='js/angular-animate.js'></script>
        <script src='js/angular-material.min.js'></script>
        <script src='js/app.js'></script>


    </body>

    </html>
>>>>>>> master
