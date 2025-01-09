/**
 * 
 */
package com.lhs.docManagmentAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.docManagmentAPI.model.entity.DocTypeMast;
import com.lhs.docManagmentAPI.model.entity.dto.LovEntityDTO;
import com.lhs.docManagmentAPI.model.entity.dto.ResponseDTO;
import com.lhs.docManagmentAPI.model.entity.dto.UploadFormDTO;
import com.lhs.docManagmentAPI.model.entity.dto.UploadSecondScreenDTO;
import com.lhs.docManagmentAPI.service.UploadDocumentService;
import com.lhs.docManagmentAPI.util.Utility;

/**
 * @author lavanya.badham
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/upload")
public class UploadDocumentsController {
	
	@Autowired
	UploadDocumentService service;
	
	@Autowired
	Utility utl;

	@GetMapping("/getMaxDocCode")
	public Long getDocumentForLov() {
		return service.getMaxDocCode();
	}

	@GetMapping("/getRefVrno")
	public ResponseDTO getRefKeyCode(@RequestParam("refKey") String refKey) {
		String refKeyVrno ="";
		ResponseDTO dto= new ResponseDTO();
	    
		dto.setResult(refKeyVrno);		
		return dto;
	}
	
	@PostMapping("/uploadNewDocs")
	public boolean saveAndUploadNewDocs(@ModelAttribute  UploadFormDTO formDTO) {
		return service.saveAndUploadNewFiles(formDTO);
		
	}

	
	@GetMapping("/getRefType")
	public String getRefType(@RequestParam String docType) {
		 
		return service.getRefType(docType);
	}
	
	@GetMapping("/getDefaultSecondScreenData")
	public List<DocTypeMast> getDefaultSecondScreenData(@RequestParam String docCode,@RequestParam String docType) {
	return service.getDefaultSecondScreenData(docCode, docType);
	}
	
	
	@GetMapping("/validatePhysicalPath")
	public ResponseDTO validatePhysicalPath(@RequestParam("docType") String docType) {
		ResponseDTO dto= new ResponseDTO();	
		dto.setResult(service.validateFilePath(docType));		
		return dto;
		
	}
	
	
	@GetMapping("/getTypeOfRef")
	public String getTypeOfRef(@RequestParam(required = false,value="docType") String docType) {
		return  service.getTypeOfRef(docType);
	}
	
	
	@GetMapping("/getDocTypeName")
	  public String getDocTypeName(@RequestParam(required = false, value = "docType") String docType){
		return this.service.getdocTypeName(docType);
	  }
	

	 
	
	 @GetMapping("/getEntityByUserCode")
	  public List<LovEntityDTO> getEntityByUserCode(@RequestParam(required = false, value = "userCode") String userCode){
		return this.service.getEntityByUserCode(userCode);
		  
	  }
	
	 @GetMapping("/getDivCodeByEntityCode")
	  public List<LovEntityDTO> getDivCodeByEntityCode(@RequestParam(required = false, value = "entityCode") String entityCode){
		return this.service.getDivCodeByEntityCode(entityCode);
		  
	  }
	 
	 
	 @GetMapping("/getAccountingYear")
	  public List<LovEntityDTO> getAccountingYear(@RequestParam(required = false, value = "entityCode") String entityCode){
		return this.service.getAccountingYear(entityCode);
		  
	  }
	 

	  @GetMapping("/getDocCodeName")
	  public String getDocCodeName(@RequestParam(required = false, value = "docCode") String docCode){
		return this.service.getDocCodeName(docCode);
		  
	  }
	

	
	
	

}
