package com.zyq.dao;

import java.sql.SQLException;

public class CreateTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql="create table musicinfo(id int not null auto_increment,userName varchar(45),description varchar(45)," +
				"musicName varchar(45),musicUrl varchar(255),phontoName varchar(45),photoUrl varchar(255),"
				+ "date varchar(45),sign varchar(45),primary key(id)) default charset=utf8";
		DBHelper db=new DBHelper(sql);
		try {
			int update = db.pst.executeUpdate();
			System.out.println(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
