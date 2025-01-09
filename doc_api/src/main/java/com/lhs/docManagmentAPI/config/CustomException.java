/**
 * 
 */
package com.lhs.docManagmentAPI.config;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lavanya.badham
 *
 */

@Component	
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends Exception {
	
	private String error_code;
	private String error_message;
}
