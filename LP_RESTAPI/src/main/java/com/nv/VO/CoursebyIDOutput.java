package com.nv.VO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;
import com.nv.Model.UserInformation;
import com.nv.VO.CourseOutput.TeacherInterface;

public interface CoursebyIDOutput {
	String getId();
	String getCreatedByID();
	String getCreatedByRole();
	String getTitle();
	String getDescription();
	String getFrequency();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	Date getStartDate();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	Date getEndDate();
	
	String getStartTime();
	String getEndTime();
	
	String getStudentCount();
	SubjectInformation getSubject();
	
	@JsonProperty(value="Class")
	ClassInformation getClassID();
	TeacherInterface getTeacher();
	ccInterface getCc();
	
	interface TeacherInterface{
		Integer getTeacherID();
		String getName();
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
		Date getCreationDate();
		String getDescription();
		String getQualifications();
		String getExperience();
		String getLpExpeience();
		String getEmail();
		String getAddress1();
		String getAddress2();
		String getPincode();
		String getCity();
		String getState();
		String getSubjectCount();
		String getClassCount();
		
		@JsonProperty(value="Phone No")
		@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "phoneNo")
		@JsonIdentityReference(alwaysAsId = true)
		UserInformation getUser();

	}
	

	interface ccInterface{
		Integer getId();
		String getName();
	}

}
