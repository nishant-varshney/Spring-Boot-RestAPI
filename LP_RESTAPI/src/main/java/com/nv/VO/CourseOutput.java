package com.nv.VO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;

public interface CourseOutput {
	
	String getId();
	String getTitle();
	String getIsActive();
	String getDescription();
	String getFrequency();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	Date getStartDate();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	Date getEndDate();
	
	String getStartTime();
	String getEndTime();
	SubjectInformation getSubject();
	ClassInformation getClassID();
	TeacherInterface getTeacher();
	
	interface TeacherInterface{
		Integer getTeacherID();
		String getName();
		
	}
	
}
