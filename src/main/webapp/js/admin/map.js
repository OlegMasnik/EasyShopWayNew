// ************************************************* MapCtrl ************************************************* 

angular.module('MyApp').controller('MapCtrl', function ($mdToast, $route, $scope, $http, $mdDialog, $translate) {

    var mapId;
    
    function downloadCanvas(link, canvasId, filename) {
        link.href = document.getElementById(canvasId).toDataURL();
        link.download = filename;
    }
    
    document.getElementById('download_1').addEventListener('click', function() {
    	var date = new Date ();
    	var name = "map_" + date.getFullYear() + date.getMonth() + 
    				date.getDay() + "-" +  date.getHours() +"-" + date.getMinutes() + 
    				"-" + date.getSeconds() + ".png";
        downloadCanvas(this, 'canvas', name);
    }, false);
    
    document.getElementById('download').addEventListener('click', function() {
    	var date = new Date ();
    	var name = "map_" + date.getFullYear() + date.getMonth() + 
    				date.getDay() + "-" +  date.getHours() +"-" + date.getMinutes() + 
    				"-" + date.getSeconds() + ".png";
        downloadCanvas(this, 'canvas', name);
    }, false);

    $scope.downloadImg = function() {
	};
    $scope.map = undefined;

    $scope.newMap = {};
    
    $scope.cupboards = undefined;

    $scope.config = {
        width: 0,
        height: 0,
        cellSize: 20,
        borderWidth: 1,
        cellColor: '#eee',
        borderColor: '#bbb',
        wallColor: '#555',
        playerColor: '#252',
        targetColor: '#522',
        searchColor: '#ccc',
        pathColor: '#999'
    };

    start();

    function start() {
        $http({
            method: "GET",
            url: "/EasyShopWayNew/edit_map?type=mapsName"
        }).then(function mySucces(response) {
            $scope.mapsName = response.data;
            mapId = $scope.mapsName[0].id;
            //console.log("Get mapsName");
            //console.log($scope.mapsName);
            if (typeof (mapId) != 'undefined') {
                $scope.someModel = mapId;
                $scope.getMapByid(mapId);
            }
        }, function myError(response) {
            //console.log(response.statusText);
        });
        //console.log("ROOT SCOPE");
    };

    $scope.clickOnSelect = function (mId) {
        $http({
            method: "GET",
            url: "/EasyShopWayNew/edit_map?type=setMapId&id=" + mId
        }).then(function mySucces(response) {
            $route.reload();
        }, function myError(response) {
            //console.log(response.statusText);
        });
    }

    $scope.getMapByid = function (m) {
        game = undefined;
        $scope.enter = undefined;
        $scope.paydesks = undefined;
        $scope.walls = undefined;
        $scope.m = m;
        mapId = m || mapId;
        //console.log(mapId);
        $http({
            method: "GET",
            url: "/EasyShopWayNew/edit_map?type=map&id=" + mapId
        }).then(function mySucces(response) {
            $scope.map = response.data.map;
            //console.log($scope.map);
            //console.log(response.data);

            $scope.enter = response.data.enters[0];
            $scope.walls = response.data.walls;
            $scope.paydesks = response.data.paydesks;
            $scope.cupboards = response.data.cupboards;
            //console.log($scope.cupboards);

            $scope.config.width = $scope.map.weight;
            $scope.config.height = $scope.map.height;
            //console.log("size = " + $scope.config.width + $scope.config.height);
            //console.log("size = " + $scope.config.enter);

            $scope.openMap();
            //console.log($scope.config);
        }, function myError(response) {
            //console.log(response.statusText);
        });
    }

    var game;
    var tCell;
    var arrayCupBoard = new Array();
    var buffPath;
    var startCupBoard;
    var endCupBoard;
    var type;
    $scope.typeValue = undefined;
    
    
    $scope.incScale = function(){
    	$scope.config.cellSize++;
    	updateMap(game); 
    };
    $scope.decScale = function(){
    	$scope.config.cellSize--;
    	updateMap(game);
    }
    $scope.scale = function(){
    	updateMap(game);
    };
    
    function updateMap(oldMap){
//    	game = new Game(document.querySelector('canvas'), $scope.config);
    	game.cellSize = $scope.config.cellSize;
    	game.cellSpace = game.cellSize + game.borderWidth;
    	game.canvas.width = game.width * game.cellSpace + game.borderWidth;
        game.canvas.height = game.height * game.cellSpace + game.borderWidth;
    	game.draw();
//    	game.notVisit = oldMap.notVisit;
//    	game.targetColors = oldMap.targetColors;
//    	game.cupBoard = oldMap.cupBoard;
//    	$scope.openMap();
    }

    var Game = function (canvas, conf) {
        game = this;
        this.enter = $scope.enter;
        this.canvas = canvas;
        this.width = conf.width;
        this.height = conf.height;
        this.cellSize = conf.cellSize;
        this.borderWidth = conf.borderWidth;
        this.cellColor = conf.cellColor;
        this.borderColor = conf.borderColor;
        this.wallColor = conf.wallColor;
        this.playerColor = conf.playerColor;
        this.searchColor = conf.searchColor;
        this.pathColor = conf.pathColor;
        this.ctx = this.canvas.getContext('2d');
        this.cellSpace = this.cellSize + this.borderWidth;
        this.canvas.width = this.width * this.cellSpace + this.borderWidth;
        this.canvas.height = this.height * this.cellSpace + this.borderWidth;
        this.cupBoard = new Map(this.width * this.height);
        initCupBoard($scope.cupboards);

        this.paint = {
            value: false,
            active: false
        };

        this.step = function (fn) {
            fn();
            game.draw();
        }

        this.getCellColor = function (cell) {
            switch (cell) {
            case this.enter:
                return '#252';
                break;
            }
            if ($scope.paydesks.indexOf(cell) != -1) return '#ff870d';
            if ($scope.walls.indexOf(cell) != -1) return '#555';
            if (this.cupBoard.map[cell]) return '#038ef0';
            return '#eee';
        };


        this.draw = function () {
            game.ctx.fillStyle = '#bbb';
            game.ctx.fillRect(0, 0, game.canvas.width, game.canvas.height);
            var cell = 0;
            for (var y = 0; y < game.height; y++) {
                for (var x = 0; x < game.width; x++) {
                    game.ctx.fillStyle = game.getCellColor(cell);
//                    if (game.ctx.fillStyle == waycolor && targetsCopy != undefined && !targetsCopy[cell]) {
//                        game.ctx.fillStyle = '#eee';
//                        game.ctx.fillRect(x * game.cellSpace + game.borderWidth, y * game.cellSpace + game.borderWidth,
//                            game.cellSize, game.cellSize);
//
//                        game.ctx.beginPath();
//                        game.ctx.arc(x * game.cellSpace + game.borderWidth + game.cellSize / 2,
//                            y * game.cellSpace + game.borderWidth + game.cellSize / 2, game.cellSize / 4, 0, 2 * Math.PI);
//                    } else {
//                        game.ctx.fillRect(x * game.cellSpace + game.borderWidth,
//                            y * game.cellSpace + game.borderWidth,
//                            game.cellSize, game.cellSize);
//                    }
//                    cell++;
                    game.ctx.fillStyle = game.getCellColor(cell);
                    game.ctx.fillRect(x * game.cellSpace + game.borderWidth,
                        y * game.cellSpace + game.borderWidth,
                        game.cellSize, game.cellSize);
                    cell++;
                }
            }
        };

        this.getMouseCell = function (event) {
            var rect = this.canvas.getBoundingClientRect(),
                xCord = event.x - rect.left,
                yCord = event.y - rect.top;
            if (xCord % this.cellSpace < this.borderWidth || yCord % this.cellSpace < this.borderWidth) {
                return false;
            }
            var x = Math.floor(xCord / this.cellSpace),
                y = Math.floor(yCord / this.cellSpace),
                cell = x + y * this.width;
            return cell;
        };
        this.mouseDown = function (e) {
            //console.log("in mouse down")
            var cell = game.getMouseCell(e);
            if (cell !== false) {
                if (e.button === 0) {
                    game.paint.active = true;
                    switch (type) {
                    case 'enter':
                        game.enter = cell;
                        game.draw();
                        break;
                    case 'payDesk':
                        game.paint.value = !($scope.paydesks.indexOf(cell) != -1);
                        if ($scope.paydesks.indexOf(cell) == -1) {
                            $scope.paydesks.add(cell);
                        } else {
                            $scope.paydesks.removeUndefined(cell);
                        }
                        game.draw();
                        break;
                    case 'cupBoard':
                        game.paint.value = true;
                        if(checkCell(cell)){
                        	console.log(game.cupBoard[cell])
                        	console.log("Cell "+ cell);
	                        if (startCupBoard == undefined) {
	                            startCupBoard = cell;
	                        } else {
	                            endCupBoard = cell;
	                            if (Math.abs(startCupBoard - endCupBoard) < $scope.config.width - 1) {
	                                if (checkRange(startCupBoard, endCupBoard, 1)) {
	                                    var arr = range(startCupBoard, endCupBoard, 1);
	                                    arr.map(function (e, i) {
	                                        game.cupBoard.map[e] = true;
	                                    });
	                                    arrayCupBoard.push(arr);
	                                    $scope.createCupBoard(arr, undefined);
	                                }
	                            } else if ((endCupBoard % $scope.config.width) == (startCupBoard % $scope.config.width)) {
	                                //console.log("in vertical: start=" + startCupBoard + " end=" + endCupBoard);
	                                if (checkRange(startCupBoard, endCupBoard, $scope.config.width)) {
	                                    var arr = range(startCupBoard, endCupBoard, $scope.config.width);
	                                    arr.map(function (e, i) {
		                                        game.cupBoard.map[e] = true;
	                                    });
	                                    arrayCupBoard.push(arr);
	                                    $scope.createCupBoard(arr, undefined);
	                                } else {
	                                    game.cupBoard.map[startCupBoard] = false;
	                                    game.cupBoard.map[endCupBoard] = false;
	                                }
	                            } else {
	                                game.cupBoard.map[startCupBoard] = false;
	                                game.cupBoard.map[endCupBoard] = false;
	                            }
	                            startCupBoard = undefined, endCupBoard = undefined;
	                        }
                        }
                        break;
                    case 'wall':
                        game.paint.value = !($scope.walls.indexOf(cell) != -1);
                        if ($scope.walls.indexOf(cell) == -1) {
                            $scope.walls.add(cell);
                        } else {
                            $scope.walls.removeUndefined(cell);
                        }
                        break;
                    case 'edit':
                        console.log("CELL #" + cell);
                        console.log($scope.cupboards);
                        for (var q = 0; q < $scope.cupboards.length; q++) {
                            for (var w = 0; w < $scope.cupboards[q].values.length; w++) {
                                if (cell == $scope.cupboards[q].values[w]) {
                                    //console.log("You click on: ");
                                    //console.log($scope.cupboards[q]);
                                    $scope.openCupBoard($scope.cupboards[q]);
                                }
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    game.draw();
                } 
            }
        };
        this.mouseMove = function (e) {
            var cell = game.getMouseCell(e);
            if (game.paint.active) {
                if (cell !== false) {
                    if (e.button === 0) {
                        switch (type) {
                        case 'wall':
                            if (!(game.cupBoard.map[cell]) && !($scope.paydesks.indexOf(cell) != -1) && !(game.enter == cell))
                                if (game.paint.value)
                                    $scope.walls.add(cell);
                                else
                                    $scope.walls.removeUndefined(cell);
                            break;
                        case 'payDesk':
                            if (!(game.cupBoard.map[cell]) && !($scope.walls.indexOf(cell) != -1) && !(game.enter == cell))
                                if (game.paint.value)
                                    $scope.paydesks.add(cell);
                                else
                                    $scope.paydesks.removeUndefined(cell);
                            break;
                        default:
                            break;
                        }
                    }
                    game.draw();
                }

            }
        };
        this.mouseUp = function () {
            game.paint.active = false;
        };
	    this.registerEvents = function () {
	        this.canvas.addEventListener('mousedown', this.mouseDown);
	        this.canvas.addEventListener('mousemove', this.mouseMove);
	        document.addEventListener('mouseup', this.mouseUp);
	        this.canvas.addEventListener('contextmenu', function (e) {
	            e.preventDefault();
	        });
	    };

        this.registerEvents();
        this.draw();
    };

    var Map = function (length) {
        this.map = new Array(length);
        for (var cell = 0; cell < length; cell++) {
            this.map[cell] = false;
        }
    };

    $scope.onClick = function () {
        clear();
        game.draw();
        game.player.cell = game.player.findStart();
        //console.log("On click start " + game.player.cell);
        if (!($scope.walls.indexOf(tCell) != -1)) game.player.moveTo();
    }

    $scope.radioOnClick = function (value) {
        type = value;
    }
    
    $scope.radioOnClick = function (value, toastMsg) {
        type = value;
        showToast(toastMsg);
    }

    $scope.openMap = function () {
        if (typeof ($scope.map) == 'undefined') {
            showToast('Please, first selected map');
        } else {
            if ($scope.map.weight != $scope.config.width || $scope.map.height != $scope.config.height) {
                var data = $.param({
                    type: 'changeSize',
                    mapId: $scope.map.id,
                    name_en: $scope.map.name_en,
                    name_uk: $scope.map.name_uk,
                    weight: $scope.config.width,
                    height: $scope.config.height
                });

                var config = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }

                $http.post('/EasyShopWayNew/edit_map', data, config)
                    .success(function (response, status, headers) {
                        $route.reload();
                    })
                    .error(function (data, status, header, config) {
                    });
            }
            game = new Game(document.querySelector('canvas'), $scope.config);
            game.draw();
            console.log(game);
        }
    }

    function wait(ms) {
        var d = new Date();
        var d2 = null;
        do {
            d2 = new Date();
        } while (d2 - d < ms);
    }

    function clear() {
        game.way = new Map($scope.config.width * $scope.config.height);
        game.targets = new Map($scope.config.width * $scope.config.height);
    }

    function initCupBoard(obj) {
        game.cupBoard = new Map(game.width * game.height);
        $scope.cupboards = obj;
        for (var i = 0; i < obj.length; i++) {
            //console.log(obj[i]);
            obj[i].values.map(function (e, i) {
                game.cupBoard.map[e] = true;
            });
        }
    }

    Array.prototype.removeUndefined = function (value) {
        this[this.indexOf(value)] = undefined;
        this.sort();
        while (typeof (this[this.length - 1]) == "undefined") {
            this.pop();
        }
    }
    Array.prototype.add = function (value) {
        if (this.indexOf(value) == -1) {
            this.push(value);
        }
    }

    function checkRange(s, e, d) {
        var arr = range(s, e, d);
        for (var k = 1; k < arr.length - 1; k++) {
            var i = arr[k];
            if ($scope.walls.indexOf(i) != -1 || $scope.paydesks.indexOf(i) != -1 || game.cupBoard.map[i] == true || game.enter == i) {
                return false;
            }
        }
        return true;
    }

    function range(start, stop, step) {
        if (start > stop) var v = start,
            start = stop,
            stop = v;
        var array = new Array();
        for (var i = start; i <= stop; i += step)
            array.push(i);
        return array;
    }


    function checkCell(i) {
        return $scope.paydesks.indexOf(i) == -1 && $scope.walls.indexOf(i) == -1 && game.enter != i && !game.cupBoard.map[i];
    }

    $scope.openCupBoard = function (cupBoard) {
        //console.log('before open');
        $mdDialog.show({
                controller: EditCupboardCtrl,
                templateUrl: 'template/admin/edit.cupBoard.tmpl.html',
                parent: angular.element(document.body),
                resolve: {
                    item: function () {
                        return cupBoard;
                    }
                },
                fullscreen: $scope.customFullscreen // Only for -xs, -sm
            })
            .then(function (answer) {
                //console.log(answer);

            }, function () {
                //console.log("cancel");
            });
    };




    function EditCupboardCtrl($scope, $mdDialog, item) {

        //console.log("item ");
        //console.log(item);
        $scope.item = item;

        $http({
            method: "GET",
            url: "/EasyShopWayNew/edit_products?type=getCupboardsProducts&cupboardId=" + item.id
        }).then(function mySucces(response) {
            //console.log("current Prods")
            $scope.currentProducts = response.data;
            $scope.cupboardCells = new Array(item.board_count * item.values.length);
            if (typeof ($scope.currentProducts) != "undefined") {
                for (var i = 0; i < $scope.currentProducts.length; i++) {
                    for (var j = 0; j < $scope.currentProducts[i].place.length; j++) {
                        $scope.cupboardCells[$scope.currentProducts[i].place[j]] = $scope.currentProducts[i];
                        //console.log("prod i " + i);
                        //console.log($scope.cupboardCells);
                    }
                }
            }
            //console.log($scope.currentProducts);
        }, function myError(response) {});


        $http({
            method: "GET",
            url: "/EasyShopWayNew/edit_products?type=getAllProducts"
        }).then(function mySucces(response) {
            //console.log("all Prods")
            $scope.allProducts = response.data;
            //console.log(response);
        }, function myError(response) {

        });

        //console.log("cupBoarards");
        //console.log($scope.cupboardCells);
        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.answer = function () {
        	while($scope.cupboardCells.indexOf("") != -1)
        		$scope.cupboardCells.removeUndefined("");
            $scope.cupboardCells.map(function (e, i) {
                if (typeof (e) == "string")
                    $scope.cupboardCells[i] = JSON.parse(e)
            })
            //console.log($scope.cupboardCells);
            $scope.sendCupboardData();
            $mdDialog.hide();
        };

        $scope.sendCupboardData = function () {
            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
            var data = [];
            $scope.cupboardCells.map(function (e, i) {
                if (typeof (e) != "undefined" || e != null)
                    data.push({
                        prodId: e.prodId,
                        cupboardId: item.id,
                        place: i
                    })
            });

            var sendData = $.param({
                type: 'setProducts',
                data: JSON.stringify(data)
            });
            //console.log($scope.cupboardCells);
            //console.log(data);
            //console.log(sendData);
            $http.post('/EasyShopWayNew/edit_products', sendData, config)
                .success(function (data, status, headers) {
                    //console.log("success send products")
                }).error(function (data, status, header, config) {
                    //console.log('failed  send products');
                });
        }

        $scope.deleteCupboard = function (item) {
            //console.log('delete cupboard');
            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.delete('/EasyShopWayNew/edit_map?type=cupboard&id=' + item.id + '&mapId=' + mapId, config)
                .then(
                    function (response) {
                        initCupBoard(response.data);
                        //console.log($scope.cupboards);
                        game.draw();
                    },
                    function (response) {
                        //console.log('delete failed')
                    }
                );
            $mdDialog.hide();
        }
    }

    $scope.createCupBoard = function (values, b_count) {

        //console.log('before create');
        $mdDialog.show({
                controller: CreateDialogController,
                templateUrl: 'template/admin/create.cupBoard.tmpl.html',
                parent: angular.element(document.body),
                resolve: {
                    values: function () {
                        return values;
                    },
                    b_count: function () {
                        return b_count;
                    }
                },
                fullscreen: $scope.customFullscreen // Only for -xs, -sm
            })
            .then(function (answer) {

            }, function () {
            		
            });
    };

    function CreateDialogController($scope, $mdDialog, values, b_count) {

        $scope.values = values;

        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function (values) {
            //console.log("Cancel");
            values.map(function (e, i) {
                game.cupBoard.map[e] = false;
            });
            game.draw();
            //console.log(values);
            $mdDialog.cancel();
        };

        $scope.answer = function (values) {
            if (typeof ($scope.b_count) == 'undefined') {
                values.map(function (e, i) {
                    game.cupBoard.map[e] = false;
                });
                game.draw();
            } else {

                var sendData = $.param({
                    type: 'cupboard',
                    data: JSON.stringify({
                        values: values,
                        bCount: $scope.b_count,
                        name_en: $scope.name_en,
                        name_uk: $scope.name_uk,
                        mapId: mapId
                    })
                });
                //console.log('SEND CUPBOARD');
                //console.log(sendData);
                var config = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }
                $http.post('/EasyShopWayNew/edit_map', sendData, config)
                    .success(function (data, status, headers) {
                        console.log('update cupboard');
                        $scope.cupboards = data;
                        console.log($scope.cupboards);
                        initCupBoard($scope.cupboards);
                        game.draw();
                    })
                    .error(function (data, status, header, config) {
                        //console.log('failed');
                    });
            }
            $mdDialog.hide();
        };
    }

    $scope.createMap = function () {

        $mdDialog.show({
                controller: CreateMapDialogController,
                templateUrl: 'template/admin/create.map.tmpl.html',
                parent: angular.element(document.body),
                fullscreen: $scope.customFullscreen
            })
            .then(function (answer) {
            }, function () {
            });
    };

    function CreateMapDialogController($scope, $mdDialog) {

        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function (values) {
            //console.log("Cancel");
            $mdDialog.cancel();
        };

        $scope.createNewMap = function () {
            var data = $.param({
                type: 'createMap',
                name_en: $scope.name_en,
                name_uk: $scope.name_uk,
                weight: $scope.weight,
                height: $scope.height
            });
s
            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/EasyShopWayNew/edit_map', data, config)
                .success(function (data, status, headers) {
                    //                    mapId = data;
                    //                    //console.log("create new map with id " + mapId);
                    //                    start();
                    
                    if (data.msg.includes("success")){
                    	$route.reload();
                    	console.log(data.msg);
                    	var msg = data.msg;
                    	showToast($mdToast, $scope, msg);
                    }else{
                    	console.log(data.msg);
                    	showToast($mdToast, $scope, msg);
                    }
                    
                })
                .error(function (data, status, header, config) {
                    //console.log('failed');
                });
            $scope.hide();
        }
    }

    $scope.saveMap = function () {
        if (typeof ($scope.map) == 'undefined') {
            //console.log('map undefined')
            showToast('Please, firstl select map');
        } else {
            //console.log($scope.map);
            //console.log($scope.walls);
            //console.log($scope.paydesks);
            //console.log(game.enter);

            var sendData = $.param({
                type: 'saveMap',
                data: JSON.stringify({
                    mapId: $scope.map.id,
                    walls: $scope.walls,
                    paydesks: $scope.paydesks,
                    enters: [game.enter]
                })
            });

            //console.log(sendData);

            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/EasyShopWayNew/edit_map', sendData, config)
                .success(function (data, status, headers) {
                    if (data == 0) {
                        //console.log('success save map');
                    } else {
                        //console.log('failed save map');
                    }

                })
                .error(function (data, status, header, config) {
                    //console.log('failed save map');
                });
        }
    }

    $scope.clearMap = function () {
        if (typeof ($scope.map) == 'undefined') {
            showToast('Please, first select map')
        } else {
            var data = $.param({
                type: 'clearMap',
                mapId: mapId,
            });
            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
            $http.post('/EasyShopWayNew/edit_map', data, config)
                .success(function (data, status, headers) {
                    $route.reload();
                    //                	mapId = data;
                    //                	$scope.getMapByid(mapId);
                    //                    //console.log(data);
                })
                .error(function (data, status, header, config) {
                	
                });
        }
    }
    $scope.deleteMap = function (ev) {
        //console.log(typeof ($scope.map) == 'undefined');
        if (typeof ($scope.map) == 'undefined') {
            showToast('Please, first select map');

        } else {
            var confirm = $mdDialog.confirm()
                .title($translate.instant('DELETE MAP'))
                .targetEvent(ev)
                .ok($translate.instant('YES'))
                .cancel($translate.instant('NO'));

            $mdDialog.show(confirm).then(function () {
                var config = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }
                $http.delete('/EasyShopWayNew/edit_map?type=map&id=' + $scope.map.id, config).success(function (data, status, headers) {
                        $route.reload();
                    })
                    .error(function (data, status, header, config) {
                    });
            }, function () {
            });
        }

    }

    $scope.showConfirmDelete = function (ev) {

    };

    function showToast(msg) {
        var last = {
            bottom: true,
            top: false,
            left: false,
            right: true
        };
        $scope.toastPosition = angular.extend({}, last);

        $scope.getToastPosition = function () {
            return Object.keys($scope.toastPosition)
                .filter(function (pos) {
                    return $scope.toastPosition[pos];
                })
                .join(' ');
        };

        $scope.showSimpleToast = function () {
            var pinTo = $scope.getToastPosition();

            $mdToast.show(
                $mdToast.simple()
                .textContent(msg)
                .position(pinTo)
                .hideDelay(4000)
            );
        };
        $scope.showSimpleToast();
    }

}).filter('range', function () {
    return function (n) {
        var res = [];
        for (var i = 0; i < n; i++) {
            res.push(i);
        }
        return res;
    };
});