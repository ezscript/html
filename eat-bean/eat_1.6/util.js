var aaaa = 0.3;
//1.丢弃小数部分,保留整数部分 
console.log(parseInt(aaaa) );
//2.向上取整,有小数就整数部分加1 
console.log(Math.ceil(aaaa) );
//3,四舍五入. 
console.log(Math.round(aaaa) );
//4,向下取整 
console.log( Math.floor(aaaa));
var direction_left = 'left';
var direction_right = 'right';
var direction_up = 'up';
var direction_down = 'down';


var GostColor = {
	violet : '#FF55FF', //紫色
	blueness : '#7FD4FF',   //青
	brown : '#AF3030',  //棕色
	orange : '#FF7F00'  //橙色
};

//随机数
var randomJudge = function(arr){
	if(arr == undefined || arr.length <= 0){
		return 0;
	}
	var maxIndex = 0;
	var max = Math.random()* arr[0].ran;
	for(var i = 1; i< arr.length; i++){
		var temp = Math.random()*arr[i].ran;
		if(max <temp){
			max = temp;
			maxIndex = i;
		}
	}
	return arr[maxIndex];
};


var Current ={
	sleep : function (n){
		var start = new Date().getTime();
		while(true)  if(new Date().getTime()-start > n) break;
	},
	repeat : function(fu,n){
		var start = new Date().getTime();
		fu();
		while(true)  if(new Date().getTime()-start > n) break;
		Current.repeat(fu,n);
	}
};



var calUtil = {
	getTargetPoint : function (x,y,len,isHorizontal){
		var lineLen = len * beanLen *unitLen ;
		if(isHorizontal){
			return { x: x+ lineLen,y : y,
					len : len };
		}else{
			return { x: x,y : y+ lineLen,
					len : len };
		}
	},
	pointByPoint : function(x,y,xLen,yLen){
		return { 
			x: x + xLen * beanLen * unitLen ,
			y : y + yLen * beanLen *unitLen ,
			xLen : xLen,
			yLen : yLen
		};
	}
	
};

//pointMap ...

function pointToMap(points){
	var pointMap = {};
	
	for(var i = 0 ; i< points.length; i++){
		var p = points[i];
		if(pointMap[p.x] == undefined){
			pointMap[p.x] = {};
			pointMap[p.x][p.y] = 1;
		
		}else{
			pointMap[p.x][p.y] = 1;
		}
	}
	return pointMap;
}//end pointToMap

function containsPMKey(pointMap,p){
	if(pointMap[p.x] == undefined){
		return false;
	}
	if(pointMap[p.x][p.y] == undefined){
		return false;
	}
	if(pointMap[p.x][p.y] == 1){
		return true;
	}
	return true;
}


//key event .....

function keyDown(e){
//		console.log( e.keyCode + ' = ' + e.keyIdentifier );
	if(e.keyCode == 38){  //上下左右
		callDoUp();
	}else if(e.keyCode == 40){
		callDoDown();
	}else if(e.keyCode == 37){
		callDoLeft();
	}else if(e.keyCode == 39){
		callDoRight();
	}else if(e.keyCode == 87){ //WSAD
		callDoUp();
	}else if(e.keyCode == 83){
		callDoDown();
	}else if(e.keyCode == 65){
		callDoLeft();
	}else if(e.keyCode == 68){
		callDoRight();
	}
}
document.onkeydown = keyDown;
 
function callDoUp(){
	beanUser.futureDirection = direction_up;
}
 
function callDoDown(){
	beanUser.futureDirection = direction_down;
}
function callDoLeft(){
	beanUser.futureDirection = direction_left;
}

function callDoRight(){
	beanUser.futureDirection = direction_right;
}


//
var refreshBeans = function(ctx,globalBeans,eatMap){
	ctx.save();
	ctx.fillStyle = 'rgb(237, 237, 0)'; // 黄
	var hasBean = false;
	for(var i = 0; i < globalBeans.length; i++){
		if(!containsPMKey(eatMap,globalBeans[i])){
			drawOneBean(ctx,globalBeans[i].x,globalBeans[i].y);
			hasBean = true;
		}
	}
	ctx.restore();
	return hasBean;
}
function drawOneBean(ctx,x,y){
	p = calUtil.pointByPoint(0,0,x,y);
	ctx.fillRect(p.x-(beanLen/2),p.y-(beanLen/2),beanLen,beanLen);
}