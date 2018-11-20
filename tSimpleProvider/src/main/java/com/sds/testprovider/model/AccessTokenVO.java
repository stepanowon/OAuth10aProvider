package com.sds.testprovider.model;

public class AccessTokenVO {
	private String accessToken;
	private String accessTokenSecret;
	private String userID;
	private long userNo;
	
	
	public AccessTokenVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AccessTokenVO(String accessToken, String accessTokenSecret, String userID, long userNo) {
		super();
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
		this.userID = userID;
		this.userNo = userNo;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo= userNo;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}
	
	
	
}
