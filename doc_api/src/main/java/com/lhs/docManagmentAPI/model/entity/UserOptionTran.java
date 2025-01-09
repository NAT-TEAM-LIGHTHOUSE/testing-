package com.lhs.docManagmentAPI.model.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author AMAN.DAHAT
 *
 */

@Data	
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_option_tran")
public class UserOptionTran implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_OPTION_TRAN_SEQ")
	@SequenceGenerator(name="USER_OPTION_TRAN_SEQ", sequenceName="USER_OPTION_TRAN_SEQ", allocationSize=1)
	@Column(name = "session_id", length =22, nullable = false)
	private Long session_id;
	@Column(name = "user_code", length =8, nullable = false)
	private String user_code;
	@Column(name = "menu_seq", length =22, nullable = false)
	private String menu_seq;
	@Column(name = "menu_option_code", length =100, nullable = false)
	private String menu_option_code;
	@Column(name = "entity_code", length =2, nullable = true)
	private String entity_code;
	@Column(name = "div_code", length =2, nullable = true)
	private String div_code;
	@Column(name = "plant_code", length =5, nullable = true)
	private String plant_code;
	@Column(name = "acc_year", length =5, nullable = true)
	private String acc_year;
	@Column(name = "login_time",  nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date login_time;
	@Column(name = "logout_time", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date logout_time;
	@Column(name = "machine_name", length =40, nullable = true)
	private String machine_name;
	@Column(name = "machine_ip", length =24, nullable = true)
	private String machine_ip;
	@Column(name = "machine_user", length =40, nullable = true)
	private String machine_user;
	@Column(name = "session_user", length =40, nullable = true)
	private String session_user;
	@Column(name = "parent_session_id", length =22, nullable = true)
	private String parent_session_id;
	@Column(name = "erp_login_time",  nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date erp_login_time;

	
}
