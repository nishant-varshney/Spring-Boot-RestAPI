package com.nv.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="LP_CC_Subject")
public class CCSubjectInformation implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	
	@ManyToOne
	@JoinColumn(name="subject_id",referencedColumnName="Subject_ID")
	private SubjectInformation  subject;
	

	
	@ManyToOne
	@JoinColumn(name="cc_id",referencedColumnName="cc_id")
	private CCInformation cc;


/*

	public Integer getId() {
		return id;
	}
	



	public void setId(Integer id) {
		this.id = id;
	}
*/


	public SubjectInformation getSubject() {
		return subject;
	}



	public void setSubject(SubjectInformation subject) {
		this.subject = subject;
	}



	@JsonIgnore
	public CCInformation getCc() {
		return cc;
	}



	public void setCc(CCInformation cc) {
		this.cc = cc;
	}

	

	

	public CCSubjectInformation(SubjectInformation subject, CCInformation cc) {
		super();
	//	this.id = id;
		this.subject = subject;
		this.cc = cc;
	}



	public CCSubjectInformation() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CCSubjectInformation(SubjectInformation subject) {
		super();
		this.subject = subject;
	} 
	
	
	
	

}
