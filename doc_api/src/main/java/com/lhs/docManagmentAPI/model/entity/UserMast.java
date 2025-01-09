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
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lavanya.badham
 *
 */

@Entity
@Table(name = "user_mast")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMast implements Serializable{


	@Column(name = "acc_code", length =9, nullable = true)
	private String acc_code;
	
	@Column(name = "acc_year_str", length =100, nullable = true)
	private String acc_year_str;
	
	@Column(name = "closedate", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date closedate;
	
	@Column(name = "closedby", length =12, nullable = true)
	private String closedby;
	
	@Column(name = "createdby", length =12, nullable = false)
	private String createdby;
	
	@Column(name = "createddate", length =7, nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date createddate;
	
	@Column(name = "dept_code", length =9, nullable = true)
	private String dept_code;
	
	@Column(name = "desig_code_str", length =500, nullable = true)
	private String desig_code_str;
	
	@Column(name = "division", length =500, nullable = true)
	private String division;
	
	@Column(name = "doc_ref_key", length =100, nullable = true)
	private String doc_ref_key;
	
	@Column(name = "doc_type_str", length =4000, nullable = true)
	private String doc_type_str;
	
	@Column(name = "email", length =50, nullable = true)
	private String email;
	
	@Column(name = "email_psw", length =50, nullable = true)
	private String email_psw;
	
	@Column(name = "emp_code", length =9, nullable = true)
	private String emp_code;
	
	@Column(name = "entity", length =500, nullable = true)
	private String entity;
	
	@Column(name = "entry_rowid_seq", length =22, nullable = false)
	private Long entry_rowid_seq;
	
	@Column(name = "flag", length =1, nullable = true)
	private String flag;
	
	@Column(name = "folder_path", length =1000, nullable = true)
	private String folder_path;
	
	@Column(name = "inspdept_str", length =4000, nullable = true)
	private String inspdept_str;
	
	@Column(name = "lastupdate", length =7, nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastupdate;
	
	@Column(name = "lastupdateby", length =12, nullable = true)
	private String lastupdateby;
	
	@Column(name = "lastupdatedby", length =12, nullable = true)
	private String lastupdatedby;
	
	@Column(name = "lhs_signature_key", length =4000, nullable = true)
	private String lhs_signature_key;
	
	@Column(name = "machine_host", length =30, nullable = true)
	private String machine_host;
			
	@Column(name = "machine_ip", length =30, nullable = true)
	private String machine_ip;
	
	@Column(name = "machine_str", length =100, nullable = true)
	private String machine_str;
	
	@Column(name = "module_code", length =100, nullable = true)
	private String module_code;
	
	@Column(name = "multi_session_flag", length =2, nullable = true)
	private String multi_session_flag;
	
	@Column(name = "open_office_csv_path", length =1000, nullable = true)
	private String open_office_csv_path;
	
	@Column(name = "open_office_source_path", length =1000, nullable = true)
	private String open_office_source_path;
	
	@Column(name = "passwd_reset_days", length =22, nullable = true)
	private Long passwd_reset_days;
	
	@Column(name = "passwd_type", length =2, nullable = true)
	private String passwd_type;
	
	@Column(name = "password", length =2000, nullable = false)
	private String password;
	
	@Column(name = "project_str", length =4000, nullable = true)
	private String project_str;
	
	@Column(name = "replaced_by_user_code", length =12, nullable = true)
	private String replaced_by_user_code;
	
	@Column(name = "replaced_with_user_code", length =12, nullable = true)
	private String replaced_with_user_code;
	
	@Column(name = "reporting_to_user_code", length =12, nullable = true)
	private String reporting_to_user_code;
	
	@Column(name = "role_str", length =2000, nullable = true)
	private String role_str;
	
	@Column(name = "signature_key", length =4000, nullable = true)
	private String signature_key;
	
	@Column(name = "start_date", length =7, nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date start_date;
	
	@Column(name = "status", length =8, nullable = true)
	private String status;
	
	@Column(name = "task_code_str", length =4000, nullable = true)
	private String task_code_str;
	
	@Id
	@Column(name = "user_code", length =12, nullable = false)
	private String user_code;
	
	@Column(name = "user_level", length =22, nullable = true)
	private Long user_level;
	
	@Column(name = "user_license_flag", length =1, nullable = true)
	private String user_license_flag;
	
	@Column(name = "user_name", length =50, nullable = false)
	private String user_name;
	
	@Column(name = "user_setting", length =100, nullable = true)
	private String user_setting;
	
	@Column(name = "user_status", length =1, nullable = true)
	private String user_status;

}
