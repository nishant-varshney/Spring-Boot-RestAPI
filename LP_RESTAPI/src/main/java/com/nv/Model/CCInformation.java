package com.nv.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Reference;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="LP_CoachingCenter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CCInformation  implements Serializable {
	
	@Id
	@Column(name="cc_id")
	private Integer id;
	private String name;
	private String picturePath;
	private String description;
	private String videoPath;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	private Date creationDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	private Date modificationDate;
	private String isActive;
	private String address1;
	private String address2;
	private String pincode;
	private String city;
	private String state;
	private String email;
	
	private Integer subjectCount;
	private Integer classCount;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="cc_id", referencedColumnName="user_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "phoneNo")
	@JsonIdentityReference(alwaysAsId = true)
	private UserInformation user;
	
	
	@OneToMany(targetEntity=CourseInformation.class,mappedBy="teacher",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CourseInformation> course;



	@OneToMany(targetEntity=CCSubjectInformation.class,mappedBy="cc",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CCSubjectInformation> subject;
	
	
	@OneToMany(targetEntity=CCClassInformation.class,mappedBy="cc",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CCClassInformation> classDetail;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	/*public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}*/


	public String getPicturePath() {
		return picturePath;
	}


	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getVideoPath() {
		return videoPath;
	}


	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getModificationDate() {
		return modificationDate;
	}


	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
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


	@JsonProperty(value="PhoneNo")
	public UserInformation getUser() {
		return user;
	}



	public void setUser(UserInformation user) {
		this.user = user;
	}


	@JsonIgnore
	public List<CourseInformation> getCourse() {
		return course;
	}

	@JsonIgnore
	public void setCourse(List<CourseInformation> course) {
		this.course = course;
	}


	public List<CCClassInformation> getClassDetail() {
		return classDetail;
	}


	public void setClassDetail(List<CCClassInformation> classDetail) {
		this.classDetail = classDetail;
	}

	
	

	public Integer getSubjectCount() {
		return subjectCount;
	}


	public void setSubjectCount(Integer subjectCount) {
		this.subjectCount = subjectCount;
	}


	public Integer getClassCount() {
		return classCount;
	}


	public void setClassCount(Integer classCount) {
		this.classCount = classCount;
	}


	public CCInformation(Integer id, String name, String picturePath,
			String description, String videoPath, Date creationDate,
			Date modificationDate, String isActive, String address1,
			String address2, String pincode, String city, String state,
			String email, UserInformation user, List<CourseInformation> course,
			List<CCSubjectInformation> subject,
			List<CCClassInformation> classDetail) {
		super();
		this.id = id;
		this.name = name;
		this.picturePath = picturePath;
		this.description = description;
		this.videoPath = videoPath;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.isActive = isActive;
		this.address1 = address1;
		this.address2 = address2;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.email = email;
		this.user = user;
		this.course = course;
		this.subject = subject;
		this.classDetail = classDetail;
	}


	public CCInformation(Integer id,String name,Date creationDate) {
		super();
		this.id = id;
		this.creationDate=creationDate;
		this.name = name;
	}


	public CCInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CCInformation(Integer id) {
		super();
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<CCSubjectInformation> getSubject() {
		return subject;
	}


	public void setSubject(List<CCSubjectInformation> subject) {
		this.subject = subject;
	}



	


/*
	public List<CourseInformation> getCourse() {
		return course;
	}


	public void setCourse(List<CourseInformation> course) {
		this.course = course;
	}*/
	
	
	

}
