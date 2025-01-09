/**
 * 
 */
package com.lhs.docManagmentAPI.model.dto;

import lombok.Data;

/**
 * @author lavanya.badham
 *
 */
@Data
public class LoginUser {
	private String username;
	private String password;
	private String tenantId;
	private String sessionId;
	private String accYear;
	private String entity;
	private String division;
}
