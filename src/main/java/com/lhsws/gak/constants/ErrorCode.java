/**
 * 
 */
package com.lhsws.gak.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author akash.meshram
 *
 */

@AllArgsConstructor
@Getter
@ToString
public enum ErrorCode {

	PUBLIC_KEY_FILE_NOT_FOUND("E0001","Public key file not found!","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	PUBLIC_KEY_INVALID_FORMAT("E0002","Given public Key is in invalid format!","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	PRIVATE_KEY_FILE_NOT_FOUND("E0003","Private  key file not found!","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	PRIVATE_KEY_INVALID_FORMAT("E0004","Given private Key is in invalid format!","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	INVALID_INPUT_STRING("E0005","Invalid input string","01",ApplicationConstants.INPUT_ERROR_MESSAGE),
	FORM16_DIRECTORY_NOTFOUND("E0006","Form16 directory folder does not exists","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	FORM16_FILES_NOTFOUND("E0007","Form16 is not available for given combination of quarter, financial year and PAN","03",ApplicationConstants.FILE_NOT_FOUND_ERROR_MESSAGE),
	DECRYPTION_FAILED_INPUT("E0008","Decryption and mapping to pojo failed","01",ApplicationConstants.INPUT_ERROR_MESSAGE),
	ZIPFILE_CREATION_FAILED("E0009","Failed to create zip file","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	RESPONSE_ENCRYPTION_FAILED("E0010","Exception occurred while encrypting response object","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	KEY_DECRYPTION_FAILED("E0013","Decryption of AES key failed","01",ApplicationConstants.INPUT_ERROR_MESSAGE),
	PROPERTIES_ISNULL("E0014","Properties are null","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	PRIVATE_KEY_PATH_EMPTY("E0015","Private key path not found in properties","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	PUBLIC_KEY_PATH_EMPTY("E0016","Public key path not found in properties","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	ENTITY_EMPTY("E0017","Entity not found in properties","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	TANNO_CONFIG_FILE_NOT_FOUND("E0018","TANNO config file not found","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	FAILED_TO_READ_TANNO_FILE("E0019","Failed to read tanno file ","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	TANNO_FILE_IS_BLANK("E0020","No tanno configure in tanno file list","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	PDF_EXCEPTION("E0021","Sending base64 pdf response failed","02",ApplicationConstants.SERVER_ERROR_MESSAGE),
	INVALID_INPUT_VALUES("E0022","Invalid input values","01",ApplicationConstants.INPUT_ERROR_MESSAGE);
	
	
	private final String code;
	private final  String errorMessage;
	private final String responseErrorCode;
	private final String responseErrorMessage;
}
