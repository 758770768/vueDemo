package org.vueDemo.utils;

import java.util.List;

import org.vueDemo.pojo.Emp;

public class Page {

	private Integer total;
	private List<Emp> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Emp> getRows() {
		return rows;
	}

	public void setRows(List<Emp> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "page [total=" + total + ", rows=" + rows + "]";
	}

}
