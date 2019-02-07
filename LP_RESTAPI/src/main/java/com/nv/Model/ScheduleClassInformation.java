package com.nv.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="LP_Schedule_Class")
public class ScheduleClassInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SCL_Id")
	private Integer id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	@Column(name="SC_Date",columnDefinition="date")
	private Date date;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:MM:ss")
	@Column(name="Start_Time",columnDefinition="time")
	private String startTime;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:MM:ss")
	@Column(name="End_Time",columnDefinition="time")
	private String endTime;
	
	
	@ManyToOne
	@JoinColumn(name="Course_ID",referencedColumnName="Course_ID")
	private CourseInformation course;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public CourseInformation getCourse() {
		return course;
	}


	public void setCourse(CourseInformation course) {
		this.course = course;
	}


	public ScheduleClassInformation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ScheduleClassInformation(Integer id, Date date, String startTime,
			String endTime, CourseInformation course) {
		super();
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.course = course;
	}


	public ScheduleClassInformation(Date date, String startTime,
			String endTime, CourseInformation course) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.course = course;
	}
	
	
	
}
