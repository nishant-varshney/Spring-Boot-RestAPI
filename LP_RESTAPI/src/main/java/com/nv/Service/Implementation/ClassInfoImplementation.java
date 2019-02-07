package com.nv.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nv.Model.ClassInformation;
import com.nv.Repository.ClassRepository;
import com.nv.Service.ClassService;


@Service
public class ClassInfoImplementation implements ClassService {

	@Autowired
	ClassRepository classRepo;
	
	@Override
	public List<ClassInformation> findAll() {
		// TODO Auto-generated method stub
		return classRepo.findAll();
	}

	@Override
	public ClassInformation getOne(Integer id) {
		// TODO Auto-generated method stub
		ClassInformation classes = null;
		try{
			classes = classRepo.getOne(id);
		}catch(Exception e){
			
		}
		return classes;
	}
	

}
