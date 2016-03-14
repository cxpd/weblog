package com.weblog.page;

import java.util.List;

public class PageBean {
	private int count;
	private int pageNo;
	private int pageSize;
	private int pageCount;
	private int nextPage;
	private int upPage;
	private int firstPage;
	private int LastPage;
	private List list;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getUpPage() {
		return upPage;
	}
	public void setUpPage(int upPage) {
		this.upPage = upPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return LastPage;
	}
	public void setLastPage(int lastPage) {
		LastPage = lastPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
