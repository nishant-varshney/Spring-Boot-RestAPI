package com.nv.Service;

import java.util.List;

import com.nv.Model.CourseStudentMapping;


public interface CourseStudentService {

	List<CourseStudentMapping> listCourseStudent();
	String addCourseStudentMapping(List<CourseStudentMapping> courseStudent);
	String deleteCourseStudentMapping(List<CourseStudentMapping> courseStudent);

}
