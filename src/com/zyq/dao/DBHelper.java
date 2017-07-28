package com.zyq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zyq.bean.MusicBean;;


public class DBHelper {
//	jdbc:mysql:127.0.0.1:3306/数据库名?userUnicode=true&characterEncoding=utf8
	public static final String url="jdbc:mysql://127.0.0.1:3306/bmi?userUnicode=true&characterEncoding=utf8";
//	用户名
	public static final String user="root";
	
//	密码
	public static final String password="123456";
//	jdbc连接数据库访问数据需要该包的支持
	public static final String name="com.mysql.jdbc.Driver";
	public Connection connection;
	public PreparedStatement pst;
	
	public DBHelper(String sql){
//		ctr+1
		try {
//			使用工具类加载驱动
			Class.forName(name);
			System.out.println("驱动加载成功");
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("成功获取连接");
//			准备可执行的sql语句
            pst = connection.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
	}
	
//	关闭数据库连接	
	public void close(){
		if(connection!=null){
			try {
				connection.close();
				pst.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}finally{
				connection=null;
				pst=null;
			}
		}
		
	}
	
	 
	
}
