<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title><c:out value="${user.firstName }"></c:out></title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/cabinet-style.css">
<link rel="stylesheet" href="css/angular-material.min.css">
</head>

<body ng-app="MyApp" layout="column">
	<jsp:include page="../parts/header.jsp"></jsp:include>
	<div layout="row" flex>
		 <md-content layout="row" class="main-page" flex>
                <md-slidenav md-is-locked-open="true" layout="column" flex md-whiteframe="4">
                    <md-content layout="column" flex>
                        <a href="">
                            <div class="cab-user-logo" layot="row" layout-padding>

                                <img src="images/admin.png" alt="" class="profile-img">

                                <div>
                                    <c:out value="${user.firstName }"></c:out>
                                    <c:out value="${user.lastName }"></c:out>
                                </div>

                            </div>
                        </a>
                        <md-button><a href="${pageContext.request.contextPath}/userStats">Statistic</a></md-button>
                        <md-button>History</md-button>
                    </md-content>
                </md-slidenav>
		
		<div flex="70" ng-controller="ChartCtrl" id="container"></div>
		</md-content>

		<script src="js/jquery-1.4.4.min.js"></script>
		<script src='js/angular.min.js'></script>
		<script src='js/angular-aria.js'></script>
		<script src='js/angular-animate.js'></script>
		<script src='js/angular-material.min.js'></script>
		<script src='js/angular-route.min.js'></script>
		<script src='js/app.js'></script>
		<script src='js/user/app.js'></script>
		<script src="template/shared/highcharts.js"></script>
		<script src="template/shared/exporting.js"></script>
</body>


</html>


