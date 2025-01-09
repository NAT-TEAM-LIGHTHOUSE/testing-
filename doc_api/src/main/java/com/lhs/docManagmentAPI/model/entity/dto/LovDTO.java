/**
 * 
 */
package com.lhs.docManagmentAPI.model.entity.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

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
public class LovDTO {

//	@Id
//	private String slno;
//	private String doc_type;
//	private String doc_type_name;
//	private String parent_code;
	@Id
	private String slno;
	private String code;
	private String name;
	private String hidden_val;
	
}
