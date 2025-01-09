/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.docManagmentAPI.dao.DocTypeMastRepository;

/**
 * @author lavanya.badham
 *
 */

@Service(value="doc_type_mast")
public class DocTypeMastServiceImpl implements DocTypeMastService {

	@Autowired
	DocTypeMastRepository docrepo;
	
	public String getImageFlag(String doc_type) {
		String imageFlag="";
		
		imageFlag = docrepo.findDocImageFlag(doc_type);
		
		return imageFlag;
	}
}
