/**
 * 
 */
package com.lhs.docManagmentAPI.util;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author lavanya.badham
 *
 */
public class testClass {

	public static void main(String[] args) {
		
		String filepath = "E:"+File.separator+"lhswsJsonFiles"+File.separator+"8283404.json";
		System.out.println("filepath===>"+filepath);
		try {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(filepath));
        JSONObject jsonObject = (JSONObject)obj;
//        convertmethod(jsonObject);
        convertData(jsonObject);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
        
	
	
	public static void convertData(JSONObject jsonObject) throws Exception {
        String inputJson = jsonObject.toString();
//        		"{\"Level5_Keys\":[],\"Level3_Keys\":[],\"Level2_Keys\":[],\"Level4_Keys\":[],\"Values\":[[\"U\",\"0D034\",null],[\"U\",\"0E032\",null],[\"U\",\"1KA03\",null],[\"U\",\"1SH08\",null]],\"Level1_Keys\":[\"WMA_RECORD_STATUS\",\"KPI\",\"Level2\"]}";
//        System.out.println("jsonObject string .."+jsonObject.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputJson);

        JsonNode valuesNode = jsonNode.get("Values");
        JsonNode level1KeysNode = jsonNode.get("Level1_Keys");

        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        
        for (JsonNode row : valuesNode) {
            LinkedHashMap<String, Object> level1 = new LinkedHashMap<>();
            int keyIndex = 0;

            for (JsonNode valueNode : row) {
                String key = level1KeysNode.get(keyIndex).asText();
                System.out.println("key.."+key);
                Object value = valueNode.isNull() ? null : valueNode.asText();
                level1.put(key, value);
                keyIndex++;
            }

            result.add(level1);
        }

        // Convert the result to a JSON string
//        String outputJson = objectMapper.writeValueAsString(result);

        System.out.println("result"+result.toString());
    }
	public static String convertmethod(JSONObject jsonObject) {
		String result="";
		JSONObject FinalJsonObject = new JSONObject();
		
		JSONArray values = (JSONArray)jsonObject.get("Values");
		JSONArray level1 = (JSONArray)jsonObject.get("Level1_Keys");
        System.out.println("values==>"+level1.size());
        System.out.println("values==>"+level1.get(0));
        List<LinkedHashMap<String, Object>> resultval = new ArrayList<>();
        
        for(int i=0;i<values.size();i++ ) {
        	 LinkedHashMap<String, Object> level = new LinkedHashMap<>();
        	for(int a=0; a<level1.size();a++) {
        		FinalJsonObject.put(level1.get(a), values.get(i));
        	}
        }
        System.out.println("FinalJsonObject.."+FinalJsonObject.toJSONString());
        
        
//        for (String key : dataList.keySet()) {
//            jsonObject.put(key, dataList.get(key));
//        }
        
		
		
		return result;
	}
      
	
	 public static Map<String, Object> setPageInfo(Map<String, Object> objectArr) {
	        Map<String, Object> objectMast = new HashMap<>();
	        objectMast.put("Level1", new ArrayList<>());

	        List<Object> level1Arr = (List<Object>) objectArr.get("Values");

	        for (Object level1Item : level1Arr) {
	            Map<String, Object> level1Obj = new HashMap<>();
	            int level1KeyIndex = 0;

	            for (Object level1Value : (List<Object>) level1Item) {
	                String level1Key = (String) objectArr.get("Level1_Keys");
	                level1Obj.put(level1Key, level1Value);
	                level1KeyIndex++;
	            }

	            if (level1Obj.containsKey("Level2")) {
	                List<Object> level2Arr = (List<Object>) level1Obj.get("Level2");
	                List<Map<String, Object>> level2Result = new ArrayList<>();

	                for (Object level2Item : level2Arr) {
	                    Map<String, Object> level2Obj = new HashMap<>();
	                    int level2KeyIndex = 0;

	                    for (Object level2Value : (List<Object>) level2Item) {
	                        String level2Key = (String) objectArr.get("Level2_Keys");
	                        level2Obj.put(level2Key, level2Value);
	                        level2KeyIndex++;
	                    }

	                    if (level2Obj.containsKey("Level3")) {
	                        List<Object> level3Arr = (List<Object>) level2Obj.get("Level3");
	                        List<Map<String, Object>> level3Result = new ArrayList<>();

	                        for (Object level3Item : level3Arr) {
	                            Map<String, Object> level3Obj = new HashMap<>();
	                            int level3KeyIndex = 0;

	                            for (Object level3Value : (List<Object>) level3Item) {
	                                String level3Key = (String) objectArr.get("Level3_Keys");
	                                level3Obj.put(level3Key, level3Value);
	                                level3KeyIndex++;
	                            }

	                            level3Result.add(level3Obj);
	                        }

	                        level2Obj.put("Level3", level3Result);
	                    }

	                    level2Result.add(level2Obj);
	                }

	                level1Obj.put("Level2", level2Result);
	            }

	            ((List<Object>) objectMast.get("Level1")).add(level1Obj);
	        }

	        return objectMast;
	    }
}
