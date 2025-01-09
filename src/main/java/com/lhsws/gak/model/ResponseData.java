package com.lhsws.gak.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author akash.meshram
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String responseStatus;
	private String responseMsg;
	private Object responseData;

}// End Class