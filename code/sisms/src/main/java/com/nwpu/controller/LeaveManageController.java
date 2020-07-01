package com.nwpu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nwpu.model.Staff;
import com.nwpu.model.StaffRecord;
import com.nwpu.service.LeaveService;
import com.nwpu.util.SendEmail;

@Controller
@RequestMapping("/leaveManage")
public class LeaveManageController {
	
	static String infoForQuery = "";
	static String flagForQuery = "";

	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping("/fycate")
	public String fycate() {
		
		return "/leaveManage/lx-fycate";
	}
	
	@RequestMapping("/jscate")
	public String jscate(Model model) {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		String year = sdf.format(date);
		String month = sdf2.format(date);

		if(Integer.valueOf(month) >= 9) {
			year = year + 1;
		}
		String leaveDate = year + "-8-9";
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = leaveService.queryBorrow(leaveDate);
		model.addAttribute("staffList", staffList);
		int size = staffList.size();
		model.addAttribute("size", size);
		
		return "/leaveManage/lx-jscate";
	}
	
	@RequestMapping("/xscate")
	public String xscate(Model model) {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		String year = sdf.format(date);
		String month = sdf2.format(date);

		if(Integer.valueOf(month) >= 9) {
			year = year + 1;
		}
		String leaveDate = year + "-8-9";
		//System.out.println(year);
		//System.out.println(month);
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = leaveService.queryStaff(leaveDate);
		model.addAttribute("staffList", staffList);
		int size = staffList.size();
		model.addAttribute("size", size);
		
		return "/leaveManage/lx-xscate";
	}
	
	@RequestMapping("/xslook")
	public String xslook(HttpServletRequest request, Model model) {
		
		String staffID = request.getSession().getAttribute("userName").toString();
		
		Staff staff = leaveService.queryStaffByID(staffID);
		model.addAttribute("staff", staff);
	
		Staff borrow = leaveService.queryBorrowByID(staffID);
		model.addAttribute("borrow", borrow);
		
		List<StaffRecord> recordList = new ArrayList<StaffRecord>();
		recordList = leaveService.queryRecordByID(staffID);
		model.addAttribute("recordList", recordList);
		
		return "/leaveManage/lx-xslook";
	}
	
	@RequestMapping("/information")
	public String information(HttpServletRequest request, Model model) {
		
		String staffID = request.getSession().getAttribute("userName").toString();
		
		Staff staff = leaveService.queryStaffByID(staffID);
		model.addAttribute("staff", staff);
	
		Staff borrow = leaveService.queryBorrowByID(staffID);
		model.addAttribute("borrow", borrow);
		
		List<StaffRecord> recordList = new ArrayList<StaffRecord>();
		recordList = leaveService.queryRecordByID(staffID);
		model.addAttribute("recordList", recordList);
		
		return "/leaveManage/information";
	}
	
	@RequestMapping("/informationEdit")
	public String informationEdit(HttpServletRequest request, Model model) {
		
		String staffID = request.getSession().getAttribute("userName").toString();
		
		Staff staff = leaveService.queryStaffByID(staffID);
		model.addAttribute("staff", staff);
	
		Staff borrow = leaveService.queryBorrowByID(staffID);
		model.addAttribute("borrow", borrow);
		
		List<StaffRecord> recordList = new ArrayList<StaffRecord>();
		recordList = leaveService.queryRecordByID(staffID);
		model.addAttribute("recordList", recordList);
		
		return "/leaveManage/informationEdit";
	}
	
	@RequestMapping("/email")
	public void email(@RequestParam("param1") String[] param1) {
		
		for(int i = 0; i < param1.length; i++) {
			//System.out.println(param1[i]);
			//dormService.deleteOffice(param1[i]);
			SendEmail.email("974047192@qq.com");
		}
	}
	
	//员工信息查询功能
		@RequestMapping("/Query")
		public String Query( Model model){
			String queryData = infoForQuery;
			String flag = flagForQuery;
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
			String year = sdf.format(date);
			String month = sdf2.format(date);

			if(Integer.valueOf(month) >= 9) {
				year = year + 1;
			}
			String leaveDate = year + "-8-9";

			List<Staff> staffList = new ArrayList<Staff>();	
			
			if(flag.equals("1")){
				if(queryData.equals("")){
					staffList = leaveService.queryStaff(leaveDate);
				}else{
					//按员工姓名模糊查询
					List<Staff> firstList = leaveService.queryStaffBystaffName(infoForQuery,leaveDate);
					if(!(firstList.isEmpty())){
						staffList = firstList;
					}
					
					//按员工号精确查询
					Staff staff = leaveService.queryStaffByID(infoForQuery);
					if(!(staff == null)){
						staffList.add(staff);
					}
					
					//均未查询到结果
					if(staffList.isEmpty()){
						staffList = leaveService.queryStaff(leaveDate);
					}
				}
			}else{
				if(queryData.equals("")){
					staffList = leaveService.queryBorrow(leaveDate);
				}else{
					//按员工姓名模糊查询
					List<Staff> firstList = leaveService.queryBorrowStaffBystaffName(infoForQuery,leaveDate);
					if(!(firstList.isEmpty())){
						staffList = firstList;
					}
					
					//按员工号精确查询
					Staff staff = leaveService.queryBorrowByID(infoForQuery);
					if(!(staff == null)){
						staffList.add(staff);
					}
					
					//均未查询到结果
					if(staffList.isEmpty()){
						staffList = leaveService.queryBorrow(leaveDate);
					}
				}
			}
			

			int length = staffList.size();
			model.addAttribute("staffList", staffList);
			model.addAttribute("size", length);
			
			if(flag.equals("1")){
				return "leaveManage/lx-xscate";
			}
			else {
				return "leaveManage/lx-jscate";
			}
			
		}
		
		//提供员工信息查询功能所需数据
		@RequestMapping("/QueryHelp")
		public void QueryHelp(ServletRequest request){
			infoForQuery = request.getParameter("info");
			flagForQuery = request.getParameter("flag");		
		}
		@RequestMapping("/askforleave")
		public String askforleave(){
			return "leaveManage/askforleave";
		}

}
