<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" isELIgnored="false"%>

    <md-toolbar class="md-hue-2" md-whiteframe="4">
        <div class="md-toolbar-tools">
            <h2>
          <span>Profile</span>
        </h2>
        </div>
    </md-toolbar>

    <div layout="row" flex="none">

        <md-card flex="50" ng-controller="formCtrl" md-theme-watch>
            <md-card-title>
                <md-card-title-text>
                    <div class="cab-category">General information</div>
                </md-card-title-text>
            </md-card-title>
            <md-card-content>
                <form name="userForm" data-ng-init="showInfo()">

                    <md-input-container class="md-block" flex-gt-sm="">
                        <label>
                            <md-icon md-svg-src="images/icons/person.svg" class="name cab-icon"></md-icon> First Name
                        </label>
                        <input ng-model="firstName"> </md-input-container>
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
     md-svg-src="images/icons/person.svg" class="name cab-icon"></md-icon></span> Last Name
                        </label>
                        <input ng-model="lastName"> </md-input-container>
                    <!--
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
     md-svg-src="images/icons/birth.svg" class="name cab-icon"></md-icon></span> Birth Day
                        </label>
                        <input ng-model="birthday"> </md-input-container>
-->
                    <md-datepicker md-open-on-focus="" datepicker-popup="@{{format}}" ng-model="birthday" is-open="showdp" max-date="dtmax" id="birthday" md-placeholder="Enter date" name="birthday"></md-datepicker>
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
     md-svg-src="images/icons/email.svg" class="name cab-icon"></md-icon></span> Email
                        </label>
                        <input ng-model="email" disabled> </md-input-container>
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
     md-svg-src="images/icons/lang.svg" class="name cab-icon"></md-icon></span> Preferred language
                        </label>
                        <md-select placeholder="User language" ng-model="language" md-on-open="loadUsers()" style="min-width: 200px;">
                            <md-option ng-value="language.sName" ng-repeat="language in languages">{{language.name}}</md-option>
                        </md-select>
                    </md-input-container>
                </form>
            </md-card-content>
            <md-card-actions layout="row" layout-align="end center">
                <md-button ng-click="saveInfo()">Save</md-button>
                <md-button ng-click="showInfo()">Cancel</md-button>
            </md-card-actions>
        </md-card>

        <div flex layout="column">
            <md-card flex>
                <form method="POST" action="cabinet/image-upload" enctype="multipart/form-data">
                    <md-card-title>
                        <md-card-title-text>
                            <div class="cab-category">Update your photo</div>
                        </md-card-title-text>
                    </md-card-title>
                    <input type="file" name="file" id="file" />
                    <md-button type="submit">Save</md-button>
                </form>
            </md-card>

            <md-card flex>
                <md-card-title>
                    <md-card-title-text>
                        <div class="cab-category">Change password</div>
                    </md-card-title-text>

                    <div style="heigth: 400px;"></div>


                </md-card-title>
                <md-card-actions layout="row" layout-align="end center">
                    <md-button>Save</md-button>
                    <md-button>Cancel</md-button>
                </md-card-actions>
            </md-card>
        </div>
    </div>