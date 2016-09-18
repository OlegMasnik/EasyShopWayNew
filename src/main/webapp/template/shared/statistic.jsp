<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <!--     <h3 class="cab-title">Statistic</h3> -->


<md-toolbar  class="md-whiteframe-1dp" >
            <div class="md-toolbar-tools">
               <h2><span>{{ 'ADMIN_CABINET_STATISTIC' | translate }}</span></h2>
            </div>
        </md-toolbar>
    <md-card ng-controller="ChartCtrl">
        
        <md-card-content layout="column" layout-gt-sm="row">
            <div layout="column" layout-align="center center" layout-gt-md="row" flex> {{ 'FROM' | translate }}
                <md-datepicker md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="startDate" md-max-date="endDate" id="startDate" md-placeholder="start date" name="startDate"></md-datepicker>
            </div>
            <div layout="column"  layout-align="center center" layout-gt-md="row" flex>{{ 'TO' | translate }}
                <md-datepicker md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="endDate" md-min-date="startDate" md-max-date="date" id="endDate" md-placeholder="end date" name="endDate"></md-datepicker>
            </div>
            <md-button style="background-color : #E91E63; color : #fff;" ng-click="getFoodData()">{{ 'SHOW' | translate }}</md-button>
        </md-card-content>
    </md-card>

    <div layout="column" flex="none">
        <md-card flex md-theme-watch layout="row" flex>
            <md-card-content layout="column" flex>
            	<div layout="row" layout-align="center center">{{noPieDiagram ? ('NO_DIAGRAM' | translate) : "" }}</div>
                <div flex layout="row" layout-align="center center" id="pieContainer"></div>
            </md-card-content>
        </md-card>
        
        <md-card flex md-theme-watch layout="row" flex>
            <md-card-content layout="column" flex>
            	<div layout="row" layout-align="center center">{{noColumnDiagram ? ('NO_DIAGRAM' | translate) : "" }}</div>
                <div flex layout="row" layout-align="center center" id="columnContainer"></div>
            </md-card-content>
        </md-card>
    </div>