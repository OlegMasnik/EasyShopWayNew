<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>404 - Page not found</title>
         <link rel="stylesheet" href="/EasyShopWayNew/css/preloader/preloader.css">
        <link rel="stylesheet" href="/EasyShopWayNew/css/style.css">
        <link rel="stylesheet" href="/EasyShopWayNew/css/angular-material.min.css">
		<link rel="shortcut icon" href="favicon.ico">

        <style>
            body {
                background-image: url('/EasyShopWayNew/images/404.gif');
                background-repeat: no-repeat;
                background-size: 100%;
            }
            .error-txt {
/*                 text-align: center; */
                font-size: 250%;
                font-weight: 800;
                color: white;
/*                 display: table-cell; */
/*                 vertical-align: middle; */
                height: 200px;
                width: 100%;
                background-color: rgba(0, 0, 0, 0.57);
            }
            
            .error-msg {
/*                 display: table; */
/*                 height: 200px; */
/*                 width: 100%; */
/*                 position: fixed; */
/*                 top: 47%; */
                /*                left: 50%;*/
/*                 background-color: rgba(0, 0, 0, 0.57); */
            }
        </style>

    </head>

    <body ng-app="MyApp" layout="column" md-theme="{{ theme }}">
    <div id="loader-wrapper">
		<div id="loader"></div>
	</div>
        <jsp:include page="parts/header.jsp"></jsp:include>

<!--                <iframe width="100%" height="100%" src="https://www.youtube.com/embed/YZDPCiEKZOI?controls=0&autoplay=1&loop=1&playlist=YZDPCiEKZOI&showinfo=0" frameborder="0" allowfullscreen></iframe> -->
       <div layout="column" flex> 
            <div class="error-msg" flex layout="row" layout-align="center center"> 
                <div class="error-txt" flex layout="row" layout-align="center center">{{ 'ERROR_PAGE_NOT_FOUND' | translate }}</div> 
        
            </div>
        </div>
    <script src="/EasyShopWayNew/js/jquery-3.1.0.min.js"></script>
	<script src='/EasyShopWayNew/js/angular.min.js'></script>
	<script src='/EasyShopWayNew/js/angular-aria.js'></script>
	<script src='/EasyShopWayNew/js/angular-animate.js'></script>
	<script src='/EasyShopWayNew/js/angular-material.min.js'></script>
	<script src='/EasyShopWayNew/js/angular-route.min.js'></script>
	<script src='/EasyShopWayNew/js/angular-translate.js'></script>
	<script src='/EasyShopWayNew/js/app.js'></script>
	<script src="/EasyShopWayNew/js/jquery.validate.min.js"></script>
<script src="/EasyShopWayNew/js/preloader/preloader.js"></script>
    </body>

    </html>