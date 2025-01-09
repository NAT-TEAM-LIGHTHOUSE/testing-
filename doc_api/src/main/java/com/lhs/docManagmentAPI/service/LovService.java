/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.List;

import com.lhs.docManagmentAPI.model.entity.dto.LovDTO;
import com.lhs.docManagmentAPI.model.entity.dto.LovEntityDTO;
import com.lhs.docManagmentAPI.model.entity.dto.LovTreeDTO;

/**
 * @author lavanya.badham
 *
 */
public interface LovService {

	
	List<LovTreeDTO> getUploadDocTypeLov(String paramString);
	
	List<LovDTO> getDocumentForLov();

	List<LovEntityDTO> getEntityMastList();

	List<LovEntityDTO> getDivMastList(String entityCode);

	List<LovEntityDTO> getAccYearMastList(String entityCode);

	List<LovEntityDTO> getDocNameList(String docCode);

	List<LovEntityDTO> getDocTypeListbyTypeofRef(String type_of_ref);
}
