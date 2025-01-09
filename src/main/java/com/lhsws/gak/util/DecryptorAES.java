/**
 * 
 */
package com.lhsws.gak.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhsws.gak.model.ResponseData;


@Component
public class DecryptorAES {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static SecretKeySpec secretKey;
	
	private static  String cipher_str="AES/ECB/PKCS5Padding";
	 
   private static final byte[] keyValue  = 
           new byte[] { 'Y', '5', '0', 't', 'Q', 'N', 'N',
   'S', 'M', 'c', 'J','U', 'p', 'p', 'l', 'd' };
   
   private static Key generateKey() throws Exception {
       Key key = new SecretKeySpec(keyValue, "AES");
       return key;
   }//End Method
   

   public static String angularDecrypt(String encryptedData) throws Exception {
   	
       Key key = generateKey();
       Cipher c = Cipher.getInstance(cipher_str);
       c.init(Cipher.DECRYPT_MODE, key);
       byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
       byte[] decValue = c.doFinal(decordedValue);
       String decryptedValue = new String(decValue);
       return decryptedValue;
       
   }//End Method
   
   public  String angularEncrypt(String Data) throws Exception {
   	//utl.getLog("getting str ciper-"+cipher_str);
       Key key = generateKey();
       Cipher c = Cipher.getInstance(cipher_str);
       c.init(Cipher.ENCRYPT_MODE, key);
       byte[] encVal = c.doFinal(Data.getBytes());
       String encryptedValue = Base64.getEncoder().encodeToString(encVal);
      // String encryptedValue = new BASE64Encoder().encode(encVal);
       return encryptedValue;
   }//End Method
 
   public static String angularDecodeAndDecrypt(String data) {
 	   String decData="";
 	   try {
 		   byte[] actualByte = Base64.getDecoder().decode(data);
 		   String actualString = new String(actualByte);
 		   decData =  angularDecrypt(actualString);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	   
 	   return decData;
    }
   
//----------------------------------------------------------------------------------------------------------------------------
	
	private static final String AESKEY = "b5p9xOFJy7zkBRoGyoIUKg==";
	//private Database_Parameters parameters ;
	

	public static String getSecretAESKeyAsString() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
//        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(secKey.getEncoded());
//        System.out.println("encodedKey..." + encodedKey);
        return encodedKey;
    }

    public static String encrypt(String plainText) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        byte[] decodedKey = Base64.getDecoder().decode(AESKEY);

        byte[] iv = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,1, 0};
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, originalKey, ivParams);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        map.put(Base64.getEncoder().encodeToString(byteCipherText), Base64.getEncoder().encodeToString(iv));
        return Base64.getEncoder().encodeToString(byteCipherText);
    }

    public static String decrypt(String encryptedText) throws Exception {

        byte[] decodedKey = Base64.getDecoder().decode(AESKEY);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); //32 bit key size
//        byte[] iv = Base64.getDecoder().decode(ivStr.trim()); // 16 bit size
        byte[] iv = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,1, 0};
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, originalKey, ivParams);
        byte[] bytePlainText = aesCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(bytePlainText);
    }
	
   
    public static String encryptAndEncode(String data) {
 	   String BasicBase64format="";
 		   try {
 			   String encData =  encrypt(data);
 			   BasicBase64format = Base64.getEncoder().encodeToString(encData.getBytes());
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	   
 	   return BasicBase64format;
    }
    
    public static String decodeAndDecrypt(String data) {
 	   String decData="";
 	   try {
 		   byte[] actualByte = Base64.getDecoder().decode(data);
 		   String actualString = new String(actualByte);
 		   decData =  decrypt(actualString);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	   
 	   return decData;
    }
	
    
    
    // Serialize and deserialize methods for ResponseData object
    public static String serialize(ResponseData responseData) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(responseData);
            out.flush();
            byte[] serializedData = bos.toByteArray();
            out.close();
            bos.close();
            return new String(serializedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResponseData deserialize(String data) {
        try {
            byte[] serializedData = data.getBytes(StandardCharsets.UTF_8);
            ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
            ObjectInput in = new ObjectInputStream(bis);
            ResponseData responseData = (ResponseData) in.readObject();
            in.close();
            bis.close();
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Encrypt and decrypt ResponseData object
    public static String encryptAndEncodeResponseData(ResponseData responseData) {
        String serializedData = serialize(responseData);
        if (serializedData != null) {
            return encryptAndEncode(serializedData);
        } else {
            return null;
        }
    }

    public static ResponseData decodeAndDecryptResponseData(String encryptedData) {
        String decryptedData = decodeAndDecrypt(encryptedData);
        if (decryptedData != null) {
            return deserialize(decryptedData);
        } else {
            return null;
        }
    }
}
