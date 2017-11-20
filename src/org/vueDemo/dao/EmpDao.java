package org.vueDemo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.vueDemo.pojo.Emp;
import org.vueDemo.utils.DBM;
import org.vueDemo.utils.Page;

public class EmpDao {

	private Connection conn = DBM.getConn();
	private QueryRunner qr = new QueryRunner();

	public Page selectAll(Page page, String start, String end, String name, String did, String sort, String order) {
		String sqlCount = "select count(*) from emp e,dept d where e.del='0' and e.did=d.did and (ename like '%"+name+"%' or city like '%"+name+"%') and d.did like '%"+did+"%'";
		String sqlPage="select * from emp  e,dept d where e.del='0'and e.did=d.did and  (ename like '%"+name+"%' or city like '%"+name+"%')  and d.did like '%"+did+"%'  order by "+sort+" "+order+"   limit "+Integer.parseInt(start)+","+Integer.parseInt(end);
		List<Emp> el = null;
		try {
		   Long c = qr.query(conn, sqlCount, new ScalarHandler<Long>(1));
		   page.setTotal(c.intValue());
			el = qr.query(conn, sqlPage, new BeanListHandler<Emp>(Emp.class));
			page.setRows(el);
			System.out.println(sqlPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

	public void deleteByEid(String eid) {
               String sql="update emp set del='1' where eid=?";
               try {
				qr.update(conn, sql,eid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 上午9:50:33 2017年11月10日
	 * 修改
	 */
	public void update(Emp emp) {
        String sql="update emp set ename=?,bdate=?,city=?,sex=?,did=? where eid=?";  
		try {
			qr.update(conn, sql, emp.getEname(),emp.getBdate(),emp.getCity(),emp.getSex(),emp.getDid(),emp.getEid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
