package org.thinker.oauth;

import java.util.Iterator;
import java.util.TreeMap;


public class ResourceTokenVO extends AccessTokenVO {
	
	
	
	private String param;
	
	private String paramKey;
	private String paramValue;
	
	private String paramValueConverted;
	
	private String result;
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getParam() {
		return param;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamValueConverted() {
		return paramValueConverted;
	}
	public void setParamValueConverted(String paramValueConverted) {
		this.paramValueConverted = paramValueConverted;
	}
	
	public void setParam(String param) {
		this.param = param;
		
		String[] tokens = param.split("=");
		
		this.paramKey= tokens[0];
		this.paramValue = tokens[1];
		
		this.paramValueConverted = changeStr(paramValue);
		
		
		
	}

	private String changeStr(String str){
		
		String[] arr = str.split(" ");
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < arr.length ; i++){
			
			builder.append(OAuthUtil.encode(arr[i]));
			
			
			if(i != (arr.length -1)){
				builder.append("%20");
			}
			
			
		}
		
		return builder.toString();
	}




	@Override
	public void sign() {
		//추가적인 파라미터가 있는 경우 순차적으로 정렬하여 
		//base String을 생성하고 서명을 생성해야 하므로..TreeMap 이용
		TreeMap<String, String> tm = new TreeMap<String,String>();
		tm.put(OAuthMsgConstants.OAUTH_CONSUMER_KEY, this.getConsumerKey());
		tm.put(OAuthMsgConstants.OAUTH_NONCE, this.getNonce());
		tm.put(OAuthMsgConstants.OAUTH_SIGNATURE_METHOD, this.getSignatureMethod());
		tm.put(OAuthMsgConstants.OAUTH_TIMESTAMP, this.getTimestamp());
		tm.put(OAuthMsgConstants.OAUTH_TOKEN, this.getRequestOauthToken());
		tm.put(OAuthMsgConstants.OAUTH_VERSION, this.getVersion());
		//URL에 있을 수 있는 파라미터를 찾아 TreeMap에 추가
		String url = this.getRequestURL();
		int index = url.indexOf("?");
		if (index > -1)
		{
			String query = url.substring(index+1);
			String[] pair = query.split("&");
			for (int i=0; i < pair.length; i++) {
				String[] data = pair[i].split("=");
				tm.put(data[0], data[1]);
			}
			url = url.substring(0, index);
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(this.getMethod() + "&");
		builder.append(OAuthUtil.encode(url) + "&");

		Iterator<String> it = tm.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (it.hasNext())
				builder.append(OAuthUtil.encode(key + "=" + tm.get(key)+ "&"));
			else
				builder.append(OAuthUtil.encode(key + "=" + tm.get(key)));
		}
		
		this.setBaseString(builder.toString());
		
		HmacSHA1 sha1 = new HmacSHA1();
		sha1.setConsumerSecret(this.getConsumerSecretKey());

		sha1.setTokenSecret(this.getRequestOauthTokenSecret());
		
		try {
			String signed = sha1.getSignature(builder.toString());
			//System.out.println(signed);
			this.setSignature(signed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//테스트용 main 메서드
//	public static void main(String[] args) {
//		
//		ResourceTokenVO vo = new ResourceTokenVO();
//		vo.setMethod("GET");
//		vo.setRequestURL("http://twitter.com/statuses/friends_timeline.atom");
//		
//		vo.setConsumerKey("GRiVVLOyXaEkf8gOolw0A");
//		vo.setConsumerSecretKey("HOsnL9rKe9mF7XrrhGIwE8IxPi7kV1r8cNI2aqiGLE");
//		vo.setNonce("1306458402443449000");
//		vo.setTimestamp("1306458402");
//		
//		vo.setRequestOauthToken("165407408-Vn2DOR7KxIYriKs1lTTx0pBbiE2ElvHKKYveQRPe");
//		vo.setRequestOauthTokenSecret("kemBOsNsJkpqvJg4u78HAyOG5PbitZLvXeUBa06Lc");
//		vo.sign();
//		
//		System.out.println(vo.getBaseString());
//		System.out.println(vo.getSignature());
//	}

}
