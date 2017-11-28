var ghost_move_1 = 0.25;
var ghost_move_2 = 0.2;

function Ghost(){
	this.moveUnit = ghost_move_2;
	this.radius = beanLen*unitLen-4;
	this.direction = 'right';
	
	this.status = 1;  // 1 正常  2 被反击  3 被吃 ...
	
	this.color = GostColor.violet;
	
	this.x = 1;
	this.y = 1;
	
	this.px = 0;
	this.py = 0;
	this.ctx;

	var ghostGlobal = this;
	
	this.init = function (context,opt){
		ghostGlobal.ctx = context;
		if(opt.moveUnit != undefined ){
			ghostGlobal.color = opt.color;
			ghostGlobal.moveUnit = opt.moveUnit;
		}
	}
	
	this.randomDir = function (){
		//
	}
	
	this.clearGhost = function (ctx){
		ctx.fillStyle = '#FFFFFF';  
		var p = calUtil.pointByPoint(0,0,ghostGlobal.x,ghostGlobal.y);
		var x = p.x;
		var y = p.y
		var w = 14;
		var leaf = w / 3 -0.5; //    |/\/\/\|
		var down = y+ 14; //116;
		var left = x - w;  // 83
		var right = x + w + 0.5;  //111
		
		ctx.beginPath();
		ctx.moveTo(left,down);
		ctx.lineTo(left,y - 14.5);
		ctx.lineTo(right,y - 14.5);
//		ctx.bezierCurveTo(left,y-8,x-8,y-14,x,y-14);
//		ctx.bezierCurveTo(x + 6,y-14,right,y-8,right,y);


		ctx.lineTo(right,down);
		ctx.lineTo(left,down);

		ctx.fill();
	}//end clearGhost
	
	this.refreshGhost = function(){
		ghostGlobal.clearGhost(ghostGlobal.ctx);
		var result = ghostMoveNext(ghostGlobal.x,ghostGlobal.y,ghostGlobal);
		ghostGlobal.x = result.x;
		ghostGlobal.y = result.y;
		ghostGlobal.addGhost(ghostGlobal.x,ghostGlobal.y,ghostGlobal.status,ghostGlobal.direction);
	}
	
	this.addGhost = function(x,y,status,direction) {
		var p = calUtil.pointByPoint(0,0,x,y);
		var color = ghostGlobal.color;
		if(status == 1){
			//TODO ...
		}
		
		var result = drawGhost(ghostGlobal.ctx,p.x,p.y,color,direction);
	}
	
}


function ghostMoveNext(x,y,ghost){

	var judgeX = x;
	var judgeY =y;

	var fx_isInteger = Math.abs(x - Math.round(x)) < 0.01;
	var fy_isInteger =  Math.abs(y - Math.round(y)) < 0.01;
	
	if(fx_isInteger && fy_isInteger){
		var futureX = x - beanUser.x;
		var futureY = y - beanUser.y;
		var widthTarget = 'left';
		var heightTarget = 'up';
		var ranUnit = 2;
		if(futureX < 0){
			widthTarget = 'right'; 
		}
		if(futureY < 0){
			heightTarget = 'down'; 
		}
		var randXY = Math.abs(futureX) /Math.abs(futureY);
		x = Math.round(x);
		y = Math.round(y);
		var wallNext = 0;
		var wallLeft = containsPMKey(wallPointsMap,{x:x - 1,y:y});
		var wallRight = containsPMKey(wallPointsMap,{x:x + 1,y:y});
		var wallUp =  containsPMKey(wallPointsMap,{x:x ,y:y - 1});
		var wallDown =  containsPMKey(wallPointsMap,{x:x ,y:y + 1});
		var judgeArr = [];
		if(!wallLeft){
			wallNext++;
			if(ghost.direction != 'right'){
				judgeArr.push({dir:direction_left,ran:'left' ==widthTarget?ranUnit * randXY :0.5});
			}
		}
		if(!wallRight){
			wallNext++;
			if(ghost.direction != 'left'){
				judgeArr.push({dir:direction_right,ran:'right' ==widthTarget?ranUnit * randXY :0.5});
			}
		}
		if(!wallUp){
			wallNext =  wallNext+10;
			if(ghost.direction != 'down'){
				judgeArr.push({dir:direction_up,ran:'up' ==widthTarget?ranUnit /randXY :0.5});  //上
			}
		}
		if(!wallDown){
			wallNext =  wallNext+10;
			if(ghost.direction != 'up'){
				judgeArr.push({dir:direction_down,ran:'down' ==widthTarget?ranUnit /randXY:0.5});  //下
			}
		}
		if(wallNext == 1 || wallNext == 10 || wallNext == 11 || wallNext == 12
			||wallNext ==  21 ||wallNext ==  22  ){
			ghost.direction  = randomJudge(judgeArr).dir;
		}
		
//		return getNextPosition(x,y,ghost.direction,ghost.moveUnit);	
	}
	return getNextPosition(x,y,ghost.direction,ghost.moveUnit);
	
}

