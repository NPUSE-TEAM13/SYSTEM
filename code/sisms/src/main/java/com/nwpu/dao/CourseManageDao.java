package com.nwpu.dao;

import java.util.List;

import com.nwpu.model.Course;

public interface CourseManageDao {

	List<Course> queryCourse();

	void deleteCourse(String courseNumber);

	void insertCourse(Course course);

	Course queryCourseBycourseNumber(String courseNumber);

	void updateCourse(Course course);

	List<Course> queryCourseBycourseName(String courseName);
}
