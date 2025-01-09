/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.List;

import com.lhs.docManagmentAPI.model.entity.UserMast;

/**
 * @author lavanya.badham
 *
 */
public interface UserService {

	UserMast findById(String userCode);	
	
	List<UserMast> findAll();
	
	boolean validateUser(String sessionId);

	UserMast getUserByPassword(String userCode, String password);
}
