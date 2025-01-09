/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.model.entity.dto.LovDTO;

/**
 * @author lavanya.badham
 *
 */
@Transactional
@Repository
public class LovDAOSupport {

	@PersistenceContext
	  EntityManager em;
	
	 public List<LovDTO> getUploadDocTypeLov(String userCode) {
		    List<LovDTO> list = null;
		    try {
		    //  String query = "select rownum slno,\r\n       l.root || '#' || j.doc_type_name code,\r\n       l.path name,\r\n       j.doc_tran_name_str hidden_val\r\n  from (select connect_by_root(doc_type) root,\r\n               sys_connect_by_path(doc_type || '(' || doc_type_name || ')',\r\n                                   ' => ') path\r\n          from doc_type_mast t\r\n         where code_level = 1\r\n         start with instr((select ' ' || m.doc_type_str || ' '\r\n                            from user_mast m\r\n                           where m.user_code = '" + userCode + "'),\r\n                          ' ' || DOC_TYPE || ' ') <> 0\r\n        connect by doc_type = prior parent_code) l,\r\n       doc_type_mast j\r\n where l.root = j.doc_type";
		    	String query="select rownum slno,doc_type code ,doc_type_name name ,parent_code hidden_val from doc_type_mast";
		      System.out.println("doc type query..." + query);
		      Query q = this.em.createNativeQuery(query, LovDTO.class);
		      list = q.getResultList();

		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    System.out.println("list .........."+list);
		    return list;
		  }
	 
	 public List<LovDTO> getDocForLov() {
		    List<LovDTO> list = null;
		    try {
		      String query = "select rownum slno, '' hidden_val, t.key_code code, t.key_name name from view_doc_key_mast t";
		      System.out.println("query..." + query);
		      Query q = this.em.createNativeQuery(query, LovDTO.class);
		      list = q.getResultList();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
		  }
}
