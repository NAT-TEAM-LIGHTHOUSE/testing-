/**
 * 
 */
package com.lhsws.gak.util;

import java.io.File;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

/**
 * @author akash.meshram
 *
 */
@Slf4j
@Generated
public class Utils {

	private Utils() {

	}

	public static void logFileData(File f, String message, boolean pathPrint) {
		String msg = message + ": " + f.getName();
		if (pathPrint) {
			msg = message + ": " + f.getAbsolutePath();
		}
		//log.info("/***************************************CCS**********************************************/");
		log.debug(msg);
		//log.info("/***************************************CCE**********************************************/");

	}
	public boolean isNull(String comparion_value) {
		boolean null_value = true;
		try {
			if (comparion_value != null && !"".equals(comparion_value) && !"null".equalsIgnoreCase(comparion_value) && !"undefined".equalsIgnoreCase(comparion_value)) {
				null_value = false;
			}
		} catch (NullPointerException npe) {
			null_value = true;
			System.err.println("Exception in null comparion : null pointer exception");
		} catch (Exception ex) {
			null_value = true;
			System.err.println("Exception in null comparion : Exception");
		}
		return null_value;
	}// end isnull method
	
	
}
