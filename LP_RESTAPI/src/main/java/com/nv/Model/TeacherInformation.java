package com.nv.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name="LP_TEACHER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeacherInformation implements Serializable {
	
	@Id
	@Column(name="teacher_id")
	private Integer teacherID;
	//private String name;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	private Date creationDate;
	private String description;
	private String qualifications;
	private String experience;
	private String email;
	private String address1;
	private String address2;
	private String pincode;
	private String city;
	private String state;
	private String lpExpeience;
	private String name;
	
	
	private Integer subjectCount;
	private Integer classCount;


	//private String phoneNo;;
	
	@OneToMany(targetEntity=TeacherSubjectInformation.class,mappedBy="teacher",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<TeacherSubjectInformation> subject;
	
	@OneToMany(targetEntity=TeacherClassInformation.class,mappedBy="teacher",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<TeacherClassInformation> classID;
	
	
	
	@OneToMany(targetEntity=CourseInformation.class,mappedBy="teacher",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CourseInformation> course;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="teacher_id",referencedColumnName="user_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "phoneNo")
	@JsonIdentityReference(alwaysAsId = true)
	private UserInformation user;

	/*
	@OneToMany(targetEntity=CCTeacherInformation.class,mappedBy="teacher",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CCTeacherInformation> ccTeacher;*/
	

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

/*	public List<CourseInformation> getCourse() {
		return course;
	}

	public void setCourse(List<CourseInformation> course) {
		this.course = course;
	}*/

	

	@JsonProperty(value="PhoneNo")
	public UserInformation getUser() {
		return user;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
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

	public String getLpExpeience() {
		return lpExpeience;
	}

	public void setLpExpeience(String lpExpeience) {
		this.lpExpeience = lpExpeience;
	}

	@JsonIgnore
	public List<CourseInformation> getCourse() {
		return course;
	}

	public void setCourse(List<CourseInformation> course) {
		this.course = course;
	}

	
	public void setUser(UserInformation user) {
		this.user = user;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
	

	public Integer getClassCount() {
		return classCount;
	}

	public void setClassCount(Integer classCount) {
		this.classCount = classCount;
	}

	public Integer getSubjectCount() {
		return subjectCount;
	}

	public void setSubjectCount(Integer subjectCount) {
		
		this.subjectCount = subjectCount;
	}

	public List<TeacherClassInformation> getClassID() {
		return classID;
	}

	public void setClassID(List<TeacherClassInformation> classID) {
		this.classID = classID;
	}

	public TeacherInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherInformation(Integer teacherID) {
		super();
		this.teacherID = teacherID;
	}

	
	

	public List<TeacherSubjectInformation> getSubject() {
		return subject;
	}

	public void setSubject(List<TeacherSubjectInformation> subject) {
		this.subject = subject;
	}

	public TeacherInformation(Integer teacherID,String name, Date creationDate) {
		super();
		this.teacherID = teacherID;
		this.creationDate = creationDate;
		this.name= name;
	//	this.phoneNo=phoneNo;
	}

	public TeacherInformation(Integer teacherID, Date creationDate,
			String description, String qualifications, String experience,
			String email, String address1, String address2, String pincode,
			String city, String state, String lpExpeience, String name,
			List<TeacherSubjectInformation> subject,
			List<TeacherClassInformation> classID,
			List<CourseInformation> course, UserInformation user) {
		super();
		this.teacherID = teacherID;
		this.creationDate = creationDate;
		this.description = description;
		this.qualifications = qualifications;
		this.experience = experience;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.lpExpeience = lpExpeience;
		this.name = name;
		this.subject = subject;
		this.classID = classID;
		this.course = course;
		this.user = user;
	}

	
	
	
	
	
	
	

}
