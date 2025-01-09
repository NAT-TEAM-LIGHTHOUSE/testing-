/**
 * 
 */
package com.lhs.docManagmentAPI.model.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "doc_tran_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocTranImage implements Serializable{

	
	@Column(name = "createdby", length =12, nullable = true)
	private String createdby;
	
	@Column(name = "createddate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date createddate;
	
	@Column(name = "doc_id", length =22, nullable = false)
	private Long doc_id;
	
	@Id
	@Column(name = "doc_image", length =0, nullable = true)
	private String doc_image;
	
	@Column(name = "doc_image_blob", length =4000, nullable = true)
	private String doc_image_blob;
	
	@Column(name = "entry_rowid_seq", length =22, nullable = true)
	private Long entry_rowid_seq;
	
	@Column(name = "flag", length =1, nullable = true)
	private String flag;
	
	@Column(name = "lastupdate", length =7, nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastupdate;
	
	@Column(name = "row_slno", length =22, nullable = true)
	private Long row_slno;
	
	@Column(name = "user_code", length =12, nullable = true)
	private String user_code;
	
	@Column(name = "ws_seqid", length =22, nullable = true)
	private Long ws_seqid;

}
