/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.model.entity.DocMast;

/**
 * @author lavanya.badham
 *
 */
@Repository
public interface DocMastRepository extends JpaRepository<DocMast, String>{

	@Query("select doc_name from DocMast where doc_code=:docCode")
	public String getDocCodeName(String docCode);

}
 