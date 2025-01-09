/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.List;

import com.lhs.docManagmentAPI.model.entity.DocTypeMast;
import com.lhs.docManagmentAPI.model.entity.dto.LovEntityDTO;
import com.lhs.docManagmentAPI.model.entity.dto.UploadFormDTO;
import com.lhs.docManagmentAPI.model.entity.dto.UploadSecondScreenDTO;

/**
 * @author lavanya.badham
 *
 */
public interface UploadDocumentService {

	
	Long getMaxDocCode();
	
	boolean saveAndUploadNewFiles(UploadFormDTO formDTO);

	String validateFilePath(String docType);

	String getTypeOfRef( String docType);
	
	String getRefType(String docType);

	List<DocTypeMast> getDefaultSecondScreenData(String docCode, String docType);
	
	
	String getdocTypeName(String docType);

	List<LovEntityDTO> getEntityByUserCode(String userCode);

	List<LovEntityDTO> getDivCodeByEntityCode(String entityCode);

	List<LovEntityDTO> getAccountingYear(String entityCode);

	String getDocCodeName(String docCode);

	
}
