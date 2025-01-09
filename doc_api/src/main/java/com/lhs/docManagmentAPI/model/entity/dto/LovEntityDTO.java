package com.lhs.docManagmentAPI.model.entity.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LovEntityDTO {

	
	@Id
	private String slno;
	private String code;
	private String name;
}
