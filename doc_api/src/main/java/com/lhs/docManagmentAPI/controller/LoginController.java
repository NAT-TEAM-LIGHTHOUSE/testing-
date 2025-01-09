package com.lhs.docManagmentAPI.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lhs.docManagmentAPI.config.TenantContext;
import com.lhs.docManagmentAPI.dao.DocTypeMastRepository;
import com.lhs.docManagmentAPI.model.dto.RequestData;
import com.lhs.docManagmentAPI.model.entity.DocTran;
import com.lhs.docManagmentAPI.model.entity.UserMast;
import com.lhs.docManagmentAPI.model.entity.dto.LovDTO;
import com.lhs.docManagmentAPI.service.DocTranImageService;
import com.lhs.docManagmentAPI.service.DocTypeMastService;
import com.lhs.docManagmentAPI.service.UserService;
import com.lhs.docManagmentAPI.util.utilLog;

import org.json.JSONObject;

/**
 * @author lavanya.badham
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

	@Autowired
	private UserService userservice;

	@Autowired
	private DocTranImageService docService;

	@Autowired
	private DocTypeMastService doctypeService; 

	@Autowired
	private utilLog util;
	
	@Value("${dataSourceFilePath}")
	private String dataSourcePath;

	@GetMapping("/login/{tenantId}")
	public String bypass(Model map, HttpSession session, @PathVariable String tenantId) {
		System.out.println("tenantId123..." + tenantId);
		TenantContext.setCurrentTenant(tenantId);
		List<UserMast> list = null;
		list = userservice.findAll();
		// System.out.println("list..."+list);
		return null;
	}

	@PostMapping("/fileinsert/{tenantId}")
	public String fileInsert(Model map, HttpSession session, @PathVariable String tenantId,
			@RequestBody RequestData requestData) throws JsonProcessingException, IOException {

		TenantContext.setCurrentTenant(tenantId);
		String result = "";
		JSONArray Jarray = requestData.getParametersList();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonArray jsonArray = JsonParser.parseString(Jarray.toString()).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
//        System.out.println("jsonArray.."+jsonArray.toString());
		int sucesscount = 0;
		for (int i = 0; i < jsonArray.size(); i++) {

			JsonObject element = jsonArray.get(i).getAsJsonObject();
			jsonObject.add("element", element);
			String jsonObjectString = jsonObject.toString();

			JsonObject jsonObject1 = JsonParser.parseString(jsonObjectString).getAsJsonObject();
			ObjectMapper objectMapper1 = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonObject1.toString());

			String imagebytes = jsonNode.get("element").get("imagebytes").asText();
			String ws_seqid = jsonNode.get("element").get("ws_seqid").asText();
			String row_slno = jsonNode.get("element").get("row_slno").asText();
			String data_type = jsonNode.get("element").get("row_slno").asText();
			String file_name = jsonNode.get("element").get("file_name").asText();
			byte[] imageByte = Base64.getDecoder().decode(imagebytes);

			DocTran doclist = docService.getDocId(ws_seqid, row_slno);

			if (doclist.getDoc_id() != 0l) {
				String imageFlag = doctypeService.getImageFlag(doclist.getDoc_type());
				System.out.println("imageFlag.."+imageFlag);
				System.out.println();
				if (imageFlag.equalsIgnoreCase("Y") || util.isnull(doclist.getTo_file_path())) {
					result = docService.insertFile(imageByte, ws_seqid, row_slno, doclist.getDoc_id(), data_type);
				}
			}

			if (!util.isnull(doclist.getTo_file_path())) {
				checkAndSaveImageToFolder(imageByte, doclist.getTo_file_path(), doclist.getTo_file_name(), file_name);
			}
			if (result.equalsIgnoreCase("success")) {
				sucesscount++;
			}
		}

		if (sucesscount == jsonArray.size()) {
			result = "success";
		} else if (sucesscount == 0) {
			result = "some error occured";
		} else {
			result = "some Images are not inserted";
		}
		return result;
	}

	public void checkAndSaveImageToFolder(byte[] imagebytes, String filepath, String to_file_filename,
			String from_filename) {

		String extension[] = from_filename.split("\\.");
		String path = filepath + File.separator + to_file_filename + "." + extension[1];
		File f = new File(filepath);
		if (!f.exists()) {
			f.mkdirs();
		}
		ByteArrayInputStream is = new ByteArrayInputStream(imagebytes);
		try {
			Path root = Paths.get(path);
			File file1 = new File(path);
			file1.getParentFile().mkdirs();
			Files.copy(is, root.resolve(path));
		} catch (Exception e) {
			// utl.getPST(e);
		}

	}
	
	@GetMapping(value = { "/login/tenantList" })
	public @ResponseBody  ArrayList<LovDTO> getTenantId(@RequestParam String db) {
		System.out.println("getTenant list===============");
		ArrayList<LovDTO> dto =new ArrayList<LovDTO>();
		try {
			File f = new File(dataSourcePath+File.separator+db);
			if(f.exists()) {
				File list[] = f.listFiles();
				if (list != null && list.length > 0) {
					for (File file : list) {
						if (file.getName().endsWith("properties") || file.getName().endsWith("PROPERTIES")) {
							String baseName = getBaseName(file.getName());
							LovDTO data =new LovDTO();
							data.setCode(baseName+db);
							data.setName(baseName);
							dto.add(data);
						}
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("dto : "+dto);
		return dto;
	}
	
	public String getBaseName(String fileName) {
		int index = fileName.lastIndexOf('.');
		if (index == -1) {
			return fileName;
		} else {
			return fileName.substring(0, index);
		}
	}

}
