package com.sds.testprovider.service;

import java.util.List;

import com.sds.testprovider.model.ConsumerVO;

public interface ConsumerService {
	void createConsumer(ConsumerVO oAuthKeyVO) throws Exception;
	void deleteConsumer(String consumerKey) throws Exception;
	ConsumerVO selectByConsumerKey(String consumerKey) throws Exception;
	List<ConsumerVO> selectByUserId(String userId) throws Exception;
}
