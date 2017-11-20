package org.vueDemo.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.vueDemo.dao.EmpDao;
import org.vueDemo.pojo.Emp;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/empServlet")
public class EmpServlet extends  ServletMain{
	 private EmpDao ed = new EmpDao();
       
     public EmpServlet() {
        super();
    }
 	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
                        String eid = request.getParameter("eid");
                        ed.deleteByEid(eid);
 	
 	}
 
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             Map<String, String[]> map = request.getParameterMap();
             Emp emp = new Emp();
	         try {
				BeanUtils.copyProperties(emp, map);
			    ed.update(emp);
			    response.setContentType("text/html;charset=utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}

}
