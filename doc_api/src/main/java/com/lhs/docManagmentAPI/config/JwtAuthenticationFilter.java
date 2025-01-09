/**
 * 
 */
package com.lhs.docManagmentAPI.config;

import static com.lhs.docManagmentAPI.model.Constants.HEADER_STRING;
import static com.lhs.docManagmentAPI.model.Constants.TENANT_HEADER;
import static com.lhs.docManagmentAPI.model.Constants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * @author lavanya.badham
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter{

		@Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;
	    
	    @Override
	    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
	    	String flag="";
	    	String byPassTenant="";
	    	try {
	    	 flag =  req.getParameter("bypassFlag");
	    	 byPassTenant =  req.getParameter("tenantId");
//	    	System.out.println("flag..."+flag);
	    	}catch(Exception e) {
	    		
	    	}
	    	if(flag==null || flag.equals("")) {
	    	String header = req.getHeader(HEADER_STRING);
	        String username = null;
	        String authToken = null;
	        String tokenHeader = req.getHeader(TENANT_HEADER);
	        if(tokenHeader !=null) {
//	        	System.out.println("tokenHeader.."+tokenHeader);
	        	TenantContext.setCurrentTenant(tokenHeader);       	
	        }
	        
	        if (header != null && header.startsWith(TOKEN_PREFIX)) {
//	        	System.out.println("header..."+header);
	            authToken = header.replace(TOKEN_PREFIX,"");
	            try {
	                username = jwtTokenUtil.getUsernameFromToken(authToken);
	            } catch (IllegalArgumentException e) {
	                logger.error("an error occured during getting username from token", e);
	            } catch (ExpiredJwtException e) {
	                logger.warn("the token is expired and not valid anymore", e);
	            } catch(SignatureException e){
	                logger.error("Authentication Failed. Username or Password not valid.");
	            }
	        } else {
	            logger.warn("couldn't find bearer string, will ignore the header");
	        }
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//	        	System.out.println("username.."+username);
	        	
	        	try {

	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
	                logger.info("authenticated user " + username + ", setting security context");
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	        	}catch(Exception e) {
	        		
	        	}
	        }
	   	}else {
	  	TenantContext.setCurrentTenant(byPassTenant);
//	  		System.out.println("1122..");
	 	}

	        chain.doFilter(req, res);
	    }

}
