package org.vueDemo.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBM {

	private static Connection conn = null;
	static {
		try {
			        Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql:///emp_dept_work?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "root", "123123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����8:04:45 2017��10��23��
	 * �������
	 */
	public static  Connection getConn() {
		return conn;
	}

}
