<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <h3 class="cab-title">History</h3>



    <div flex>

        <md-card>
            <md-card-title flex="none">
                Title
            </md-card-title>
            <md-card-content layot="column" flex>
                <md-content class="left-column">

                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem magni, cupiditate est atque minus nisi assumenda repellat totam sequi accusantium laboriosam, reprehenderit in consequatur aliquam expedita fuga. Enim perferendis fuga rem, consequatur minus officiis quo voluptate
                </md-content>
            </md-card-content>
        </md-card>

        <md-card class="left-column3" layout="column">
            <md-card-title>
                Title
            </md-card-title>
            <md-card-content layout="column">
                <md-content class="left-column">
                    <md-list class="md-dense" flex="">
                        <md-list-item class="md-3-line" ng-repeat="item in todos">
                            <img ng-src="{{item.face}}?{{$index}}" class="md-avatar" alt="{{item.who}}">
                            <div class="md-list-item-text" layout="column">
                                <h3>{{ item.who }}</h3>
                                <h4>{{ item.what }}</h4>
                                <p>{{ item.notes }}</p>
                            </div>
                        </md-list-item>
                    </md-list>
                </md-content>
            </md-card-content>
        </md-card>

        <md-card class=" left-column2 ">
            <md-card-title>
                Title
            </md-card-title>
            <md-card-content>
                <div layout-margin>

                </div>
            </md-card-content>
        </md-card>

        <md-card class="left-column ">
            <md-card-title>
                Title
            </md-card-title>
            <md-card-content>
                <div layout-margin>

                </div>
            </md-card-content>
        </md-card>

        <md-card class="left-column ">
            <md-card-title>
                Title
            </md-card-title>
            <md-card-content>
                <div layout-margin>

                </div>
            </md-card-content>
        </md-card>

        <md-card class="left-column3 ">
            <md-card-title>
                Title
            </md-card-title>
            <md-card-content>
                <div layout-margin>

                </div>
            </md-card-content>
        </md-card>

        <md-card class="left-column2 ">
            <md-card-title>
                Title
            </md-card-title>
            <md-card-content>
                <div layout-margin>

                </div>
            </md-card-content>
        </md-card>


    </div>