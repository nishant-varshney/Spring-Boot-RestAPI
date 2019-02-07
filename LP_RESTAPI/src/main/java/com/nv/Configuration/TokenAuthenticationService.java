package com.nv.Configuration;

import java.util.Date;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
	
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	  static final String SECRET = "ThisIsASecret";
	  static final String TOKEN_PREFIX = "Bearer";
	  static final String HEADER_STRING = "Authorization";

	  static void addAuthentication(HttpServletResponse res, String username) {
	    String JWT = Jwts.builder()
	        .setSubject(username)
	        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	        .signWith(SignatureAlgorithm.HS512, SECRET)
	        .compact();
	    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	    
	  }

	  static Authentication getAuthentication(HttpServletRequest request) {
	    String token = request.getHeader(HEADER_STRING);
	    System.out.println("my received token:::"+token);
	//    JSONObject errorAuthentication = new JSONObject();

		    if (token != null) {
		      // parse the token.
		      String user = Jwts.parser()
		          .setSigningKey(SECRET)
		          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
		          .getBody()
		          .getSubject();
		      System.out.println("Username in token::"+user);
		      return user != null ?
		          new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
		          null;
		    }
	    return null;
	  }
	  

}
