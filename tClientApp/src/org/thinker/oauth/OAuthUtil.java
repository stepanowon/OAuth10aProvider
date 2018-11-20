package org.thinker.oauth;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class OAuthUtil {
	
	public static String getTimeStamp(){
	     
	     return System.currentTimeMillis() / 1000 +"";
	}
	
	public static String getNonce(){
		//DigestUtils.md5Hex(token_data)
		return System.nanoTime() + "";
	}
	
	public static String getSignatureMethod(){
		
		return OAuthMsgConstants.HMAC_SHA1;
	}

	public static String getVersion(){
		return OAuthMsgConstants.VERSION_1_0;
	}
	
	public static String encode(String str){
		
		if(str == null){
			return "";
		}
		
		try{
			return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decode(String str){
		
		if(str == null){
			return "";
		}
		
		try{
			
			return URLDecoder.decode(str, "UTF-8");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> setAccessData(String twitterMsg) {
		
		Map<String, String> dataMap = new HashMap<String, String>();
		String[] arr = twitterMsg.split("&");
		
		for(String token  : arr){
			
			String[] keyAndValue = token.split("=");
			
			dataMap.put(keyAndValue[0], keyAndValue[1]);
		}
		
		return dataMap;
	}

}
