package com.nv.Service;

import java.util.List;

import com.nv.Model.CourseInformation;
import com.nv.VO.CoursebyIDOutput;
import com.nv.VO.ExploreCourseInput;
import com.nv.VO.SCOutput;
import com.nv.VO.SearchCCInput;
import com.nv.VO.SearchCourseInput;





public interface CourseInfoService {
	
	List<CourseInformation> listCourse();
	CoursebyIDOutput findOne(Integer id);
	String addCourse(CourseInformation courseInfo);
	String deleteCourse(Integer id);

	String updateCourse(CourseInformation courseInfo);
	String updateCourseStatus(Integer id,String active);
	
	
	String searchCourse(SearchCourseInput searchInput);
	
	String exploreCourse(ExploreCourseInput exploreCourse);
	
	//SCOutput getSCInfo();
}
