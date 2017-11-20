package org.vueDemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vueDemo.dao.EmpDao;
import org.vueDemo.utils.Page;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class Servlet01
 */
@WebServlet("/Servlet01")
public class Servlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet01() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String start = request.getParameter("offset");
		String end = request.getParameter("limit");
		String name = request.getParameter("search");
		String did = request.getParameter("did");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		if(did==null)did="";
		if(name==null)name="";
		EmpDao ed = new EmpDao();
		Page page = new Page();
		page = ed.selectAll(page,start,end,name,did,sort,order);
		String json = JSON.toJSONString(page);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(json);
	}

	 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
