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
@Table(name="LP_Teacher_Subject")
public class TeacherSubjectInformation implements Serializable{
	
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	
	@ManyToOne
	@JoinColumn(name="subject_id",referencedColumnName="Subject_ID")
	private SubjectInformation  subject;
	

	
	@ManyToOne
	@JoinColumn(name="teacher_id",referencedColumnName="teacher_id")
	private TeacherInformation teacher;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public SubjectInformation getSubject() {
		return subject;
	}



	public void setSubject(SubjectInformation subject) {
		this.subject = subject;
	}


	@JsonIgnore
	public TeacherInformation getTeacher() {
		return teacher;
	}



	public void setTeacher(TeacherInformation teacher) {
		this.teacher = teacher;
	}



	public TeacherSubjectInformation() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TeacherSubjectInformation(SubjectInformation subject) {
		super();
		this.subject = subject;
	}
	
	
	
	

}
