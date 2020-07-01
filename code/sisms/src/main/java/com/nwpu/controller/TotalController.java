package com.nwpu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nwpu.model.User;
import com.nwpu.service.LoginService;

@Controller
public class TotalController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) {
		
		boolean isAdmin;
		//获得session对象
		HttpSession session = request.getSession();
		String randomKey = session.getAttribute("randCode").toString();
		//获取用户的登录密码
		String identifyID = loginService.login(user.getUserName());
		if(identifyID == null) {
			return "";//不存在该用户
		}else {
			isAdmin = loginService.isAdmin(user.getUserName());
			long temp = Long.parseLong(identifyID) % 1000000; 
			String password = String.valueOf(temp);
			//登录密码判断
			if(password.equals(user.getPassword())) {
				//判断验证码
				if(randomKey.equals(user.getVerificationCode())) {
					session.setAttribute("userName", user.getUserName());
					session.setAttribute("password", user.getPassword());
					if(isAdmin == true){
						return "/indexAdmin";	//用户为管理员，返回管理员主页
					}else{
						return "/indexStaff";	//用户为员工，返回员工页面
					}
				}else{
					return "";//验证码错误
				}
			} else {
				return "";//密码错误
			}
		}
	}
	
	
	@RequestMapping("/loginTest")
	@ResponseBody
	public Map<String,String> loginTest(HttpServletRequest request) {
		
		String userName = request.getParameter("userName");
		String password1 = request.getParameter("password");
		String VerificationCode = request.getParameter("VerificationCode");
		
		System.out.println("test");
		Map<String,String> map = new HashMap<String, String>();
		boolean isAdmin;
		//获得session对象
		HttpSession session = request.getSession();
		String randomKey = session.getAttribute("randCode").toString();
		//获取用户的登录密码
		String identifyID = loginService.login(userName);
		if(identifyID == null) {
			map.put("result", "用户不存在");
			return map;//不存在该用户
		}else {
			isAdmin = loginService.isAdmin(userName);
			long temp = Long.parseLong(identifyID) % 1000000; 
			String password = String.valueOf(temp);
			//登录密码判断
			if(password.equals(password1)) {
				//判断验证码
				if(equal(randomKey, VerificationCode)) {
					session.setAttribute("userName", userName);
					session.setAttribute("password", password);
					if(isAdmin == true){
						map.put("result", "admin");
						return map;	//用户为管理员，返回管理员主页
					}else{
						map.put("result", "staff");
						return map;	//用户为员工，返回员工页面
					}
				}else{
					map.put("result", "验证码错误");
					return map;//验证码错误
				}
			} else {
				map.put("result", "密码错误");
				return map;//密码错误
			}
		}
	}
	
	@RequestMapping("/adminLogin")
	public String adminLogin(HttpServletRequest request, Model model) {
		
		String userName = request.getSession().getAttribute("userName").toString();
		String staffName = loginService.getNameById(userName);
		model.addAttribute("name", staffName);
		return "/indexAdmin";
	}
	
	@RequestMapping("/staffLogin")
	public String staffLogin(HttpServletRequest request, Model model) {
		
		String userName = request.getSession().getAttribute("userName").toString();
		String staffName = loginService.getNameById(userName);
		model.addAttribute("name", staffName);
		return "/indexStaff";
	}
	
	@RequestMapping("/room-add")
	public String roomAdd() {
		
		
		return "/officeManage/office-add";
	}
	
	@RequestMapping("/officeApply-add")
	public String officeApplyAdd() {
		
		return "/officeManage/officeApply-add";
	}
	
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request, Model model) {
		
		String userName = request.getSession().getAttribute("userName").toString();
		String staffName = loginService.getNameById(userName);
		model.addAttribute("name", staffName);
		
		Date now = new Date(); 
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm");
		String time = ft.format(now);
		model.addAttribute("time", time);
		
		String context = loginService.getNotice();
		model.addAttribute("context", context);
		return "/welcome";
	}
	
	public boolean equal(String input, String output) {
		
		if(input.toLowerCase().equals(output.toLowerCase())) {
			return true;
		}else {
			return false;
		}
	}
}
