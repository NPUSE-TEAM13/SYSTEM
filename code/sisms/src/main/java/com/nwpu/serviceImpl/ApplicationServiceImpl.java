package com.nwpu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.ApplicationDao;
import com.nwpu.model.Office;
import com.nwpu.model.Application;
import com.nwpu.service.ApplicationService;
import com.nwpu.service.StaffService;

@Service("appService")
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	private ApplicationDao appDao;
	public void deny(String appID) {
		appDao.deny(appID);
	}
	public void entry(String appID) {
		appDao.entry(appID);
	}

}
