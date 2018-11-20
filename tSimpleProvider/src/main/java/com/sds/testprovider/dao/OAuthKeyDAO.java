package com.sds.testprovider.dao;

import java.util.List;

import com.sds.testprovider.model.OAuthKeyVO;

public interface OAuthKeyDAO {
	void createOAuthToken(OAuthKeyVO oAuthKeyVO) throws Exception;
	void deleteOAuthToken(String consumerKey) throws Exception;
	OAuthKeyVO selectByConsumerKey(String consumerKey) throws Exception;
	List<OAuthKeyVO> selectByUserId(String userId) throws Exception;
}
