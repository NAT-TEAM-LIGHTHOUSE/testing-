/**
 * 
 */
package com.lhs.docManagmentAPI.model.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lavanya.badham
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData implements java.io.Serializable {

private static final long serialVersionUID = 1L;
	
	private JSONObject parameters;
	private JSONArray parametersList;
	

}
