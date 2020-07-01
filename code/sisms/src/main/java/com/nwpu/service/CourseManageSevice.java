package com.nwpu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nwpu.model.Course;

public interface CourseManageSevice {

	List<Course> queryCourse();

	void deleteCourse(String ISBN);

	void insertCourse(Course course);

	Course queryCourseBycourseNumber(String courseNumber);

	void updateCourse(Course course);

	List<Course> queryCourseBycourseName(String courseName);
}
