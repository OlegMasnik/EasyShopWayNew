<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <h3 class="cab-title">Statistic</h3>






    <md-card flex md-theme-watch ng-content="StatisticDateCtrl">
        <md-card-content layout="row">
            <md-datepicker flex md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="startDate" is-open="showdp" ng-min-date="stMinDt" ng-max-date="stMaxDt" id="startDate" md-placeholder="Enter date" name="startDate"></md-datepicker>
            
            <md-datepicker flex md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="endDate" is-open="showdp" ng-min-date="stMinDt" ng-max-date="stMaxDt" id="endDate" md-placeholder="Enter date" name="endDate"></md-datepicker>
        </md-card-content>

    </md-card>


    <div layout="row" flex="none">
        <md-card flex md-theme-watch>
            <md-card-content>
                <div flex="70" id="container"></div>
            </md-card-content>
        </md-card>
    </div>