package com.nv.Service.Implementation;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nv.Model.UserInformation;
import com.nv.Repository.UserInfoRepository;


@Service
public class AppUserDetailService implements UserDetailsService {
	
	 @Autowired
	    private UserInfoRepository userinfoRepo;
	 
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 	System.out.println("CAlled3"+username);
		 	String loginData[] = username.split("_");
		 	String loginRole = loginData[1];
		 	String loginUser = loginData[0];
		 	System.out.println(loginRole+"---"+loginUser);
	        UserInformation user = userinfoRepo.findActiveUser(loginUser, loginRole);
	        String userNametoSend="";
	        if (user == null) {
	        	System.out.println("usernot found");
	            throw new UsernameNotFoundException("User '" + username + "' not found");
	        }else{
	        	 Integer userId = user.getId();
	        	 String userName = user.getName();
	        	 userNametoSend= userName+'_'+userId+'_'+user.getRole();
	        }

	        return org.springframework.security.core.userdetails.User
	                .withUsername(userNametoSend)
	                .password(user.getPassword())
	                .authorities(Collections.emptyList())
	                .accountExpired(false)
	                .accountLocked(false)
	                .credentialsExpired(false)
	                .disabled(false)
	                .build();

	    }

}
