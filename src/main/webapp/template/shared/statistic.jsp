<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <!--     <h3 class="cab-title">Statistic</h3> -->


<md-toolbar class="md-hue-2" md-whiteframe="4" >
            <div class="md-toolbar-tools">
               <h2><span>Statistic</span></h2>
            </div>
        </md-toolbar>
    <md-card ng-controller="ChartCtrl">
        
        <md-card-content layout="row">
<!--   		<div flex>
            From: 
                <md-datepicker md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="startDate" md-max-date="sdEndDate" id="startDate" md-placeholder="start date" name="startDate"></md-datepicker>
            </div>
            <div flex>
            ({{minDate}}) ({{maxDate}}) Date validity {{checkValidity}}
            </div>
            <div flex>
            To: 
                <md-datepicker md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="endDate" md-min-date="startDate" md-max-date="endMaxDate" id="endDate" md-placeholder="end date" name="endDate"></md-datepicker>
            </div> -->
            <div flex>
       			<h4>Date-picker with min date ({{minDate | date}}) and max date ({{maxDate | date}}) >  Date validity : {{checkValidity()}}</h4>
        		<md-datepicker ng-model="myDate" md-placeholder="Enter date" md-min-date="minDate" md-max-date=""></md-datepicker>
      		</div>
            <md-button style="background-color : #E91E63; color : #fff;" ng-click="getFoodData()">Show</md-button>
        </md-card-content>
    </md-card>


    <div layout="row" flex="none">
        <md-card flex md-theme-watch>
            <md-card-content>
                <div flex="70" id="container"></div>
            </md-card-content>
        </md-card>
    </div>