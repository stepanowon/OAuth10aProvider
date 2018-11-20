package com.sds.testprovider.model;

import java.io.Serializable;

public class ConsumerVO implements Serializable {
	private static final long serialVersionUID = 2L;
	
	private String consumerKey;
	private String consumerSecret;
	private String userId;
	private String callbackUrl;
	private String appName;
	private String regDate;
	
	public String toString() {
		return "consumerKey=" + this.getConsumerKey() + 
			", consumerSecret=" + this.getConsumerSecret() +
			", userId=" + this.getUserId() +
			", callbackUrl=" + this.getCallbackUrl() + 
			", appName=" + this.getAppName() + 
			", regDate=" + this.getRegDate();
	}
	
	public ConsumerVO(String consumerKey, String consumerSecret, String userId,
			String callbackUrl,	String appName, String regDate) {
		super();
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.userId = userId;
		this.callbackUrl = callbackUrl;
		this.appName = appName;
		this.regDate = regDate;
	}
	public ConsumerVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getConsumerKey() {
		return consumerKey;
	}
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	public String getConsumerSecret() {
		return consumerSecret;
	}
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}