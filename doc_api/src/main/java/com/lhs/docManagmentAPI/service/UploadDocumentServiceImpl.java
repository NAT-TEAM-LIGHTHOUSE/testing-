/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.ArrayList;
import java.util.List;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.docManagmentAPI.dao.DocMastRepository;
import com.lhs.docManagmentAPI.dao.DocMastRepositorySupport;
import com.lhs.docManagmentAPI.dao.DocTranRepository;
import com.lhs.docManagmentAPI.dao.DocTranRepositorySupport;
import com.lhs.docManagmentAPI.dao.DocTypeMastRepository;
import com.lhs.docManagmentAPI.dao.DocTypeMastRepositorySupport;
import com.lhs.docManagmentAPI.model.entity.DocTypeMast;
import com.lhs.docManagmentAPI.model.entity.dto.LovEntityDTO;
import com.lhs.docManagmentAPI.model.entity.dto.UploadFormDTO;
import com.lhs.docManagmentAPI.model.entity.dto.UploadSecondScreenDTO;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author lavanya.badham
 *
 */

@Service
public class UploadDocumentServiceImpl implements UploadDocumentService{

	
	DocMastRepositorySupport docMastRepSupport;
	
	DocTypeMastRepositorySupport docTyperepo;
	
	@Autowired
	DocTranRepositorySupport doctranrepo;
	
	@PersistenceContext
	  EntityManager em;
	
	@Autowired
	DocTypeMastRepository docTypeMastRepo;
	
	@Autowired
	DocMastRepository docMastRepo;
	
	public Long getMaxDocCode() {
		Long docCode = null;
		try {
			//docCode = docMastRepSupport.getMaxDocCode();
			docCode = doctranrepo.getMaxDocId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return docCode;
	}
	
	@Override
	public boolean saveAndUploadNewFiles(UploadFormDTO dto) {
		boolean result = false;
		try {
//			 Long doc_code = getMaxDocCode();
//			 dto.setDoc_code(doc_code.toString());
			result= doctranrepo.saveNewDocMastTranAndImage(dto);
//			System.out.println("result---->"+result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	@Override 
	public String getRefType(String docType) {
//		System.out.println("docTypeMastRepo.getRefType(docType)--------------------------"+docTypeMastRepo.getRefType(docType));
		return docTypeMastRepo.getRefType(docType);
	}
	
	

	@Override
	public List<DocTypeMast> getDefaultSecondScreenData(String docCode, String docType) {
		UploadSecondScreenDTO dto = new UploadSecondScreenDTO();
		List<DocTypeMast> DTO = new ArrayList<DocTypeMast>();
		try {
			DTO = docTypeMastRepo.findAll();
//			System.out.println("DTO----------->"+DTO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return DTO;
	}
		

	@Override
	public String getTypeOfRef(String docType) {
		String TypeofRef=null;
		try {
//			System.out.println("docType-->"+docType);
			 TypeofRef=docTypeMastRepo.findTypeOfRef(docType);
//			 System.out.println("TypeofRef---->"+TypeofRef);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return TypeofRef;
	}

	@Override
	public String validateFilePath(String docType) {
	
//        String functionName = "lhs_gd_train.get_doc_path";
//
//		try {
//			 // Prepare the SQL call with the Oracle function
//            String sql = "{ ? = call " + functionName + " }";	
//			Session hibernateSession = (Session) this.em.unwrap(Session.class);
//			hibernateSession.doWork(new org.hibernate.jdbc.Work() {
//
//				@Override
//				public void execute(Connection connection) throws SQLException {
//					PreparedStatement pb = connection.prepareStatement(sql);
//					((CallableStatement) pb).registerOutParameter(1, Types.VARCHAR);
//
//		                // Execute the function
//					pb.execute();
//
//		                // Retrieve the result
//		            String    filePath = ((CallableStatement) pb).getString(1);
//		            System.out.println("filePath----->"+filePath);
//					
//				}
//
//			});
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		return docType;
		  
		
	}
	        

	public String getdocTypeName(String docType) {
		String list = null;
		    try {
		      if (docType != null) {
		    	  
		  
		    list =docTypeMastRepo.getDocTypeName(docType); 
		      
//		      System.out.println("list for doc_type_name---->"+list);
		      }
		     
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	}

	@Override
	public List<LovEntityDTO> getEntityByUserCode(String userCode) {
		
		  List<LovEntityDTO> list = null;

		    try {
	 String query = "select rownum slno,t.entity_code code, t.entity_name name \r\n  from entity_mast t\r\n";
		      if (userCode != null) {
		        query = query + " where user_code= '" + userCode + "'\r\n"; 
//		      System.out.println("entitylist  by  user_code list..." + query);
		      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
		      list = q.getResultList();
//		      System.out.println("list--->"+list);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	
		
	}

	@Override
	public List<LovEntityDTO> getDivCodeByEntityCode(String entityCode) {
		  List<LovEntityDTO> list = null;

		    try {
			      String query = "select rownum slno, t.div_code code, t.div_name name\r\n  from div_mast t";
		      if (entityCode != null) {
		        query = query + " where entity_code= '" + entityCode + "'\r\n"; 
//		      System.out.println("Div list  by  user_code list..." + query);
		      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
		      list = q.getResultList();
//		      System.out.println("list--->"+list);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	
	}

	@Override
	public List<LovEntityDTO> getAccountingYear(String entityCode) {
		 List<LovEntityDTO> list = null;

		    try {
			      String query = "select rownum slno, t.acc_year code, t.acc_year name\r\n  from acc_year_mast t ";
		      if (entityCode != null) {
		        query = query + " where t.entity_code= '" + entityCode + "'\r\n"; 
//		      System.out.println("year list  by  user_code list..." + query);
		      Query q = this.em.createNativeQuery(query, LovEntityDTO.class);
		      list = q.getResultList();
//		      System.out.println("list--->"+list);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return list;
	}
  
	@Override
	public String getDocCodeName(String docCode) {
		String DocName = "";
		    try {
		    	DocName =docMastRepo.getDocCodeName(docCode); 
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return DocName;
	}


}
