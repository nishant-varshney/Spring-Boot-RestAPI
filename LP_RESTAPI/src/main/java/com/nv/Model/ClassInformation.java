package com.nv.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name="LP_CLASS_MST")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClassInformation implements Serializable {
	
	
	
	@Id
	@Column(name="class_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer classID;
	private String ClassDetail;
	

	@OneToMany(targetEntity=CCClassInformation.class,mappedBy="classID",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<CCClassInformation> cc;
	
	
	@OneToMany(targetEntity=TeacherClassInformation.class,mappedBy="classID",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<TeacherClassInformation> teacher;
	
	
	@OneToMany(targetEntity=CourseInformation.class,mappedBy="classID",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<CourseInformation> course;
	
	@OneToMany(targetEntity=StudentInformation.class,mappedBy="classId",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<StudentInformation> student;
	
	public Integer getClassID() {
		return classID;
	}
	public void setClassID(Integer classID) {
		this.classID = classID;
	}
	
	public String getClassDetail() {
		return ClassDetail;
	}
	public void setClassDetail(String classDetail) {
		ClassDetail = classDetail;
	}
	

	
	public ClassInformation(Integer classID, String classDetail) {
		super();
		this.classID = classID;
		this.ClassDetail = classDetail;
	}
	
	public ClassInformation(Integer classID) {
		super();
		this.classID = classID;
	}
	
	public ClassInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
