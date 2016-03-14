package com.weblog.role;

import com.weblog.context.Context;

public class Test {
	public static void main(String args[]){
		RoleDao roleDao = (RoleDao)Context.getBean("RoleDao");
		Role role = roleDao.get(1);
		System.out.println(role.getRoleName());
	}
}
