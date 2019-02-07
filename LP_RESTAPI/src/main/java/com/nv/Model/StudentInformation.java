package com.nv.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="LP_STUDENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentInformation implements Serializable {

	@Id
	@Column(name="student_id")
	private Integer id;
	private String name;
	private String email;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	private Date creationDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	private Date modificationDate;
	
	private String isActive;
	
	@ManyToOne
	@JoinColumn(name="classId",referencedColumnName="class_id")
	private ClassInformation classId;
	
	private String address1;
	private String address2;
	private String pincode;
	private String city;
	private String state;
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="student_id",referencedColumnName="user_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "phoneNo")
	@JsonIdentityReference(alwaysAsId = true)
	private UserInformation user;

	
	@OneToMany(targetEntity=CourseStudentMapping.class,mappedBy="student",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CourseStudentMapping> course;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	


	public ClassInformation getClassId() {
		return classId;
	}


	public void setClassId(ClassInformation classId) {
		this.classId = classId;
	}


	@JsonProperty(value="PhoneNo")
	public UserInformation getUser() {
		return user;
	}


	public void setUser(UserInformation user) {
		this.user = user;
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

	
	@JsonIgnore
	public List<CourseStudentMapping> getCourse() {
		return course;
	}


	public void setCourse(List<CourseStudentMapping> course) {
		this.course = course;
	}


	public StudentInformation() {
		super();
		// TODO Auto-generated constructor stub
	}




	public StudentInformation(Integer id, String name, String email,
			Date creationDate, Date modificationDate, String isActive,
			ClassInformation classId, String address1, String address2, String pincode,
			String city, String state, UserInformation user,
			List<CourseStudentMapping> course) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.isActive = isActive;
		this.classId = classId;
		this.address1 = address1;
		this.address2 = address2;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.user = user;
		this.course = course;
	}


	public StudentInformation(Integer id, String name, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
	}


	public StudentInformation(Integer id) {
		super();
		this.id = id;
	}


	
	
	
}
