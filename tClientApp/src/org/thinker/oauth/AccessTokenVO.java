package org.thinker.oauth;

import java.util.Map;

public class AccessTokenVO extends RequestTokenVO  {

    private String screenName;
    private long userId;
    
    public AccessTokenVO(){
    	this(null);
    }
    
	public AccessTokenVO(String twitterMsg) {
		
		super();
		
		if(twitterMsg  == null){
			return;
		}
		
		Map<String, String> dataMap = OAuthUtil.setAccessData(twitterMsg);
		
		this.setRequestOauthToken(dataMap.get("oauth_token"));
		this.setVerifier(dataMap.get("oauth_verifier"));
		
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return super.toString()+",\nscreenName=" + screenName + ", \nuserId=" + userId;
	}
	
	@Override
	public void sign(){
		
		StringBuilder builder = new StringBuilder();
		builder.append(this.getMethod() +"&");
		builder.append(OAuthUtil.encode(this.getRequestURL())+"&");
		builder.append(OAuthUtil.encode("oauth_consumer_key="+this.getConsumerKey()+"&"));
		builder.append(OAuthUtil.encode("oauth_nonce=" + this.getNonce()+"&"));
		builder.append(OAuthUtil.encode("oauth_signature_method="+this.getSignatureMethod()+"&"));
		builder.append(OAuthUtil.encode("oauth_timestamp="+this.getTimestamp()+"&"));
		builder.append(OAuthUtil.encode("oauth_token="+this.getRequestOauthToken()+"&"));
		builder.append(OAuthUtil.encode("oauth_verifier="+this.getVerifier()+"&"));
		builder.append(OAuthUtil.encode("oauth_version="+this.getVersion()));
		
		System.out.println("@@@ Client BaseString : " + builder.toString());
		HmacSHA1 sha1 = new HmacSHA1();
	
		System.out.println("client Consumer Secret key" + this.getConsumerSecretKey());
		System.out.println("client Consumer token Secret key" + this.getRequestOauthTokenSecret());
		
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
		
		setBaseString(builder.toString());
	}

	//�׽�Ʈ�� main �޼���
//	public static void main(String[] args) {
//		
//		AccessTokenVO vo = new AccessTokenVO();
//		vo.setMethod("POST");
//		vo.setRequestURL("https://api.twitter.com/oauth/access_token");
//		
//		vo.setConsumerKey("GDdmIQH6jhtmLUypg82g");
//		vo.setConsumerSecretKey("MCD8BKwGdgPHvAuvgvz4EQpqDAtx89grbuNMRd7Eh98");
//		vo.setNonce("9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8");
//		vo.setTimestamp("1272323047");
//		
//		vo.setRequestOauthToken("8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc");
//		vo.setRequestOauthTokenSecret("x6qpRnlEmW9JbQn4PQVVeVG8ZLPEx6A0TOebgwcuA");
//		vo.setVerifier("pDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY");
//		vo.sign();
//		
//		System.out.println(vo.getBaseString());
//		
//		System.out.println(vo.getSignature());
//	}
    
    
}
