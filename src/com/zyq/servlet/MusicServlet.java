package com.zyq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zyq.bean.MusicBean;
import com.zyq.dao.DBHelper;
import com.zyq.dao.MusicDao;
import com.zyq.tools.Config;
import com.zyq.tools.ServletManage;

/**
 * Servlet implementation class MusicServlet
 */
@WebServlet("/MusicServlet")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter writer;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request=request;
		this.response=response;
		this.request.setCharacterEncoding("utf-8");
		this.response.setCharacterEncoding("utf-8");
		this.response.setContentType("text/html;charset=utf-8");
		//跨域处理
		this.response.setHeader("Access-Control-Allow-Origin", "*");
		writer=this.response.getWriter();
		String method=this.request.getParameter("method");
		
		
		if (method.equalsIgnoreCase("sendData")){
			String path=getServletContext().getRealPath("/upload");
			System.out.println("path:"+path);
			Map<String,String> map=new ServletManage().upload(request, path);
			if (MusicDao.setMusic(map)==true){
				String json=new Gson().toJson(map);
				System.out.println("\n--------sendData---------\n"+json);
				writer.println(json);
				writer.flush();
			}else{
				writer.println("error");
			}
		}
		
		if (method.equals("getData")){
			List<MusicBean> list=MusicDao.getDiary();
			String json=new Gson().toJson(list);
			writer.println(json);
			System.out.println("\n-----getData--------\n"+json);
			writer.flush();
		}
		
		
		if (method.equalsIgnoreCase("downData")){
			String musicName=this.request.getParameter("music");
			System.out.println("musicName: "+musicName);
			if (ServletManage.download(musicName)==true){
				writer.print(Config.DOWNLOADPATH+musicName);
			}else{
				writer.print("下载失败！");
			}
		}
		
	}

}
