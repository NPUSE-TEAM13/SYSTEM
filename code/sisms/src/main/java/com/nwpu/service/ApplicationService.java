package com.nwpu.service;

import java.util.List;

import com.nwpu.model.Office;
import com.nwpu.model.Staff;
public interface ApplicationService {

		void deny(String appID);
		
		/*
		 * 查询特定学生 by staffID
		 */
		void entry(String appID);
		
		/*
		 * 学生入职
		 * */
}
