/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lhs.docManagmentAPI.config.TenantContext;
import com.lhs.docManagmentAPI.dao.DocTranRepositorySupport;
import com.lhs.docManagmentAPI.model.entity.DocTran;
import com.lhs.docManagmentAPI.model.entity.dto.DashboardAllDataDTO;
import com.lhs.docManagmentAPI.model.entity.dto.DashboardFilterDTO;
import com.lhs.docManagmentAPI.util.Utility;


/**
 * @author lavanya.badham
 *
 */

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	DocTranRepositorySupport docTran;
	 	
	@Autowired
	Utility utl;
	
	@PersistenceContext
	EntityManager em;
	
	@Value("${Local-Drive}")
	 private String localDrive;
	
	@Value("${ocr-resource-path}")
	 private String ocrResourcePath;
	
	public  List<DashboardAllDataDTO> getAllDocsDashboardList(String fileType, DashboardFilterDTO dto,String userCode){
		
		String indexPath =localDrive+"//DOC_MANAGEMENT//INDEXES//"+TenantContext.getCurrentTenant();
		List<DashboardAllDataDTO> list1 = null;
//		List<DashboardAllDataDTO> data = docTran.getAllDocsDashboardList(dto,userCode,indexPath);
		List<DocTran> data = docTran.getAllDocsDashboardList1(dto, userCode, indexPath);
		List<DocTran> list = null;
		if(data !=null && data.size()>0) {
//			System.out.println("data..."+data);
			list = new ArrayList<DocTran>();	
			list1 = new ArrayList<DashboardAllDataDTO>();
			for (DocTran ent : data) {
				DocTran ent1 = ent;
				DashboardAllDataDTO list2 = new DashboardAllDataDTO();
//				list2.setRow_num(ent1.getRow_num());
//				System.out.println("file_name..."+ent.getTo_file_name());
				if(ent1.getCreateddate() != null) {
					list2.setCreated(ent1.getCreateddate().toString());
				}
				if(ent1.getTo_file_name() != null) {
					list2.setFile_name(ent1.getTo_file_name());
				}else {
					list2.setFile_name(ent1.getFrom_file_name());
				}

				list2.setDoc_name(ent1.getFrom_file_name());
				list2.setDoc_code(ent1.getDoc_code());
//				list2.setDoc_date(ent1.getDoc_date().toString());
				list2.setDoc_desc(ent1.getDoc_desc());
				list2.setDoc_id(ent1.getDoc_id());
				list2.setDoc_type(ent1.getDoc_type());
				list2.setFile_code(ent1.getFile_code());
//				list2.setReviewed(ent1.getReviewdate().toString());
				list2.setSlno(ent1.getSlno());
//				list2.setVrno(ent1.getVrno());
				list2.setDoc_type_name(ent1.getDoc_type_name());
			if(!utl.isNull(ent.getTo_file_name())) {
				
					if( ent1.getTo_file_name().endsWith("doc") || ent1.getTo_file_name().endsWith("docx") ) {
						list2.setIcon("./assets/imgs/word.png");
		            	 }else if(ent1.getTo_file_name().endsWith("jpg") ||ent1.getTo_file_name().endsWith("jpeg") || ent1.getTo_file_name().endsWith("png"))  {
		            		 list2.setIcon("./assets/imgs/image.png");
					       
		            	}else if(ent1.getTo_file_name().endsWith("pdf") || ent1.getTo_file_name().endsWith("PDF") ){
		            		list2.setIcon("./assets/imgs/pdf.png");
						     
		            	}else if(ent1.getTo_file_name().endsWith("xlsx") ||ent1.getTo_file_name().endsWith("xls") ){
		            		list2.setIcon("./assets/imgs/excel.png");
						   
		            	}
		            	else {
		            		list2.setIcon("./assets/imgs/blank-file.png");
						     
		            	}
				}
			
			
//			PWEI
			
			if(!utl.isNull(fileType) && !fileType.equalsIgnoreCase("All")) {
				if(fileType.equalsIgnoreCase("P") && !utl.isNull(ent1.getTo_file_name()) && (ent1.getTo_file_name().endsWith(".pdf") || ent1.getTo_file_name().endsWith(".pdf"))) {
					list1.add(list2);
				}else 	if(fileType.equalsIgnoreCase("W") && !utl.isNull(ent1.getTo_file_name()) && (ent1.getTo_file_name().endsWith(".doc") || ent1.getTo_file_name().endsWith(".docx") || ent1.getTo_file_name().endsWith(".docs"))) {
					list1.add(list2);
				}else 	if(fileType.equalsIgnoreCase("E") && !utl.isNull(ent1.getTo_file_name()) && (ent1.getTo_file_name().endsWith(".xls") || ent1.getTo_file_name().endsWith(".xlsx") || ent1.getTo_file_name().endsWith(".XLS"))) {
					list1.add(list2);
				}else 	if(fileType.equalsIgnoreCase("I") && !utl.isNull(ent1.getTo_file_name()) && (ent1.getTo_file_name().endsWith(".png") || ent1.getTo_file_name().endsWith(".jpg") || ent1.getTo_file_name().endsWith(".jpeg"))) {
					list1.add(list2);
				}
			
			}else {
				list1.add(list2);
			}
				
			}
			
		}
//		System.out.println("list1-----------------------"+list1);
		 return list1;
	 }
}
