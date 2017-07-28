package com.zyq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zyq.bean.MusicBean;

public class MusicDao {

	public static boolean setMusic(Map<String,String> map){
		String sql="insert into musicinfo(userName,description,musicName,musicUrl,photoName,photoUrl,date,sign) values(?,?,?,?,?,?,?,?)";
		DBHelper db=new DBHelper(sql);
		try {
			db.pst.setString(1, map.get("userName"));
			db.pst.setString(2, map.get("description"));
			db.pst.setString(3, map.get("musicName"));
			db.pst.setString(4, map.get("musicUrl"));
			db.pst.setString(5, map.get("photoName"));
			db.pst.setString(6, map.get("photoUrl"));
			db.pst.setString(7, map.get("date"));
			db.pst.setString(8, map.get("sign"));
			int update=db.pst.executeUpdate();
			if (update>0)
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			db.close();
		}
		return false;
	}
	
	public static List<MusicBean> getDiary(){
		String sql="select * from musicinfo order by id desc";
		DBHelper db = new DBHelper(sql);
		List<MusicBean> list=new ArrayList<>();
		try {
			ResultSet query = db.pst.executeQuery();
			
			while(query.next()){
				MusicBean bean=new MusicBean();
				bean.setDate(query.getString("date"));
				bean.setDescription(query.getString("description"));
				bean.setMusicName(query.getString("musicName"));
				bean.setMusicUrl(query.getString("musicUrl"));
				bean.setPhotoName(query.getString("photoName"));
				bean.setPhotoUrl(query.getString("photoUrl"));
				bean.setSign(query.getString("sign"));
				bean.setUserName(query.getString("userName"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return list;
	}
}
