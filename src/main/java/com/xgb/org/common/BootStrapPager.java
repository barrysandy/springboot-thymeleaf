package com.xgb.org.common;

import java.util.List;

/**
 * BootStrapPager<T>
 * <p>Title: BootStrap 前端数据封装</p>
 * <p>Description: </p>
 *  	
 * @author XuGongBin
 * @version 1.0.0
 * @date 2018/7/19 11:30
 */
public class BootStrapPager {

	private int pageSize = 10;
	private int pageNum;
	private int total;
	private int offset;
	private String search;
	
	private List<?> rows;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public BootStrapPager() {
		super();
	}
	
	
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public BootStrapPager( int offset, int pageSize, int total) {
		super();
		this.pageSize = pageSize;
		this.offset = offset;
		this.total = total;
		this.pageNum = (offset + pageSize) / pageSize;
		this.rows = null;
	}
	
	public BootStrapPager(int pageSize, int pageNum, int total, int offset,String search, List<?> rows) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.total = total;
		this.offset = offset;
		this.search = search;
		this.rows = rows;
	}

	
}
