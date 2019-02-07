package com.nv.Service.Implementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nv.Model.CCInformation;
import com.nv.Model.CCTeacherInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Repository.CCInfoRepository;
import com.nv.Repository.CCTeacherRepository;
import com.nv.Repository.TeacherInfoRepository;
import com.nv.Repository.UserInfoRepository;
import com.nv.Repository.BeanInterfaces.TeacherbyCC;
import com.nv.Service.CCTeacherInfoService;
import com.nv.Utility.Utility;



@Service
public class CCTeacherImplementation implements CCTeacherInfoService{

	@Autowired
	private CCTeacherRepository ccTeacherRepo;
	
	@Autowired
	private CCInfoRepository ccRepo;
	
	@Autowired
	private TeacherInfoRepository teacherRepo;
	
	@Autowired
	private UserInfoRepository userRepo;
	
	@Override
	public List<CCTeacherInformation> listccTeacher() {
		// TODO Auto-generated method stub
		return ccTeacherRepo.findAll();
	}

	@Override
	public CCTeacherInformation findOne(Integer id) {
		// TODO Auto-generated method stub
		return ccTeacherRepo.getOne(id);
	}

	@Override
	public String addCCTeacher(CCTeacherInformation ccTeacherInfo) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		
		try{
			System.out.println("CC ID::::"+ccTeacherInfo.getCc().getId());
			System.out.println("Teacher ID::::"+ccTeacherInfo.getTeacher().getTeacherID());
				Boolean ccDB = ccRepo.existsById(ccTeacherInfo.getCc().getId());
				Boolean teacherDB  = teacherRepo.existsById(ccTeacherInfo.getTeacher().getTeacherID());
			//	System.out.println("teacher DB::"+teacherDB);
				int teacherUserDB = userRepo.getCountByIDandRole(ccTeacherInfo.getTeacher().getTeacherID(), "Teacher");
				
				if(!ccDB){
					key.add("Status");value.add("Fail");
					key.add("Message");value.add("No Coaching Center is registered with given ID");
				}else if(teacherUserDB == 0){
					System.out.println("teacher not found!!");
					key.add("Status");value.add("Fail");
					key.add("Message");value.add("No Teacher is registered with given ID");
				}
				else{
					int ccTeacherDBcount = ccTeacherRepo.getCountByTeacherAndCCId(ccTeacherInfo.getTeacher(), ccTeacherInfo.getCc());
					if(ccTeacherDBcount == 0){
						ccTeacherInfo.setRequestedDate(Utility.getTodayDate());
						ccTeacherInfo.setRequestStatus("false");
						if(!teacherDB){
							teacherRepo.save(ccTeacherInfo.getTeacher());
						}
						ccTeacherRepo.save(ccTeacherInfo);
						key.add("Status");value.add("Success");
						key.add("Message");value.add("Request Successfully Sent");
					}else{
						key.add("Status");value.add("Fail");
						key.add("Message");value.add("Request Already Sent");
					}
				}
		}catch(Exception e){
			key.clear();value.clear();
			key.add("Status"); value.add("Error");
			key.add("Message"); value.add("Some Error occured at Server End");
			e.printStackTrace();
		}
		return Utility.getResponce(key, value);
	}

	@Override
	public String deleteCCTeacher(Integer id) {
		// TODO Auto-generated method stub
		ccTeacherRepo.deleteById(id);
		return "Deleted";
	}

	@Override
	public String getPendingReq(String role, Integer id) throws JsonProcessingException {
		// TODO Auto-generated method stub
		List<String> key=new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		String json="";
		List<CCTeacherInformation> ccTeacherReq=null;
			try{
				if(role.equalsIgnoreCase("Teacher")){ //Get Request which is initiated by CC and not have any action on it
					Boolean teacherDB  = teacherRepo.existsById(id);
					if(!teacherDB){
						key.add("Status");val.add("Fail");
						key.add("Message");val.add("No Teacher is registered with given ID");
					}else{
						ccTeacherReq =  ccTeacherRepo.getTeacherPendingCase(teacherRepo.getOne(id));
						if(ccTeacherReq == null || ccTeacherReq.size() == 0){
							key.add("Status");val.add("Warning");
							key.add("Message");val.add("No Request Found");
						}else{
							key.add("Status");val.add("Success");
							key.add("Message");val.add("Data Fetched Successfully");
							ObjectMapper ob= new ObjectMapper();
							json=ob.writeValueAsString(ccTeacherReq);
							key.add("DATA");val.add(json);
							return json;
						}
					}
				}else if(role.equalsIgnoreCase("CC")){ //Get Request which is initiated by Teacher and not have any action on it
					Boolean ccDB = ccRepo.existsById(id);
					if(!ccDB){
						key.add("Status");val.add("Fail");
						key.add("Message");val.add("No CC is registered with given ID");
					}else{
						ccTeacherReq =  ccTeacherRepo.getCCPendingCase(ccRepo.getOne(id));
						if(ccTeacherReq == null || ccTeacherReq.size() == 0){
							key.add("Status");val.add("Warning");
							key.add("Message");val.add("No Request Found");
						}else{
							key.add("Status");val.add("Success");
							key.add("Message");val.add("Data Fetched Successfully");
							ObjectMapper ob= new ObjectMapper();
							json=ob.writeValueAsString(ccTeacherReq);
							key.add("DATA");val.add(json);
							return json;
						}
					}
				}else{
					key.add("Status");val.add("Fail");
					key.add("Message");val.add("Invalid Role or ID");
				}
			}catch(Exception e){
				key.clear();val.clear();
				key.add("Status");val.add("Error");
				key.add("Message");val.add("Some Error occured at Server End");
				e.printStackTrace();
			}
		return Utility.getResponce(key, val);
	}

	@Override
	public String updateReq(CCTeacherInformation ccTeacherInfo) {
		// TODO Auto-generated method stub
		List<String> key=new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		try{
			if(ccTeacherInfo.getRequestStatus().equalsIgnoreCase("Approve") || ccTeacherInfo.getRequestStatus().equalsIgnoreCase("Reject")){
				Boolean ccTeacherExist = ccTeacherRepo.existsById(ccTeacherInfo.getRequestId());
				if(!ccTeacherExist){
					key.add("Status");val.add("Fail");
					key.add("Message");val.add("Request Not Found");
				}else{
					CCTeacherInformation ccTeacherDB = ccTeacherRepo.getOne(ccTeacherInfo.getRequestId());
					if(!ccTeacherDB.getRequestStatus().equalsIgnoreCase("false")){
						key.add("Status");val.add("Fail");
						key.add("Message");val.add("This request is already:"+ccTeacherDB.getRequestStatus());
					}else{
						ccTeacherDB.setConfirmationDate(Utility.getTodayDate());
						ccTeacherDB.setRequestStatus(ccTeacherInfo.getRequestStatus());
						ccTeacherRepo.save(ccTeacherDB);
						key.add("Status");val.add("Success");
						key.add("Message");val.add("Action Sucessfull");
					}
				}
			}else{
				key.add("Status");val.add("Warning");
				key.add("Message");val.add("Action is not valid!Please Approve/Reject the Request");
			}
		}catch(Exception e){
			key.clear();val.clear();
			key.add("Status");val.add("Error");
			key.add("Message");val.add("Some Error occured at Server End");
			e.printStackTrace();
		}
		return Utility.getResponce(key, val);
	}

	@Override
	public String getTeacherListByCC(Integer ccID) {
		// TODO Auto-generated method stub
		List<String> key=new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		String json="";
		Boolean ccDB = ccRepo.existsById(ccID);
		if(!ccDB){
			key.add("Status");val.add("Fail");
			key.add("Message");val.add("No CC is registered with given ID");
		}else{
				List<TeacherbyCC> ccTeacher=null;
				ObjectMapper ob= new ObjectMapper();
				try {
					ccTeacher=ccTeacherRepo.getTeacherListByCC(ccRepo.getOne(ccID));
					System.out.println("recievd data:"+ccTeacher);
					if(ccTeacher == null || ccTeacher.size() == 0){
						key.add("Status");val.add("Warning");
						key.add("Message");val.add("No Teacher Assosciate with given CC");
					}else{
						json=ob.writeValueAsString(ccTeacher);
						System.out.println(json.replaceAll(".*\": null(,)?\\r\\n", ""));
						//return json;
						key.add("Status");val.add("Success");
						key.add("Message");val.add("Data Fetched Successfully");
						key.add("Data");val.add(json);
						return json;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					key.add("Status");val.add("Error");
					key.add("Message");val.add("Some Error occured at Server End");
					e.printStackTrace();
				}
		}
		return Utility.getResponce(key, val);
	}
	
	
	

}
