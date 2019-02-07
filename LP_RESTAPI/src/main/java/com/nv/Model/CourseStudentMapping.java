package com.nv.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="LP_Course_Student")
public class CourseStudentMapping implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="Course_ID",referencedColumnName="Course_ID")
	CourseInformation course;
	
	
	@ManyToOne
	@JoinColumn(name="Student_ID",referencedColumnName="student_id")
	StudentInformation student;

	public CourseInformation getCourse() {
		return course;
	}

	public void setCourse(CourseInformation course) {
		this.course = course;
	}

	public StudentInformation getStudent() {
		return student;
	}

	public void setStudent(StudentInformation student) {
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseStudentMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
