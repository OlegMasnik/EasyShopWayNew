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
            <link rel="stylesheet" href="css/angular-material.min.css">
        </head>

        <body ng-app="MyApp" layout="column">
            <jsp:include page="../parts/header.jsp"></jsp:include>
            <div layout="row" class="main-page" flex>
                <md-slidenav md-is-locked-open="true" layout="column" flex md-whiteframe="4">
                        <div class="cab-user-logo" layot="row" layout-padding>
                           <a href="">
                            <img src="images/admin.png" alt="" class="profile-img">
                            </a>
                            <div>
                                <c:out value="${user.firstName }"></c:out>
                            </div>
                        </div>
                    <md-content layout="column" flex layout-padding>
                        <md-button>Statistic</md-button>
                        <md-button>History</md-button>
                    </md-content>
                </md-slidenav>
      <body ng-app="MyApp" layout="column">
          <jsp:include page="../parts/header.jsp"></jsp:include>
          <div layout="row" flex>
              <md-slidenav md-is-locked-open="true" layout="row" flex md-whiteframe="4">
                  <md-content layout="column" flex layout-margin>
                      <div> <img src="images/admin.png" alt="" class="profile-img">
                          <c:out value="${user.firstName }"></c:out>
                      </div>
                      <md-button>Information</md-button>
                      <md-button><a href="/EasyShopWayNew/UserStatisticServlet">Statistic</a></md-button>
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
  </body>
</html>

                <div flex="80" class="content" layout-margin layout-padding>

                    <h3 class="cab-title">Profile</h3>

                    <md-content class="content-card" layout-padding md-whiteframe="4">
                        <div class="cab-category">General information</div>
                        <form name="userForm" ng-controller="formCtrl" data-ng-init="showInfo()">
                           
                            <md-input-container class="md-block" flex-gt-sm="">
                                <label> <span><md-icon
							md-svg-src="images/icons/person.svg" class="name"></md-icon></span> First Name
                                </label>
                                <input ng-model="firstName"> </md-input-container>
                            <md-input-container class="md-block" flex-gt-sm="">
                                <label> <span><md-icon
							md-svg-src="images/icons/person.svg" class="name"></md-icon></span> Last Name
                                </label>
                                <input ng-model="lastName"> </md-input-container>
                            <md-input-container class="md-block" flex-gt-sm="">
                                <label> <span><md-icon
							md-svg-src="images/icons/birth.svg" class="name"></md-icon></span> Birth Day
                                </label>
                                <input ng-model="birthday"> </md-input-container>
                            <md-input-container class="md-block" flex-gt-sm="">
                                <label> <span><md-icon
							md-svg-src="images/icons/email.svg" class="name"></md-icon></span> Email
                                </label>
                                <input ng-model="email"> </md-input-container>
                            <md-input-container class="md-block" flex-gt-sm="">
                                <label> <span><md-icon
							md-svg-src="images/icons/lang.svg" class="name"></md-icon></span> Preferred language
                                </label>
                                <input ng-model="language"> </md-input-container>
                        </form>
                    </md-content>
                    
                    
                </div>
            </div>
            <script src="js/jquery.min.js"></script>
            <script src='js/angular.min.js'></script>
            <script src='js/angular-aria.js'></script>
            <script src='js/angular-animate.js'></script>
            <script src='js/angular-material.min.js'></script>
            <script src='js/app.js'></script>
        </body>

        </html>