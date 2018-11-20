package org.thinker.oauth.util;



public class OAuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OAuthException(String msg){
		super(msg);
	}
	
	public OAuthException(Exception e){
		super(e);
	}

	

}
