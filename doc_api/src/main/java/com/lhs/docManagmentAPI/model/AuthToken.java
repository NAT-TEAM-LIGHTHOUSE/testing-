/**
 * 
 */
package com.lhs.docManagmentAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lavanya.badham
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthToken {

	private String token;
	private String username;
	private String tenantId;
	private String userFullName;
	private String entityCode;
	private String divCode;
	private String accYear;
	private String entityName;
	private String divName;
	private Long sessionId;
}
