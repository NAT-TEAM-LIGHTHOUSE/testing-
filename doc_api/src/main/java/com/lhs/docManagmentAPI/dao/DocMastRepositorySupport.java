/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.util.Utility;

/**
 * @author lavanya.badham
 *
 */
@Transactional
@Repository
public class DocMastRepositorySupport {

	@PersistenceContext
	EntityManager em;

	@Autowired
	Utility utl;
	
	@Autowired
	public DocMastRepository repo;
	
	@Autowired
	public DocTranRepository docTranrepo;
	
	
	public Long getMaxDocCode() {
		Long list = null;
		try {
			String query = "SELECT nvl((max(DOC_CODE)+1),1) FROM DOC_MAST T ";
			System.out.println("query..." + query);
			Query q = this.em.createNativeQuery(query);
			BigDecimal num = (BigDecimal) q.getSingleResult();
			list = Long.valueOf(num.longValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}


