package com.sds.testprovider.dao;

import java.util.List;

import com.sds.testprovider.model.ConsumerVO;

public interface ConsumerDAO {
	void createOAuthToken(ConsumerVO oAuthKeyVO) throws Exception;
	void deleteOAuthToken(String consumerKey) throws Exception;
	ConsumerVO selectByConsumerKey(String consumerKey) throws Exception;
	List<ConsumerVO> selectByUserId(String userId) throws Exception;
}
