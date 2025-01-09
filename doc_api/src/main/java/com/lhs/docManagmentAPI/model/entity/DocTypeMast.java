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

@Entity
@Table(name = "doc_type_mast")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocTypeMast {

	
	@Column(name = "closedate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date closedate;
	
	@Column(name = "closedby", length =12, nullable = true)
	private String closedby;
	
	@Column(name = "cloud_path", length =500, nullable = true)
	private String cloud_path;
	
	@Column(name = "code_level", length =22, nullable = true)
	private Long code_level;
	
	@Column(name = "createdby", length =12, nullable = false)
	private String createdby;
	
	@Column(name = "createddate", length =7, nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date createddate;
	
	@Column(name = "doc_image_flag", length =1, nullable = true)
	private String doc_image_flag;
	
	@Id
	@Column(name = "doc_type", length =9, nullable = false)
	private String doc_type;
	
	@Column(name = "doc_type_name", length =50, nullable = false)
	private String doc_type_name;
	
	@Column(name = "entry_rowid_seq", length =22, nullable = false)
	private Long entry_rowid_seq;
	
	@Column(name = "erp_ref_type", length =1, nullable = true)
	private String erp_ref_type;
	
	@Column(name = "flag", length =1, nullable = true)
	private String flag;
	
	@Column(name = "folder_path", length =500, nullable = true)
	private String folder_path;
	
	@Column(name = "lastupdate", length =7, nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastupdate;
	
	@Column(name = "org_group", length =15, nullable = true)
	private String org_group;
	
	@Column(name = "org_level", length =22, nullable = true)
	private Long org_level;
	
	@Column(name = "parent_code", length =9, nullable = true)
	private String parent_code;
	
	@Column(name = "period_type_group", length =15, nullable = true)
	private String period_type_group;
	
	@Column(name = "period_type_level", length =22, nullable = true)
	private Long period_type_level;
	
	@Column(name = "ref_table_name", length =30, nullable = true)
	private String ref_table_name;
	
	@Column(name = "ref_tnature", length =6, nullable = true)
	private String ref_tnature;
	
	@Column(name = "ref_trantype", length =100, nullable = true)
	private String ref_trantype;
	
	@Column(name = "relation_flag", length =1, nullable = true)
	private String relation_flag;
	
	@Column(name = "status", length =8, nullable = true)
	private String status;
	
	@Column(name = "user_code", length =12, nullable = false)
	private String user_code;

}
