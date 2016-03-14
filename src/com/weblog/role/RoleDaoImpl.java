package com.weblog.role;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

	public void save(Role role) {
		try{
			getHibernateTemplate().save(role);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void delete(int roleId) {
		try{
			//getSession().delete("from Role where roleid=?", new Object[]{roleID});
			//Role role = new Role();
			//role.setRoleId(roleId);
			//getHibernateTemplate().delete(get(roleId));
			getHibernateTemplate().bulkUpdate("delete from Role where roleid="+roleId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Role get(int roleId) {
		Role role = null;
		try{
			role = (Role) getHibernateTemplate().get(Role.class, roleId);
			if (role != null) return role;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public List getSubRole(int parentId) {
		List list = null;
		try{
			list = getHibernateTemplate().find("from Role where parentid=?", new Object[]{parentId});
			if (list != null) return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public void update(Role role) {
		try{
			getHibernateTemplate().update(role);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
