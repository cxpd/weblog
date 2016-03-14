package com.weblog.page;

import java.util.List;

public interface PageDao {
	public PageBean getPageBeans(int pageSize, int pageNo, List list);
}
