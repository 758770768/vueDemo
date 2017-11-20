package org.vueDemo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vueDemo.pojo.Dept;
import org.vueDemo.utils.DBM;

public class DeptDao {

	private Connection conn = DBM.getConn();
	private QueryRunner qr = new QueryRunner();

	public List<Dept> selectAll() {
		List<Dept> ddl = null;
		String sql = "select * from dept";
		try {
			ddl = qr.query(conn, sql, new BeanListHandler<Dept>(Dept.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ddl;
	}

}
