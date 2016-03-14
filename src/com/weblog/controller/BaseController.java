package com.weblog.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.weblog.page.PageBean;
import com.weblog.page.PageDao;

/**
 * 封装了基本的Controller功能
 * <p>
 * handle方法：
 * 基类中封装了对Controller的handleRequest的处理。
 * 一般继承类只要实现其中的handle方法，完成具体业务逻辑即可。
 * </p>
 * <p>
 * 封装了一些基础功能：<br/>
 * output: 方便response输出<br/>
 * createPageBean: 实现分页<br/>
 * recordFlow: 流程操作记录<br/>
 * getUserInfo: 获取session中登录用户<br/>
 * </p>
 * <p>
 * TODO 以后需要实现自动捕捉异常，log等基本功能
 * </p>
 * @author pengdan
 * @created Oct 28, 2012 11:16:42 PM
 */
public abstract class BaseController implements Controller {
	
	protected PageDao pageDao = null;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    return handle(request, response);
	}
	
	/**
	 * 简单情况下，子类只要实现<code>handle</code>方法即可
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected abstract ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	/**
	 * 实现分页，需要传入：pageSize,pageNo
	 * @param request
	 * @param list
	 * @return
	 */
	protected PageBean createPageBean(HttpServletRequest request, List list){
		int pageSize;
		int pageNo;
		if (request.getParameter("pageSize") == null)
			pageSize = 15;
		else
			pageSize = Integer.parseInt(request
					.getParameter("pageSize"));
		if (request.getParameter("pageNo") == null)
			pageNo = 1;
		else
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		PageBean pageBean = pageDao.getPageBeans(pageSize, pageNo,
				list);
		return pageBean;
	}
	
	protected void output(HttpServletResponse response, String returnCode) throws Exception{
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter write=response.getWriter();
		write.write(returnCode);
		write.flush();
		write.close();
	}

}
