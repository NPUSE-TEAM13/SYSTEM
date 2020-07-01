package com.nwpu.dao;

import java.util.List;

import com.nwpu.model.Office;
import com.nwpu.model.Staff;

public interface ApplicationDao {
	void deny(String appID);
	
	/*
	 * 查询特定员工 by staffID
	 */
	void entry (String appID);
	/*
	 * 员工入职
	 * */
}
