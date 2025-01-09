/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import com.lhs.docManagmentAPI.model.entity.DocTran;

/**
 * @author lavanya.badham
 *
 */
public interface DocTranImageService {
	
	String insertFile(byte[] filebase64 , String ws_seqid, String row_slno,long doc_id,String data_type);
	
	DocTran getDocId(String ws_seqid, String row_slno);

}
