package com.nv.Configuration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.nv.Utility.Utility;

public class JWTAuthenticationFilter extends GenericFilterBean {

	  @Override
	  public void doFilter(ServletRequest request,
	             ServletResponse response,
	             FilterChain filterChain)
	      throws IOException, ServletException {
			   try{
					  Authentication authentication = TokenAuthenticationService
				        .getAuthentication((HttpServletRequest)request);
				    SecurityContextHolder.getContext()
				        .setAuthentication(authentication);
				    filterChain.doFilter(request,response);
			  }catch(ExpiredJwtException expiredJWTException){
				//  e.printStackTrace();
				  SecurityContextHolder.getContext()
			        .setAuthentication(null);
				  response.setContentType("application/json; charset=UTF-8");
			      PrintWriter printout = response.getWriter();
			      List<String> key = new ArrayList<String>();
			      List<String> val = new ArrayList<String>();
			      key.add("Status");val.add("Fail");
			      key.add("Message");val.add("JWT Token Expired!Please Login Again");
			      printout.print(Utility.getResponce(key, val));
			      printout.flush();
			  }catch (SignatureException misMatchJWTException) {
				// TODO: handle exception
				  SecurityContextHolder.getContext()
			        .setAuthentication(null);
				  response.setContentType("application/json; charset=UTF-8");
			      PrintWriter printout = response.getWriter();
			      List<String> key = new ArrayList<String>();
			      List<String> val = new ArrayList<String>();
			      key.add("Status");val.add("Fail");
			      key.add("Message");val.add("JWT Token Invalid");
			      printout.print(Utility.getResponce(key, val));
			      printout.flush();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("exception occured::"+e.toString());
				e.printStackTrace();
				 SecurityContextHolder.getContext()
			        .setAuthentication(null);
				  response.setContentType("application/json; charset=UTF-8");
			      PrintWriter printout = response.getWriter();
			      List<String> key = new ArrayList<String>();
			      List<String> val = new ArrayList<String>();
			      key.add("Status");val.add("Fail");
			      key.add("Message");val.add("Error During JWT Token Validation.");
			      printout.print(Utility.getResponce(key, val));
			      printout.flush();
			}
		}
}