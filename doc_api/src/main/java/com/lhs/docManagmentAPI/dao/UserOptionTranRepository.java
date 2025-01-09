package com.lhs.docManagmentAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.model.entity.UserOptionTran;


/**
 * @author AMAN.DAHAT
 *
 */
@Repository
public interface UserOptionTranRepository extends JpaRepository<UserOptionTran, Long> {
	
	
	@Query("select max(t.session_id)+1 from UserOptionTran t ")
	public Long getMaxSessionId();
}
