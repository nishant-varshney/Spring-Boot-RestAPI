package com.nv.Service.Implementation;

import java.util.ArrayList;
import java.util.List;














import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.Model.SearchTeacher;
import com.nv.Model.TeacherClassInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Model.TeacherSubjectInformation;
import com.nv.Model.UserInformation;
import com.nv.Repository.TeacherClassRepository;
import com.nv.Repository.TeacherInfoRepository;
import com.nv.Repository.TeacherSubjectRepository;
import com.nv.Repository.UserInfoRepository;
import com.nv.Service.TeacherInfoService;
import com.nv.Specification.TeacherSpecification;
import com.nv.Specification.Criteria.SearchCriteria;
import com.nv.Utility.Utility;
import com.nv.VO.ClassOutput;
import com.nv.VO.SearchTeacherInput;
import com.nv.VO.SubjectOutput;


@Service
public class TeacherInfoImplementation implements TeacherInfoService {

	
	@Autowired
	TeacherInfoRepository teacherRepo;
	
	@Autowired
	UserInfoRepository userRepo;
	
	
	@Autowired
	TeacherSubjectRepository teacherSubRepo;
	
	
	@Autowired
	TeacherClassRepository teacherClassRepo;
	
	@Override
	public List<TeacherInformation> listTeacher() {
		// TODO Auto-generated method stub
		return teacherRepo.findAll();
	}

	@Override
	public String findOne(Integer id) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		String json="";
		ObjectMapper ob= new ObjectMapper();
		try {
			if(!teacherRepo.existsById(id)){
				json = Utility.getResponce("Message", "No Teacher Registered with given ID");
			}else{
					TeacherInformation teacher = teacherRepo.getOne(id);
					System.out.println("teacher creation date::"+teacher.getCreationDate());
					teacher.setLpExpeience(Utility.getDifference(teacher.getCreationDate()));
					teacher.setSubjectCount(teacher.getSubject().size());
					teacher.setClassCount(teacher.getClassID().size());
					json=ob.writeValueAsString(teacher);
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
	@Transactional
	public String addTeacher(TeacherInformation teacherInfo) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		
	
		if(teacherInfo.getSubject() != null){
			for (TeacherSubjectInformation sub : teacherInfo.getSubject()) {
				sub.setTeacher(teacherInfo);
			}
			teacherInfo.setSubjectCount(teacherInfo.getSubject().size());
		}else{
			teacherInfo.setSubjectCount(0);
		}
		
		if(teacherInfo.getClassID() != null){
			for (TeacherClassInformation cls : teacherInfo.getClassID()) {
				cls.setTeacher(teacherInfo);
			}
			teacherInfo.setClassCount(teacherInfo.getClassID().size());
		}else{
			teacherInfo.setClassCount(0);
		}
		
		
		try{
			if(teacherRepo.existsById(teacherInfo.getTeacherID())){
				TeacherInformation teacherDB = teacherRepo.getOne(teacherInfo.getTeacherID());
				teacherInfo.setCreationDate(teacherDB.getCreationDate());
				if(teacherDB.getName() == null || !teacherDB.getName().equalsIgnoreCase(teacherInfo.getName())){
					UserInformation userDB = userRepo.getOne(teacherInfo.getTeacherID());
					userDB.setName(teacherInfo.getName());
					userRepo.save(userDB);
				}
				
				teacherSubRepo.deleteByteacher(teacherInfo);
				teacherClassRepo.deleteByteacher(teacherInfo);
				
			
				teacherRepo.save(teacherInfo);
				key.add("Status"); value.add("Success");
				key.add("Message"); value.add("Teacher Infomration Updated Successfully");
			}else{
				key.add("Status"); value.add("Fail");
				key.add("Message"); value.add("No Teacher with gievn ID");
			}
		}catch(Exception e){
			key.add("Status"); value.add("Error");
			key.add("Message"); value.add("Saving Failed");
			e.printStackTrace();
		}
		return Utility.getResponce(key, value);
	}

	@Override
	public String deleteUser(Integer id) {
		// TODO Auto-generated method stub
		teacherRepo.deleteById(id);
		return "delted";
	}

	@Override
	public String findTeacherforaffilliation(SearchTeacherInput searchTeacherInput ) {
		// TODO Auto-generated method stub
		String searchResult = "";
		
		try{
		ObjectMapper ob= new ObjectMapper();
		
		Boolean isSearch = false;
		System.out.println("in search method:"+searchTeacherInput.getClassID());
			
		TeacherSpecification nameSpec=null;
		TeacherSpecification subjectSpec=null;
		TeacherSpecification classSpec=null;
		TeacherSpecification citySpec=null;
		TeacherSpecification emailSpec=null;
		TeacherSpecification experienceSpec=null;
		TeacherSpecification phoneSpec=null;
		TeacherSpecification pincodeSpec=null;
		TeacherSpecification qualificationSpec=null;
		TeacherSpecification stateSpec=null;
		
		if(!Utility.isNull(searchTeacherInput.getName())){
			 nameSpec = new TeacherSpecification(new SearchCriteria("name",":",searchTeacherInput.getName()));
			 isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getSubject())){
			subjectSpec = new TeacherSpecification(new SearchCriteria("subject",":",searchTeacherInput.getSubject()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getClassID())){
			classSpec = new TeacherSpecification(new SearchCriteria("classID",":",searchTeacherInput.getClassID()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getCity())){
			citySpec = new TeacherSpecification(new SearchCriteria("city",":",searchTeacherInput.getCity()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getEmail())){
			emailSpec = new TeacherSpecification(new SearchCriteria("email",":",searchTeacherInput.getEmail()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getExperience())){
			experienceSpec = new TeacherSpecification(new SearchCriteria("experience",":",searchTeacherInput.getExperience()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getPhoneNo())){
			phoneSpec = new TeacherSpecification(new SearchCriteria("phoneNo",":",searchTeacherInput.getPhoneNo()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getPincode())){
			pincodeSpec = new TeacherSpecification(new SearchCriteria("pincode",":",searchTeacherInput.getPincode()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getQualification())){
			qualificationSpec = new TeacherSpecification(new SearchCriteria("qualification",":",searchTeacherInput.getQualification()));
			isSearch=true;
		}
		if(!Utility.isNull(searchTeacherInput.getState())){
			stateSpec = new TeacherSpecification(new SearchCriteria("state",":",searchTeacherInput.getState()));
			isSearch=true;
		}
	

		if(isSearch){
			 List<TeacherInformation> listreturn = teacherRepo.findAll(Specification.where(nameSpec).and(subjectSpec).and(classSpec)
																		.and(citySpec).and(emailSpec).and(experienceSpec).and(phoneSpec)
																		.and(pincodeSpec).and(qualificationSpec).and(stateSpec));
			
			 for(TeacherInformation list : listreturn){
				 list.setLpExpeience(Utility.getDifference(list.getCreationDate()));
			 }
			 
			 searchResult =  ob.writeValueAsString(listreturn);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public List<SubjectOutput> findSubject(Integer teacherID) {
		// TODO Auto-generated method stub
		List<SubjectOutput> subject = null;
		try{
			if(teacherRepo.existsById(teacherID)){
				subject= teacherSubRepo.getByteacher(teacherRepo.getOne(teacherID));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return subject;
	}

	@Override
	public List<ClassOutput> findClass(Integer teacherID) {
		// TODO Auto-generated method stub
		List<ClassOutput> classes = null;
		try{
			if(teacherRepo.existsById(teacherID)){
				classes= teacherClassRepo.getByteacher(teacherRepo.getOne(teacherID));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return classes;
	}

}
