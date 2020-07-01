package com.nwpu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.nwpu.model.StaffRecord;
import com.nwpu.service.StaffRecordService;
import javax.swing.JOptionPane;

@Controller
@RequestMapping("/static")
public class StaffRecordController {

	@Autowired
	private StaffRecordService staffRecordService;

	/*
	 * 列出员工交易记录(admin)
	 */
	@RequestMapping("/paymentManage_admin/list")
	public String getSelectStaff(Model model) {
		//System.out.println("controller运行");

		List<StaffRecord> staffList = staffRecordService.getStaffRecordListAll();

		model.addAttribute("staffList", staffList);

		//System.out.println(staffList.get(1).getIsPay());
		// return "redirect:http://localhost:8080/simss/index.jsp";
		return "/paymentManage_admin/tradingRecord";
	}
	
	/*
	 * 列出员工交易记录(staff)
	 */
	@RequestMapping("/paymentManage_staff/list")
	public String getSelectStaff_S(Model model, HttpServletRequest request) {
		//System.out.println("controller运行");

		String staffID = request.getSession().getAttribute("userName").toString();
		List<StaffRecord> staffList = staffRecordService.getStaffRecordList(staffID);

		model.addAttribute("staffList", staffList);

		//System.out.println("test");
		// return "redirect:http://localhost:8080/simss/index.jsp";
		return "/paymentManage_staff/tradingRecord";
	}
	
	/*
	 * 列出员工缴费通知（staff）
	 */
	@RequestMapping("/paymentManage_staff/Info")
	public String getStaffRecordInfo(Model model, HttpServletRequest request) {
		//System.out.println("controller运行");

		String staffID = request.getSession().getAttribute("userName").toString();
		List<StaffRecord> staffList = staffRecordService.getStaffRecordList(staffID);

		model.addAttribute("staffList", staffList);

		//System.out.println("test");
		// return "redirect:http://localhost:8080/simss/index.jsp";
		return "/paymentManage_staff/paymentInfo";
	}
	
	/*
	 * 根据员工号查询交易记录
	 */
	@GetMapping("/paymentManage_admin/tradingRecord")
	public String queryStaffRecordByStaffId(String check, Model model){
		//System.out.println("查询");
		//System.out.println(check);
		if(check == ""){
			List<StaffRecord> staffList = staffRecordService.getStaffRecordListAll();
			model.addAttribute("staffList", staffList);
			return "/paymentManage_admin/tradingRecord";
		}
		else{
			List<StaffRecord> staffList = staffRecordService.getStaffRecordByStaffId(check);
			model.addAttribute("staffList", staffList);
			return "/paymentManage_admin/tradingRecord";
		}
	}


	/*
	 * 更新员工交易记录（即确认缴费）
	 */
	@GetMapping("/paymentManage_staff/update")
	public String deleteStaffRecordByKey(HttpServletRequest request, Model model){
		
		String staffID = request.getSession().getAttribute("userName").toString();
		String key = request.getParameter("payNumber");
		//System.out.println(key);
		staffRecordService.updateStaffRecordByKey(key);
		List<StaffRecord> staffList = staffRecordService.getStaffRecordList(staffID);
		model.addAttribute("staffList", staffList);
		
		return "/paymentManage_staff/paymentInfo";
	}
	
	/*
	 * 发布收费信息
	 */
	@PostMapping("/paymentManage_admin/insert")
	public String insertStaffRecord(Model model, StaffRecord record){
		
		String rec = queryStaffNameByID(record.getStaffID());
		if(rec == "error"){
			List<StaffRecord> staffList = staffRecordService.getStaffRecordListAll();
			model.addAttribute("staffList", staffList);
			JOptionPane.showMessageDialog(null, "You input is not stay");
			return "/paymentManage_admin/otherPayment";
		}
		else{
		String firstID, lastID;
		String[] temp = record.getStaffID().split("-");
		if(temp.length > 1) {
			firstID = temp[0];
			lastID = temp[1];
			int i = 0;
			do {
				record.setStaffID(firstID);
				Date now = new Date(); 
				SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
				record.setPayNumber(ft.format(now) + i);
				record.setStaffName(queryStaffNameByID(record.getStaffID()));
				staffRecordService.insertStaffRecord(record);
				firstID = String.valueOf((Integer.valueOf(firstID) + 1));
				i++;
			}while(!firstID.equals(lastID));
		}else {
			Date now = new Date(); 
			SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
			record.setPayNumber(ft.format(now));
			record.setStaffName(queryStaffNameByID(record.getStaffID()));
			staffRecordService.insertStaffRecord(record);

		}

		List<StaffRecord> staffList = staffRecordService.getStaffRecordListAll();
		model.addAttribute("staffList", staffList);
		
		return "/paymentManage_admin/otherPayment";
		}
	}
	
	/*
	 * 显示已发布缴费通知（admin）
	 */
	@RequestMapping("/paymentManage_admin/other")
	public String sendStaffRecordInfo(Model model) {
		//System.out.println("controller运行");

		List<StaffRecord> staffList = staffRecordService.getStaffRecordListAll();

		model.addAttribute("staffList", staffList);

		//System.out.println("test");
		// return "redirect:http://localhost:8080/simss/index.jsp";
		return "/paymentManage_admin/otherPayment";
	}
	
	
	public String queryStaffNameByID(String staffID) {	
		String result = staffRecordService.queryStaffNameByID(staffID);
		if(result == null){
			return "error";
		}
		else{
			return staffRecordService.queryStaffNameByID(staffID);
		}
	}
	
	/*
	 @RequestMapping("/test") 
	 public String test(Model model)
	 { 
		 String s = "test";
		 model.addAttribute("a", s);
		 System.out.println("执行!");
		return "index";
	 }
	 */
	 

	/*
	 * @RequestMapping(value = "myTest1") public void
	 * getInfo1(@RequestParam(required=false) String account,String pwd){
	 * System.out.println("账号是："+account); System.out.println("密码是："+pwd); }
	 */
}
