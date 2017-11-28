/**
 * user move speed
 */
var user_move = 0.25;

function BeanUser(){
	this.moveUnit = user_move;
	this.radius = beanLen*unitLen-6.5;
	this.direction = direction_left;
	this.futureDirection = direction_left;
	this.status = 1;
	this.x = 12;
	this.y = 24;
	
	this.px = 0;
	this.py = 0;
	this.ctx;

	var userGlobal = this;
	
	this.init = function (context){
		userGlobal.ctx = context;
	
	}
	
	this.changeDirection = function (dir){
		userGlobal.futureDirection = dir;
	}
	
	this.refreshUser = function(){
		testEat(userGlobal);
	//	userGlobal.clearUser();
		testAcross(userGlobal);
		//判断未来方向是否可行...
		var jduge = jdugeDir(userGlobal.x,userGlobal.y,userGlobal.futureDirection,userGlobal.moveUnit);
		if(jduge.isSuccess){
			userGlobal.direction = userGlobal.futureDirection;
			userGlobal.x = jduge.x;
			userGlobal.y = jduge.y;
		}else{
			//判断当前方向是否可行
			jduge = jdugeDir(userGlobal.x,userGlobal.y,userGlobal.direction,userGlobal.moveUnit);
			if(jduge.isSuccess){
				userGlobal.x = jduge.x;
				userGlobal.y = jduge.y;
			}
		
		}
		
		userGlobal.addUser(userGlobal.ctx,userGlobal.x,userGlobal.y,userGlobal.status,userGlobal.direction);
		userGlobal.status++;
		if(userGlobal.status >6){
			userGlobal.status = 1;
		}
	}
	
	this.clearUser = function (){
		if(userGlobal.px == 0){
			return;
		}
		ctx.save();
		ctx.fillStyle = 'rgb(255, 255, 255)'; // 白
		if(userGlobal.x <=0.5){
			ctx.clearRect(userGlobal.px -(userGlobal.radius +8), userGlobal.py - ( userGlobal.radius +1 ),  (userGlobal.radius +8 )*2, (userGlobal.radius +1)*2);
		}else{	
			ctx.arc(userGlobal.px, userGlobal.py,userGlobal.radius +1, 0, 2 * Math.PI, false);
		}
		ctx.closePath();
		ctx.fill();
		ctx.restore();
	}
	
	this.addUser = function(ctx,x,y,status,direction) {
		var startPosition = 1.2 * Math.PI;
		var endPosition = 0.8 * Math.PI;
		
		var decPosition = Math.PI;
		var addPosition = Math.PI;
		
		if('left' == direction){
			decPosition = Math.PI;
			addPosition = Math.PI;
		}else if('right' == direction){
			decPosition = 2*Math.PI;
			addPosition = 0;
		}else if('up' == direction){
			decPosition = 1.5*Math.PI;
			addPosition = 1.5*Math.PI;
		}else if('down' == direction){
			decPosition = 0.5*Math.PI;
			addPosition = 0.5*Math.PI;
		}
		
		if(status == 1){
			startPosition = addPosition + 0.2 * Math.PI;
			endPosition = decPosition - 0.2 * Math.PI;
		}else if(status == 2){
			startPosition = addPosition + 0.1 * Math.PI;
			endPosition = decPosition - 0.1 * Math.PI;
		}else if(status == 3){
	//		startPosition = 0;
	//		endPosition =  2*Math.PI;
			startPosition = addPosition + 0.03 * Math.PI;
			endPosition = decPosition - 0.03 * Math.PI;
		}else if(status == 4){
			startPosition = addPosition + 0.1 * Math.PI;
			endPosition = decPosition - 0.1 * Math.PI;
		}else if(status == 5){
			startPosition =addPosition + 0.2 * Math.PI;
			endPosition = decPosition - 0.2 * Math.PI;
		}else if(status == 6){
			startPosition =addPosition + 0.3 * Math.PI;
			endPosition = decPosition - 0.3 * Math.PI;
		}else if(status == 7){
			startPosition =addPosition + 0.5 * Math.PI;
			endPosition = decPosition - 0.5 * Math.PI;
		}else if(status == 8){
			startPosition =addPosition + 0.65 * Math.PI;
			endPosition = decPosition - 0.65 * Math.PI;
		}else if(status == 9){
			startPosition =addPosition + 0.75 * Math.PI;
			endPosition = decPosition - 0.75 * Math.PI;
		}else if(status == 10){
			startPosition =addPosition + 0.85 * Math.PI;
			endPosition = decPosition - 0.85 * Math.PI;
		}else if(status == 11){
			startPosition =addPosition + 0.95 * Math.PI;
			endPosition = decPosition - 0.95 * Math.PI;
		}
		
		ctx.save();
		ctx.fillStyle = 'rgb(255, 212, 0)'; // 黄
		ctx.strokeStyle = 'rgb(255, 212, 0)'; // 黄
		if(status == 12){
			var p = calUtil.pointByPoint(0,0,x,y);
			ctx.beginPath();
			var split_len = 8;
			for(var i = 1 ; i<= split_len; i++){
				var r_l =userGlobal.radius/3.5;
				var r_g =userGlobal.radius/1.8;
				ctx.moveTo(p.x + Math.cos(2*Math.PI * i/split_len )*r_l,p.y + Math.sin(2*Math.PI * i/split_len )* r_l);
				ctx.lineTo(p.x + Math.cos(2*Math.PI * i/split_len )*r_g,p.y + Math.sin(2*Math.PI * i/split_len )* r_g);
			}
			ctx.stroke();
		}else{	
			var p = calUtil.pointByPoint(0,0,x,y);
			ctx.beginPath();
			ctx.moveTo(p.x,p.y);
			userGlobal.px = p.x;
			userGlobal.py = p.y;
			ctx.arc(p.x, p.y, userGlobal.radius, startPosition, endPosition, false);
			ctx.closePath();
			
		}
		ctx.fill();
		ctx.restore();
	}//end 
	
	this.disappear = function() {
		userGlobal.addUser(userGlobal.ctx,userGlobal.x,userGlobal.y,3,userGlobal.direction);
		var s = 3;
		if(userGlobal.status < 3){
			var s = 3 - userGlobal.status + 3;
		}else if(userGlobal.status > 3){
			s = userGlobal.status;
		}
		var timerUserDisappear = setInterval(function(){
			if(s > 12){
				console.log('end disappear...');
				userGlobal.clearUser();
				clearInterval(timerUserDisappear);
				return;
			}
			userGlobal.clearUser();
			userGlobal.addUser(userGlobal.ctx,userGlobal.x,userGlobal.y,s,userGlobal.direction);
			s++;
		},300);
	//	userGlobal.clearUser();
	}
}
	
	
function testEat(user){
	var x = user.x;
	var y = user.y;
	var fx_isInteger = Math.abs(x - Math.round(x)) < 0.01;
	var fy_isInteger =  Math.abs(y - Math.round(y)) < 0.01;
	if(fx_isInteger && fy_isInteger){
		if(eatMap[x] == undefined){
			eatMap[x] = {};
			eatMap[x][y] = 1;
		}else{
			eatMap[x][y] = 1;
		}
	}
}
	
function jdugeDir(x,y,dir,moveUnit){
	var result =  {
		isSuccess : false,
		x : 0,
		y :0
	};
	var judgeX = x;
	var judgeY =y;
	var futureX = x;
	var futureY =y;
	var fx_isInteger = (x - Math.floor(x)) < 0.01;
	var fy_isInteger = (y - Math.floor(y)) < 0.01;
	//减舍 | 加增
	if(direction_up == dir){
		futureY = y - moveUnit;
		judgeY = Math.floor(futureY);
		if(!fx_isInteger){
			return false;
		}
	}else if(direction_down == dir){
		futureY = y + moveUnit;
		judgeY = Math.ceil(futureY);
		if(!fx_isInteger){
			return false;
		}
	}else if(direction_left ==  dir){
		futureX = x - moveUnit;
		judgeX = Math.floor(futureX);
		if(!fy_isInteger){
			return false;
		}
	}else if(direction_right ==  dir){
		futureX = x + moveUnit;
		judgeX = Math.ceil(futureX);
		if(!fy_isInteger){
			return false;
		}
	}
	if(!containsPMKey(wallPointsMap,{x:judgeX,y:judgeY})){
		result.isSuccess = true;
		result.x = futureX;
		result.y = futureY;
	}

	return result;
}