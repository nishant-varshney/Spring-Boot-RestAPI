package com.nv.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.Utility.Utility;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

  public JWTLoginFilter(String url, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
		  throws AuthenticationException, IOException, ServletException {
	  System.out.println("attemptAuthentication called");
		    AccountCredentials creds = new ObjectMapper()
		        .readValue(req.getInputStream(), AccountCredentials.class);
		    return getAuthenticationManager().authenticate(
		        new UsernamePasswordAuthenticationToken(
		        		creds.getUsername()+"_"+creds.getRole(),
		            creds.getPassword(),
		            Collections.emptyList()
		        )
		    );
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest req,
      HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
	  System.out.println("NAME AFTER LOGIN:::"+auth.getName());
    TokenAuthenticationService
        .addAuthentication(res, auth.getName());
    res.setContentType("application/json; charset=UTF-8");
    PrintWriter printout = res.getWriter();
    List<String> key = new ArrayList<String>();
    List<String> val = new ArrayList<String>();
    String loginData[] = auth.getName().split("_");
 	String loginUserID = loginData[1];
 	String loginUser = loginData[0];
 	String loginRole = loginData[2];
    key.add("Status");val.add("Success");
    key.add("Message");val.add("Login Successfully");
    key.add("UserID");val.add(loginUserID);
    key.add("UserName");val.add(loginUser);
    key.add("UserRole");val.add(loginRole);
    printout.print(Utility.getResponce(key, val));
    printout.flush();
  }
  
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException{
	  response.setContentType("application/json; charset=UTF-8");
      PrintWriter printout = response.getWriter();
      List<String> key = new ArrayList<String>();
      List<String> val = new ArrayList<String>();
      key.add("Status");val.add("Fail");
      key.add("Message");val.add("Username/Password Invalid");
      printout.print(Utility.getResponce(key, val));
      printout.flush();
  }


}
