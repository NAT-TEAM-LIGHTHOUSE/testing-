
package com.lhsws.gak.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JSONObject parameters;
	private JSONObject wslp;
	private JSONObject wscp;
	private JSONArray wsdp;
	private JSONArray wsdptp;
	private JSONArray wsdpcl;
	private JSONObject wsud;
	private String serviceType;
	private JSONObject wslogs;
	private String data;
	private JSONObject clientDetails;

}// End Class