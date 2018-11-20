package com.sds.testprovider.model;


public class RequestTokenVO {
	private String requestToken;
	private String requestTokenSecret;
	private String consumerKey;
	private String verifier;
	private String regDate;
	private String appName;
	private String callback;
	private long userNo;

	public String toString() {
		return "requestToken=" + this.getRequestToken() + 
			", requestTokenSecret=" + this.getRequestTokenSecret() +
			", consumerKey=" + this.getConsumerKey() +
			", verifier=" + this.getVerifier() + 
			", appName=" + this.getAppName() + 
			", callback=" + this.getCallback() + 
			", regDate=" + this.getRegDate();
	}
	
	public RequestTokenVO(String requestToken, String requestTokenSecret,
			String consumerKey, String verifier, String appName, String callback) {
		super();
		this.requestToken = requestToken;
		this.requestTokenSecret = requestTokenSecret;
		this.consumerKey = consumerKey;
		this.verifier = verifier;
		this.appName = appName;
		this.callback = callback;
	}
	
	public RequestTokenVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	
	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getRequestToken() {
		return requestToken;
	}
	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	public String getRequestTokenSecret() {
		return requestTokenSecret;
	}
	public void setRequestTokenSecret(String requestTokenSecret) {
		this.requestTokenSecret = requestTokenSecret;
	}
	public String getConsumerKey() {
		return consumerKey;
	}
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
}
