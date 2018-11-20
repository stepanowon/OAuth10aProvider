package org.thinker.oauth.generator;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

import com.sds.testprovider.model.AccessTokenVO;
import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.model.UsersVO;

public class TokenGenerator {
	
	public static void generateConsumerKey(ConsumerVO consumerVO) throws Exception {
		consumerVO.setConsumerKey(UUID.randomUUID().toString());
		String consumerSecret = DigestUtils.md5Hex(UUID.randomUUID().toString());
		consumerVO.setConsumerSecret(consumerSecret);
	}

	public static void generateRequestToken(RequestTokenVO requestTokenVO) throws Exception {
		if (requestTokenVO.getConsumerKey() == null) {
			throw new Exception("because ConsumerKey is null, request token can't be created!!");
		}
		
		String requestToken = UUID.randomUUID().toString();
		requestTokenVO.setRequestToken(requestToken);
		
        String requestTokenSecret = DigestUtils.md5Hex(UUID.randomUUID().toString());
        requestTokenVO.setRequestTokenSecret(requestTokenSecret);
        
        requestTokenVO.setVerifier( DigestUtils.md5Hex(System.currentTimeMillis()+""));
	}
	
	
	public static AccessTokenVO generateAccessToken(UsersVO usersVO, ConsumerVO consumerVO) {
		String tokenBase = usersVO.getUserno() + ":" + consumerVO.getConsumerKey();
		String accessToken = usersVO.getUserno() + "-" + DigestUtils.md5Hex(tokenBase);
		String accessTokenSecret = getAccessTokenSecret(
				usersVO.getPassword(), consumerVO.getConsumerSecret());
		return new AccessTokenVO(accessToken, accessTokenSecret, usersVO.getUserid(), usersVO.getUserno()); 
	}
	
	public static String getAccessTokenSecret(String password, String consumerSecret) {
		String secretBase = password + ":" + consumerSecret;
		String accessTokenSecret = DigestUtils.md5Hex(secretBase);
		return accessTokenSecret;
	}
	
	
}
