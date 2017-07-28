package com.zyq.bean;

public class MusicBean {
	private int id;
	private String musicName;
	private String musicUrl;
	private String photoName;
	private String photoUrl;
	private String date;
	private String sign;
	private String userName;
	private String description;
	
	public MusicBean() {
		super();
	}

	public MusicBean(String musicName, String musicUrl, String photoName, String photoUrl, String date, String sign,
			String userName, String description) {
		super();
		this.musicName = musicName;
		this.musicUrl = musicUrl;
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.date = date;
		this.sign = sign;
		this.userName = userName;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "MusicBean [id=" + id + ", musicName=" + musicName + ", musicUrl=" + musicUrl + ", photoName="
				+ photoName + ", photoUrl=" + photoUrl + ", date=" + date + ", sign=" + sign + ", userName=" + userName
				+ ", description=" + description + "]";
	}
	

}
