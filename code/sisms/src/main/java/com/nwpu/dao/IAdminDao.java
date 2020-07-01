package com.nwpu.dao;

import java.util.List;

import com.nwpu.model.Staff;

public interface IAdminDao {

	/*
	 * 新增管理员
	 */
	void insertAdmin(Staff staff);

	/*
	 * 查询管理员
	 */
	List<Staff> queryAdmin();

	/*
	 * 删除管理员
	 */
	void deleteAdmin(String staffID);

	/*
	 * 发布公告
	 */
	void subNotice(String context);
}
