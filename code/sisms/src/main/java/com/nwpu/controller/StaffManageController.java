package com.nwpu.controller;

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
import com.nwpu.model.Staff;
import com.nwpu.service.StaffService;

@Controller
@RequestMapping("/staffManage")
public class StaffManageController {

	static String staffIDForEdit="";
	static String infoForQuery="";
	static String flagForQuery="";
	@Autowired
	private StaffService staffService;
	
	//新员工数据导入与导出页面
	@RequestMapping("/newStaffIfo")
	public String newStaffInfo(Model model) {
		
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = staffService.queryStaff();
		model.addAttribute("staffList", staffList);
		int size = staffList.size();
		model.addAttribute("size", size);
		
		return "/staffManage/newStaffIfo";
	}
	
	
	//新员工管理（原编制员工号）页面
	@RequestMapping("/setStaffID")
	public String setStaffID(Model model) {
		List<Staff> firstList = new ArrayList<Staff>();
		List<Staff> staffList = new ArrayList<Staff>();
		
		firstList = staffService.queryStaff();
		
		for(Staff staff : firstList){
			if(staff.getOfficeNumber().equals("waitForSet")){
				staffList.add(staff);
			}
		}
		model.addAttribute("staffList", staffList);
		int size = staffList.size();
		model.addAttribute("size", size);
		return "/staffManage/setStaffID";
	}
	
	//插入员工信息
	@RequestMapping("/insertStaff")
	public void insertStaff(Staff staff) {
		
		int size = staffService.queryStaff().size();
		staff.setStaffID(String.valueOf(size + 1));
		staff.setOfficeNumber("waitForSet");
		staff.setGroupID("waitForSet");
		staff.setOnJob(false);
		staff.setLeaveDate("2019-8-30");
		staff.setAdmin(false);
		staffService.insertStaff(staff);
	}
	
	//添加员工信息页面
	@RequestMapping("/addStaff")
	public String addStaff() {
		
		return "staffManage/newStaffAdd";
	}
	
	
	//一键编制页面
	@RequestMapping("/setIDps")
	public String setIDps() {
		
		return "/staffManage/setIDps";
	}
	
	//删除员工信息功能
	@RequestMapping("/deleteStaff")
	public void deleteStaff(ServletRequest servletRequest){
		String staffID = servletRequest.getParameter("staffID");
		staffService.deleteStaff(staffID);
	}
	
	//批量删除员工信息功能
	@RequestMapping("/deleteAll")
	public void deleteAll(@RequestParam("deleteData") String[] param1) {
		
		for(int i = 0; i < param1.length; i++){
			staffService.deleteStaff(param1[i]);
		}
	}
	
	//员工信息编辑页面
	@RequestMapping("/staffEdit")
	public String staffEdit(Model model){
		
		Staff staff = staffService.queryStaffBystaffID(staffIDForEdit);
		model.addAttribute("staff", staff);
		return "staffManage/staffEdit";
	}
	
	//员工信息编辑页面所需数据
	@RequestMapping("/staffEditHelp")
	public void staffEditHelp(ServletRequest servletRequest){
		
		staffIDForEdit = servletRequest.getParameter("staffID");
	}
	
	//更新员工信息
	@RequestMapping("/updateStaff")
	public void updateStaff(Staff staff){
		staffService.updateStaff(staff);
	}
	
	//员工信息查询功能
	//旺仔益说他是傻逼
	@RequestMapping("/Query")
	public String Query( Model model){
		String queryData = infoForQuery;
		String flag = flagForQuery;

		List<Staff> staffList = new ArrayList<Staff>();	
		if(queryData.equals("")){
			staffList = staffService.queryStaff();
		}else{
			//按员工姓名模糊查询
			List<Staff> firstList = staffService.queryStaffBystaffName(infoForQuery);
			if(!(firstList.isEmpty())){
				staffList = firstList;
			}
			
			//按员工号精确查询
			Staff staff = staffService.queryStaffBystaffID(infoForQuery);
			if(!(staff == null)){
				staffList.add(staff);
			}
			
			//均未查询到结果
			if(staffList.isEmpty()){
				staffList = staffService.queryStaff();
			}
		}
		
		if(flag.equals("2")){
			List<Staff> another = new ArrayList<Staff>();
			for(Staff staff : staffList){
				if(staff.getOfficeNumber().equals("waitForSet")){
					another.add(staff);
				}
			}
			staffList = another;
		}

		int length = staffList.size();
		model.addAttribute("staffList", staffList);
		model.addAttribute("size", length);
		
		if(flag.equals("1")){
			return "staffManage/newStaffIfo";
		}
		else {
			return "staffManage/setStaffID";
		}
		
	}
	
