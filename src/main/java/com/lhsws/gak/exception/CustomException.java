/**
 * 
 */
package com.lhsws.gak.exception;

import com.lhsws.gak.constants.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author akash.meshram
 *
 */

@Generated
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomException extends Exception {
	private static final long serialVersionUID = -2361006705878103968L;
	private ErrorCode errorCode;
	
	public CustomException(ErrorCode errorCode, Throwable e) {
		super(e);
		this.errorCode = errorCode;
	}
	
	public CustomException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public void printError(ErrorCode errorCode, Throwable e) {
		log.info("ErrorCode: {} ",errorCode);
	}
	
	public void printError(String message,ErrorCode errorCode, Throwable e) {
		log.info("Error Message: {}, ErrorCode: {}  ",message,errorCode);
	}
	
}
