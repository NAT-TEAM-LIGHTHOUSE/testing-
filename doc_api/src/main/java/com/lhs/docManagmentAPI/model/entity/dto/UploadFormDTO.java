/**
 * 
 */
package com.lhs.docManagmentAPI.model.entity.dto;


import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lavanya.badham
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFormDTO {
	
	private String doc_id;
	private String doc_code;
	private String doc_date;
	private String doc_type;
	private String doc_name;
	private String doc_details;
//	private String key_code;
//	private String key_mast;
	private String attched_to;
	private  MultipartFile[] files;	
	private String[] fileCode;
	private String[] expiryDate;
	private String user_code;
	private String[] fileDesc;
//	private String ref_key;
	private String multipleFiles;
	private String[] docSlno;
	private String physicalFileFlag;
	private String physicalFilePath;
	private String entity_code;
	private String acc_year;
	private String div_code;
	private String[] keywordSearch;
	  
	  private String[] docTypeStr;
	  
	  private Date createddate;
	  private Date lastupdate;

}
