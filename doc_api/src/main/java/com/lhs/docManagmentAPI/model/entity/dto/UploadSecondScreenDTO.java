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
@AllArgsConstructor
@NoArgsConstructor
public class UploadSecondScreenDTO {

	private String formType;
	private String vrno;
	private String entryType;
	private String docFor;
	private String parentDocType;
	private String parentDocTypeName;
	private String divCode;
	private String divName;
	private String entityCode;
	private String entityName;
	private String keyCode;
	private String keyName;
	private String accYear;
}
