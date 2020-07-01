package com.nwpu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.CourseManageDao;
import com.nwpu.model.Course;
import com.nwpu.service.CourseManageSevice;

@Service("CourseManageService")
public class CourseManageServiceImpl implements CourseManageSevice {

	@Autowired
	private CourseManageDao CourseManageDao;

	public List<Course> queryCourse() {

		return CourseManageDao.queryCourse();
	}

	public void deleteCourse(String courseNumber) {

		CourseManageDao.deleteCourse(courseNumber);
	}

	public void insertCourse(Course course) {

		CourseManageDao.insertCourse(course);
	}

	public Course queryCourseBycourseNumber(String courseNumber) {

		return CourseManageDao.queryCourseBycourseNumber(courseNumber);
	}

	public void updateCourse(Course course) {

		CourseManageDao.updateCourse(course);

	}

	public List<Course> queryCourseByCourseName(String courseName) {

		return CourseManageDao.queryCourseBycourseName(courseName);
	}

	@Override
	public List<Course> queryCourseBycourseName(String courseName) {
		// TODO 自动生成的方法存根
		return null;
	}

}
