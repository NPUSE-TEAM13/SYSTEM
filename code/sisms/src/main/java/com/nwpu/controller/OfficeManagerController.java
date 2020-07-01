package com.nwpu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.nwpu.model.Office;import com.nwpu.model.Course;
import com.nwpu.model.OfficeApplication;
import com.nwpu.model.Staff;
import com.nwpu.service.OfficeService;
import com.nwpu.serviceImpl.OfficeServiceImpl;

/*
 * 宿舍管理控制器
 */
@Controller
@RequestMapping("/officeManage")
public class OfficeManagerController {
	
	static String officeNumberForEdit="";
	static String staffNumberForEdit="";
	
	@Autowired
	private OfficeService officeService;
	
	@RequestMapping("/addOffice")
	public void addOffice(Office office) {
		
		office.setFull(false);
		System.out.println(office.toString());
		officeService.insertOffice(office);
	}
	
	@RequestMapping("/addRoom")
	public String addRoom(Model model) {
		
		List<Office> officeList = new ArrayList<Office>();
		officeList = officeService.selectOffice();
		int length = officeList.size();
		model.addAttribute("dList", officeList);
		model.addAttribute("size", length);
		
		return "/officeManage/addRoom";
	}
	
	@RequestMapping("/checkIn")
	public String checkIn(Model model) {
		
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = officeService.queryStaff();
		int length = staffList.size();
		model.addAttribute("length", length);
		model.addAttribute("staffList", staffList);
		return "/officeManage/checkIn";
	}
	
	@RequestMapping("/office-staff")
	public String queryApply(Model model, HttpServletRequest request) {
		
		String staffID = request.getSession().getAttribute("userName").toString();
		Staff staff = officeService.getStaffById(staffID);
		String staffName = staff.getStaffName();
		model.addAttribute("name", staffName);
		
		String officeNumber = staff.getOfficeNumber();
		model.addAttribute("officeNumber", officeNumber);
		
		List<Staff> staffList = officeService.queryStaffByDrom(staff.getOfficeNumber());
		model.addAttribute("staffList", staffList);
		
		List<OfficeApplication> applyList = new ArrayList<OfficeApplication>();
		applyList = officeService.queryApply(staffID);
		int length = applyList.size();
		model.addAttribute("length", length);
		model.addAttribute("applyList", applyList);
		
		return "/officeManage/office-staff";
	}
	
	@RequestMapping("/insertApply")
	public void insertApply(HttpServletRequest request, OfficeApplication officeApply) {
		
		String userName = request.getSession().getAttribute("userName").toString();
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
		officeApply.setApplyNumber(ft.format(now));
		officeApply.setStaffID(userName);
		officeApply.setStatus(false);
		String date = dateFormat.format( now ); 
		officeApply.setDate(date);
		
		officeService.insertApply(officeApply);
	}
	
	@RequestMapping("/deleteOffice")
	public void deleteOffice(@RequestParam("param1") String[] param1) {
		
		for(int i = 0; i < param1.length; i++) {
			//System.out.println(param1[i]);
			officeService.deleteOffice(param1[i]);
		}
		//System.out.println(param1);
		//String[] selected = request.getParameterValues("ids");
		
	}
	
	/*@RequestMapping("/findStaffById")
	public String findStaffById(ServletRequest request, Model model) {
		
		String queryData = request.getParameter("queryData");
		System.out.println(queryData);
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = officeService.queryStaffById(queryData);
		model.addAttribute("staffList", staffList);
		//model.addAttribute("lengh", 1);
		return "error";
	}*/
	
	@RequestMapping("/showQueryStaff")
	public String showQueryStaff(ServletRequest request, Model model) {
		
		String queryData = request.getParameter("queryData");
		if(queryData == "") {
			List<Staff> staffList = new ArrayList<Staff>();
			staffList = officeService.queryStaff();
			model.addAttribute("staffList", staffList);
			int length = staffList.size();
			model.addAttribute("length", length);
		}else {
			System.out.println(queryData);
			List<Staff> staffList = new ArrayList<Staff>();
			staffList = officeService.queryStaffById(queryData);
			model.addAttribute("staffList", staffList);
			int length = staffList.size();
			model.addAttribute("length", length);
		}
		return "/officeManage/checkIn";
	}
	
    //宿舍编辑页面
	@RequestMapping("/officeEdit")
	public String officeEdit(Model model){

		Office office = officeService.queryOfficeByOfficeNumber(officeNumberForEdit);
		model.addAttribute("office", office);
		return "officeManage/officeEdit";
	}
	
	//宿舍编辑页面所需数据
	@RequestMapping("/officeEditHelp")
	public void officeEditHelp(ServletRequest servletRequest){
		officeNumberForEdit = servletRequest.getParameter("officeNumber");
	}
	
	//宿舍信息更新
	@RequestMapping("/officeUpdate")
	public void officeUpdate(Office office){
		officeService.updateOffice(office);
	}
	
	//入住管理编辑页面
	@RequestMapping("/checkEdit")
	public String checkEdit(Model model){
		Staff staff = officeService.getStaffById(staffNumberForEdit);
		model.addAttribute("staff", staff);
		return "officeManage/checkEdit";
	}
	
	//入住管理编辑页面所需数据
	@RequestMapping("/checkEditHelp")
	public void checkEditHelp(ServletRequest servletRequest){
		staffNumberForEdit = servletRequest.getParameter("staffID");
	}
	
	//宿舍信息更新
	@RequestMapping("/checkUpdate")
	public void checkUpdate(Staff staff){
		officeService.updateStaff(staff);
	}
	
	@RequestMapping("/handelOfficeApply")
	public String handleOfficeApply(Model model) {
		
		List<OfficeApplication> applyList = new ArrayList<OfficeApplication>();
		applyList = officeService.queryAllApply();
		int length = applyList.size();
		model.addAttribute("applyList", applyList);
		model.addAttribute("length", length);
		
		return "/officeManage/handelOfficeApply";
	}
	
	@RequestMapping("/updateStatus")
	public void updateStatus(@RequestParam("applyNumber") String applyNumber) {
		
		officeService.updateApply(applyNumber);
	}
}
