package org.thinker.oauth.parameter;

import javax.servlet.http.HttpServletRequest;

import org.thinker.oauth.util.HmacSHA1;
import org.thinker.oauth.util.OAuthException;
import org.thinker.oauth.util.OAuthMsgConstants;
import org.thinker.oauth.util.OAuthUtil;

//AccessToken 요청시 Consumer가 전송하는 정보
public class AccessTokenParam {
	
	private String baseString;	
	private String method;
	private String requestURL;
	
	private String consumerKey;
	private String requestToken;
	private String signatureMethod;
	private String signature;
	private String timeStamp;
	private String nonce;
	private String verifier;
	private String version;
	
	public AccessTokenParam(HttpServletRequest request) {
		this.method = request.getMethod();
		this.requestURL = request.getScheme() + "://" + 
			request.getServerName();
		if (request.getServerPort() != 80 && request.getServerPort() != 443) {
			this.requestURL += ":" + request.getServerPort();
		}
		this.requestURL += request.getRequestURI();
		
		this.requestToken = request.getParameter(OAuthMsgConstants.OAUTH_TOKEN);
		this.consumerKey = request.getParameter(OAuthMsgConstants.OAUTH_CONSUMER_KEY);
		this.signatureMethod = request.getParameter(OAuthMsgConstants.OAUTH_SIGNATURE_METHOD);
		this.signature = request.getParameter(OAuthMsgConstants.OAUTH_SIGNATURE);
		this.timeStamp = request.getParameter(OAuthMsgConstants.OAUTH_TIMESTAMP);
		this.nonce = request.getParameter(OAuthMsgConstants.OAUTH_NONCE);
		this.verifier = OAuthUtil.encode(request.getParameter(OAuthMsgConstants.OAUTH_VERIFIER));
		this.version = request.getParameter(OAuthMsgConstants.OAUTH_VERSION);
	}
	
	public void validateRequestToken(String requestTokenSecret, String requestTokenVerifier, String ConsumerSecret) throws Exception {
		
		makeBaseString();
		
		HmacSHA1 sha1 = new HmacSHA1();
		sha1.setConsumerSecret(ConsumerSecret);
		sha1.setTokenSecret(requestTokenSecret);
		System.out.println("###1 : validation Start! " + sha1.getTokenSecret() + "," + sha1.getConsumerSecret());
		//1. 서명이 일치하는지..확인		
		boolean valid = false;
		String newSignature = sha1.getSignature(this.baseString);
		if (newSignature.equals(this.signature)) 
			valid = true;
		else
			valid = false;
		
		System.out.println("### New Signature : " + newSignature);
		System.out.println("### Client Signature : " + this.signature);
		System.out.println("### baseString : " + this.baseString);
		System.out.println("###" + valid);
		
		if (!valid) 
			throw new OAuthException("signature is invalid!");
		
		//2. timestmap 가 클라이언트에서 보내온 timestamp와 차이가 많이 나면 
		String timeStampNow = OAuthUtil.getTimeStamp();
		long lngTimeStampNow = Long.parseLong(timeStampNow);
		long lngTimeStampClient = Long.parseLong(this.timeStamp);
		long timeSpan = lngTimeStampNow - lngTimeStampClient;
		if (timeSpan < 0 || timeSpan / 60 > 60 ) 
			throw new OAuthException("timestamp is invalid");
		
		System.out.println("### timsSpan : " + timeSpan );
		
		//3. verifier가 유효한지 확인..
		if (!this.verifier.equals(requestTokenVerifier)) {
			throw new OAuthException("Invalid verifier!");
		}
		
		
	}
	
	public void makeBaseString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append(this.method +"&");
		builder.append(OAuthUtil.encode(this.requestURL)+"&");
		builder.append(OAuthMsgConstants.OAUTH_CONSUMER_KEY + OAuthUtil.encode("=" + this.consumerKey + "&"));
		builder.append(OAuthMsgConstants.OAUTH_NONCE + OAuthUtil.encode("=" + this.nonce + "&"));
		builder.append(OAuthMsgConstants.OAUTH_SIGNATURE_METHOD + OAuthUtil.encode("=" + this.signatureMethod + "&"));
		builder.append(OAuthMsgConstants.OAUTH_TIMESTAMP + OAuthUtil.encode("=" + this.timeStamp + "&"));
		builder.append(OAuthMsgConstants.OAUTH_TOKEN + OAuthUtil.encode("=" + this.requestToken + "&"));
		builder.append(OAuthMsgConstants.OAUTH_VERIFIER + OAuthUtil.encode("=" + this.verifier));
		if (this.version != null) {
			builder.append(OAuthUtil.encode("&" + OAuthMsgConstants.OAUTH_VERSION + "=" + this.version));
		}
		
		this.baseString = builder.toString();
		System.out.println("### AccessToken BaseString : " + this.baseString);
	}	

	public String getRequestToken() {
		return requestToken;
	}
	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public String getBaseString() {
		return baseString;
	}
	public void setBaseString(String baseString) {
		this.baseString = baseString;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getConsumerKey() {
		return consumerKey;
	}
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	public String getSignatureMethod() {
		return signatureMethod;
	}
	public void setSignatureMethod(String signatureMethod) {
		this.signatureMethod = signatureMethod;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
