/**
 * 
 */
package com.lhs.docManagmentAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.docManagmentAPI.model.entity.dto.LovDTO;
import com.lhs.docManagmentAPI.model.entity.dto.LovEntityDTO;
import com.lhs.docManagmentAPI.model.entity.dto.LovTreeDTO;
import com.lhs.docManagmentAPI.service.LovService;

/**
 * @author lavanya.badham
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lov")
public class LovController {
	
	@Autowired
	LovService service;
	
	 @GetMapping({"/getUploadDocTypeLov/{userCode}"})
	  public List<LovTreeDTO> getUploadDocTypeLov(@PathVariable String userCode) {
	    return this.service.getUploadDocTypeLov(userCode);
	  }
	 
	 @GetMapping({"/getDocForLov"})
	  public List<LovDTO> getDocumentForLov() {
	    return this.service.getDocumentForLov();
	  }
	 
	 
	 @GetMapping({"/getEntityList"})
	  public List<LovEntityDTO> getEntityList() {
	    return this.service.getEntityMastList();
	  }
	 
	 
	  @GetMapping({"/getDivMastList"})
	  public List<LovEntityDTO> getDivMastList(@RequestParam(required = false, value = "entityCode") String entityCode) {
	    return this.service.getDivMastList(entityCode);
	  }
	  
	  @GetMapping({"/getAccYearMastList"})
	  public List<LovEntityDTO> getAccYearMastList(@RequestParam(required = false, value = "entityCode") String entityCode) {
	    return this.service.getAccYearMastList(entityCode);
	  }
	 
	  @GetMapping({"/getDocNameList"})
	  public List<LovEntityDTO> getDocNameList(@RequestParam(required = false, value = "doc_type") String doc_type) {
	    return this.service.getDocNameList(doc_type);
	  }
	  
	  @GetMapping({"/getDocTypeListbyTypeofRef"})
	  public List<LovEntityDTO> getDocTypeListbyTypeofRef(@RequestParam(required = false, value = "type_of_ref") String type_of_ref) {
	    return this.service.getDocTypeListbyTypeofRef(type_of_ref);
	  }
	 
}
