function load (){  
   
    document.addEventListener('touchstart',touch, false);  
    document.addEventListener('touchmove',touch, false);  
    document.addEventListener('touchend',touch, false);  
	var tempX = 0;
	var tempY = 0;

    function touch (event){  
        var event = event || window.event;  
           
   //     var oInp = document.getElementById("innerSpan");  
   
        switch(event.type){  
            case "touchstart":  
				tempX = event.touches[0].clientX;
				tempY = event.touches[0].clientY;
         //       oInp.innerHTML = "Touch started (" + event.touches[0].clientX + "," + event.touches[0].clientY + ")";  
                break;  
            case "touchend":  
     //           oInp.innerHTML = "<br>Touch end (" + event.changedTouches[0].clientX + "," + event.changedTouches[0].clientY + ")";  
                break;  
            case "touchmove":  
                event.preventDefault();  
				
				var x = event.touches[0].clientX;
				var y = event.touches[0].clientY;
				var isHorizontal = Math.abs(x - tempX) >  Math.abs(y - tempY);
				if(isHorizontal){
					x > tempX? callDoRight():callDoLeft();
				}else{
					y > tempY? callDoDown():callDoUp();
				}
            //    oInp.innerHTML = "<br>Touch moved (" + event.touches[0].clientX + "," + event.touches[0].clientY + ")";  
                break;  
        }  
           
    }  
}  
window.addEventListener('load',load, false);  