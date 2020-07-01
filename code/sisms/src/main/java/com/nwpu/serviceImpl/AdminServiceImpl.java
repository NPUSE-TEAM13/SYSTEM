package com.nwpu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.IAdminDao;
import com.nwpu.model.Staff;
import com.nwpu.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private IAdminDao adminDao;

	public void insertAdmin(Staff staff) {

		adminDao.insertAdmin(staff);
	}

	public List<Staff> queryAdmin() {

		return adminDao.queryAdmin();
	}

	public void deleteAdmin(String staffID) {

		adminDao.deleteAdmin(staffID);
	}

	public void subNotice(String context) {
		// TODO Auto-generated method staffb
		adminDao.subNotice(context);
	}

}
