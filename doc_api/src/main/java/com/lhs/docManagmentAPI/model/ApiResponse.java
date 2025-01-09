/**
 * 
 */
package com.lhs.docManagmentAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lavanya.badham
 *
 */

@Data
@AllArgsConstructor
public class ApiResponse<T> {

	private int status;
	private String message;
	private Object result;
}
