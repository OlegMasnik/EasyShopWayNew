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
    <link rel="stylesheet" href="css/user-statistic.css"/>    
   </head>

<body ng-app="MyApp" layout="column"  >
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
        <md-content flex="70"  ng-controller="ChartCtrl" data-ng-init="getFoodData()"> 	
			<div id="container" ></div>
        </md-content>
        
        <script src="js/jquery.min.js"></script>
        <script src='js/angular.min.js'></script>
        <script src='js/angular-aria.js'></script>
        <script src='js/angular-animate.js'></script>
        <script src='js/angular-material.min.js'></script>
	  	<script src="js/highcharts.js"></script>
		<script src="js/exporting.js"></script>
	    <script src='js/app.js'></script>
        <script src='js/statistic.js'></script>
</body>
       

</html>
        
        
        