/**
 * 
 */
package com.lhsws.gak.constants;

/**
 * @author akash.meshram
 *
 */
public class ApplicationConstants {

	private ApplicationConstants() {

	}

	public static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
	public static final String CIPHER_DEC_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String KEY_ALGORITHM = "RSA";
	public static final String DEC_ALGORITHM = "GCM";
	public static final String ALGORITHM = "AES";
	public static final int AES_KEY_SIZE = 256;
	public static final int GCM_IV_LENGTH = 12;
	public static final int GCM_TAG_LENGTH = 16;
	public static final int BUFFER_SIZE = 1024;


	public static String DESTINATION_PK = "";
	public static String ENTITY = "";
	public static String TANNO_FP = "";
	public static String TANNO_SAL_FP = "";

	public static String URN = "";

	public static final String SERVER_ERROR_MESSAGE = "Server error"; //01
	
	public static final String INPUT_ERROR_MESSAGE = "Incorrect Input String";//02
	
	public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "File not present";//03



}
