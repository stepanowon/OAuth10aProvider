package org.thinker.oauth.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;



public class HmacSHA1  {
	
    private static final String BASE64_ENCODING = "ISO-8859-1";
    private static final Base64 BASE64 = new Base64();
    
    private static final String ENCODING = "UTF-8";
    private static final String MAC_NAME = "HmacSHA1";
    
    private String consumerSecret;

    private String tokenSecret;

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }


    public static byte[] decodeBase64(String s) {
    	byte[] b;
    	try {
    	    b = s.getBytes(BASE64_ENCODING);
    	} catch (UnsupportedEncodingException e) {
    	    System.err.println(e + "");
    	    b = s.getBytes();
    	}
    	return BASE64.decode(b);
    }

    public static String base64Encode(byte[] b) {
		byte[] b2 = BASE64.encode(b);
		try {
		    return new String(b2, BASE64_ENCODING);
		} catch (UnsupportedEncodingException e) {
		    System.err.println(e + "");
		}
		return new String(b2);
    }
    
	
    
    public String getSignature(String baseString) throws Exception {
    	
    	//System.out.println("HMAC_SHA1 getSignature baseString " + baseString);
    	
        try {
            String signature = base64Encode(computeSignature(baseString));
            
            //System.out.println("HMAC_SHA1 getSignature signature " + signature);
            return signature;
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
    



    public boolean isValid(String signature, String baseString)throws Exception {
        try {
            byte[] expected = computeSignature(baseString);
            byte[] actual = decodeBase64(signature);
            return equals(expected, actual);
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
    

    
    public static boolean equals(byte[] a, byte[] b) {
        if (a == null)
            return b == null;
        else if (b == null)
            return false;
        else if (b.length <= 0)
            return a.length <= 0;
        byte diff = (byte) ((a.length == b.length) ? 0 : 1);
        int j = 0;
        for (int i = 0; i < a.length; ++i) {
            diff |= a[i] ^ b[j];
            j = (j + 1) % b.length;
        }
        return diff == 0;
    }

    private byte[] computeSignature(String baseString)
    throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKey key = null;
		synchronized (this) {
		    if (this.key == null) {
		    	
//		    	System.out.println("compute Signature consumer secret " + getConsumerSecret());
//		    	System.out.println("compute Signature token secret " + getTokenSecret());
		    	
		        String keyString = percentEncode(getConsumerSecret())
		                + '&' + percentEncode(getTokenSecret());
		        
//		        System.out.println("compute Signature keyString " + keyString);
		        
		        byte[] keyBytes = keyString.getBytes(ENCODING);
		        this.key = new SecretKeySpec(keyBytes, MAC_NAME);
		    }
		    key = this.key;
		    
		    
		}
		Mac mac = Mac.getInstance(MAC_NAME);
		mac.init(key);
		byte[] text = baseString.getBytes(ENCODING);
		
		System.out.println("compute Signature text " + new String(text));
		
		return mac.doFinal(text);
	}



    private SecretKey key = null;

    public static String percentEncode(String s) {
        if (s == null) {
            return "";
        }
        try {
            return URLEncoder.encode(s, ENCODING)
                    // OAuth encodes some characters differently:
                    .replace("+", "%20").replace("*", "%2A")
                    .replace("%7E", "~");
            // This could be done faster with more hand-crafted code.
        } catch (UnsupportedEncodingException wow) {
            throw new RuntimeException(wow.getMessage(), wow);
        }
    }


}
