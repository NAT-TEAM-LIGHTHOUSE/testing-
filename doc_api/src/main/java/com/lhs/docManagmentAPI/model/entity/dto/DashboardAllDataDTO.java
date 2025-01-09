/**
 * 
 */
package com.lhs.docManagmentAPI.model.entity.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

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
@Entity
public class DashboardAllDataDTO {

		
		@Id
		private Long slno;	
		private String file_name;
//		private String to_file_name;
		private String doc_type;
		@Transient
		private String icon;
		private String doc_name;
		private String doc_desc;
		private String doc_type_name;
		private String file_code;
		private String created;
		private String reviewed;
		private String doc_code;
		private Long doc_id;
//		private String doc_slno;
//		private String ref_key;
//		private String ref_key_info;
//		private String key_code;
//		private String key_mast;
//		private String doc_detail;
//		private String expiry_flag;
//		private String doc_search_str;
		private String doc_date;
		private String vrno;
		
		@Transient
		private String parent;
		
		@Transient
		private String row_num;
		
//		@Transient
//		private String doc_type_name;
		
}
