<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

        <html>

        <head>
            <title>Search</title>
            <link rel="stylesheet" href="css/preloader/preloader.css">
            <link rel="stylesheet" href="css/angular-material.min.v1.1.css">
            <link rel="stylesheet" href="css/style.css">
            <link rel="stylesheet" href="css/icon.css">
            <link rel="stylesheet" href="css/search-style.css">
            <link rel="shortcut icon" href="favicon.ico">

        </head>

        <body ng-app="SearchApp" layout="column" md-theme="${ user.theme == null ? "default" : user.theme }">

	<div id="loader-wrapper">
		<div id="loader"></div>
	</div>

	<script src='js/moment.js'></script>
            
            <jsp:include page="parts/header.jsp"></jsp:include>

            <md-content layout="row" class="main-page" flex ng-controller="ProductListCtrl as ctrl">
                <md-slidenav class="search-sidenav" md-is-locked-open="true" layout="column" md-whiteframe="4">

                    <div class="autocompletedemoBasicUsage" layout="column" flex ng-cloak="">

                        
                        <md-content class="md-padding" layout="column" flex>
                            <md-input-container class="md-block" flex="none">
                                <label>Map</label>
                                <md-select ng-change="ctrl.click(map)" md-on-open="ctrl.open()" ng-click="" placeholder='{{ "SELECT_MAP" | translate}}' ng-model="maps" style="min-width: 200px;">
                                    <md-option ng-value="map.value" ng-repeat="map in ctrl.maps">{{map.display}}</md-option>
                                </md-select>
                            </md-input-container>
                            <form ng-submit="$event.preventDefault()">
                                <md-autocomplete id="auto" ng-model="searchModel" ng-disabled="ctrl.isDisabled" md-no-cache="ctrl.noCache" md-selected-item="ctrl.selectedItem" md-search-text-change="ctrl.searchTextChange(ctrl.searchText)" md-search-text="ctrl.searchText" md-selected-item-change="ctrl.selectedItemChange(item, ctrl.searchText)" md-items="item in ctrl.querySearch(ctrl.searchText)" md-item-text="item.display" md-min-length="0" placeholder='{{ "WHAT_ARE_YOU_LOOKING" | translate}}'>
                                    <md-item-template>
                                        <span md-highlight-text="ctrl.searchText" md-highlight-flags="^i">{{item.display}}</span>
                                    </md-item-template>
                                    <md-not-found> No products matching "{{ctrl.searchText}}" were found. </md-not-found>
                                </md-autocomplete>
                                <br>
                            </form>
                            <md-content flex>
                                <div class="chips" flex="none" ng-repeat="item in items" ng-style='{"background-color": item.color}'>
                                    <div class="chip-precontext" layout="row">
                                        <span class="chip-context" flex> <span>
						{{item.display}}</span>
                                        </span> <span> <a style="cursor: pointer;"
					class="md-fab md-mini close-but"
					ng-click="ctrl.toggleChecked(item)">&#10006;</a>
				</span>
                                    </div>
                                </div>
                            </md-content>
                        </md-content>
                    </div>
                    <md-button ng-click="ctrl.sendOnMap()" style="background-color : #E91E63; color : #fff;">{{ 'SHOW' | translate }}</md-button>
                </md-slidenav>


                <md-content layout-margin flex>


                        <md-card-content layout="row">
                            <div flex="70" layout="row" flex-offset="15">
                                <md-button class="md-fab md-mini md-primary" ng-click="decScale()" ng-disabled="config.cellSize < 6">
                                    <i class="material-icons">remove</i></md-button>

                                <md-slider flex="" min="5" max="51" step="1" ng-model="config.cellSize" ng-change="scale()" aria-label=" "></md-slider>

                                <md-button class="md-fab md-mini md-primary" ng-click="incScale()" ng-disabled="config.cellSize > 50">
                                    <i class="material-icons">add_circle_outline</i></md-button>

                            </div>

                            <a layout="row" layout-align="start start" class="md-button md-primary md-raised" id="download_1">{{ 'DOWNLOAD' | translate }}</a>
                        </md-card-content>
                    </md-card>
                    
                    <md-card layout="column">
                    	<div layout="row"  layout-align="center center">
    						{{ 'LEGEND' | translate }}
    					</div>
                    	<div layout="row"  layout-align="center center">
	    					<ul id="legend">
	    						<li><img height="20" alt="cupboard" src="images/cupboard/central pat_ capboard.gif"> - {{ 'CUP_BOARD' | translate }}</li>
	    						<li><canvas width="20" height="20" style="background-color: #555"></canvas> - {{ 'WALL' | translate }}</li>
	    						<li><img height="20" alt="cupboard" src="images/elements/enter.svg"> - {{ 'ENTER' | translate }}</li>
	    						<li><img height="20" alt="cupboard" src="images/paydesk/payDesk_90x90.gif"> - {{ 'PAY_DESCK' | translate }}</li>
	    						<li><img height="20" alt="cupboard" src="images/elements/target.png"> - {{ 'TARGET' | translate }}</li>
	    						<li><img height="20" alt="cupboard" src="images/elements/target_off.png"> - {{ 'TARGET_NOT_VISITED' | translate }}</li>
	    						<li><img height="20" alt="cupboard" src="images/path.png"> - {{ 'PATH' | translate }}</li>
	    					</ul>
    					</div>
    				</md-card>

                    <md-card flex="none">
                        <md-card-content>
                            <md-content>
                                <canvas id="canvas"></canvas>
                            </md-content>
                        </md-card-content>
                    </md-card>


                </md-content>

            </md-content>
            <script src="js/jquery-3.1.0.min.js"></script>
            <script src='js/angular.min.js'></script>
            <script src='js/angular-aria.js'></script>
            <script src='js/angular-animate.js'></script>
            <script src='js/angular-material.min.js'></script>
            <script src='js/angular-route.min.js'></script>
            <script src="js/angular-messages.min.js"></script>
            <script src="js/angular-translate.js"></script>

            <script src='js/app.js'></script>
            <script src='js/user/map.js'></script>
            <script src="js/jquery.validate.min.js"></script>

            <script>
                function downloadCanvas(link, canvasId, filename) {
                    link.href = document.getElementById(canvasId).toDataURL();
                    link.download = filename;
                }

                document.getElementById('download_1').addEventListener('click', function () {
                    var date = new Date();
                    var name = "map_" + date.getFullYear() + date.getMonth() +
                        date.getDay() + "-" + date.getHours() + "-" + date.getMinutes() +
                        "-" + date.getSeconds() + ".png";
                    downloadCanvas(this, 'canvas', name);
                }, false);
            </script>
			
			<script src="js/preloader/preloader.js"></script>

        </body>

        </html>