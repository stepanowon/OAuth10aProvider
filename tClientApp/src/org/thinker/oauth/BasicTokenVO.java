package org.thinker.oauth;


public class BasicTokenVO {
	
	private String method ="GET";


	private String consumerKey;
	private String consumerSecretKey;
	
	private String timestamp;
	private String nonce;
	private String signatureMethod;
	private String signature;
	private String version;
	
	public BasicTokenVO(){
		
		this.timestamp =OAuthUtil.getTimeStamp();
		this.nonce =  OAuthUtil.getNonce();
		this.signatureMethod= OAuthUtil.getSignatureMethod();
		this.version = OAuthUtil.getVersion();
	}
	
	

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}




	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecretKey() {
		return consumerSecretKey;
	}

	public void setConsumerSecretKey(String consumerSecretKey) {
		this.consumerSecretKey = consumerSecretKey;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
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



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}

	
	
	

	@Override
	public String toString() {
		return "method=" + method + ", \nconsumerKey=" + consumerKey
				+ ", \nconsumerSecretKey=" + consumerSecretKey
				+ ", \ntimestamp=" + timestamp + ", \nnonce=" + nonce
				+ ", \nsignatureMethod=" + signatureMethod + ", \nsignature="
				+ signature + ", \nversion=" + version;
	}


	//테스트용 메인 메서드
//	public static void main(String[] args) {
//		BasicTokenVO vo = new BasicTokenVO();
//		System.out.println(vo);
//	}
	
	
}
