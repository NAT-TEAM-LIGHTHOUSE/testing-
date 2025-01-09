/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.docManagmentAPI.dao.LovDAOSupport;
import com.lhs.docManagmentAPI.model.entity.dto.LovDTO;
import com.lhs.docManagmentAPI.model.entity.dto.LovEntityDTO;
import com.lhs.docManagmentAPI.model.entity.dto.LovTreeDTO;
import com.lhs.docManagmentAPI.util.Utility;

/**
 * @author lavanya.badham
 *
 */
@Service
public class LovServiceImpl implements LovService{

	
	@Autowired
	LovDAOSupport lovDao;
	
	@PersistenceContext
	  EntityManager em;
	
	 @Autowired
	 Utility utl;
	  
	public List<LovTreeDTO> getUploadDocTypeLov(String userCode) {
	    List<LovDTO> list = null;
	    List<LovTreeDTO> list1 = new ArrayList<>();
	    String[] arr = new String[0];
	    try {
	      list = this.lovDao.getUploadDocTypeLov(userCode);
	      System.out.println("list ---->"+list);
	      if (list != null && list.size() > 0)
	        for (LovDTO lovDTO : list) {
	        	  LovTreeDTO d = new LovTreeDTO();
	        	try {
	    	        d.setSlno(lovDTO.getSlno());
//	        		d.setCode(lovDTO.getDoc_type());
//	        		d.setName(lovDTO.getDoc_type_name());
//	        		d.setParent(lovDTO.getParent_code());
	    	        d.setCode(lovDTO.getCode());
	        		d.setName(lovDTO.getName());
	        		d.setParent(lovDTO.getHidden_val());
	    	        
	        	}catch(Exception e) {
	        		e.printStackTrace();
	        	}
	          list1.add(d);
	        }  
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    System.out.println("list ---->"+list1);
	    return list1;
	  }
	
	public List<LovDTO> getDocumentForLov() {
	    List<LovDTO> list = null;
	    try {
	      list = this.lovDao.getDocForLov();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return list;
	  }

	@Override
	public List<LovEntityDTO> getEntityMastList() {
		List<LovEntityDTO> list = null;
	    try {
	      String query = "select rownum slno,t.entity_code code, t.entity_name name \r\n  from entity_mast t\r\n";
	      System.out.println("query for entity List..." + query);
	      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
	      list = q.getResultList();
	      System.out.println("list-->"+list);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return list;
	}

	@Override
	public List<LovEntityDTO> getDivMastList(String entityCode) {
		   List<LovEntityDTO> list = null;
		    try {
		      String query = "select rownum slno, t.div_code code, t.div_name name\r\n  from div_mast t";
		      if (!this.utl.isNull(entityCode))
		        query = query + " where t.entity_code = '" + entityCode + "'\r\n"; 
		      System.out.println("query for division list..." + query);
		      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
		      list = q.getResultList();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	}

	@Override
	public List<LovEntityDTO> getAccYearMastList(String entityCode) {
		 List<LovEntityDTO> list = null;
		    try {
		      String query = "select rownum slno, t.acc_year code, t.acc_year name\r\n  from acc_year_mast t ";
		      if (!this.utl.isNull(entityCode)) {
		        query = query + " where t.entity_code = '" + entityCode + "'\r\n";
		      } else {
		        query = "select rownum slno , '' hidden_val,m.* from (select distinct t.acc_year code, t.acc_year name\r\n  from acc_year_mast t) m";
		      } 
		      System.out.println("query for accounting year ..." + query);
		      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
		      list = q.getResultList();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	}
	
	@Override
	public List<LovEntityDTO> getDocNameList(String doc_type) {
		 List<LovEntityDTO> list = null;
		    try {
		      String query = "select rownum slno, t.doc_code code, t.doc_name name from doc_mast t ";
		      if (!this.utl.isNull(doc_type)) {
		        query = query + " where t.doc_type = '" + doc_type + "'\r\n";
		      } else {
		        query = "select rownum slno , '' hidden_val,m.* from (select distinct t.doc_code code, t.doc_name name\r\n  from doc_mast t) m";
		      } 
		      System.out.println("query for doc name  ..." + query);
		      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
		      list = q.getResultList();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	}
	
	@Override
	public List<LovEntityDTO> getDocTypeListbyTypeofRef(String type_of_ref) {
		 List<LovEntityDTO> list = null;
		    try {
		    	
		      String query = "";
		    	
		      if (!this.utl.isNull(type_of_ref) && type_of_ref.equalsIgnoreCase("Transaction")) {
		    	  query = "select   rownum slno,doc_type code ,doc_type_name name ,parent_code hidden_val from doc_type_mast t where t.erp_ref_type='Y' and t.ref_tnature is not null ";
		      }else  if (!this.utl.isNull(type_of_ref) && type_of_ref.equalsIgnoreCase("Master")) {
		    	  query = "select rownum slno,doc_type code ,doc_type_name name ,parent_code hidden_val from doc_type_mast t where t.erp_ref_type='Y' and t.ref_tnature is null ";
		      }else  if (!this.utl.isNull(type_of_ref) && type_of_ref.equalsIgnoreCase("Other")) {
		    	  query = "select rownum slno,doc_type code ,doc_type_name name ,parent_code hidden_val from doc_type_mast t where nvl(t.erp_ref_type,'N')='N' ";
		      }else {
		    	  query = "select rownum slno,doc_type code ,doc_type_name name ,parent_code hidden_val from doc_type_mast t";
		      }
		       
		      System.out.println("query for doc type lov..." + query);
		      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
		      list = q.getResultList();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	}
	
}
