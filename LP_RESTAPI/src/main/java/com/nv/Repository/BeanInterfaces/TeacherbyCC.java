package com.nv.Repository.BeanInterfaces;

import java.util.Date;

import com.nv.Model.TeacherInformation;

public interface TeacherbyCC {
	TeacherInformation getTeacher();
	String getRequestByRole();
	Date getRequestedDate();
	 Date getConfirmationDate() ;

}
