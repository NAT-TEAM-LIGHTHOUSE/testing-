/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lhs.docManagmentAPI.dao.UserMastRepository;
import com.lhs.docManagmentAPI.dao.UserMastRepositorySupport;
import com.lhs.docManagmentAPI.model.entity.UserMast;
import com.lhs.docManagmentAPI.util.Utility;

/**
 * @author lavanya.badham
 *
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService,UserDetailsService{
	
	@Autowired
	private UserMastRepository repo;
	
	@Autowired
	UserMastRepositorySupport repoSupport;
	
	@Autowired
	Utility utl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("username.."+username);
		UserMast user=null;
		try {
		 user = repo.findById(username).get();
		}catch(Exception e) {
			
		}
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUser_code(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	@Override
	public UserMast findById(String userCode) {
		UserMast user = null;
//		System.out.println("userCode.."+userCode);
		try {
			user = repo.findById(userCode).get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public List<UserMast> findAll() {
		List<UserMast> list = null;
		list = repo.findAll();
		
		return list;
	}

	public boolean validateUser(String sessionId) {
		return utl.isNull(repoSupport.validateUser(sessionId));
	}
	
	@Override
	public UserMast getUserByPassword(String userCode,String password) {
		return repoSupport.getUserByPassword(userCode, password);
	}
}
