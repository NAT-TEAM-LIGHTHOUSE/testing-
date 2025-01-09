/**
 * 
 */
package com.lhs.docManagmentAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.docManagmentAPI.model.entity.dto.DashboardAllDataDTO;
import com.lhs.docManagmentAPI.model.entity.dto.DashboardFilterDTO;
import com.lhs.docManagmentAPI.service.DashboardService;

/**
 * @author lavanya.badham
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService service;
	
	@RequestMapping(value = {"/getAllDashboardData/{userCode}"}, method = {RequestMethod.GET, RequestMethod.POST})
	  public List<DashboardAllDataDTO> getAllDashboard(@PathVariable String userCode, @RequestParam(value = "fileType", required = false) String fileType, @RequestBody DashboardFilterDTO dto) {
	   return this.service.getAllDocsDashboardList(fileType, dto, userCode);
	  }
}
