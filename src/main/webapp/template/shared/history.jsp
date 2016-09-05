<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <h3 class="cab-title">History</h3>



    <div flex ng-controller="UserHistoryCtrl">

        <md-card class="product-card" layout="column" ng-repeat="historyItem in history">
            <md-card-title>
                {{historyItem.date}}
            </md-card-title>
            <md-card-content layout="column">
                <md-content class="product-list">
                    <md-list class="md-dense" flex="">
                        <md-list-item class="md-3-line" ng-repeat="item in historyItem.list">
                            <img ng-src="{{item.img}}" class="md-avatar" alt="{{item.name}}">
                            <div class="md-list-item-text" layout="column">
                                <h3>{{ item.name }}</h3>
                                <h4>{{ item.type }}</h4>
                            </div>
                        </md-list-item>
                    </md-list>
                </md-content>
            </md-card-content>
        </md-card>

    </div>