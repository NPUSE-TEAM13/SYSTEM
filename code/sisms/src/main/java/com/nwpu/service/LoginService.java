package com.nwpu.service;

import org.springframework.stereotype.Service;

public interface LoginService {

	/*
	 * 登录获取密码
	 */
	String login(String userName);

	/*
	 * 获取用户信息
	 */
	boolean isAdmin(String userName);

	/*
	 * 获取用户名
	 */
	String getNameById(String staffID);

	/*
	 * 获取公告
	 */
	String getNotice();
}
