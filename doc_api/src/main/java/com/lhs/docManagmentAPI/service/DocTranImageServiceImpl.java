/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.docManagmentAPI.dao.DocTranImageRepository;
import com.lhs.docManagmentAPI.dao.DocTranImageRepositorySupport;
import com.lhs.docManagmentAPI.dao.DocTranRepository;
import com.lhs.docManagmentAPI.model.entity.DocTran;

/**
 * @author lavanya.badham
 *
 */

@Service(value="doc_tran_image")
public class DocTranImageServiceImpl implements DocTranImageService{
	
	@Autowired
	DocTranImageRepositorySupport repo;
	
	@Autowired
	DocTranRepository doctranrepo;
	
	@Autowired
	DocTranImageRepository doctranimagerepo;
	
	@Override
	public String insertFile(byte[] imageByte, String ws_seqid, String row_slno,long doc_id,String data_type) {
		String result ="error";
		System.out.println("inside service..");
		 Long docId =0l;
		 int val =0;
		 try {
		 docId = doctranimagerepo.findDocId(doc_id);
		 if(docId == null || docId.equals("null")) {
				docId =0l;
			}
		 if(docId !=0l) {
			 val = repo.uploadImage(imageByte, ws_seqid, row_slno, doc_id, data_type);
		 }else {
			 val = repo.insertImage(imageByte, ws_seqid, row_slno, doc_id, data_type);
		 }
		 
		 if(val ==1) {
			 result ="success";
		 }
		 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		return result;
	}
	
	public DocTran getDocId(String ws_seqid, String row_slno) {
		Long docId =0l;
		System.out.println();
		Float rowslno = Float.parseFloat(row_slno);
		long wsSeqid = Long.parseLong(ws_seqid);
		System.out.println("rowslno.."+rowslno);
		System.out.println("wsSeqid.."+wsSeqid);
		docId = doctranrepo.findDocId(rowslno, wsSeqid);
		System.out.println("docId.."+docId);
		DocTran docTranList = doctranrepo.findById(docId).get();
		
		if(docId == null || docId.equals("null")) {
			docId =0l;
		}
		
		return docTranList;
	}

}
