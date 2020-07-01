package com.nwpu.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nwpu.model.Course;
import com.nwpu.model.Office;
import com.nwpu.model.Application;
import com.nwpu.service.ApplicationService;

@Controller
@RequestMapping("/ApplicationManage")
public class ApplicationController {

	@Autowired
	private ApplicationService appService;
	
	//删除员工信息功能
	@RequestMapping("/deny")
	public void deleteStaff(ServletRequest servletRequest) throws ClassNotFoundException, SQLException{
		String appID = servletRequest.getParameter("appID");
		/*
		 * 以下代码新加的
		 * 
		 */
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("加载数据库驱动成功");
		String url="jdbc:mysql://localhost:3306/simss";
		String user="root";
		String password="";
		Connection connection=DriverManager.getConnection(url,user,password);
		System.out.println("数据库连接成功");
		String sqll="delete from application where appID="+appID;
		Statement stmt =connection.createStatement();
		stmt.execute(sqll);
		connection.close();
		/* 以上插入数据库
		 * 
		 * */
	}
	@RequestMapping("/applicationEdit")
	public String applicationEdit() {
		
		return  "/applicationEdit";
	}
	/*
	 * 职员入职
	 * */
	@RequestMapping("/entry")
	public void entry(ServletRequest servletRequest) throws SQLException, ClassNotFoundException{
		String appID = servletRequest.getParameter("appID");
		/*
		 * 以下代码新加的
		 * 
		 */
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("加载数据库驱动成功");
		String url="jdbc:mysql://localhost:3306/simss";
		String user="root";
		String password="";
		Connection connection=DriverManager.getConnection(url,user,password);
		System.out.println("数据库连接成功");
		String sqll="delete from application where appID="+appID;
		String sql="insert into staff (staffID,officeNumber,staffName,sex,identifyID,onJob,projectKind,isAdmin,leaveDate,groupID)" + 
				"values ("+appID+",'02','添加一','女','14578',1,'实验室',0,'2021-9-11','003')";
		Statement stmt =connection.createStatement();
		stmt.execute(sqll);
		stmt.executeUpdate(sql);
		connection.close();
		/* 以上插入数据库
		 * 
		 * */
	}
}