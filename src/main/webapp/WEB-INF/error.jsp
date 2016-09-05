<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>404 - Page not found</title>
        <link rel="stylesheet" href="/EasyShopWayNew/css/style.css">
        <link rel="stylesheet" href="/EasyShopWayNew/css/angular-material.min.css">

        <style>
            body {
                background-image: url('/EasyShopWayNew/images/404.gif');
                background-repeat: no-repeat;
                background-size: 100%;
            }
            .error-txt {
                text-align: center;
                font-size: 250%;
                font-weight: 800;
                color: white;
                display: table-cell;
                vertical-align: middle;
            }
            
            .error-msg {
                display: table;
                height: 200px;
                width: 100%;
                position: fixed;
                top: 47%;
                /*                left: 50%;*/
                background-color: rgba(0, 0, 0, 0.57);
            }
        </style>

    </head>

    <body ng-app="MyApp" layout="column">
        <jsp:include page="parts/header.jsp"></jsp:include>

        <!--        <iframe width="100%" height="100%" src="https://www.youtube.com/embed/YZDPCiEKZOI?controls=0&autoplay=1&loop=1&playlist=YZDPCiEKZOI&showinfo=0" frameborder="0" allowfullscreen></iframe>-->
        <div class="content" flex>
            <div class="error-msg">
                <div class="error-txt">404 PAGE NOT FOUND</div>
            </div>
        </div>
        
        
    <script src="/EasyShopWayNew/js/jquery-1.4.4.min.js"></script>
	<script src='/EasyShopWayNew/js/angular.min.js'></script>
	<script src='/EasyShopWayNew/js/angular-aria.js'></script>
	<script src='/EasyShopWayNew/js/angular-animate.js'></script>
	<script src='/EasyShopWayNew/js/angular-material.min.js'></script>
	<script src='/EasyShopWayNew/js/angular-route.min.js'></script>
	<script src='/EasyShopWayNew/js/app.js'></script>
	<script src="/EasyShopWayNew/js/jquery.validate.min.js"></script>

    </body>

    </html>