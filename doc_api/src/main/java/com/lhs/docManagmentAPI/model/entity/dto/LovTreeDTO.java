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
public class LovTreeDTO {

	@Id
	  private String slno;
	  
	  private String code;
	  
	  private String name;
	  
	  private String hidden_val;
	  
	  private String parent;
	  
	  private String grandParent;
}
