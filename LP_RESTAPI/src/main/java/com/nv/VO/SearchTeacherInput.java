package com.nv.VO;

import java.util.Collection;
import java.util.List;

import com.nv.Model.ClassInformation;
import com.nv.Model.SubjectInformation;
import com.nv.Model.TeacherSubjectInformation;

public class SearchTeacherInput {
	
	String name;
	List<SubjectInformation> subject;
	List<ClassInformation> classID;
	List<String> pincode;
	List<String> city;
	List<String> state;
	List<String> qualification;
	List<String> experience;
	List<String> email;
	List<String> phoneNo;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubjectInformation> getSubject() {
		return subject;
	}
	public void setSubject(List<SubjectInformation> subject) {
		this.subject = subject;
	}
	
	
	
	public List<ClassInformation> getClassID() {
		return classID;
	}
	public void setClassID(List<ClassInformation> classID) {
		this.classID = classID;
	}
	public List<String> getPincode() {
		return pincode;
	}
	public void setPincode(List<String> pincode) {
		this.pincode = pincode;
	}
	public List<String> getCity() {
		return city;
	}
	public void setCity(List<String> city) {
		this.city = city;
	}
	public List<String> getState() {
		return state;
	}
	public void setState(List<String> state) {
		this.state = state;
	}
	public List<String> getQualification() {
		return qualification;
	}
	public void setQualification(List<String> qualification) {
		this.qualification = qualification;
	}
	public List<String> getExperience() {
		return experience;
	}
	public void setExperience(List<String> experience) {
		this.experience = experience;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	public List<String> getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(List<String> phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public SearchTeacherInput(String name, List<SubjectInformation> subject,
			List<ClassInformation> classID, List<String> pincode, List<String> city,
			List<String> state, List<String> qualification,
			List<String> experience, List<String> email, List<String> phoneNo) {
		super();
		this.name = name;
		this.subject = subject;
		this.classID = classID;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.qualification = qualification;
		this.experience = experience;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	

	
	

}
