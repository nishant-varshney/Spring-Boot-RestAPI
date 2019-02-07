package com.nv.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.Model.StudentInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Model.UserInformation;
import com.nv.Repository.StudentRepository;
import com.nv.Repository.UserInfoRepository;
import com.nv.Service.StudentService;
import com.nv.Utility.Utility;


@Service
public class StudentInfoImplementation implements StudentService {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	UserInfoRepository userRepo;
	
	@Override
	public List<StudentInformation> listStudent() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

	@Override
	public String findOne(Integer id) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		String json="";
		ObjectMapper ob= new ObjectMapper();
		try {
			if(!studentRepo.existsById(id)){
				json = Utility.getResponce("Message", "No Student Registered with given ID");
			}else{
					json=ob.writeValueAsString(studentRepo.getOne(id));
				    key.add("Data");value.add(json);
			}
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			key.add("Status"); value.add("Error");
			key.add("Message"); value.add("Server Error in fetching the data");
			e.printStackTrace();
			return Utility.getResponce(key, value);
		}
	}

	@Override
	public String addStudent(StudentInformation studentInfo) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
	
		try{
			if(studentRepo.existsById(studentInfo.getId())){
				StudentInformation studentDB = studentRepo.getOne(studentInfo.getId());
				studentInfo.setCreationDate(studentDB.getCreationDate());
				if(!studentDB.getName().equalsIgnoreCase(studentInfo.getName())){
					UserInformation userDB = userRepo.getOne(studentInfo.getId());
					userDB.setName(studentInfo.getName());
					userRepo.save(userDB);
				}
				studentInfo.setModificationDate(Utility.getTodayDate());
				studentRepo.save(studentInfo);
				key.add("Status"); value.add("Success");
				key.add("Message"); value.add("Student Information Updated Successfully");
			}else{
				key.add("Status"); value.add("Fail");
				key.add("Message"); value.add("No Student with gievn ID");
			}
		}catch(Exception e){
			key.add("Status"); value.add("Error");
			key.add("Message"); value.add("Saving Failed");
			e.printStackTrace();
		}
		return Utility.getResponce(key, value);
	}

	@Override
	public String deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
