function addHistoryDiary(data,diary){
	//第一层盒子
	var imgdiv=document.createElement("div");
	imgdiv.className="imgbox";
	imgdiv.background="red";
	diary.appendChild(imgdiv);
	//第二层盒子 imgbox-left
	var leftdiv=document.createElement("div");
	leftdiv.className="imgbox-left";
	imgdiv.appendChild(leftdiv);
	
	var imgw=document.createElement("img");
	imgw.className="images";
	imgw.src="images/7.jpg";
	
	if(!jQuery.isEmptyObject(data.photoUrl)){
		console.log(data.photoUrl);
		imgw.src=data.photoUrl;
	}
	leftdiv.appendChild(imgw);
	
	//第二层盒子  imgbox-right
	var rightdiv=document.createElement("div");
	rightdiv.className="imgbox-right";
	imgdiv.appendChild(rightdiv);
	
	var deletes=document.createElement("a");
	deletes.innerHTML="删除";
	deletes.className="delete";
	deletes.href="#";
	rightdiv.appendChild(deletes);
	
	//给删除添加事件
	deletes.onclick=function(){
		//移除当前发布的内容
		
	}
	
	var descript=document.createElement("p");
	descript.innerHTML="评论: 暂无评论";
	if(!jQuery.isEmptyObject(data.descript)){
		descript.innerHTML="评论: "+data.descript;
	}
	descript.className="descript";
	rightdiv.appendChild(descript);
	
	
	var iconbox=document.createElement("div");
	iconbox.className="iconbox";
	rightdiv.appendChild(iconbox);
	
	var imgrs=document.createElement("img");
	imgrs.className="imagess";	
	imgrs.src="images/6.jpg";	
	if(!jQuery.isEmptyObject(data.photoUrl)){
		imgrs.src=data.photoUrl;
	}	
	iconbox.appendChild(imgrs);
	//用户名
	var userspan=document.createElement("span");
	userspan.className="users";
	userspan.innerHTML=data.userName;
	iconbox.appendChild(userspan);
	
	var brs=document.createElement("br");
	iconbox.appendChild(brs);
	var brs2=document.createElement("br");
	iconbox.appendChild(brs2);
	//时间
	var times=document.createElement("span");
	times.className="times";
	times.innerHTML="发表时间:"+data.date;
	iconbox.appendChild(times);
	//分享的盒子
	var share=document.createElement("div");
	share.className="share";
	rightdiv.appendChild(share);
	//分享音乐
	var sharemusic=document.createElement("span");
	sharemusic.className="sharemusic";
	sharemusic.innerHTML="分享音乐：暂无音乐分享";
	if(!jQuery.isEmptyObject(data.musicName)){
		sharemusic.innerHTML="分享音乐："+data.musicName;
	}
	share.appendChild(sharemusic);
	
	var downloads=document.createElement("a");
	downloads.innerHTML="下载";
	downloads.className="downloads";
	downloads.href="#";
	//if(!jQuery.isEmptyObject(data.musicUrl)){
		//downloads.href="data.musicUrl";
	//}
	share.appendChild(downloads);
	
	//给下载添加点击事件
	downloads.onclick=function(){
		if(!jQuery.isEmptyObject(data.musicName)){
		$.ajax({
		type:"post",
		url:"/Music/MusicServlet?method=downData",
		async:false,//异步请求
		timeout:5000,
		data:{music:data.musicName},
		dataType:"text",
		success:function(data){
			alert(data);
		
		},
		error:function(){
			alert("请求数据错误！");
		}
		
	});
	}
		else{
			alert("很抱歉，音乐文件不存在！");
		}
	}
}




