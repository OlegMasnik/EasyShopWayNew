<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <h3 class="cab-title">Statistic</h3>

    <div layout="row" flex="none">

        <md-card flex="50" md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch>
            <md-card-content>
                <div flex="70" ng-controller="ChartCtrl" data-ng-init="getFoodData()">
                    <div id="container"></div>
                </div>
            </md-card-content>
        </md-card>
        
    </div>