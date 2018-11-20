package com.sds.testprovider.service;

import java.util.List;

import com.sds.testprovider.model.OAuthKeyVO;

public interface OAuthKeyService {
	void createConsumer(OAuthKeyVO oAuthKeyVO) throws Exception;
	void deleteConsumer(String consumerKey) throws Exception;
	OAuthKeyVO selectByConsumerKey(String consumerKey) throws Exception;
	List<OAuthKeyVO> selectByUserId(String userId) throws Exception;
}