	//提供员工信息查询功能所需数据
	@RequestMapping("/QueryHelp")
	public void QueryHelp(ServletRequest request){
		infoForQuery = request.getParameter("info");
		flagForQuery = request.getParameter("flag");		
	}

	/*@RequestMapping("/queryNewStaff")
	public void queryNewStaff(Model model) {
		
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = staffService.queryNewStaff();
		model.addAttribute("staffList", staffList);
		int size = staffList.size();
		model.addAttribute("size", size);
		
		return "/staffManage";
	}*/

	@RequestMapping("/setStaffIdPre")
	public void setStaffID(ServletRequest request) {
		
		String prefix = request.getParameter("prefix");
		//System.out.println(prefix);
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = staffService.queryNewStaff();
		//int size = staffList.size();
		String newID;
		for(int j = 0; j < staffList.size(); j++) {
			int i = j + 1;
			if(i < 10) {
				newID = prefix + "000" + String.valueOf(i);
			}else if(i < 100){
				newID = prefix + "00" + String.valueOf(i);
			}else if(i < 1000) {
				newID = prefix + "0" + String.valueOf(i);
			}else {
				newID = prefix + String.valueOf(i);
			}
			staffList.get(j).setStaffID(newID);
			staffService.updateStaff(staffList.get(j));
			
		}
	}
	
	/*
	 * 批量分配办公室
	 */
	@RequestMapping("/setOfficeGroup")
	public void setOfficeGroup() {
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
		String year = dateFormat.format(now);
		int temp = Integer.valueOf(year) % 100;
		String groupPrefix = String.valueOf(temp);
		//System.out.println("test");
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = staffService.queryNewStaff();
		int size = staffList.size();
		
		int groupNumber = 1, j = 1;
		

		for(Staff staff : staffList) {
			if(size > 10) {
				for(int i = 0; i < 25; i++) {
					if(groupNumber < 10) {
						staff.setGroupID(groupPrefix + "00" + groupNumber); 
						size--;
					} else if (groupNumber < 100) {
						staff.setGroupID(groupPrefix + "0" + groupNumber);
						size--;
					} else {
						staff.setGroupID(groupPrefix + groupNumber);
						size--;
					}
				}
				if(size > 10) {
					groupNumber++;
				}
			}else {
				staff.setGroupID(groupPrefix + "00" + j);
				if(j < groupNumber){
					j++;
				}else {
					j = 1;
				}
			}
		}
		
		//更新数据
		for(Staff staff : staffList) {
			staffService.updateStaff(staff);
		}
				
		//根据宿舍类型分配给员工宿舍
		
		List<Office> officeListGirl = new ArrayList<Office>();
		List<Office> officeListBoy = new ArrayList<Office>();
		officeListGirl = staffService.queryFemaleOffice();
		officeListBoy = staffService.queryMaleOffice();
		
		List<Staff> staffListGirl = new ArrayList<Staff>();
		List<Staff> staffListBoy = new ArrayList<Staff>();
		staffListGirl = staffService.queryNewStaffFemale();
		staffListBoy = staffService.queryNewStaffMale();
		int sizeGirl = staffListGirl.size();
		int sizeBoy = staffListBoy.size();
		

		int flagGirl = 0;
		if(sizeGirl != 0) {
			
			for(Office office : officeListGirl) {
				for(int h = 0; h < office.getOfficeKind(); h++) {
					staffListGirl.get(flagGirl).setOfficeNumber(office.getOfficeNumber());
					if(flagGirl >= sizeGirl-1) {
						break;
					}else {
						flagGirl++;
					}
				}
				office.setFull(true);
				staffService.updateOffice(office);
				if(flagGirl >= sizeGirl-1) {
					break;
				}
			}
			
			for(Staff staff : staffListGirl) {
				staffService.updateStaff(staff);
			}
		}
		
	
		int flagBoy = 0;
		
		if(sizeBoy != 0) {
			
			for(Office office : officeListBoy) {
				for(int h = 0; h < office.getOfficeKind(); h++) {
					staffListBoy.get(flagBoy).setOfficeNumber(office.getOfficeNumber());
					if(flagBoy >= sizeBoy-1) {
						break;
					}else {
						flagBoy++;
					}
				}
				office.setFull(true);
				staffService.updateOffice(office);
				if(flagBoy >= sizeBoy-1) {
					break;
				}
			}
			
			for(Staff staff : staffListBoy) {
				staffService.updateStaff(staff);
			}
		}
	}
	
	//显示员工的数量变化
		@RequestMapping("/stuPicture")
		public String stuPicture(ServletRequest request){
			return "staffManage/stuPicture";	
		}
}

