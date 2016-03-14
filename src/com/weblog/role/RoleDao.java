package com.weblog.role;

import java.util.List;

public interface RoleDao {
	public void save(Role role);
	public void update(Role role);
	public void delete(int roleId);
	public Role get(int roleId);
	public List getSubRole(int parentId);
}