function getNextPosition(x,y,dir,moveUnit){
	var result =  {
		x : x,
		y :y
	};
	if(direction_up == dir){
		result.y = y - moveUnit;
	}else if(direction_down == dir){
		result.y = y + moveUnit;
	}else if(direction_left ==  dir){
		result.x = x - moveUnit;
	}else if(direction_right ==  dir){
		result.x = x + moveUnit;
	}
	return result;
}

/**
 * x 、y : 绝对坐标...
 * color :'#FFFFFF'
 * dir : 'up'|'down'|'left'|'right'
 */
function drawGhost(ctx,x,y,color,dir){
	ctx.save();
	
	ctx.fillStyle = color;  
//	x = 97;
//	y = 102;
	var w = 14;
	
	var leaf = w / 3; //    |/\/\/\|
	
//	console.log(leaf);
	var down = y+ 14; //116;
	var left = x - w;  // 83
	var right = x + w;  //111
	
	ctx.beginPath();
	ctx.moveTo(left,down);
	ctx.lineTo(left,y);
	ctx.bezierCurveTo(left,y-8,x-8,y-14,x,y-14);
	ctx.bezierCurveTo(x + 6,y-14,right,y-8,right,y);
	ctx.lineTo(right,down);
	
	ctx.lineTo(right-leaf,down - leaf);
	ctx.lineTo(right-2*leaf,down);
	ctx.lineTo(x,down - leaf);
	ctx.lineTo(left + 2*leaf,down);
	ctx.lineTo(left + leaf,down - leaf);
	ctx.lineTo(left,down);

	ctx.fill();
	

	//draw eyes..
	var eyeWidthDis = 6;
	var eyeHeightDis = -1;
	var eyeWidthR = 4;
	var eyeHeightR = 5;
	var leftEye = {
		x: x - eyeWidthDis,   //91
		y : y + eyeHeightDis  //101
	};
	var rightEye = {
		x: x + eyeWidthDis,   //103
		y : y + eyeHeightDis  //101
	};
	

	
	ctx.fillStyle = "white";
	ctx.beginPath();
	drawGhostEyeFrame(ctx,leftEye.x,leftEye.y,eyeWidthR,eyeHeightR);
	drawGhostEyeFrame(ctx,rightEye.x,rightEye.y,eyeWidthR,eyeHeightR);
	ctx.fill();
	
	ctx.fillStyle = "black";
	drawGhostEyesCircle(ctx,leftEye,rightEye,eyeWidthR,eyeHeightR,dir);
	
	ctx.restore();
}

//眼框
function drawGhostEyeFrame(ctx,x,y,eyeWidthR,eyeHeightR){

//		x: x - eyeWidthDis,   //91
//		y : y + eyeHeightDis  //101
//var eyeWidthR = 4;
//	var eyeHeightR = 5;
	ctx.moveTo(x,y-eyeHeightR);
	ctx.bezierCurveTo(x -eyeWidthR + 1 ,y-eyeHeightR,x -eyeWidthR,y-2,x -eyeWidthR,y);
	ctx.bezierCurveTo(x -eyeWidthR,y + 2,x -eyeWidthR + 1 ,y + eyeHeightR,x,y + eyeHeightR);
	ctx.bezierCurveTo(x + eyeWidthR - 1,y + eyeHeightR,x + eyeWidthR,y+2,x + eyeWidthR,y);
	ctx.bezierCurveTo(x + eyeWidthR,y - 2,x + eyeWidthR -1,y - eyeHeightR,x,y - eyeHeightR);
}//end drawGhostEyeFrame

//眼睛
function drawGhostEyesCircle(ctx,leftEye,rightEye,eyeWidthR,eyeHeightR,direction){
	var targetLeft = {
		x: leftEye.x,
		y: leftEye.y
	};
	var targetRight = {
		x: rightEye.x,
		y: rightEye.y
	};	
	if('left' == direction){
		targetLeft.x = leftEye.x - eyeWidthR + 2;
		targetRight.x = rightEye.x - eyeWidthR + 2;
	}else if('right' == direction){
		targetLeft.x = leftEye.x + eyeWidthR-2;
		targetRight.x = rightEye.x + eyeWidthR-2;
	}else if('up' == direction){
		targetLeft.y = leftEye.y - eyeHeightR+2;
		targetRight.y = rightEye.y - eyeHeightR+2;
	}else if('down' == direction){
		targetLeft.y = leftEye.y + eyeHeightR-2;
		targetRight.y = rightEye.y + eyeHeightR-2;
	}
	ctx.beginPath();
	ctx.arc(targetLeft.x, targetLeft.y , 2, 0, 2*Math.PI, true);
	ctx.fill();  
	ctx.beginPath();

	ctx.arc(targetRight.x, targetRight.y , 2, 0, 2*Math.PI, true);
	ctx.fill();  
}//end drawGhostEyesCircle