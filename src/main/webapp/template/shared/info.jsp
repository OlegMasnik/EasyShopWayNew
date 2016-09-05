<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

    <h3 class="cab-title">Profile</h3>

    <div layout="row" flex="none">

        <md-card flex="50" md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch>
            <md-card-title>
                <md-card-title-text>
                    <div class="cab-category">General information</div>
                </md-card-title-text>
            </md-card-title>
            <md-card-content>
                <form name="userForm" ng-controller="formCtrl" data-ng-init="showInfo()">

                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
					md-svg-src="images/icons/person.svg" class="name cab-icon"></md-icon></span> First Name
                        </label>
                        <input ng-model="firstName"> </md-input-container>
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
					md-svg-src="images/icons/person.svg" class="name cab-icon"></md-icon></span> Last Name
                        </label>
                        <input ng-model="lastName"> </md-input-container>
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
					md-svg-src="images/icons/birth.svg" class="name cab-icon"></md-icon></span> Birth Day
                        </label>
                        <input ng-model="birthday"> </md-input-container>
                    <div class="cab-category">Contact information</div>
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
					md-svg-src="images/icons/email.svg" class="name cab-icon"></md-icon></span> Email
                        </label>
                        <input ng-model="email"> </md-input-container>
                    <div class="cab-category">Localization</div>
                    <md-input-container class="md-block" flex-gt-sm="">
                        <label> <span><md-icon
					md-svg-src="images/icons/lang.svg" class="name cab-icon"></md-icon></span> Preferred language
                        </label>
                        <input ng-model="language"> </md-input-container>
                </form>
            </md-card-content>
            <md-card-actions layout="row" layout-align="end center">
                <md-button>Save</md-button>
                <md-button>Cancel</md-button>
            </md-card-actions>
        </md-card>

        <div flex layout="column">
            <md-card flex>
                <md-card-title>
                    <md-card-title-text>
                        <div class="cab-category">Update your photo</div>
                    </md-card-title-text>
                </md-card-title>
                <md-card-actions layout="row" layout-align="end center">
                    <md-button>Save</md-button>
                    <md-button>Cancel</md-button>
                </md-card-actions>
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