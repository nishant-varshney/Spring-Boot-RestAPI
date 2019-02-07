package com.nv.Model;

import java.io.Serializable;
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

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="LP_Subject_MST")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectInformation implements Serializable {
	

	@Id
	@Column(name="Subject_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer subjectId;

	private String subjectName;
	
	@OneToMany(targetEntity=CCSubjectInformation.class,mappedBy="subject",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<CCSubjectInformation> cc;
	
	
	@OneToMany(targetEntity=TeacherSubjectInformation.class,mappedBy="subject",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<TeacherSubjectInformation> teacher;
	
	
	@OneToMany(targetEntity=CourseInformation.class,mappedBy="subject",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<CourseInformation> course;

	
	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
	
	
	public SubjectInformation(Integer subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}
	
	

	public SubjectInformation(Integer subjectId) {
		super();
		this.subjectId = subjectId;
	}

	public SubjectInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
