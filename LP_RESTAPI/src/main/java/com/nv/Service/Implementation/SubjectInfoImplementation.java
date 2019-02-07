package com.nv.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nv.Model.SubjectInformation;
import com.nv.Repository.SubjectRepository;
import com.nv.Service.SubjectService;


@Service
public class SubjectInfoImplementation implements SubjectService {

	@Autowired
	SubjectRepository subjectRepo;
	
	@Override
	public List<SubjectInformation> findAll() {
		// TODO Auto-generated method stub
		return subjectRepo.findAll();
	}

	@Override
	public SubjectInformation getOne(Integer id) {
		// TODO Auto-generated method stub
		SubjectInformation subject = null;
		try{
			subject = subjectRepo.getOne(id);
		}catch(Exception e){
			
		}
		return subject;
	}

}
