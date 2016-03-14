package com.weblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.weblog.role.RoleDao;

public class RoleController extends BaseController {
	
	private RoleDao roleDao = null;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
