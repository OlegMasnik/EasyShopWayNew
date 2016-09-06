<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <h3 class="cab-title">Statistic</h3>






    <md-card flex md-theme-watch ng-controller="ChartCtrl">
        <md-card-content layout="row">
            <md-datepicker flex md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="startDate"  ng-min-date="maxDate" ng-max-date="minDate" id="startDate" md-placeholder="start date" name="startDate"></md-datepicker>
            
            <md-datepicker flex md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="endDate"  ng-min-date="maxDate" ng-max-date="minDate" id="endDate" md-placeholder="end date" name="endDate"></md-datepicker>
        <md-button ng-click="getFoodData()">Show</md-button>
        </md-card-content>
    </md-card>


    <div layout="row" flex="none">
        <md-card flex md-theme-watch>
            <md-card-content>
                <div flex="70" id="container"></div>
            </md-card-content>
        </md-card>
    </div>