package com.zyq.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ServletManage {

	public Map<String,String> upload(HttpServletRequest request,String path){
		Map<String,String> map=new HashMap<>();
		boolean isContent=ServletFileUpload.isMultipartContent(request);
		if(!isContent){
//			验证，如果不包含entype，结束
			return null;
		}
		
		System.out.println("上传");
		
		File file=new File(path);
		if (!file.exists()&&!file.isDirectory()){
			file.mkdir();
		}
//		创建一个上传工具类
		ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
//		给上传文件的路径进行编码
		upload.setHeaderEncoding("utf-8");
		try {
//			通过upload解析请求码
			List<FileItem> list=upload.parseRequest(request);
			System.out.println("list的数量"+list.size());
//			判断当前文件是否为普通文本表单
			for (FileItem fileItem:list) {
				System.out.println("文件名"+fileItem.getName());
				if (fileItem.isFormField()){
				String name=fileItem.getFieldName();
//				获取文本表单的内容，utf-8解决中文乱码问题
				String value=fileItem.getString("utf-8");
				System.out.println("文本内容"+value);
				map.put(name, value);
				}else{
					String filename=fileItem.getName().replace(" ", "");
					if (filename==null||filename.equals("")){
						continue;
					}
					filename=filename.substring(filename.lastIndexOf("/")+1);
//					正则表达式
					Pattern pattern1=Pattern.compile(".+(jpg|JPG|png|PNG|gif|GIF)");
					Matcher m1=pattern1.matcher(filename);
					if (m1.matches()){
						map.put("photoName", filename);
//						http://127.0.0.1:8080/Music/upload
						map.put("photoUrl", Config.PATHURL+filename);
						System.out.println("图片名称"+filename);
					}
					
					Pattern pattern2=Pattern.compile(".+(MP3|mp3|WAV|wav|MPEG-4)");
					 Matcher m2 = pattern2.matcher(filename);
					 if(m2.matches()){
						 map.put("musicName", filename);
						 System.out.println("*******"+filename);
						 map.put("musicUrl", Config.PATHURL+filename);
					 }
				
					 InputStream in=fileItem.getInputStream();
					 FileOutputStream out=new FileOutputStream(path+"/"+filename);
					 byte[] buffer=new byte[1024];
					 int len=0;
					 while ((len=in.read(buffer))>0){
						 out.write(buffer,0,len);
					 }
					 out.close();
					 in.close();
				}
			}
		} catch (Exception e) {
			
		}
		return map;
	}
	
	//下载
		public static boolean download(String musicName) {
			// 获取音乐路径的拼接地址
			// 服务器路径
			String path = Config.PATHURL + musicName;
			try {
				URL url = new URL(path);
				URLConnection connection = url.openConnection();
				// 设置超时时间
				connection.setConnectTimeout(5 * 1000);
				// 执行连接操作
				connection.connect();
				File file = new File(Config.DOWNLOADPATH);
				// 判断文件是否存在
				if (!file.exists() && !file.isDirectory()) {
					file.mkdir();
					System.out.println("文件创建");
				}
				InputStream is = connection.getInputStream();
				FileOutputStream os = new FileOutputStream(Config.DOWNLOADPATH
						+ musicName);
				// 创建缓冲工具
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) > 0) {
					// 写入文件，循环读取文件
					os.write(buffer, 0, len);
				}
				// 关闭流
				is.close();
				os.close();
				return true;

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	
	
}
