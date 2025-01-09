package com.lhsws.gak.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
/**
 * @author akash.meshram
 *
 */
public class Base64Commons {
	
	private Base64Commons() {
		
	}
	protected static Decoder decoder = Base64.getDecoder();
	protected static Encoder encoder = Base64.getEncoder();
	protected static byte[] decode(String text){
		return decoder.decode(text.getBytes());
	}
	protected static byte[] decode(byte[] b){
		return decoder.decode(b);
	}
	protected static String encode(byte[] b){
		return encoder.encodeToString(b);
	}	
	
}
