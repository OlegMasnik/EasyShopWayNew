<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <md-toolbar class="md-hue-2" md-whiteframe="4">
            <div class="md-toolbar-tools">

                <h2>
          <span>History</span>
        </h2>


            </div>
        </md-toolbar>



    <div flex ng-controller="UserHistoryCtrl">
        
        <md-card class="product-card" layout="column" ng-repeat="historyItem in history">
        <md-toolbar>
            <md-card-title  layout="column">
            
                <div class="prod-card-date" flex>Date: {{historyItem.products[0].date}}</div>
                <div class="prod-card-time" flex>Time: {{historyItem.products[0].time}}</div>
                
            </md-card-title>
            </md-toolbar>
            <md-divider></md-divider>
            <md-card-content layout="column">
                <md-content class="product-list">
                    <md-list class="md-dense" flex="">
                        <md-list-item class="md-3-line" ng-repeat="item in historyItem.products">
                            <img ng-src="{{item.img}}" class="md-avatar" alt="{{item.name}}">
                            <div class="md-list-item-text" layout="column">
                                <h3>{{ item.name_uk }}</h3>
                                <h4>{{ item.type_uk }}</h4>
                            </div>
                        </md-list-item>
                    </md-list>
                </md-content>
                <md-divider></md-divider>
                <md-card-actions layout="row" layout-align="end center">
                    <md-button class="md-raised" ng-click="sendToMap(historyItem)" style="background-color : #E91E63; color : #fff;">Show on map</md-button>
                </md-card-actions>
            </md-card-content>
        </md-card>

    </div>