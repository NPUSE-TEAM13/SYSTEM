package com.nwpu.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.ILoginDao;
import com.nwpu.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ILoginDao loginDao;

	public String login(String userName) {

		return loginDao.login(userName);
	}

	public boolean isAdmin(String userName) {

		return loginDao.isAdmin(userName);
	}

	public String getNameById(String staffID) {

		return loginDao.getNameById(staffID);
	}

	public String getNotice() {

		return loginDao.getNotice();
	}

}
