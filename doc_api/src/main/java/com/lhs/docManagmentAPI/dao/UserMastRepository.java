/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.model.entity.UserMast;

/**
 * @author lavanya.badham
 *
 */

@Repository
public interface UserMastRepository extends JpaRepository<UserMast, String>{

}
