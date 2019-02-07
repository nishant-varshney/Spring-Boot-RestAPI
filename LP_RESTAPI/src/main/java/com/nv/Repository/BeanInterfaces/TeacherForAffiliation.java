package com.nv.Repository.BeanInterfaces;

import java.util.List;

import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;

public interface TeacherForAffiliation {

	Integer getTeacherID();
	String getName();
	String getEmail();
	String getPhoneNo();
	String getAddress1();
	String getAddress2();
	
	String getPincode();
	String getCity();
	String getState();
	String getSubject();
	
	ClassInformation getClassID();
	
	String getExperience();
	String getLpExpeience();
	String getDescription();
}
