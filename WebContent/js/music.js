function playMusic(obj){
	var player=document.getElementById("player");
	if (player.paused){
	player.play();
	obj.src="images/musicBtn.png";
	}else{
		player.pause();
//		obj.src="/Music/images/musicBtnOff.png";
		obj.src="images/musicBtnOff.png";
	}
	
}
