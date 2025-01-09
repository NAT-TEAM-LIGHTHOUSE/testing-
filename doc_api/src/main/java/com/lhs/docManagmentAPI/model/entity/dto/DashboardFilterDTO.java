/**
 * 
 */
package com.lhs.docManagmentAPI.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lavanya.badham
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardFilterDTO {

	
	private String keyCode;
	private String docCode;
	private String tnature;
	private String ref_key;
	private String file_code;
	private String softFileName;
	private String doc_detail;
	private String doc_name;
	private String doc_detail_name;
	private String doc_type;
	private String reviewed_by;
	private String captured_by;
	private String date_type;
	private String from_date;
	private String to_date;
	private String recent_flag;
	private String[] doc_type_array;
	private String entity_code;
	private String div_code;
	private String acc_year_code;
	private String expiryDays;
	private String keyword;
	private String[] docTypeFilter;
	private String contentSearch;
	  
	  private String breadcumDocType;
}
