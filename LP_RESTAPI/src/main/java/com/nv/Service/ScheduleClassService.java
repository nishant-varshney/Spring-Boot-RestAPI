package com.nv.Service;

import java.util.List;

import com.nv.Model.CourseInformation;
import com.nv.Model.ScheduleClassInformation;
import com.nv.VO.SCOutput;



public interface ScheduleClassService {
	
	String listSC(Integer Id,String role,String startDate,String endDate);
	
	Boolean addSC(CourseInformation courseInformation);
	Boolean updateSC(CourseInformation courseInformation);
	
	String updateSC(ScheduleClassInformation scInfo);
	String addSc(ScheduleClassInformation scInfo);

}
