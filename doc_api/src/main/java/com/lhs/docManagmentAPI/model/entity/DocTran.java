package com.lhs.docManagmentAPI.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doc_tran")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocTran {

	@Column(name = "acc_year", length =5, nullable = true)
	private String acc_year;
	
	@Column(name = "amendno", length =22, nullable = true)
	private Long amendno;
	
	@Column(name = "cost_code", length =9, nullable = true)
	private String cost_code;
	
	@Column(name = "createdby", length =12, nullable = true)
	private String createdby;
	
	@Column(name = "createddate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date createddate;
	
	@Column(name = "cvrdate", length =30, nullable = true)
	private String cvrdate;
	
	@Column(name = "div_code", length =5, nullable = true)
	private String div_code;
	
	@Column(name = "div_group", length =2, nullable = true)
	private String div_group;
	
	@Column(name = "doc_code", length =15, nullable = false)
	private String doc_code;
	
	@Column(name = "doc_date", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date doc_date;
	
	@Column(name = "doc_desc", length =100, nullable = true)
	private String doc_desc;
	
	@Id
	@Column(name = "doc_id", length =22, nullable = false)
	private Long doc_id;
	
	@Column(name = "doc_medium", length =1, nullable = true)
	private String doc_medium;
	
	@Column(name = "doc_refno", length =200, nullable = true)
	private String doc_refno;
	
	@Column(name = "doc_tags", length =500, nullable = true)
	private String doc_tags;
	
	@Column(name = "doc_type", length =9, nullable = false)
	private String doc_type;
	
	@Column(name = "entity_code", length =2, nullable = true)
	private String entity_code;
	
	@Column(name = "entry_rowid_seq", length =22, nullable = true)
	private Long entry_rowid_seq;
	
	@Column(name = "expiry_date", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date expiry_date;
	
	@Column(name = "file_code", length =9, nullable = true)
	private String file_code;
	
	@Column(name = "file_flag", length =1, nullable = true)
	private String file_flag;
	
	@Column(name = "flag", length =1, nullable = true)
	private String flag;
	
	@Column(name = "from_file_name", length =500, nullable = true)
	private String from_file_name;
	
	@Column(name = "from_file_path", length =500, nullable = true)
	private String from_file_path;
	
	@Column(name = "lastupdate", length =7, nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastupdate;
	
	@Column(name = "object_code", length =30, nullable = true)
	private String object_code;
	
	@Column(name = "period_code", length =12, nullable = true)
	private String period_code;
	
	@Column(name = "ref_column_name", length =100, nullable = true)
	private String ref_column_name;
	
	@Column(name = "ref_column_value", length =500, nullable = true)
	private String ref_column_value;
	
	@Column(name = "reviewby", length =12, nullable = true)
	private String reviewby;
	
	@Column(name = "reviewdate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date reviewdate;
	
	@Column(name = "row_slno", length =22, nullable = true)
	private Float row_slno;
	
	@Column(name = "series", length =3, nullable = true)
	private String series;
	
	@Column(name = "slno", length =22, nullable = true)
	private Long slno;
	
	@Column(name = "tcode", length =2, nullable = true)
	private String tcode;
	
	@Column(name = "tnature", length =6, nullable = true)
	private String tnature;
	
	@Column(name = "to_file_name", length =500, nullable = true)
	private String to_file_name;
	
	@Column(name = "to_file_path", length =500, nullable = true)
	private String to_file_path;
	
	@Column(name = "uploadedby", length =12, nullable = true)
	private String uploadedby;
	
	@Column(name = "uploadeddate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date uploadeddate;
	
	@Column(name = "user_code", length =12, nullable = true)
	private String user_code;
	
	@Column(name = "vrdate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date vrdate;
	
	@Column(name = "vrno", length =16, nullable = true)
	private String vrno;
	
	@Column(name = "ws_seqid", length =22, nullable = true)
	private Long ws_seqid;
	
	@Transient
	private String doc_type_name;
	
	
}
