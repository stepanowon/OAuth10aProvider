package com.sds.testprovider.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sds.testprovider.dao.ConsumerDAO;
import com.sds.testprovider.model.ConsumerVO;

@Service("ConsumerService")
public class ConsumerServiceImpl implements ConsumerService {

	
	@Resource(name="ConsumerDAO")
	private ConsumerDAO consumerDAO;
	
	@Override
	public void createConsumer(ConsumerVO oAuthKeyVO) throws Exception {
		consumerDAO.createOAuthToken(oAuthKeyVO);
	}

	@Override
	public void deleteConsumer(String consumerKey) throws Exception {
		consumerDAO.deleteOAuthToken(consumerKey);
	}

	@Override
	public ConsumerVO selectByConsumerKey(String consumerKey) throws Exception {
		return consumerDAO.selectByConsumerKey(consumerKey);
	}

	@Override
	public List<ConsumerVO> selectByUserId(String userId) throws Exception {
		return consumerDAO.selectByUserId(userId);
	}

}
