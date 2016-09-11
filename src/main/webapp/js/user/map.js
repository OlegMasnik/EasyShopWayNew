'use strict';
		var serApp = angular
				.module('SearchApp', [ 'ngMaterial', 'ngMessages' ]);

		serApp.controller('ProductListCtrl', DemoCtrl);

		function DemoCtrl($timeout, $q, $log, $scope, $http) {
			var self = this;
			self.a = 1;
			self.simulateQuery = false;
			self.isDisabled = true;
			console.log($scope.tryToSend);
			var config = {
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
				}
			}
			self.click = function() {
				console.log($scope.maps);
				$scope.getMapByid($scope.maps);
				start();
			}

			$http.get('/EasyShopWayNew/searchMaps', config).success(
					function(data, status, headers, config) {
						console.log(data);
						self.maps = loadAllMaps(data);
						console.log(self.maps);
					}).error(function(data, status, header, config) {
				console.log(data);
			});

			function loadAllMaps(data) {

				var maps = data.maps;

				return maps.map(function(it) {
					return {
						value : it.id,
						name_uk : it.name_uk,
						name_en : it.name_en,
						display : it.name_en,
					};
				});
			}

			self.querySearch = querySearch;
			self.selectedItemChange = selectedItemChange;
			self.searchTextChange = searchTextChange;

			self.newState = newState;

			self.toggleChecked = function(item) {
				remove(item);
			}

			self.sendOnMap = function() {
				$scope.onClick();
			}

			$scope.items = [];

			function newState(state) {
				alert("Sorry! You'll need to create a Constitution for "
						+ state + " first!");
			}
			function querySearch(query) {
				var results = query ? self.states
						.filter(createFilterFor(query)) : self.states, deferred;
				if (self.simulateQuery) {
					deferred = $q.defer();
					$timeout(function() {
						deferred.resolve(results);
					}, Math.random() * 1000, false);
					return deferred.promise;
				} else {
					return results;
				}
			}

			function searchTextChange(text) {
				// $log.info('Text changed to ' + text);
			}

			function selectedItemChange(item, text) {
				if (item != undefined) {
					if (find(item) == -1) {
						$scope.items.push(item);
						// self.searchText = undefined;
					}
				}

			}

			function find(value) {
				for (var i = 0; i < $scope.items.length; i++) {
					if ($scope.items[i].value == value.value) {
						return i;
					}
				}
				return -1;
			}

			function remove(value) {

				for (var i = 0; i < $scope.items.length; i++) {
					if ($scope.items[i].value == value.value) {
						$scope.items.splice(i, 1);
						return;
					}
				}
			}
			function loadAll(data) {

				var products = data.products;

				return products.map(function(product) {
					return {
						value : product.id,
						name_uk : product.name_uk,
						name_en : product.name_en,
						display : product.name_en,
						img : product.img,
						coordinates : product.coordinates
					};
				});
			}

			function createFilterFor(query) {
				var lowercaseQuery = angular.lowercase(query);

				return function filterFn(state) {
					return (state.display.indexOf(lowercaseQuery) === 0);
				};
			}
			
			
			
			var mapId;

		    $scope.map = [];
		    $scope.enter = [];
		    $scope.paydesks = [];
		    $scope.walls = [];

		    $scope.newMap = {};

		    $scope.cupboards = [];

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

		    function start() {
		        game = new Game(document.querySelector('canvas'), $scope.config);
		        game.draw();
		    };

		    $scope.clickOnSelect = function (mId) {
		        $http({
		            method: "GET",
		            url: "/EasyShopWayNew/edit_map?type=setMapId&id=" + mId
		        }).then(function mySucces(response) {
		            $route.reload();
		        }, function myError(response) {
		        });
		    }

		    $scope.getMapByid = function (m) {
		        game = undefined;
		        $scope.m = m;
		        mapId = m || mapId;
		        $http({
		            method: "GET",
		            url: "/EasyShopWayNew/edit_map?type=map&id=" + mapId
		        }).then(function mySucces(response) {
		            $scope.map = response.data.map;

		            $scope.config.enter = response.data.enters[0];
		            $scope.walls = response.data.walls;
		            $scope.paydesks = response.data.paydesks;
		            $scope.cupboards = response.data.cupboards;

		            $scope.config.width = $scope.map.weight;
		            $scope.config.height = $scope.map.height;

		            $scope.openMap();
		        }, function myError(response) {
		            console.log(response.statusText);
		        });
		    }

		    var game;
		    var tCell;
		    var arrayTarget = new Array();
		    var arrayCupBoard = new Array();
		    var buffPath;
		    var curTarget;
		    var startCupBoard;
		    var endCupBoard;
		    var type;
		    var waycolor = '#d80000';
		    var targetsCopy;

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
		        this.way = new Map(this.width * this.height);
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
		        this.getCellColor = function (cell) {
		            switch (cell) {
		            case this.player.cell:
		                return '#252';
		                break;
		            case this.enter:
		                return '#252';
		            }
		            if (this.targets.map[cell]) return '#522';
		            if ($scope.paydesks.indexOf(cell) != -1) return '#ff870d';
		            if ($scope.walls.indexOf(cell) != -1) return '#555';
		            if (this.way.map[cell]) return waycolor;
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
		            var cell = game.getMouseCell(e);
		            if (cell !== false) {
		                if (e.button === 0) {
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
		                    game.draw();
		                } else {
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

		    var Player = function (game) {
		        var player = this;
		        this.target = this.cell;

		        this.findStart = function () {
		            for (var i = 0; i < $scope.paydesks.length; i++) {
		                this.cell = $scope.paydesks[i];
		                for (var j = 0; j < arrayTarget.length; j++) {
		                    this.target = arrayTarget[j];
		                    var _target = this.target;
		    		        if(!game.cupBoard.map[_target + 1])
		    		        	this.target = _target + 1;
		    		        else if(!game.cupBoard.map[_target - 1])
		    		        	this.target = _target - 1;
		    		        else if(!game.cupBoard.map[_target + game.width])
		    		        	this.target = _target +  game.width;
		    		        else if(!game.cupBoard.map[_target - game.width])
		    		        	this.target = _target - game.width;
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
		            	var _target;
		            	var _targetToDelete;
		                for (var f = 0; f < arrayTarget.length; f++) {
		                    this.target = arrayTarget[f];
		                    console.log("FROM LIST" + this.target);
		                    _target = this.target;
		    		        if(!game.cupBoard.map[_target + 1])
		    		        	this.target = _target + 1;
		    		        else if(!game.cupBoard.map[_target - 1])
		    		        	this.target = _target - 1;
		    		        else if(!game.cupBoard.map[_target + game.width])
		    		        	this.target = _target +  game.width;
		    		        else if(!game.cupBoard.map[_target - game.width])
		    		        	this.target = _target - game.width;
		    		        console.log("TARGET" + this.target);
		                    this.path = new Path(game, this.cell, this.target, this.followPath);
		                    if ((typeof (buffPath) == "undefined") || (buffPath.fmin > this.path.fmin)) {
		                        buffPath = this.path;
		                        curTarget = this.target;
		                        _targetToDelete = _target;
		                    }
		                }
		                buffPath.tracePath();
		                
		                console.log("REMOVED" + this.target);
		                arrayTarget.removeUndefined(_targetToDelete);
		                buffPath = undefined;
		                this.cell = curTarget;
//		                console.log("Targets: " + arrayTarget);
		                this.moveTo();
		            } else {
		                this.path = new Path(game, this.cell, game.enter, this.followPath);
		                this.path.tracePath();
		                console.log("Finish");
		                this.cell = undefined;
		                curTarget = undefined;
		                this.target = undefined;
		            }
		            if (typeof (buffPath) != 'undefined') {
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
		            if (($scope.walls.indexOf(pos - 1) != -1) && ($scope.walls.indexOf(pos - game.width) != -1)) blocked[0] = true;
		            if (($scope.walls.indexOf(pos - 1) != -1) && ($scope.walls.indexOf(pos + game.width) != -1)) blocked[5] = true;
		            if (($scope.walls.indexOf(pos + 1) != -1) && ($scope.walls.indexOf(pos - game.width) != -1)) blocked[2] = true;
		            if (($scope.walls.indexOf(pos + 1) != -1) && ($scope.walls.indexOf(pos + game.width) != -1)) blocked[7] = true;
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
		            game = new Game(document.querySelector('canvas'), $scope.config);
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

		    function initCupBoard(obj) {
		        game.cupBoard = new Map(game.width * game.height);
		        $scope.cupboards = obj;
		        for (var i = 0; i < obj.length; i++) {
		            console.log(obj[i]);
		            obj[i].values.map(function (e, i) {
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
		        return $scope.paydesks.indexOf(i) != -1 || $scope.walls.indexOf(i) != -1 || game.enter == i;
		    }


		    $scope.openCupBoard = function (cupBoard) {
		        console.log('before open');
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
		                console.log(answer);

		            }, function () {
		                console.log("cancel");
		            });
		    };




		    function EditCupboardCtrl($scope, $mdDialog, item) {
		        console.log("item ");
		        console.log(item);
		        $scope.item = item;

		        $http({
		            method: "GET",
		            url: "/EasyShopWayNew/edit_products?type=getCupboardsProducts&cupboardId=" + item.id
		        }).then(function mySucces(response) {
		            console.log("current Prods")
		            $scope.currentProducts = response.data;
		            $scope.cupboardCells = new Array(item.board_count * item.values.length);
		            if (typeof ($scope.currentProducts) != "undefined") {
		                for (var i = 0; i < $scope.currentProducts.length; i++) {
		                    for (var j = 0; j < $scope.currentProducts[i].place.length; j++) {
		                        $scope.cupboardCells[$scope.currentProducts[i].place[j]] = $scope.currentProducts[i];
		                        console.log("prod i " + i);
		                        console.log($scope.cupboardCells);
		                    }
		                }
		            }
		            console.log($scope.currentProducts);
		        }, function myError(response) {});


		        $http({
		            method: "GET",
		            url: "/EasyShopWayNew/edit_products?type=getAllProducts"
		        }).then(function mySucces(response) {
		            console.log("all Prods")
		            $scope.allProducts = response.data;
		            console.log(response);
		        }, function myError(response) {

		        });

		        console.log("cupBoarards");
		        console.log($scope.cupboardCells);
		        $scope.hide = function () {
		            $mdDialog.hide();
		        };

		        $scope.cancel = function () {
		            $mdDialog.cancel();
		        };
		        $scope.answer = function () {
		            $scope.cupboardCells.map(function (e, i) {
		                if (typeof (e) == "string")
		                    $scope.cupboardCells[i] = JSON.parse(e)
		            })
		            console.log($scope.cupboardCells);
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
		            console.log($scope.cupboardCells);
		            console.log(data);
		            console.log(sendData);
		            $http.post('/EasyShopWayNew/edit_products', sendData, config)
		                .success(function (data, status, headers) {
		                    console.log("success send products")
		                }).error(function (data, status, header, config) {
		                    console.log('failed  send products');
		                });
		        }

		    }

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
		    
		    start();

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		serApp.controller('AppCtrl', function($scope, $mdDialog, $mdMedia) {
			$scope.status = '  ';
			$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
			$scope.showLogInForm = function(ev) {
				var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
						&& $scope.customFullscreen;
				$mdDialog.show({
					controller : DialogController,
					templateUrl : 'login.tmpl.html',
					parent : angular.element(document.body),
					targetEvent : ev,
					clickOutsideToClose : true,
					fullscreen : useFullScreen
				}).then(
						function(answer) {
							$scope.status = 'You said the information was "'
									+ answer + '".';
						}, function() {
							$scope.status = 'You cancelled the dialog.';
						});
				$scope.$watch(function() {
					return $mdMedia('xs') || $mdMedia('sm');
				}, function(wantsFullScreen) {
					$scope.customFullscreen = (wantsFullScreen === true);
				});
			};
			$scope.showRegistrationInFrom = function(ev) {
				var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
						&& $scope.customFullscreen;
				$mdDialog.show({
					controller : DialogController,
					templateUrl : 'signup.tmpl.html',
					parent : angular.element(document.body),
					targetEvent : ev,
					clickOutsideToClose : true,
					fullscreen : useFullScreen
				}).then(
						function(answer) {
							$scope.status = 'You said the information was "'
									+ answer + '".';
						}, function() {
							$scope.status = 'You cancelled the dialog.';
						});
				$scope.$watch(function() {
					return $mdMedia('xs') || $mdMedia('sm');
				}, function(wantsFullScreen) {
					$scope.customFullscreen = (wantsFullScreen === true);
				});
			};
		});

		serApp
				.controller(
						'LoginCtrl',
						[
								'$scope',
								'$http',
								'$window',
								function($scope, $http, $window) {
									$scope.sendLoginData = function() {
										console.log('hello' + $scope.email)
										var data = $.param({
											email : $scope.email,
											password : $scope.password
										});
										console.log('Read ' + data);

										var config = {
											headers : {
												'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
											}
										}
										if ($('#emailL').valid()
												&& $('#passwordL').valid()) {
											$http
													.post(
															'http://localhost:8080/EasyShopWayNew/login',
															data, config)
													.success(
															function(data,
																	status,
																	headers,
																	config) {
																if (data.emailErrMsg == undefined) {
																	$window.location.href = 'cabinet';
																} else {
																	$scope.error = data.emailErrMsg;
																}
																console
																		.log(data.emailErrMsg);
															})
													.error(
															function(data,
																	status,
																	header,
																	config) {
																console
																		.log('fail');
															});
										} else {
											cosole.log("sory");
										}
									};
								} ]);

		serApp
				.controller(
						'SignUpCtrl',
						[
								'$scope',
								'$http',
								function($scope, $http) {

									$scope.sendRegData = function() {
										console.log('hello ' + $scope.email)
										console.log("date " + dateBirthday)
										var data = $.param({
											email : $scope.email,
											password : $scope.password,
											firstName : $scope.firstName,
											lastName : $scope.lastName,
											birthday : dateBirthday
										});
										console.log('Read ' + data);

										var config = {
											headers : {
												'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
											}
										}

										console.log($('#fName1').val());
										console.log($('#lName1').val());

										console.log($('#emailR').valid() + " "
												+ $('#fName1').valid() + " "
												+ $('#lName1').valid() + " "
												+ $('#passwordR').valid())
										if ($('#emailR').valid()
												&& $('#passwordR').valid()
												&& $('#fName1').valid()
												&& $('#lName1').valid()) {

											$http
													.post(
															'http://localhost:8080/EasyShopWayNew/reg',
															data, config)
													.success(
															function(data,
																	status,
																	headers,
																	config) {
																console
																		.log("QWEER"
																				+ data.emailErrMsg);
																$scope.error = data.emailErrMsg;
																if (data.emailErrMsg == undefined) {
																	$scope.success = "Check your email";
																}
																// var esc = $
																// .Event(
																// "keydown", {
																// keyCode: 27
																// });
																// $("body").trigger(esc);

															})
													.error(
															function(data,
																	status,
																	header,
																	config) {
																console
																		.log('fail');
															});
										} else {
											console.log("oq");
										}
									};
								} ]);

		serApp.controller('DatePickerCtrl', function($scope) {

			$scope.today = function() {
				$scope.dt = new Date();
			};
			$scope.dateformat = "MM/dd/yyyy";
			$scope.today();
			$scope.showcalendar = function($event) {
				$scope.showdp = true;
			};
			$scope.showdp = false;
			$scope.dtmax = new Date();
			dateBirthday = moment($scope.dt).format('YYYY-MM-DD');
		});

		function DialogController($scope, $mdDialog) {
			$scope.hide = function() {
				$mdDialog.hide();
			};
			$scope.cancel = function() {
				$mdDialog.cancel();
			};
			$scope.answer = function(answer) {
				$mdDialog.hide(answer);
			};
		}
		
		serApp.controller('MapCtrl', function ($mdToast, $route, $scope, $http, $mdDialog) {

		    

		});
// .filter('range', function () {
// return function (n) {
// var res = [];
// for (var i = 0; i < n; i++) {
// res.push(i);
// }
// return res;
// };
// });
