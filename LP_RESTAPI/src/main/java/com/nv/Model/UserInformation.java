package com.nv.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name="LP_USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInformation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer id;
	
	
	private String phoneNo;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String role;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String otp;
	
	@Column(columnDefinition="varchar(10) default 'false'")
	private String userActive;
	
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY,targetEntity=TeacherInformation.class,cascade=CascadeType.ALL,mappedBy="user")
	private TeacherInformation teacherInfo;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY,targetEntity=CCInformation.class,cascade=CascadeType.ALL,mappedBy="user")
	private CCInformation CCInfo;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY,targetEntity=StudentInformation.class,cascade=CascadeType.ALL,mappedBy="user")
	private StudentInformation studentInfo;
	
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	@JsonIgnore
	public String getUserActive() {
		return userActive;
	}
	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}
	
	@JsonIgnore
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	

	@JsonIgnore
	public TeacherInformation getTeacherInfo() {
		return teacherInfo;
	}
	public void setTeacherInfo(TeacherInformation teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
	

	@JsonIgnore
	public CCInformation getCCInfo() {
		return CCInfo;
	}
	public void setCCInfo(CCInformation cCInfo) {
		CCInfo = cCInfo;
	}
	

	@JsonIgnore
	public StudentInformation getStudentInfo() {
		return studentInfo;
	}
	public void setStudentInfo(StudentInformation studentInfo) {
		this.studentInfo = studentInfo;
	}
	public UserInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
