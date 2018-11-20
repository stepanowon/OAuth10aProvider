package org.thinker.oauth.parameter;

import javax.servlet.http.HttpServletRequest;

import org.thinker.oauth.util.HmacSHA1;
import org.thinker.oauth.util.OAuthMsgConstants;
import org.thinker.oauth.util.OAuthUtil;

//Consumer가 RequestToken 요청시 Consumer가 전송하는 정보
public class RequestTokenParam {

	private String baseString;
	
	private String method;
	private String requestURL;
	private String consumerKey;
	private String signatureMethod;
	private String signature;
	private String timeStamp;
	private String nonce;
	private String callback;
	private String version;
	
	//oauth_version은 생략
	
	public RequestTokenParam(HttpServletRequest request) {
		this.method = request.getMethod();
		this.requestURL = request.getScheme() + "://" + 
			request.getServerName();
		if (request.getServerPort() != 80 && request.getServerPort() != 443) {
			this.requestURL += ":" + request.getServerPort();
		}
		this.requestURL += request.getRequestURI();
		this.consumerKey = request.getParameter(OAuthMsgConstants.OAUTH_CONSUMER_KEY);
		this.signatureMethod = request.getParameter(OAuthMsgConstants.OAUTH_SIGNATURE_METHOD);
		this.signature = request.getParameter(OAuthMsgConstants.OAUTH_SIGNATURE);
		this.timeStamp = request.getParameter(OAuthMsgConstants.OAUTH_TIMESTAMP);
		this.nonce = request.getParameter(OAuthMsgConstants.OAUTH_NONCE);
		this.callback = OAuthUtil.encode(request.getParameter(OAuthMsgConstants.OAUTH_CALLBACK));
		this.version = request.getParameter(OAuthMsgConstants.OAUTH_VERSION);
	}
	
	public void validateRequestToken(String consumerSecret) throws Exception {
		
		//GET&http%3A%2F%2Flocalhost%3A8000%2FtSimpleProvider%2Fauth%2Frequest_token&oauth_callback%3Dhttp%253A%252F%252Fjcornor.com%252FTestOAuth%252Fauthorized.htm%26oauth_consumer_key%3DqwXidQXejg9XKKxKbbIpw%26oauth_nonce%3DISWfRd%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1308223552
		makeBaseString();
		
		HmacSHA1 sha1 = new HmacSHA1();
		sha1.setConsumerSecret(consumerSecret);
		sha1.setTokenSecret(null);
		
		boolean valid = false;
//		valid = sha1.isValid(OAuthUtil.decode(this.signature), this.baseString);
		String newSig = sha1.getSignature(this.baseString);
		if (newSig.equals(this.signature)) 
			valid = true;
		else
			valid = false;
		System.out.println("###New Signature : " + newSig);
		System.out.println("###Client Signature : " + this.signature);
		System.out.println("###baseString : " + this.baseString);
		System.out.println("###" + valid);
		
		if (!valid) 
			throw new Exception("signature is invalid!");
		
		//timestmap 가 클라이언트에서 보내온 timestamp와 차이가 많이 나면 
		String timeStampNow = OAuthUtil.getTimeStamp();
		long lngTimeStampNow = Long.parseLong(timeStampNow);
		long lngTimeStampClient = Long.parseLong(this.timeStamp);
		long timeSpan = lngTimeStampNow - lngTimeStampClient;
		if (timeSpan < 0 || timeSpan / 60 > 60 ) 
			throw new Exception("timestamp is invalid");
		
		System.out.println("### timsSpan : " + timeSpan );
		
	}

	
	public void makeBaseString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.method +"&");
		builder.append(OAuthUtil.encode(this.requestURL)+"&");
		builder.append(OAuthMsgConstants.OAUTH_CALLBACK + OAuthUtil.encode("=" + this.callback + "&"));
		builder.append(OAuthMsgConstants.OAUTH_CONSUMER_KEY + OAuthUtil.encode("=" + this.consumerKey + "&"));
		builder.append(OAuthMsgConstants.OAUTH_NONCE + OAuthUtil.encode("=" + this.nonce + "&"));
		builder.append(OAuthMsgConstants.OAUTH_SIGNATURE_METHOD + OAuthUtil.encode("=" + this.signatureMethod + "&"));
		builder.append(OAuthMsgConstants.OAUTH_TIMESTAMP + OAuthUtil.encode("=" + this.timeStamp));
		if (this.version != null) {
			builder.append(OAuthUtil.encode("&" + OAuthMsgConstants.OAUTH_VERSION + "=" + this.version));
		}
		
		this.baseString = builder.toString();
		System.out.println(this.baseString);
	}
	
	public String getBaseString() {
		return baseString;
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
	public String getCallback() {
		return OAuthUtil.decode(callback);
	}
	public void setCallback(String callback) {
		this.callback = OAuthUtil.encode(callback);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setBaseString(String baseString) {
		this.baseString = baseString;
	}
	
	
}
