/**
 * 
 */
package com.lhs.docManagmentAPI.service;

import java.util.List;

import com.lhs.docManagmentAPI.model.entity.dto.DashboardAllDataDTO;
import com.lhs.docManagmentAPI.model.entity.dto.DashboardFilterDTO;

/**
 * @author lavanya.badham
 *
 */
public interface DashboardService {

	
	 List<DashboardAllDataDTO> getAllDocsDashboardList(String fileType,DashboardFilterDTO dto,String userCode) ;
}
