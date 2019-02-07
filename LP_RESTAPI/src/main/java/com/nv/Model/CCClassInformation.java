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
@Table(name="LP_CC_Class")
public class CCClassInformation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="cc_id",referencedColumnName="cc_id")
	private CCInformation cc;

	
	
	@ManyToOne
	@JoinColumn(name="class_id",referencedColumnName="class_id")
	private ClassInformation classID;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	@JsonIgnore
	public CCInformation getCc() {
		return cc;
	}



	public void setCc(CCInformation cc) {
		this.cc = cc;
	}



	public ClassInformation getClassID() {
		return classID;
	}



	public void setClassID(ClassInformation classID) {
		this.classID = classID;
	}



	public CCClassInformation(ClassInformation classID) {
		super();
		this.classID = classID;
	}



	public CCClassInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
