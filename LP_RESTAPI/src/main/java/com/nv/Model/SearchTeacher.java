package com.nv.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SearchTeacher {
	
	private Integer teacherID;
	
	private String name;
	private String subject;
	private ClassInformation classID;
	private String pincode;
	private String city;
	private String state;
	private String qualifications;
	private String experience;
	private String email;
	private String phoneNo;
	
	private String address1;
	private String address2;
	private String description;
	private Integer lpExpeience;
	
	
	public Integer getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public ClassInformation getClassID() {
		return classID;
	}
	public void setClassID(ClassInformation classID) {
		this.classID = classID;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	
	
	public String getQualifications() {
		return qualifications;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public Integer getLpExpeience() {
		return lpExpeience;
	}
	public void setLpExpeience(Integer lpExpeience) {
		this.lpExpeience = lpExpeience;
	}
	public SearchTeacher(String name, String subject, ClassInformation classID,
			String pincode, String city, String state, String qualifications,
			String experience, String email, String phoneNo) {
		super();
		this.name = name;
		this.subject = subject;
		this.classID = classID;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.qualifications = qualifications;
		this.experience = experience;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	
	
	/*public SearchTeacher(String name, String subject, String classID,
			String pincode, String city, String state, String qualifications,
			String experience, String email, String phoneNo) {
		super();
		this.name = name;
		if(subject != null)
			this.subject = Arrays.asList(subject);
		if(classID != null)
			this.classID = Arrays.asList(classID);
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.qualifications = qualifications;
		this.experience = experience;
		this.email = email;
		this.phoneNo = phoneNo;
	}*/
	
	/*public SearchTeacher(String name, String[] subject, String[] classID,
			String pincode, String city, String state, String qualifications,
			String experience, String email, String phoneNo) {
		super();
		this.name = name;
		if(subject != null && subject.length > 0)
		this.subject = Arrays.asList(subject);
		if(classID != null && classID.length > 0)
		this.classID = Arrays.asList(classID);
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.qualifications = qualifications;
		this.experience = experience;
		this.email = email;
		this.phoneNo = phoneNo;
	}*/
	
	
	public SearchTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchTeacher(Integer teacherID, String name,String email,
			String phoneNo, String address1, String address2,
			String pincode, String city, String state,
			String qualifications, String experience,Integer lpExpeience,String description,
			String subject,ClassInformation classID)
			 {
		super();
		this.teacherID = teacherID;
		this.name = name;
		this.subject = subject;
		this.classID = classID;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.qualifications = qualifications;
		this.experience = experience;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address1 = address1;
		this.address2 = address2;
		this.lpExpeience=lpExpeience;
		this.description=description;
	}
	

	
	
}
