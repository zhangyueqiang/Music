$(document).ready(function(e){
	setInterval(function(){
		var datas=new Date();
		var h=datas.getHours();
		var m=datas.getMinutes();
		var s=datas.getSeconds();
		if(h<10){
			h="0"+h;
		}
		if(m<10){
			m="0"+m;
		}
		if(s<10){
			s="0"+s;
		}
		document.getElementById("time").innerHTML=h+":"+m+":"+s;
	},1000);
	
});
