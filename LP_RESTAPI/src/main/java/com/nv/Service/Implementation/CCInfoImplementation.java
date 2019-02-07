package com.nv.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.Model.CCClassInformation;
import com.nv.Model.CCInformation;
import com.nv.Model.CCSubjectInformation;
import com.nv.Model.SearchCC;
import com.nv.Model.SubjectInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Model.UserInformation;
import com.nv.Repository.CCClassRepository;
import com.nv.Repository.CCInfoRepository;
import com.nv.Repository.CCSubjectRepository;
import com.nv.Repository.UserInfoRepository;
import com.nv.Service.CCService;
import com.nv.Specification.CCSpecification;
import com.nv.Specification.TeacherSpecification;
import com.nv.Specification.Criteria.SearchCriteria;
import com.nv.Utility.Utility;
import com.nv.VO.ClassOutput;
import com.nv.VO.SearchCCInput;
import com.nv.VO.SubjectOutput;


@Service
public class CCInfoImplementation implements CCService {
	
	@Autowired
	CCInfoRepository ccRepo;
	
	@Autowired
	UserInfoRepository userRepo;
	
	@Autowired
	CCSubjectRepository ccSubRepo;

	
	@Autowired
	CCClassRepository ccClassRepo;
	
	@Override
	public List<CCInformation> listCC() {
		// TODO Auto-generated method stub
		return ccRepo.findAll();
	}

	@Override
	public String findOne(Integer id) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		String json="";
		ObjectMapper ob= new ObjectMapper();
		try {
			if(!ccRepo.existsById(id)){
				json = Utility.getResponce("Message", "No CC Registered with given ID");
			}else{
					CCInformation ccDb = ccRepo.getOne(id);
					ccDb.setSubjectCount(ccDb.getSubject().size());
					ccDb.setClassCount(ccDb.getClassDetail().size());
					json=ob.writeValueAsString(ccDb);
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
	public String addCC(CCInformation CCInfo) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		
		Integer deleteId = 0;
		
		if(CCInfo.getSubject() != null){
			 for (CCSubjectInformation sub : CCInfo.getSubject()){
				 sub.setCc(CCInfo);
			 }
			 CCInfo.setSubjectCount(CCInfo.getSubject().size());
		}else{
			 CCInfo.setSubjectCount(0);
		}
		 
		if(CCInfo.getClassDetail() != null){
			 for (CCClassInformation cls : CCInfo.getClassDetail()){
				 cls.setCc(CCInfo);
			 }
			 CCInfo.setClassCount(CCInfo.getClassDetail().size());
		}else{
			 CCInfo.setClassCount(0);
		}
		
	
		try{
			if(ccRepo.existsById(CCInfo.getId())){
			//	System.out.println("CC in function:::"+CCInfo.getSubject().get(0).getSubject().getSubjectId());
				CCInformation ccDB = ccRepo.getOne(CCInfo.getId());
				CCInfo.setCreationDate(ccDB.getCreationDate());
				CCInfo.setModificationDate(Utility.getTodayDate());
				if(ccDB.getName() == null || !ccDB.getName().equalsIgnoreCase(CCInfo.getName())){
					
					UserInformation userDB = userRepo.getOne(CCInfo.getId());
					userDB.setName(CCInfo.getName());
					userRepo.save(userDB);
				}
				ccSubRepo.deleteBycc(CCInfo);
				ccClassRepo.deleteBycc(CCInfo);
				ccRepo.save(CCInfo);
				key.add("Status"); value.add("Success");
				key.add("Message"); value.add("CC Infomration Updated Successfully");
			}else{
				key.add("Status"); value.add("Fail");
				key.add("Message"); value.add("No CC with Given ID");
			}
			
		}catch(Exception e){
			key.add("Status"); value.add("Error");
			key.add("Message"); value.add("Saving Failed");
			e.printStackTrace();
		}
		return Utility.getResponce(key, value);
		
	}

	@Override
	public String deleteCC(Integer id) {
		// TODO Auto-generated method stub
		ccRepo.deleteById(id);
		return "Delete";
	}

	@Override
	public String findCCforaffilliation(SearchCCInput seachCCInput) {
		// TODO Auto-generated method stub
		String searchResult = "";
		try{
		ObjectMapper ob= new ObjectMapper();
		
		Boolean isSearch = false;
		
		CCSpecification nameSpec=null;
		CCSpecification emailSpec=null;
		CCSpecification pincodeSpec=null;
		CCSpecification stateSpec=null;
		CCSpecification citySpec=null;
		CCSpecification phoneSpec=null;
		
		if(!Utility.isNull(seachCCInput.getName())){
			 nameSpec = new CCSpecification(new SearchCriteria("name",":",seachCCInput.getName()));
			 isSearch=true;
		}
		if(!Utility.isNull(seachCCInput.getCity())){
			citySpec = new CCSpecification(new SearchCriteria("city",":",seachCCInput.getCity()));
			isSearch=true;
		}
		if(!Utility.isNull(seachCCInput.getEmail())){
			emailSpec = new CCSpecification(new SearchCriteria("email",":",seachCCInput.getEmail()));
			isSearch=true;
		}
		if(!Utility.isNull(seachCCInput.getPhoneno())){
			phoneSpec = new CCSpecification(new SearchCriteria("phoneNo",":",seachCCInput.getPhoneno()));
			isSearch=true;
		}
		if(!Utility.isNull(seachCCInput.getPincode())){
			pincodeSpec = new CCSpecification(new SearchCriteria("pincode",":",seachCCInput.getPincode()));
			isSearch=true;
		}
		if(!Utility.isNull(seachCCInput.getState())){
			stateSpec = new CCSpecification(new SearchCriteria("state",":",seachCCInput.getState()));
			isSearch=true;
		}
	
		if(isSearch){
			 List<CCInformation> listreturn = ccRepo.findAll(Specification.where(nameSpec)
																.and(citySpec).and(emailSpec).and(phoneSpec)
																.and(pincodeSpec).and(stateSpec));
			 searchResult =  ob.writeValueAsString(listreturn);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public List<SubjectOutput> findSubject(Integer ccID) {
		// TODO Auto-generated method stub
		List<SubjectOutput> subjects = null;
		try{
			if(ccRepo.existsById(ccID)){
				System.out.println("inside iffff");
				subjects= ccSubRepo.getBycc(ccRepo.getOne(ccID));
				System.out.println("subject in service::"+subjects.size());
			}
		}catch(Exception e){
			e.printStackTrace();
			subjects= null;
		}
		return subjects;
	}

	@Override
	public List<ClassOutput> findClass(Integer ccID) {
		// TODO Auto-generated method stub
		List<ClassOutput> classes = null;
		try{
			if(ccRepo.existsById(ccID)){
				System.out.println("inside iffff");
				classes= ccClassRepo.getBycc(ccRepo.getOne(ccID));
			}
		}catch(Exception e){
			e.printStackTrace();
			classes= null;
		}
		return classes;
	}


}
