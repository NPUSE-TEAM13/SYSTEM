package com.nwpu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nwpu.dao.CourseManageDao;
import com.nwpu.model.Course;
import com.nwpu.service.CourseManageSevice;

/*
 * 课程管理控制类
 */
@Controller
@RequestMapping("/courseManage")
public class CourseManageController {
	
	//所编辑课程的courseNumber
	static String courseNumberForEdit="";
	//所查询的信息内容
	static String infoForQuery="";
	//申请查询的页面
	static String flagForQuery="";
	
	@Autowired
	private CourseManageSevice CourseManageService;
	
	//课程信息页面
	@RequestMapping("/jcInformation")
	public String jcInformation(Model model) {
		
		List<Course> courseList = new ArrayList<Course>();
		courseList = CourseManageService.queryCourse();
		int length = courseList.size();
		model.addAttribute("courseList", courseList);
		model.addAttribute("length", length);
		return "CourseManage/jcinformation";
	}
	
	//课程审定页面
	@RequestMapping("/jcCheck")
	public String jcCheck(Model model) {
		
		List<Course> courseList = new ArrayList<Course>();
		courseList = CourseManageService.queryCourse();
		int length = courseList.size();
		model.addAttribute("courseList", courseList);
		model.addAttribute("length", length);
		
		return "CourseManage/jccheck";
	}
	
	//课程征订页面
	@RequestMapping("/jcPurchase")
	public String jcPurchase(Model model){
		List<Course> courseList = new ArrayList<Course>();
		courseList = CourseManageService.queryCourse();
		int length = courseList.size();
		//根据征订时间有无计算数量
		int num = 0;
		for(Course course : courseList){
			if(course.getSemeter()!=null){
				num++;
			}
		}
		model.addAttribute("courseList", courseList);
		model.addAttribute("length", length);
		model.addAttribute("num", num);
		return "CourseManage/jcpurchase";
	}
	
	//课程发放页面
	@RequestMapping("/jcProvide")
	public String jcProvide(Model model){
		List<Course> courseList = new ArrayList<Course>();
		courseList = CourseManageService.queryCourse();
		int length = courseList.size();
		//根据是否发放有无计算数量
		int num = 0;
		for(Course course : courseList){
			if(course.getProviding()!=null){
				num++;
			}
		}
		model.addAttribute("courseList", courseList);
		model.addAttribute("length", length);
		model.addAttribute("num", num);
		return "CourseManage/jcprovide";
	}
	
	//课程编辑页面
	@RequestMapping("/jcEdit")
	public String jcEdit(Model model){

		System.out.println(courseNumberForEdit);
		Course course = CourseManageService.queryCourseBycourseNumber(courseNumberForEdit);
		model.addAttribute("course", course);
		return "CourseManage/jc-edit";
	}
	
	//课程编辑页面所需数据
	@RequestMapping("/jcEditHelp")
	public void jcEditHelp(ServletRequest servletRequest){
		
		courseNumberForEdit = servletRequest.getParameter("courseNumber");
	}
	
	
	//课程添加页面
	@RequestMapping("/jcAdd")
	public String jcAdd(){
		
		return "CourseManage/jc-add";
	}
	
	//课程删除功能
	@RequestMapping("/Delete")
	public void jcDelete(ServletRequest servletRequest) {
	
		String courseNumber = servletRequest.getParameter("courseNumber");
		CourseManageService.deleteCourse(courseNumber);
	}
	
	//课程添加功能
	@RequestMapping("/Add")
	public void Add(Course course){
		if(course.getSemeter().equals("")){
			course.setSemeter(null);
		}
		if(course.getProviding().equals("")){
			course.setProviding(null);
		}
		CourseManageService.insertCourse(course);
	}
	
	private void println(String courseName) {
		// TODO 自动生成的方法存根
		
	}

	private void println() {
		// TODO 自动生成的方法存根
		
	}

	//课程更新功能
	@RequestMapping("/Update")
	public void Update(Course course){
		if(course.getSemeter().equals("")){
			course.setSemeter(null);
		}
		
		CourseManageService.updateCourse(course);
	}
	
	//课程查询功能
	@RequestMapping("/Query")
	public String Query( Model model){
		String queryData = infoForQuery;
		String flag = flagForQuery;

		List<Course> courseList = new ArrayList<Course>();	
		if(queryData.equals("")){
			courseList = CourseManageService.queryCourse();
		}else{
			//按名称模糊查询
			List<Course> firstList = CourseManageService.queryCourseBycourseName(queryData);
			if(!(firstList.isEmpty())){
				courseList = firstList;
			}
			
			//按courseNumber精确查询
			Course course = CourseManageService.queryCourseBycourseNumber(queryData);
			if(!(course == null)){
				courseList.add(course);
			}
			
			//均未查询到结果
			if(courseList.isEmpty()){
				courseList = CourseManageService.queryCourse();
			}
		}

		int length = courseList.size();
		System.out.println(length);
		model.addAttribute("courseList", courseList);
		model.addAttribute("length", length);
		
		//征订页面数量
		if(flag.equals("3")){
			int num = 0;
			for(Course course : courseList){
				if(course.getSemeter()!=null){
					num++;
				}
			}
			model.addAttribute("num", num);
		}
		
		//发放页面数量
		if(flag.equals("4")){
			int num = 0;
			for(Course course : courseList){
				if(course.getProviding()!=null){
					num++;
				}
			}
			model.addAttribute("num", num);
		}
		if(flag.equals("1")){
			return "CourseManage/jcinformation";
		}
		else if(flag.equals("2")){
			return "CourseManage/jccheck";
		}
		else if(flag.equals("3")){
			return "CourseManage/jcpurchase";
		}else{
			return "CourseManage/jcprovide";
		}
	}
	
	//提供课程查询功能所需数据
	@RequestMapping("/QueryHelp")
	public void QueryHelp(ServletRequest request){
		infoForQuery = request.getParameter("info");
		flagForQuery = request.getParameter("flag");		
	}
	
	//课程批量删除功能
	@RequestMapping("/deleteAll")
	public void deleteAll(@RequestParam("deleteData") String[] param1) {
		
		for(int i = 0; i < param1.length; i++){
			CourseManageService.deleteCourse(param1[i]);
		}
	}

}
