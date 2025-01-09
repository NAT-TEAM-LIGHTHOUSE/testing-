/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.model.entity.DocTypeMast;

/**
 * @author lavanya.badham
 *
 */
@Repository
public interface DocTypeMastRepository extends JpaRepository<DocTypeMast, String> {

	@Query("select t.doc_image_flag from DocTypeMast t where  t.doc_type=:doc_type")
	public String findDocImageFlag(String doc_type);


	@Query("select t.erp_ref_type from DocTypeMast t where t.doc_type=:doc_type")
	public String getRefType(String doc_type);


	@Query("select t.ref_tnature from DocTypeMast t where t.doc_type=:docType")
	public String findTypeOfRef(String docType);

	@Query("select t.doc_type_name from DocTypeMast t where t.doc_type=:docType")
	public String getDocTypeName(String docType);

}
