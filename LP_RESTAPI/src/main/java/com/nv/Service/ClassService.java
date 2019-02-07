package com.nv.Service;

import java.util.List;

import com.nv.Model.ClassInformation;

public interface ClassService {

	List<ClassInformation> findAll();
	
	ClassInformation getOne(Integer id);
}
