<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <md-toolbar class="md-whiteframe-1dp">
        <div class="md-toolbar-tools">
            <h2>
		<span>{{ 'PROFILE' | translate }}</span>
	</h2>
        </div>
    </md-toolbar>

    <div layout="column" layout-gt-sm="row" flex="none">

        <div flex layout="column">
            <md-card flex ng-controller="formCtrl" md-theme-watch>
                <md-card-title>
                    <md-card-title-text>
                        <div class="cab-category">{{ 'GENERAL_INFORMATION' | translate }}
                        </div>
                    </md-card-title-text>
                </md-card-title>
                <md-card-content flex>
                    <form name="userForm" data-ng-init="showInfo()">
                        <md-input-container class="md-block" flex-gt-sm="">
                            <label>
                                <md-icon md-svg-src="images/icons/person.svg" class="name cab-icon"></md-icon> {{ 'FIRST_NAME' | translate }}
                            </label>
                            <input ng-model="firstName"> </md-input-container>
                        <md-input-container class="md-block" flex-gt-sm="">
                            <label> <span><md-icon
						md-svg-src="images/icons/person.svg" class="name cab-icon"></md-icon></span> {{ 'LAST_NAME' | translate }}
                            </label>
                            <input ng-model="lastName"> </md-input-container>
                        <md-input-container class="md-block" flex-gt-sm="">
                            <label> <span><md-icon
						md-svg-src="images/icons/email.svg" class="name cab-icon"></md-icon></span> {{ 'EMAIL' | translate }}
                            </label>
                            <input ng-model="email" disabled> </md-input-container>
                        <!-- 		<md-input-container class="md-block" flex-gt-sm=""> -->
                        <!-- 			<label> <span> -->
                        <!-- 				<md-icon md-svg-src="images/icons/lang.svg" class="name cab-icon"></md-icon></span> -->
                        <!-- 				{{ 'LANGUAGE' | translate }} -->
                        <!-- 			</label>  -->
                        <!-- 		<md-select  ng-model="language" md-on-open="loadUsers()" style="min-width: 200px;">  -->
                        <!-- 		<md-option ng-value="language.sName" ng-repeat="language in languages">{{language.name}}</md-option> -->
                        <!-- 		</md-select>  -->
                        <!-- 		</md-input-container> -->
                    </form>
                </md-card-content>
                <md-card-actions layout="row" layout-align="end center">

                    <md-button class="md-raised" ng-click="saveInfo()">{{ 'SAVE' | translate }}</md-button>
                    <md-button class="md-raised" ng-click="showInfo()">{{ 'CANCEL' | translate }}</md-button>
                </md-card-actions>
            </md-card>

            <md-card ng-controller="ThemeCtrl">
                <md-card-title>

                    <md-card-title-text>
                        <div class="cab-category">Theme</div>
                    </md-card-title-text>
                </md-card-title>
                <md-card-content>
                    <md-switch ng-model="themeSwitch" ng-change="onChange(themeSwitch)" label="Dark theme: " ng-true-value="'enabled'" ng-false-value="'disabled'" aria-label="Switch 1"> Dark theme: {{ message }} </md-switch>
                </md-card-content>
            </md-card>

        </div>


        <div flex layout="column">
            <form method="POST" id="formUserImg" action="cabinet/image-upload" enctype="multipart/form-data">
                <md-card ng-controller="UploadImageCtrl">
                    <md-card-title>
                        <md-card-title-text>
                            <div class="cab-category">{{ 'UPDATE_YOUR_PHOTO' | translate }}</div>
                        </md-card-title-text>
                    </md-card-title>
                    <md-card-content layout="column">
<!--                         			<md-input-container  flex style=" text-align: center; margin-bottom: 0px;  margin-top: 8px;"> -->
<!--                         				<div class="fileinputs"> -->
<!--                         				<input type="file" -->
<!--                         					accept=".jpg,.png,.gif,.bmp, image/vnd.sealedmedia.softseal-jpg,image/vnd.sealed-png,image/vnd.sealedmedia.softseal-gif,image/bmp" -->
<!--                         					ng-model="file" name="file" id="file" class="md-raised file" required aria-label=" "/> -->
<!--                         					</div> -->
<!--                         			</md-input-container> -->
                        			
<!--                           <choose-file layout="row" flex style="margin: 0; padding: 0;"> -->
<!--                             <input accept=".jpg,.png,.gif,.bmp, image/vnd.sealedmedia.softseal-jpg,image/vnd.sealed-png,image/vnd.sealedmedia.softseal-gif,image/bmp" id="file" type="file" class="ng-hide"> -->
<!--                             <md-input-container flex class="md-block"> -->
<!--                               <input type="text" ng-model="fileName" disabled> -->
<!--                               <div class="hint">Select your file</div> -->
<!--                             </md-input-container> -->
<!--                             <div> -->
<!--                               <md-button id="uploadButton" class="md-fab md-mini"> -->
<!--                                 <md-icon class="material-icons">attach_file</md-icon> -->
<!--                               </md-button> -->
<!--                             </div> -->
<!--                           </choose-file>       -->

                        <aps-upload-file>
                            <input id="file" type="file" class="ng-hide">
                            <md-button id="uploadButton" class="md-raised md-primary" aria-label="attach_file">
                                Choose file </md-button>
                            <md-input-container md-no-float>
                                <input id="textInput" ng-model="fileName" type="text" placeholder="No file chosen" ng-readonly="true">
                            </md-input-container>
                        </aps-upload-file>


                    </md-card-content>
                    <md-card-actions layout="row" layout-align="end center">
                        <md-button class="md-raised"  ng-click="sendImgAj()">{{ 'SAVE' | translate }}</md-button>
                        <md-button class="md-raised" type="reset">{{ 'CANCEL' | translate }}</md-button>
                    </md-card-actions>
                </md-card>
            </form>

            <form id="changePassForm" ng-model="changePassForm" name="changePassForm" action="">
                <md-card ng-controller="changePassCtrl">
                    <md-card-title>
                        <md-card-title-text>
                            <div class="cab-category">{{ 'CHANGE_PASSWORD' | translate }}</div>
                        </md-card-title-text>
                    </md-card-title>
                    
                    <md-card-content>
                    	<c:if test="${not empty user.password }">
                        <md-input-container class="md-block" flex-gt-sm="" style="margin-bottom: 0px;  margin-top: 8px;">
                            <label>{{ 'OLD_PASSWORD' | translate }}</label>
                            <input id="oldPass" required type="password" name="oldPass" ng-model="user.oldPass" minlength="6" maxlength="25" ng-model="oldPass"> </md-input-container>
                        </c:if>
                        <md-input-container class="md-block" flex-gt-sm="" style="margin-bottom: 0px; margin-top: 8px;">
                            <label>
                                {{ 'NEW_PASSWORD' | translate }} </label>
                            <input id="newPass" required type="password" name="newPass" ng-model="user.newPass" minlength="6" maxlength="25" ng-model="newPass"> </md-input-container>
                    </md-card-content>
                    <md-card-actions layout="row" layout-align="end center">
                        <md-button class="md-raised" ng-click="changePass()">{{ 'SAVE' | translate }}</md-button>
                        <md-button class="md-raised" type="reset" ng-click="cancel()">{{ 'CANCEL' | translate }}</md-button>
                    </md-card-actions>
                </md-card>
            </form>

            <!-- 		<md-button class="md-primary" ng-controller="ThemeCtrl" ng-click=changeTheme()> -->
            <!-- 		    Change theme -->
            <!-- 		</md-button> -->

        </div>
    </div>