package com.nv.VO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;



public interface SCOutput {
	
	String getId();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	Date getDate();
	
	String getStartTime();
	String getEndTime();
	
	CourseInterface getCourse();
	
	interface CourseInterface{
		String getId();
		String getTitle();
		String getIsActive();
		String getDescription();
		Integer getStudentCount();
		TeacherInterface getTeacher();
		SubjectInformation getSubject();
		ClassInformation getClassID();
	}
	
	
	interface TeacherInterface{
		Integer getTeacherID();
		String getName();
		
	}
}
