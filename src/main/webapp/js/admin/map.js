// ************************************************* MapCtrl
// *************************************************//

angular.module('MyApp').controller('MapCtrl', function ($scope, $http) {
	
    (function(){
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
    }
    )();
    
    $scope.getMapByid = function (id) {
    	$http({
    		method: "GET",
    		url: "/EasyShopWayNew/edit_map?type=map&id=" + id
    }).then(function mySucces(response) {
        $scope.map = response.data;
        console.log("Get mapsName");
        console.log($scope.mapsName);
    }, function myError(response) {
        console.log(response.statusText);
    });
    }


    var game;
    var tCell;
    var arrayTarget = new Array();
    var arrayPayDesk = new Array();
    var arrayCupBoard = new Array();
    var buffPath;
    var curTarget;
    var startCupBoard;
    var endCupBoard;
    var type;
    var waycolor = '#d80000';
    $scope.typeValue = undefined;

    $scope.config = {
        width: 50,
        height: 25,
        cellSize: 20,
        borderWidth: 1,
        cellColor: '#eee',
        borderColor: '#bbb',
        wallColor: '#555',
        playerColor: '#252',
        targetColor: '#522',
        searchColor: '#ccc',
        pathColor: '#999'
    }

    var Game = function (canvas, conf) {
        game = this;
        this.enter = $scope.config.width + 1;
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
        this.walls = new Map(this.width * this.height);
        this.way = new Map(this.width * this.height);
        this.payDesk = new Map(this.width * this.height);
        this.cupBoard = new Map(this.width * this.height);
        this.targets = new Map(this.width * this.height);

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
            if (this.payDesk.map[cell]) return '#ff870d';
            if (this.walls.map[cell]) return '#555';
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
                        game.paint.value = !game.payDesk.map[cell];
                        game.payDesk.map[cell] = game.paint.value;
                        if (game.payDesk.map[cell]) {
                            arrayPayDesk.add(cell);
                        } else {
                            arrayPayDesk.removeUndefined(cell);
                        }
                        console.log("Каси " + arrayPayDesk);
                        game.draw();
                        break;
                    case 'cupBoard':
                        console.log("in mouse move");
                        game.paint.value = true;
                        if (startCupBoard == undefined) {
                            startCupBoard = cell;
                            // game.cupBoard.map[cell] = game.paint.value;
                        } else {
                            endCupBoard = cell;
                            // game.cupBoard.map[cell] = game.paint.value;
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
                                } else {
                                    // game.cupBoard.map[startCupBoard] = false;
                                    // game.cupBoard.map[endCupBoard] = false;
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
                        if (!(game.cupBoard.map[cell]) && !(game.payDesk.map[cell]) && !(game.enter == cell)) {
                            game.paint.value = !game.walls.map[cell];
                            game.walls.map[cell] = game.paint.value;
                        }
                        break;
                    case 'edit':
                        console.log("QWERT " + cell)

                        for (var q = 0; q < arrayCupBoard.length; q++) {
                            for (var w = 0; w < arrayCupBoard[q].length; w++) {
                                if (cell == arrayCupBoard[q][w]) {
                                    console.log("You click on: " + arrayCupBoard[q]);
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
                            // case 'cupBoard':
                            // console.log("in mouse move")
                            // game.cupBoard.map[cell] = game.paint.value;
                            // break;
                        case 'wall':
                            if (!(game.cupBoard.map[cell]) && !(game.payDesk.map[cell]) && !(game.enter == cell))
                                game.walls.map[cell] = game.paint.value;
                            break;
                        case 'payDesk':
                            console.log("in mouse move")
                            game.payDesk.map[cell] = game.paint.value;
                            arrayPayDesk.add(cell);
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
            for (var i = 0; i < arrayPayDesk.length; i++) {
                this.cell = arrayPayDesk[i];
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
            buffPath.tracePath();
            arrayTarget.removeUndefined(curTarget);
            buffPath = undefined;
            this.cell = curTarget;
            this.moveTo();
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
        this.pathCells = new Map(game.walls.map.length);
        this.found = false;
        this.closed = new Map(game.walls.map.length);
        this.open = new Map(game.walls.map.length);
        this.h = new Uint16Array(game.walls.map.length);
        this.g = new Uint16Array(game.walls.map.length);
        this.parents = new Uint16Array(game.walls.map.length);
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
            if (pos + game.width > game.walls.map.length) blocked[5] = blocked[6] = blocked[7] = true;
            if (Math.floor((pos - 1) / game.width) < row) blocked[0] = blocked[3] = blocked[5] = true;
            if (Math.floor((pos + 1) / game.width) > row) blocked[2] = blocked[4] = blocked[7] = true;
            if (game.walls.map[pos - 1] && game.walls.map[pos - game.width]) blocked[0] = true;
            if (game.walls.map[pos - 1] && game.walls.map[pos + game.width]) blocked[5] = true;
            if (game.walls.map[pos + 1] && game.walls.map[pos - game.width]) blocked[2] = true;
            if (game.walls.map[pos + 1] && game.walls.map[pos + game.width]) blocked[7] = true;
            for (var i = 0; i < adjacent.length; i++) {
                if (path.closed.map[adjacent[i]] || game.walls.map[adjacent[i]] || game.cupBoard.map[adjacent[i]] || blocked[i]) continue;
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
        if (!game.walls.map[tCell]) game.player.moveTo();
    }

    $scope.radioOnClick = function (value) {
        type = value;
        console.log(type);
    }

    $scope.createNewMap = function () {
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
            if (game.walls.map[i] == true || game.payDesk.map[i] == true || game.cupBoard.map[i] == true || game.enter == i) {
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
        return game.walls.map[i] || game.cupBoard.map[i] || game.enter == i;
    }


    $scope.openCupBoard = function (cupBoard) {
        $mdDialog.show({
                controller: DialogController,
                templateUrl: 'template/admin/edit.prod.tmpl.html',
                parent: angular.element(document.body),
                resolve: {
                    types: function () {
                        return types;
                    },
                    item: function () {
                        return item;
                    }
                },
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm
                    // breakpoints.
            })
            .then(function (answer) {
                $http({
                    method: "GET",
                    url: "/EasyShopWayNew/products"
                }).then(function mySucces(response) {
                    $scope.data = response.data;
                    originalProd.prods = $scope.data.prods;
                    originalProd.count = $scope.data.prods.length;
                    $scope.datatable = angular.copy(originalProd);
                    console.log("Get");
                    console.log($scope.data);
                }, function myError(response) {
                    console.log(response.statusText);
                });
            }, function () {
                $scope.status = 'You cancelled the dialog.';
            });
    };

    function DialogController($scope, $mdDialog, types, item) {

        console.log("item " + item)
        if (item == undefined) {
            $scope.item = {
                id: 0,
                nen: "",
                nuk: "",
                ptid: 0
            };
            $scope.types = types;

        } else {
            $scope.item = item;
            $scope.types = types;
            $scope.selectedType = types[getIdDatatype(item.ptid)];

            function getIdDatatype(pid) {
                for (var i = 0; i < types.length; i++)
                    if (types[i].id == pid)
                        return i;
            }
        }

        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $http({
                method: "GET",
                url: "/EasyShopWayNew/products"
            }).then(function mySucces(response) {
                $scope.data = response.data;
                originalProd.prods = $scope.data.prods;
                originalProd.count = $scope.data.prods.length;
                $scope.datatable = angular.copy(originalProd);
                console.log("Get");
                console.log($scope.data);
            }, function myError(response) {
                console.log(response.statusText);
            });
            $mdDialog.cancel();
        };

        $scope.answer = function (item, tid) {
            item.ptid = tid.id

            var data = $.param({
                id: item.id,
                nameUk: item.nuk,
                nameEn: item.nen,
                ptid: item.ptid
            });
            console.log("New ITEM");
            console.log(item);
            $http.put('/EasyShopWayNew/products?' + data)
                .success(function (data, status, headers) {
                    console.log('update');

                })
                .error(function (data, status, header, config) {
                    console.log('failed');
                });
            $mdDialog.hide();
        };
    }


});