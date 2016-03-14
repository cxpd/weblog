package com.weblog.page;

import java.util.ArrayList;
import java.util.List;

public class PageDaoImpl implements PageDao {
	public PageBean getPageBeans(int pageSize,int pageNo,List list){
		PageBean pageBean=new PageBean();
		List pageList=new ArrayList();
		int pagesize=pageSize;
		int count=0;
		int pageCount=0;
		int index=1;
		if(list!=null&&list.size()>0){
			count=list.size();
			pageCount=getPageCount(list, pageSize);
			index=getIndex(pageNo, pageCount);
			pageList=getPageList(list,index,pageSize,pageCount);
		}
		pageBean.setCount(count);
		pageBean.setFirstPage(1);
		pageBean.setLastPage(pageCount);
		pageBean.setList(pageList);
		pageBean.setUpPage((index<=1?1:index-1));
		pageBean.setPageCount(pageCount);
		pageBean.setPageNo(index);
		pageBean.setPageSize(pageSize);
		pageBean.setNextPage((index>=pageCount?pageCount:index+1));
		return pageBean;
	}
	
	private int getIndex(int pageNo,int pageCount){
		int index=pageNo;
		if(index>=pageCount)
			index=pageCount;
		else if(index<=1)
			index=1;
		return index;
	}
	
	private int getPageCount(List list,int pageSize){
		int pageCount=0;
		int count=list.size();
		if(count%pageSize==0)
			pageCount=count/pageSize;
		else
			pageCount=count/pageSize+1;
		return pageCount;
	}
	
	private List getPageList(List list,int index,int pageSize,int pageCount){
		List pageList=new ArrayList();
		if(index==pageCount){
			for(int i=(index-1)*pageSize;i<list.size();i++)
				pageList.add(list.get(i));
		}else{
			for(int i=(index-1)*pageSize;i<index*pageSize;i++)
				pageList.add(list.get(i));
		}
		return pageList;
	}
		
}
