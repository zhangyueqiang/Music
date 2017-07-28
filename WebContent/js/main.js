function commitData(){
	//选中要提交的表单，并进行格式化处理
	var formData=new FormData($("#formdata")[0]);
	formData.append("userName","张岳强");
	var date=new Date();
	formData.append("date",date.getFullYear()+"-"
	+date.getDate());
	formData.append("sign",date.getTime());
	$.ajax({
		type:"post",
		url:"/Music/MusicServlet?method=sendData",
		async:false,
		data:formdata,
		timeout:5000,
		cache:false,
		processData:false,
		contentType:false,
		dataType:"json",
		success:function(data){
			alert('发表成功');
			if (!jQuery.isEmptyObject(data.musicUrl)){
				var player=document.getElementById("player");
				player.src=data.musicUrl;
			}
			var diary=document.getElementById("diary");
			addHistoryDiary(data,diary);
		},
		error:function(xhr,textState){
			alert("ajax request error");
		}
	});
}

$(document).ready(function(e){
	$.ajax({
		type:"post",
		url:"/Music/MusicServlet?method=getData",
		async:false,
		timeout:5000,
		dataType:"json",
		success:function(data){
			var diary=document.getElementById("diary");
			for (var i in data){
				addHistoryDiary(data[i],diary);
			}
		},
		error:function(xhr,textState){
			alert('ajax request error');
		}
	});
	
	
	
	
});