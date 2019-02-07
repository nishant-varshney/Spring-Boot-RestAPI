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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@Entity
@Table(name="LP_Course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseInformation implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Course_ID")
	private Integer id;
	
	private Integer createdByID;
	private String createdByRole;
	
	
	@ManyToOne
	@JoinColumn(name="class_id",referencedColumnName="class_id")
	private ClassInformation classID;
	
	
	@ManyToOne
	@JoinColumn(name="subject_id",referencedColumnName="Subject_ID")
	private SubjectInformation subject;
	
	//private Integer teacherID;
	

	@ManyToOne
	@JoinColumn(name = "teacher_ID",referencedColumnName="teacher_id")
	private TeacherInformation teacher;
	
	@ManyToOne
	@JoinColumn(name="CC_ID",referencedColumnName="cc_id")
	private CCInformation cc;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	@Column(name="Start_Date",columnDefinition="date")
	private Date startDate;
	

	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	@Column(name="End_Date",columnDefinition="date")
	private Date endDate;
	
	private String frequency;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:MM:ss")
	@Column(name="Start_Time",columnDefinition="time")
	private String startTime;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:MM:ss")
	@Column(name="End_Time",columnDefinition="time")
	private String endTime;
	
	private String title;
	private String description;
	
	
	@Column(columnDefinition="varchar(10) default 'false'")
	private String isActive;
	
	
	private Integer studentCount;
	
	@OneToMany(targetEntity=CourseStudentMapping.class,mappedBy="course",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CourseStudentMapping> student;

	@OneToMany(targetEntity=ScheduleClassInformation.class,mappedBy="course",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<ScheduleClassInformation> scheduleClass;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCreatedByRole() {
		return createdByRole;
	}

	public void setCreatedByRole(String createdByRole) {
		this.createdByRole = createdByRole;
	}

	
/*

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}*/

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public Integer getCreatedByID() {
		return createdByID;
	}

	public void setCreatedByID(Integer createdByID) {
		this.createdByID = createdByID;
	}

	/*public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}
*/


	public TeacherInformation getTeacher() {
		return teacher;
	}

	
	public void setTeacher(TeacherInformation teacher) {
		this.teacher = teacher;
	}

	public CCInformation getCc() {
		return cc;
	}

	public void setCc(CCInformation cc) {
		this.cc = cc;
	}
	

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public ClassInformation getClassID() {
		return classID;
	}

	public void setClassID(ClassInformation classID) {
		this.classID = classID;
	}

	public SubjectInformation getSubject() {
		return subject;
	}

	public void setSubject(SubjectInformation subject) {
		this.subject = subject;
	}

	public List<CourseStudentMapping> getStudent() {
		return student;
	}

	public void setStudent(List<CourseStudentMapping> student) {
		this.student = student;
	}

	
	
	
	public List<ScheduleClassInformation> getScheduleClass() {
		return scheduleClass;
	}

	public void setScheduleClass(List<ScheduleClassInformation> scheduleClass) {
		this.scheduleClass = scheduleClass;
	}

	public CourseInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseInformation(Integer id, Integer createdByID,
			String createdByRole, ClassInformation classID, TeacherInformation teacher,
			CCInformation cc, SubjectInformation subject, Date startDate, Date endDate,
			String frequency, String startTime, String endTime, String title,
			String description, String isActive,
			List<CourseStudentMapping> student,List<ScheduleClassInformation> scheduleClass ) {
		super();
		this.id = id;
		this.createdByID = createdByID;
		this.createdByRole = createdByRole;
		this.classID = classID;
		this.teacher = teacher;
		this.cc = cc;
		this.subject = subject;
		this.startDate = startDate;
		this.endDate = endDate;
		this.frequency = frequency;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.description = description;
		this.isActive = isActive;
		this.student = student;
		this.scheduleClass = scheduleClass;
	}

	public CourseInformation(Integer id) {
		super();
		this.id = id;
	}
		
	
	
	
	
}
