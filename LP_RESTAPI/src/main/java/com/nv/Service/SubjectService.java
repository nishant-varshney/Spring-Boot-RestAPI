package com.nv.Service;

import java.util.List;

import com.nv.Model.SubjectInformation;


public interface SubjectService {
	
	List<SubjectInformation> findAll();
	
	SubjectInformation getOne(Integer id);

}
