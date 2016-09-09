// ************************************************* MapCtrl ************************************************* 

angular.module('MyApp').controller('MapCtrl', function ($route, $scope, $http, $mdDialog) {
	
	var mapId;

    $scope.map = undefined;
    $scope.enter = undefined;
// $scope.paydesks = [1, 5, 45, 25, 65];
// $scope.walls = [2, 45, 10, 4, 8];
    $scope.paydesks = undefined;
    $scope.walls = undefined;
    
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

    (function () {
        $http({
            method: "GET",
            url: "/EasyShopWayNew/edit_map?type=mapsName"
        }).then(function mySucces(response) {
            $scope.mapsName = response.data;
            console.log("Get mapsName");
            console.log($scope.mapsName);
        }, function myError(response) {
            console.log(response.statusText);
        });
    })();

    $scope.getMapByid = function (m) {
        // $scope.map = m;
        console.log("get map by id " + m.id);
        mapId = m.id;
        $http({
            method: "GET",
            url: "/EasyShopWayNew/edit_map?type=map&id=" + m.id
        }).then(function mySucces(response) {
            $scope.map = response.data.map;
            console.log($scope.map);
            console.log(response.data);
            
            $scope.config.enter = response.data.enters[0];
            $scope.walls = response.data.walls;
            $scope.paydesks = response.data.paydesks;
            $scope.cupboards = response.data.cupboards;
            console.log($scope.cupboards)
            
            $scope.config.width = $scope.map.weight;
            $scope.config.height = $scope.map.height;
            console.log("size = " + $scope.config.width + $scope.config.height);
            console.log("size = " + $scope.config.enter);
        }, function myError(response) {
            console.log(response.statusText);
        });
    }

    var game;
    var tCell;
    var arrayTarget = new Array();
// var arrayPayDesk = new Array();
    var arrayCupBoard = new Array();
    var buffPath;
    var curTarget;
    var startCupBoard;
    var endCupBoard;
    var type;
    var waycolor = '#d80000';
    $scope.typeValue = undefined;

    var Game = function (canvas, conf) {
        game = this;
        this.enter = $scope.config.enter;
        this.canvas = canvas;
        this.width = conf.width;
        this.height = conf.height;
        this.cellSize = conf.cellSize;
        this.borderWidth = conf.borderWidth;
        this.cellColor = conf.cellColor;
        this.borderColor = conf.borderColor;
        this.wallColor = conf.wallColor;
        this.playerColor = conf.playerColor;
        this.targetColor = conf.targetColor;
        this.searchColor = conf.searchColor;
        this.pathColor = conf.pathColor;
        this.ctx = this.canvas.getContext('2d');
        this.cellSpace = this.cellSize + this.borderWidth;
        this.canvas.width = this.width * this.cellSpace + this.borderWidth;
        this.canvas.height = this.height * this.cellSpace + this.borderWidth;
        this.player = new Player(this);
// this.walls = new Map(this.width * this.height);
        this.way = new Map(this.width * this.height);
// this.payDesk = new Map(this.width * this.height);
        this.cupBoard = new Map(this.width * this.height);
        this.targets = new Map(this.width * this.height);
        initCupBoard($scope.cupboards);

        this.paint = {
            value: false,
            active: false
        };

        this.step = function (fn) {
            fn();
            game.draw();
        }

        var targetsCopy;
        this.getCellColor = function (cell) {
            switch (cell) {
            case this.player.cell:
                return '#252';
                break;
            case this.enter:
                return '#252';
            }
            if ($scope.paydesks.indexOf(cell) != -1) return '#ff870d';
            if ($scope.walls.indexOf(cell) != -1) return '#555';
            if (this.way.map[cell]) return waycolor;
            if (this.cupBoard.map[cell]) return '#038ef0';
            if (this.targets.map[cell]) return '#522';
            return '#eee';
        };


        this.draw = function () {
            game.ctx.fillStyle = '#bbb';
            game.ctx.fillRect(0, 0, game.canvas.width, game.canvas.height);
            var cell = 0;
            for (var y = 0; y < game.height; y++) {
                for (var x = 0; x < game.width; x++) {
                    game.ctx.fillStyle = game.getCellColor(cell);
                    if (game.ctx.fillStyle == waycolor && targetsCopy != undefined && !targetsCopy[cell]) {

                        game.ctx.fillStyle = '#eee';
                        game.ctx.fillRect(x * game.cellSpace + game.borderWidth, y * game.cellSpace + game.borderWidth,
                            game.cellSize, game.cellSize);

                        game.ctx.beginPath();
                        game.ctx.arc(x * game.cellSpace + game.borderWidth + game.cellSize / 2,
                            y * game.cellSpace + game.borderWidth + game.cellSize / 2, game.cellSize / 4, 0, 2 * Math.PI);
                        game.ctx.fillStyle = waycolor;
                        game.ctx.strokeStyle = waycolor;
                        game.ctx.fill();
                        game.ctx.stroke();
                    } else {
                        if (targetsCopy != undefined && targetsCopy[cell]) {
                            var tmp1 = this.targets.map[cell];
                            var tmp2 = this.way.map[cell];
                            this.targets.map[cell] = true;
                            this.way.map[cell] = false;
                            game.ctx.fillStyle = game.getCellColor(cell);
                            this.targets.map[cell] = tmp1;
                            this.way.map[cell] = tmp2;
                        }

                        game.ctx.fillRect(x * game.cellSpace + game.borderWidth,
                            y * game.cellSpace + game.borderWidth,
                            game.cellSize, game.cellSize);
                    }
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
            if (cell === this.player.cell || cell === this.player.target) {
                return false;
            }
            return cell;
        };
        this.mouseDown = function (e) {
            console.log("in mouse down")
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
                        console.log("Каси " + $scope.paydesks);
                        game.draw();
                        break;
                    case 'cupBoard':
                        console.log("in mouse move");
                        game.paint.value = true;
                        if (startCupBoard == undefined) {
                            startCupBoard = cell;
                        } else {
                            endCupBoard = cell;
                            if (Math.abs(startCupBoard - endCupBoard) < $scope.config.width - 1) {
                                console.log("in horizont: start=" + startCupBoard + " end=" + endCupBoard);
                                if (checkRange(startCupBoard, endCupBoard, 1)) {
                                    console.log("dsdsds")
                                    var arr = range(startCupBoard, endCupBoard, 1);
                                    arr.map(function (e, i) {
                                        game.cupBoard.map[e] = true;
                                    });
                                    console.log(arr)
                                    arrayCupBoard.push(arr);
                                    $scope.createCupBoard(arr, undefined);
                                } 
                            } else if ((endCupBoard % $scope.config.width) == (startCupBoard % $scope.config.width)) {
                                console.log("in vertical: start=" + startCupBoard + " end=" + endCupBoard);
                                if (checkRange(startCupBoard, endCupBoard, $scope.config.width)) {
                                    var arr = range(startCupBoard, endCupBoard, $scope.config.width);
                                    arr.map(function (e, i) {
                                        game.cupBoard.map[e] = true;
                                    });
                                    console.log(arr)
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
                            console.log(arrayCupBoard);
                            startCupBoard = undefined, endCupBoard = undefined;
                        }
                        break;
                    case 'wall':
                    	game.paint.value = !($scope.walls.indexOf(cell) != -1);
                      if ($scope.walls.indexOf(cell) == -1) {
                      	$scope.walls.add(cell);
                      } else {
                      	$scope.walls.removeUndefined(cell);
                      }
                      console.log("Каси " + $scope.paydesks);
                        break;
                    case 'edit':
                        console.log("CELL #" + cell)

                        for (var q = 0; q < $scope.cupboards.length; q++) {
                            for (var w = 0; w < $scope.cupboards[q].values.length; w++) {
                                if (cell == $scope.cupboards[q].values[w]) {
                                    console.log("You click on: ");
                                    console.log($scope.cupboards[q]);
                                    $scope.openCupBoard($scope.cupboards[q]);
                                }
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    game.draw();
                } else {
                    console.log("right click");
                    if (!checkCell(cell)) {
                        game.paint.active = true;

                        game.paint.value = !game.targets.map[cell];
                        game.targets.map[cell] = game.paint.value;
                        if (game.targets.map[cell]) {
                            arrayTarget.add(cell);
                        } else {
                            arrayTarget.removeUndefined(cell);
                        }
                        game.draw();
                        console.log("Цілі " + arrayTarget);
                        targetsCopy = game.targets.map;
                    } else {
                        console.log("хуйня якась")
                    }
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
                            	if(game.paint.value)
                            		$scope.walls.add(cell);
                            	else 
                            		$scope.walls.removeUndefined(cell);
                            break;
                        case 'payDesk':
                            console.log("in mouse move")
                            if (!(game.cupBoard.map[cell]) && !($scope.walls.indexOf(cell) != -1) && !(game.enter == cell))
                            	if(game.paint.value)
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
            console.log("in mouse up")
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

    var Player = function (game) {
        var player = this;
        this.target = this.cell;

        this.findStart = function () {
        	for (var i = 0; i < $scope.paydesks.length; i++) {
                this.cell = $scope.paydesks[i];
                for (var j = 0; j < arrayTarget.length; j++) {
                    this.target = arrayTarget[j];
                    this.path = new Path(game, this.cell, this.target, this.followPath);
                    if ((typeof (buffPath) == "undefined") || (buffPath.fmin > this.path.fmin)) {
                        buffPath = this.path;
                        curTarget = this.cell;
                    }
                }
            }
            return curTarget;
        };


        this.followPath = function () {
            player.cell = player.path.cells.pop();
            if (player.path.cells.length > 0) {
                game.step(player.followPath);
            };
        }
        this.moveTo = function () {
            if (arrayTarget.length > 0) {
                for (var f = 0; f < arrayTarget.length; f++) {
                    this.target = arrayTarget[f];
                    this.path = new Path(game, this.cell, this.target, this.followPath);
                    if ((typeof (buffPath) == "undefined") || (buffPath.fmin > this.path.fmin)) {
                        buffPath = this.path;
                        curTarget = this.target;
                    }
                }
                buffPath.tracePath();
                arrayTarget.removeUndefined(curTarget);
                buffPath = undefined;
                this.cell = curTarget;
                console.log("Targets: " + arrayTarget);
                this.moveTo();
            } else {
                console.log("this.cell = " + this.cell);
                console.log("this.target = " + this.target);
                console.log("curTarget = " + curTarget);

                // this.cell = this.findEnd();
                this.path = new Path(game, this.cell, game.enter, this.followPath);
                // console.log("Looking for enter" + curTarget);
                this.path.tracePath();
                console.log("Finish");
                this.cell = undefined;
                curTarget = undefined;
                this.target = undefined;
            }
            if(typeof(buffPath) != 'undefined'){
            buffPath.tracePath();
            arrayTarget.removeUndefined(curTarget);
            buffPath = undefined;
            this.cell = curTarget;
            this.moveTo();
            }
        }
    }

    var Map = function (length) {
        this.map = new Array(length);
        for (var cell = 0; cell < length; cell++) {
            this.map[cell] = false;
        }
    };
    
    var Path = function (game, start, target, callback) {
        var path = this;
        this.cells = [];
        this.pathCells = new Map($scope.config.width * $scope.config.height);
        this.found = false;
        this.closed = new Map($scope.config.width * $scope.config.height);
        this.open = new Map($scope.config.width * $scope.config.height);
        this.h = new Uint16Array($scope.config.width * $scope.config.height);
        this.g = new Uint16Array($scope.config.width * $scope.config.height);
        this.parents = new Uint16Array($scope.config.width * $scope.config.height);
        this.fmin = undefined;
        for (var i = 0; i < this.g.length; i++) {
            this.g[i]--;
        }
        var targetX = target % game.width,
            targetY = Math.floor(target / game.width),
            cell = 0;
        for (var y = 0; y < game.height; y++) {
            for (var x = 0; x < game.width; x++) {
                this.h[cell] = (Math.abs(x - targetX) + Math.abs(y - targetY)) * 10;
                cell++;
            }
        }
        var pos = start;
        this.g[pos] = 0;
        this.search = function () {
            path.closed.map[pos] = true;
            path.open.map[pos] = false;
            var adjacent = [pos - 1 - game.width, pos - game.width, pos + 1 - game.width,
                pos - 1, pos + 1,
                pos - 1 + game.width, pos + game.width, pos + 1 + game.width],
                blocked = [false, false, false, false, false, false, false, false],
                distance = [14, 10, 14, 10, 10, 14, 10, 14],
                row = Math.floor(pos / game.width);
            if (pos - game.width < 0) blocked[0] = blocked[1] = blocked[2] = true;
            if (pos + game.width > this.closed.map.length) blocked[5] = blocked[6] = blocked[7] = true;
            if (Math.floor((pos - 1) / game.width) < row) blocked[0] = blocked[3] = blocked[5] = true;
            if (Math.floor((pos + 1) / game.width) > row) blocked[2] = blocked[4] = blocked[7] = true;
            if (($scope.walls.indexOf(pos -1) != -1) && ($scope.walls.indexOf(pos - game.width) != -1)) blocked[0] = true;
            if (($scope.walls.indexOf(pos -1) != -1) && ($scope.walls.indexOf(pos + game.width) != -1)) blocked[5] = true;
            if (($scope.walls.indexOf(pos +1) != -1) && ($scope.walls.indexOf(pos - game.width) != -1)) blocked[2] = true;
            if (($scope.walls.indexOf(pos +1) != -1) && ($scope.walls.indexOf(pos + game.width) != -1)) blocked[7] = true;
            for (var i = 0; i < adjacent.length; i++) {
                if (path.closed.map[adjacent[i]] || ($scope.walls.indexOf(adjacent[i]) != -1) || game.cupBoard.map[adjacent[i]] || blocked[i]) continue;
                path.open.map[adjacent[i]] = true;
                var g = path.g[pos] + distance[i];
                if (g < path.g[adjacent[i]]) {
                    path.g[adjacent[i]] = g;
                    path.parents[adjacent[i]] = pos;
                }
            }
            this.fmin = 131071;
            for (var i = 0; i < path.g.length; i++) {
                var f = path.g[i] + path.h[i];
                if (path.open.map[i] && f < this.fmin) {
                    this.fmin = f;
                    pos = i;
                }
            }
            if (this.fmin !== 131071) {
                if (pos === target) {
                    return this;
                } else {
                    path.search();
                }
            }
        };
        this.tracePath = function () {
            path.cells.push(pos);
            path.pathCells.map[pos] = true;
            game.way.map[pos] = true;

            if (pos !== start) {
                pos = path.parents[pos];
                game.step(path.tracePath);
            } else {
                callback();
            };
        }
        this.search();
    }
    $scope.onClick = function () {
        clear();
        game.draw();
        game.player.cell = game.player.findStart();
        console.log("On click start " + game.player.cell);
        if (!($scope.walls.indexOf(tCell) != -1)) game.player.moveTo();
    }

    $scope.radioOnClick = function (value) {
        type = value;
        console.log(type);
    }

    $scope.openMap = function () {
        console.log("size " + $scope.config.width + $scope.config.height);
        game = new Game(document.querySelector('canvas'), $scope.config);
    }

    $scope.clearWay = function () {
        console.log("clear last way")
        clear();
        game.draw();
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
    
    function initCupBoard(obj){
    	game.cupBoard = new Map(game.width * game.height);
    	$scope.cupboards = obj;
    	for(var i = 0; i<obj.length; i++){
    		console.log(obj[i]);
    		obj[i].values.map(function(e, i){
    			game.cupBoard.map[e] = true;
    		});
    	}
    }

    Array.prototype.removeUndefined = function (value) {
        this[this.indexOf(value)] = undefined;
        this.sort();
        if (typeof (this[this.length - 1]) == "undefined") {
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
        return $scope.paydesks.indexOf(i) != -1 || game.cupBoard.map[i] || game.enter == i;
    }


    $scope.openCupBoard = function (cupBoard) {
    	console.log('before open');
        $mdDialog.show({
                controller: DialogController,
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
            	console.log(answer);
            	
            }, function () {
                console.log("cancel");
            });
    };

    function DialogController($scope, $mdDialog, item) {

        console.log("item ");
        console.log(item);
        $scope.item = item;

        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.answer = function () {
            $mdDialog.hide();
        };
        
        $scope.deleteCupboard = function(item){
        	console.log('delete cupboard');
        	var config = {
		            headers: {
		            	'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
		        }
		     }
        	
        	$http.delete('/EasyShopWayNew/edit_map?type=cupboard&id=' + item.id + '&mapId=' + mapId, config)
        	   .then(
        	       function(response){
                       initCupBoard(response.data);
                       console.log($scope.cupboards);
                       game.draw();
        	       }, 
        	       function(response){
        	    	   console.log('delete failed')
        	       }
        	    );
        	 $mdDialog.hide();
        }
    }
    
    $scope.createCupBoard = function (values, b_count) {
    	
    	console.log('before create');
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
    		console.log(answer);
    		
    	}, function () {
    		console.log("cancel");
    	});
    };
    
    function CreateDialogController($scope, $mdDialog, values, b_count) {
    	
    	console.log("values");
    	console.log(values);
    	$scope.values = values;
    	
    	$scope.hide = function () {
    		$mdDialog.hide();
    	};
    	
    	$scope.cancel = function (values) {
    		console.log("Cancel");
    		values.map(function(e, i) {
				game.cupBoard.map[e] = false;
			});
    		game.draw();
    		console.log(values);
    		$mdDialog.cancel();
    	};
    	
    	$scope.answer = function (values, b_count) {
    		console.log("Send");
    		console.log(values);
    		console.log(b_count);
    		if(typeof(b_count) == 'undefined'){
    			values.map(function(e, i) {
    				game.cupBoard.map[e] = false;
    			});
    			game.draw();
    		}else{
    			
    			var sendData = $.param({
                   type: 'cupboard',
                   data: JSON.stringify({
                	   values: values,
                       bCount: b_count,
                       mapId: mapId
                   })
                });
    			console.log('SEND CUPBOARD');
    			console.log(sendData);
    			var config = {
    		            headers: {
    		            	'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
    		        }
    		     }
    			$http.post('/EasyShopWayNew/edit_map', sendData, config)
                .success(function (data, status, headers) {
                    console.log('update');
                    $scope.cupboards = data;
                    console.log($scope.cupboards);
                    initCupBoard($scope.cupboards);
                    game.draw();
                })
                .error(function (data, status, header, config) {
                    console.log('failed');
                });
    		}
    		$mdDialog.hide();
    	};
    }
    
    $scope.createMap = function () {
    	
    	console.log('before create');
    	$mdDialog.show({
    		controller: CreateMapDialogController,
    		templateUrl: 'template/admin/create.map.tmpl.html',
    		parent: angular.element(document.body),
    		fullscreen: $scope.customFullscreen // Only for -xs, -sm
    	})
    	.then(function (answer) {
    		
    		console.log(answer);
    		
    	}, function () {
    		console.log("cancel");
    	});
    };
    
    function CreateMapDialogController($scope, $mdDialog) {
    	
    	$scope.hide = function () {
    		$mdDialog.hide();
    	};
    	
    	$scope.cancel = function (values) {
    		console.log("Cancel");
    		$mdDialog.cancel();
    	};
    	ea
    	$scope.answer = function () {
    		console.log('created ....');
    		
    		var data = $.param({
    			type: 'createMap',
    			name_en: $scope.newMap.name_en,
    			name_uk: $scope.newMap.name_uk,
    			weight: $scope.newMap.width,
    			height: $scope.newMap.height
    		});
    		
    		console.log(data);
    		
            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
    		
    		$http.post('/EasyShopWayNew/edit_map', data, config)
            .success(function (data, status, headers) {
                console.log('create new');
                $route.reload();
                
            })
            .error(function (data, status, header, config) {
                console.log('failed');
            });
    		$mdDialog.hide();
    	};
    }
    
    $scope.saveMap = function(map){
    	console.log(map);
    	console.log($scope.walls);
    	console.log($scope.paydesks);
    	console.log($scope.config.enter);
    	
    	var sendData = $.param({
    		type: 'saveMap',
    		data: JSON.stringify({
    			mapId: map.id,
    			walls: $scope.walls,
    			paydesks: $scope.paydesks,
    			enter: [$scope.config.enter]
    		})
//    		map_id: map.id,
//    		walls: JSON.stringify({
//    			values: $scope.walls
//    		}),
//    		paydesks: JSON.stringify({
//    			values: $scope.paydesks
//    		}),
//    		enter: JSON.stringify({
//    			values: [$scope.config.enter]
//    		})
    	});
    	
		console.log(sendData);
		
        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }
		
		$http.post('/EasyShopWayNew/edit_map', sendData, config)
        .success(function (data, status, headers) {
            console.log('success save map');
        })
        .error(function (data, status, header, config) {
            console.log('failed save map');
        });
    }
    
    $scope.clearMap = function(map){
    	
    	var data = $.param({
    		type: 'clearMap',
    		mapId: map.id,
    	});
    	
		console.log(data);
		
        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }
		
		$http.post('/EasyShopWayNew/edit_map', config)
        .success(function (data, status, headers) {
//            console.log('clear map');
        	 $route.reload();
        })
        .error(function (data, status, header, config) {
            console.log('failed clear');
        });
        $scope.walls = [];
		$scope.paydesks = [];
		$scope.config.enter = [];
		game.enter = undefined;
		$scope.config.enter= undefined;
		game.cupBoard = new Map(game.width * game.height);
		game.draw();
    }
    function deleteMap(m){
    	 var config = {
    	            headers: {
    	                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
    	            }
    	        }
    	 $http.delete('/EasyShopWayNew/edit_map?type=map&id=' + m.id, config).success(function (data, status, headers) {
             console.log('delete map');
         })
         .error(function (data, status, header, config) {
             console.log('failed delete');
         });
    }
    $scope.showConfirmDelete = function(ev, map) {
        // Appending dialog to document.body to cover sidenav in docs app
    	
    	if(typeof(map) == 'undefined'){
    		
    	}else{
        var confirm = $mdDialog.confirm()
              .title('Would you like to delete this map?')
              .textContent('All of the banks have agreed to forgive you your debts.')
              .ariaLabel('Lucky day')
              .targetEvent(ev)
              .ok('Yes')
              .cancel('No');
    	}
        $mdDialog.show(confirm).then(function() {
        	deleteMap(map);
        }, function() {
        	console.log('You decided to keep your debt.');
        });
      };


}).filter('range', function(){
    return function(n) {
        var res = [];
        for (var i = 0; i < n; i++) {
          res.push(i);
        }
        return res;
      };
});