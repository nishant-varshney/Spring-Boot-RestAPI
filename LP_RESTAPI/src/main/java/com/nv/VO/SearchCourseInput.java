package com.nv.VO;

import java.util.Date;
import java.util.List;

import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;
import com.nv.Model.TeacherInformation;

public class SearchCourseInput {
	
	private Integer userID;
	private String role;
	private String startDate;
	private String endDate;
	private List<ClassInformation> classID;
	private List<SubjectInformation> subject;
	private List<TeacherInformation> teacher;
	
	
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<ClassInformation> getClassID() {
		return classID;
	}
	public void setClassID(List<ClassInformation> classID) {
		this.classID = classID;
	}
	public List<SubjectInformation> getSubject() {
		return subject;
	}
	public void setSubject(List<SubjectInformation> subject) {
		this.subject = subject;
	}
	public List<TeacherInformation> getTeacher() {
		return teacher;
	}
	public void setTeacher(List<TeacherInformation> teacher) {
		this.teacher = teacher;
	}
	
	public SearchCourseInput(Integer userID, String role, String startDate,
			String endDate, List<ClassInformation> classID,
			List<SubjectInformation> subject, List<TeacherInformation> teacher) {
		super();
		this.userID = userID;
		this.role = role;
		this.startDate = startDate;
		this.endDate = endDate;
		this.classID = classID;
		this.subject = subject;
		this.teacher = teacher;
	}
	
	public SearchCourseInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
