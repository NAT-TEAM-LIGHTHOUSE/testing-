/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.model.entity.DocTranImage;

/**
 * @author lavanya.badham
 *
 */

@Repository
public interface DocTranImageRepository extends JpaRepository<DocTranImage, Long>{

	
	@Query("select doc_id as doc_slno from DocTranImage t where t.doc_id=:doc_id")
	public Long findDocId(Long doc_id);
}