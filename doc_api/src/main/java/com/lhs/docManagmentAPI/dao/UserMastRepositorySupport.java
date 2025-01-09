/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lhs.docManagmentAPI.model.entity.UserMast;

/**
 * @author lavanya.badham
 *
 */
@Repository
@Transactional
public class UserMastRepositorySupport {
	
	@Autowired
	UserMastRepository repo;
	
	@PersistenceContext
	EntityManager em;

	
	public String getLoginDetails(String sessionId) {
		String userCode="";
		try {
			String query="select t.user_code ||'#'|| t.entity_code || '#'|| t.div_code || '#' || t.acc_year "
					+ " || '#'|| (select div_name from div_mast where div_code = t.div_code) "
					+ " || '#'|| (select entity_name from entity_mast where entity_code = t.entity_code) || '#' from user_option_tran  t where session_id='"+sessionId+"'";
			System.out.println("query.."+query);
			Query q = em.createNativeQuery(query);
			
			userCode = (String)q.getSingleResult();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return userCode;
	}
	
	public String validateUser(String sessionId) {
		String logoutTime="";
		try {
			Query q = em.createNativeQuery("select t.logout_time from user_option_tran t where t.session_id ='"+sessionId+"'");
			logoutTime = (String)q.getSingleResult();
		}catch(Exception e) {
			logoutTime="error";
		}
		System.out.println("logoutTime..."+logoutTime);
		return logoutTime;
	}

	public UserMast getUserByPassword(String userCode,String password) {
		UserMast user=null;
		try {
			System.out.println("select * from user_mast t where t.user_code='"+userCode+"' and t.password='"+password+"'");
			Query q = em.createNativeQuery("select * from user_mast t where t.user_code='"+userCode+"' and t.password='"+password+"'",UserMast.class);
			user = (UserMast)q.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		System.out.println("user..."+user);
		return user;
	}
}
