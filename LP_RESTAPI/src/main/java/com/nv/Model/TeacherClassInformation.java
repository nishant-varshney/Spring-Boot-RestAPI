package com.nv.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="LP_Teacher_Class")
public class TeacherClassInformation implements Serializable {

	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	
	@ManyToOne
	@JoinColumn(name="class_id",referencedColumnName="class_id")
	private ClassInformation classID;
	

	
	@ManyToOne
	@JoinColumn(name="teacher_id",referencedColumnName="teacher_id")
	private TeacherInformation teacher;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public ClassInformation getClassID() {
		return classID;
	}



	public void setClassID(ClassInformation classID) {
		this.classID = classID;
	}


	@JsonIgnore
	public TeacherInformation getTeacher() {
		return teacher;
	}



	public void setTeacher(TeacherInformation teacher) {
		this.teacher = teacher;
	}



	public TeacherClassInformation(ClassInformation classID) {
		super();
		this.classID = classID;
	}



	public TeacherClassInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
