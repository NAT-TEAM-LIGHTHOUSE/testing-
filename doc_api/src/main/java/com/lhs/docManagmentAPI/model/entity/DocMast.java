/**
 * 
 */
package com.lhs.docManagmentAPI.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

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
@Entity
@Table(name="doc_mast")
public class DocMast {

	@Id
	@Column(name = "doc_code", length =12, nullable = false)
	private String doc_code;
	
	@Column(name = "closedate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date closedate;
	
	@Column(name = "closedby", length =12, nullable = true)
	private String closedby;
	
	@Column(name = "createdby", length =12, nullable = true)
	private String createdby;
	
	@Column(name = "createddate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date createddate;
	
		@Column(name = "doc_medium", length =10, nullable = true) 
	private String doc_medium;
	
	@Column(name = "doc_name", length =100, nullable = false)
	private String doc_name;
	
	@Column(name = "doc_type", length =9, nullable = false)
	private String doc_type;
	
	@Column(name = "entry_rowid_seq", length =22, nullable = true)
	private Long entry_rowid_seq;
	
	@Column(name = "expiry_flag", length =1, nullable = true)
	private String expiry_flag;
	
	@Column(name = "file_flag", length =1, nullable = true)
	private String file_flag;
	
	@Column(name = "flag", length =1, nullable = true)
	private String flag;
	
	@Column(name = "head_body_flag", length =1, nullable = true)
	private String head_body_flag;
	
	@Column(name = "lastupdate", length =7, nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastupdate;
	
	@Column(name = "relation_flag", length =1, nullable = true)
	private String relation_flag;
	
	@Column(name = "req_at_mode", length =12, nullable = true)
	private String req_at_mode;
	
	@Column(name = "review_flag", length =1, nullable = true)
	private String review_flag;
	
	@Column(name = "sharing_flag_str", length =20, nullable = true)
	private String sharing_flag_str;
	
	@Column(name = "sharing_text", length =100, nullable = true)
	private String sharing_text;
	
	@Column(name = "status", length =8, nullable = true)
	private String status;
	
	@Column(name = "user_code", length =12, nullable = true)
	private String user_code;
	
	@Column(name = "user_seq", length =22, nullable = true)
	private Long user_seq;
			
	@Column(name = "ws_seqid", length =22, nullable = true)
	private Long ws_seqid;

}
