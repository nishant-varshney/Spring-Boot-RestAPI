package com.nv.VO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;

public interface ExploreCourseOutput {

	String getId();
	String getTitle();
	String getDescription();
	String getFrequency();
	SubjectInformation getSubject();
	ClassInformation getClassID();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	Date getStartDate();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	Date getEndDate();
	
	String getStartTime();
	String getEndTime();
	

	TeacherInterface getTeacher();

	String getIsActive();
	
	interface TeacherInterface{
		Integer getTeacherID();
		String getName();
		String getEmail();
		String getQualifications();
		String getExperience();
		String getAddress1();
		String getAddress2();
		
	}
}
