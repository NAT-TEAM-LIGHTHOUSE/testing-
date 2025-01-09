package com.lhsws.gak.controller;

import com.lhsws.gak.model.RequestData;
import com.lhsws.gak.model.ResponseData;
import com.lhsws.gak.service.DataService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author akash.meshram
 *
 */
@RestController
@RequestMapping("/gak")
public class GetAccessKeyController {

    private final DataService dataService;

    @Autowired
    public GetAccessKeyController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping(value = "/getListOfServerDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseData getListOfServerDetails(@RequestBody RequestData requestData) {
        String appKey = "";
        String deviceId = "";
        String deviceName = "";
        String platform = "";
        
        if (requestData != null && requestData.getParameters() != null) {
            JSONObject parameters = requestData.getParameters();
            
            // Check for null values before invoking toString()
            if (parameters.get("appKey") != null) {
                appKey = parameters.get("appKey").toString();
            }
            if (parameters.get("device_id") != null) {
                deviceId = parameters.get("device_id").toString();
            }
            if (parameters.get("device_name") != null) {
                deviceName = parameters.get("device_name").toString();
            }
            if (parameters.get("platform") != null) {
                platform = parameters.get("platform").toString();
            }
        }
        
        // Call service layer method to get server details
        return dataService.getAuthServerDetails(appKey, deviceId, deviceName, platform);
    }

}
