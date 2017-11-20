package org.vueDemo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vueDemo.dao.DeptDao;
import org.vueDemo.pojo.Dept;

import com.alibaba.fastjson.JSON;

@WebServlet("/deptServlet")
public class DeptServlet extends ServletMain{

	private DeptDao dd = new DeptDao();

	public DeptServlet() {
		super();
	}

	public void selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Dept> dl = dd.selectAll();
		String json = JSON.toJSONString(dl);
		PrintWriter out = response.getWriter();
		out.write(json);
	}

	public void a(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
