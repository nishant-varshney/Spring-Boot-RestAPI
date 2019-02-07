package com.nv.VO;

import java.util.List;

import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;
import com.nv.Model.TeacherInformation;

public class ExploreCourseInput {

	private String pincode;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private List<ClassInformation> classID;
	private List<SubjectInformation> subject;
	private String day;
	
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public ExploreCourseInput(String pincode, String startDate, String endDate,
			String startTime, String endTime, List<ClassInformation> classID,
			List<SubjectInformation> subject, String day) {
		super();
		this.pincode = pincode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.classID = classID;
		this.subject = subject;
		this.day = day;
	}
	
	
	
}
