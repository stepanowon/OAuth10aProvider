package com.sds.testprovider;

import java.net.URLDecoder;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String message = "GET&https%3A%2F%2Fapi.twitter.com%2Foauth%2Frequest_token&oauth_callback%3Dhttp%253A%252F%252Fjcornor.com%252FTestOAuth%252Fauthorized.htm%26oauth_consumer_key%3DqwXidQXejg9XKKxKbbIpw%26oauth_nonce%3DGXsPEL%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1308137819";
		String decoded = URLDecoder.decode(message, "utf-8");
		System.out.println(decoded);
	}

}
