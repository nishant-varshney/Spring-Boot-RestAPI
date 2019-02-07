package com.nv.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nv.Model.CCInformation;
import com.nv.Model.StudentInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Model.UserInformation;
import com.nv.Repository.CCInfoRepository;
import com.nv.Repository.StudentRepository;
import com.nv.Repository.TeacherInfoRepository;
import com.nv.Repository.UserInfoRepository;
import com.nv.Service.UserInfoService;
import com.nv.Utility.Utility;


@Service
public class UserInfoImplementation implements UserInfoService{

	
	@Autowired
	UserInfoRepository userInfoRepo;
	
	@Autowired
	CCInfoRepository ccInfoRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	TeacherInfoRepository teacherInfoRepo;
	
	@Override
	public List<UserInformation> listUser() {
		// TODO Auto-generated method stub
		return userInfoRepo.findAll();
		
	}

	@Override
	public UserInformation findOne(Integer id) {
		// TODO Auto-generated method stub
		return userInfoRepo.getOne(id);
	}

	@Override
	public String addUser(UserInformation userInfo) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		try{
			if(userInfo.getPassword() != null){
				key.add("Status");value.add("Fail");
				key.add("Message");value.add("Invalid Parameter");
			}else{
					UserInformation userInfoDB = userInfoRepo.findByRoleAndPhone(userInfo.getPhoneNo(), userInfo.getRole());
					if(userInfoDB != null && userInfoDB.getId() != null){  // USER ALREADY THERE
						key.add("Status");value.add("Warning");
						key.add("Message");value.add("User Already Added");
						key.add("Phone No");value.add(userInfoDB.getPhoneNo());
						key.add("Role");value.add(userInfoDB.getRole());
						key.add("isActive");value.add(userInfoDB.getUserActive());
						
					}else{							// NEW USER SO GENEARTE THE OTP AND SEND TO MOBILE AND SAVE TO DB
						userInfo.setOtp(Utility.getOTP());
						userInfoRepo.save(userInfo);
						key.add("Status");value.add("Success");
						key.add("Message");value.add("User Added Successfully");
						key.add("userID");value.add(userInfo.getId().toString());
						key.add("Phone No");value.add(userInfo.getPhoneNo());
						key.add("Role");value.add(userInfo.getRole());
						key.add("OTP");value.add(userInfo.getOtp());
					}
			}
		}catch(Exception e){
			key.clear();value.clear();
			key.add("Status");value.add("Error");
			key.add("Message");value.add("Some Error Occured at Server Side");
		}
		// userInfoRepo.
		 return Utility.getResponce(key, value);
	}

	@Override
	public String deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userInfoRepo.deleteById(id);
		return "deleted";
	}

	@Override
	public String validateOTP(UserInformation userInfo) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		UserInformation userInfoDB = userInfoRepo.findByRoleAndPhone(userInfo.getPhoneNo(), userInfo.getRole());
		try{
				if(userInfoDB == null){  // USER NOT THERE
							key.add("Status");value.add("Fail");
							key.add("Message");value.add("Please Register the User First before Validating the OTP");
					
				}else{												// USER IS THERE
						if(userInfoDB.getOtp().equalsIgnoreCase(userInfo.getOtp())){
								userInfo.setUserActive("True");
								userInfo.setId(userInfoDB.getId());
								userInfo.setName(userInfoDB.getName());
								userInfoRepo.save(userInfo);
								if(userInfo.getRole().equalsIgnoreCase("CC")){
									CCInformation ccInfo = new CCInformation(userInfo.getId(),userInfo.getName(),Utility.getTodayDate());
									ccInfoRepo.save(ccInfo);
								}else if(userInfo.getRole().equalsIgnoreCase("Teacher")){
									TeacherInformation teacher = new TeacherInformation(userInfo.getId(),userInfo.getName(),Utility.getTodayDate());
									teacherInfoRepo.save(teacher);
								}else{
									StudentInformation student = new StudentInformation(userInfo.getId(),userInfo.getName(),Utility.getTodayDate());
									studentRepo.save(student);
								}
								key.add("Status");value.add("Success");
								key.add("Message");value.add("OTP Matches");
						}else{
							key.add("Status");value.add("Warning");
							key.add("Message");value.add("OTP is not correct!Please Try Again");
						}
				}
		}catch(Exception e){
			key.clear();value.clear();
			key.add("Status");value.add("Error");
			key.add("Message");value.add("Some Error Occured at Server Side");
		}
		 return Utility.getResponce(key, value);
	}

	@Override
	public int findByIDandRole(Integer id, String role) {
		// TODO Auto-generated method stub
		return userInfoRepo.getCountByIDandRole(id, role);
	}

}
