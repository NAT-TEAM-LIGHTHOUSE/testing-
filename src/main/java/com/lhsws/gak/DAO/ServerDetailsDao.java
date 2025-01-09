package com.lhsws.gak.DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhsws.gak.model.ResponseData;
import com.lhsws.gak.util.DecryptorAES;
import com.lhsws.gak.util.Util;
import com.lhsws.gak.util.utilLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedHashMap;

/**
 * @author akash.meshram
 *
 */
@Repository
public class ServerDetailsDao {
	
	
	@Autowired
	private DecryptorAES encdec;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private utilLog utllog;

    private final DataSource dataSource;
    
    @Value("${dbEncrypt}")
    private String dbEncrypt;
    
    @Value("${payloadEncrypt}")
    private String payloadEncrypt;
    
    
    

    @Autowired
    public ServerDetailsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ResponseData getAuthServerDetails(String appKey, String device_id, String device_name, String platform) {
        //utllog.getLog("getAuthServerDetails","");
    	System.out.println("getAuthServerDetails");
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        ResponseData resData = new ResponseData();
        String appLogSql = "select * from LHSSYS_APP_COFIGRATION_LOG where upper(appkey)=upper(?)";
        String access_key = "";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(appLogSql)) {

            ps.setString(1, appKey.toUpperCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs != null && rs.next()) {
                	//utllog.getLog("Server details found in the database.","");
                	System.out.println("Server details found in the database.");
                    String device_validation = rs.getString("device_validation");
                    
                    System.out.println("dbEncrypt--"+dbEncrypt);
                    
                   if(dbEncrypt.equalsIgnoreCase("T")) { 
                   access_key = rs.getString("entity_code") + "#" + rs.getString("database_ip") + "#"
    						+ rs.getString("database_port") + "#" + rs.getString("database_name") + "#"
    						+ rs.getString("database_password") + "#" + rs.getString("db_sid");
                   
			                String encAccessKey = "";
			   				try {
			   					encAccessKey = encdec.encryptAndEncode(access_key);
			   				} catch (Exception e) {
			   					e.printStackTrace();
			   				}
			   				map.put("access_key", encAccessKey);
                   
                   }else {
                    map.put("entity", rs.getString("entity_code"));
                    map.put("dbName", rs.getString("database_name"));
                    map.put("dbPassword", rs.getString("database_password"));
                    map.put("dbUrl", rs.getString("database_ip"));
                    map.put("dbSid", rs.getString("db_sid"));
                    map.put("portNo", rs.getString("database_port"));
                    
                   }
                    
                    map.put("status", "true");
                    map.put("msg", "Server Details Found");
                    map.put("app_name", rs.getString("app_name"));
                    map.put("serverUrl", rs.getString("server_url1"));
                    map.put("server_url2", rs.getString("server_url2"));
                    map.put("server_url3", rs.getString("server_url3"));
                    map.put("server_url4", rs.getString("server_url4"));
                    map.put("war_name", rs.getString("war_name"));
                  
                   
                  
                    map.put("entity_code_str", rs.getString("entity_code_str"));
                    map.put("app_valid_upto_date", rs.getString("app_valid_upto_date"));
                    map.put("app_valid_upto_message", rs.getString("app_valid_upto_message"));
                    map.put("validupto_message_prompt_days", rs.getString("validupto_message_prompt_days"));
                    map.put("device_validation", device_validation);
                    map.put("loginFlag", rs.getString("otp_flag"));
                    map.put("appcode", rs.getString("app_code"));
                    map.put("default_theme", rs.getString("default_theme"));
                    map.put("appkey", appKey);
                    map.put("webapplink", rs.getString("webapp_link"));
                    map.put("minIdel", rs.getString("min_idel_session"));
                    map.put("maxIdel", rs.getString("max_idel_session"));
                    map.put("maxTotal", rs.getString("max_total_session"));
                    InputStream imgstream = null;
                    if (rs.getBlob("app_image") != null) {
                        imgstream = rs.getBlob("app_image").getBinaryStream();
                        map.put("icon_img", Util.getImgstreamToBytes(imgstream));
                    } else {
                        imgstream = getClass().getResourceAsStream("/defualtDp.png");
                        map.put("icon_img", "");
                    }

                    resData.setResponseData(map);
                    String status = checkDeviceAndUpdateAppkeyLog(appKey, device_id, device_name, platform,
                            rs.getInt("seq_no"));
                    resData.setResponseStatus("success");
                    resData.setResponseMsg(status);
                    
                    if (payloadEncrypt.equalsIgnoreCase("T")) {
                        // Encrypt response status
                        String encryptedResponseStatus = encdec.encryptAndEncode(resData.getResponseStatus());
                        resData.setResponseStatus(encryptedResponseStatus);
                        
                        // Encrypt response message
                        String encryptedResponseMsg = encdec.encryptAndEncode(status);
                        resData.setResponseMsg(encryptedResponseMsg);
                        
                        try {
                            // Convert the map to a JSON string
                            String mapJson = objectMapper.writeValueAsString(map);
                            
                            // Encrypt the JSON string
                            String encMap = encdec.encryptAndEncode(mapJson);
                            
                            // Set the encrypted map as the responseData object
                            resData.setResponseData(encMap);
                            
                            // Create a new ResponseData object to store encrypted data
                            ResponseData encryptedResponseData = new ResponseData();
                            encryptedResponseData.setResponseStatus(resData.getResponseStatus());
                            encryptedResponseData.setResponseMsg(resData.getResponseMsg());
                            encryptedResponseData.setResponseData(resData.getResponseData());
                            
                            // Serialize the encryptedResponseData object
                            //String serializedEncryptedData = encdec.encryptAndEncodeResponseData(encryptedResponseData);
                            
                            // Decrypt the encoded and encrypted data
                           // ResponseData responseDataDecrypt = encdec.decodeAndDecryptResponseData(serializedEncryptedData);
                            
                          //  System.out.println("ResponseDataDecrypt-----" + responseDataDecrypt.toString());
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }


                     else {
                        resData.setResponseStatus(rs.getString("responseStatus"));
                        resData.setResponseMsg(rs.getString("responseMsg"));
                        resData.setResponseData(map);
                    }
                 
                  
                  
                    
                    
                } else {
                    System.out.println("No server details found for the provided appKey.");
                    resData.setResponseStatus("error");
                    resData.setResponseMsg("Please Enter Valid AppKey");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while executing SQL query: " + e.getMessage());
            resData.setResponseStatus("error");
            resData.setResponseMsg("Something went wrong..., Server Details Not Found");
            Util.log(e);
        }
        
        
//        if(payloadEncrypt.equalsIgnoreCase("T")) { 
//      	  
//     	   String encypteddata =  encdec.encryptAndEncodeObj(resData);
//            System.out.println("encypteddata---"+encypteddata);
//           // ResponseData ResponseDataa =  encdec.decodeAndDecryptObj(encypteddata);
//           //System.out.println("ResponseDataa---"+ResponseDataa.toString());
//        
//         
//         
//         }
        
        // Encrypting individual fields and setting them in ResponseData object
        
        

        return resData;
    }

    public String checkDeviceAndUpdateAppkeyLog(String appKey, String deviceId, String deviceName, String platform,
                                                int seqNo) {
        String status = "";
        String functionResponse = "";
        String sql = "{ ? = call lhs_wma_configuration.flhswma_main(?,?,?,?,?,?)}";

        try (Connection con = dataSource.getConnection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2, appKey);
            callableStatement.setString(3, deviceId);
            callableStatement.setString(4, deviceName);
            callableStatement.setString(5, platform);
            callableStatement.setString(6, null);
            callableStatement.setString(7, null);
            callableStatement.execute();
            functionResponse = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return functionResponse;
    }
}
