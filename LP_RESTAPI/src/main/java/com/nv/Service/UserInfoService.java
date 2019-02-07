package com.nv.Service;

import java.util.List;

import com.nv.Model.UserInformation;



public interface UserInfoService {
	
	List<UserInformation> listUser();
	UserInformation findOne(Integer id);
	String addUser(UserInformation userInfo);
	String deleteUser(Integer id);
	String validateOTP(UserInformation userInfo);
	
	int findByIDandRole(Integer id,String role);
}
