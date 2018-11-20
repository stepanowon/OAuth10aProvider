package com.sds.testprovider.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sds.testprovider.dao.OAuthKeyDAO;
import com.sds.testprovider.model.OAuthKeyVO;

@Service("OAuthKeyService")
public class OAuthKeyServiceImpl implements OAuthKeyService {
	
	@Resource(name="OAuthKeyDAO")
	private OAuthKeyDAO oAuthKeyDAO;
	
	@Override
	public void createConsumer(OAuthKeyVO oAuthKeyVO) throws Exception {
		oAuthKeyDAO.createOAuthToken(oAuthKeyVO);
	}

	@Override
	public void deleteConsumer(String consumerKey) throws Exception {
		oAuthKeyDAO.deleteOAuthToken(consumerKey);
	}

	@Override
	public OAuthKeyVO selectByConsumerKey(String consumerKey) throws Exception {
		return oAuthKeyDAO.selectByConsumerKey(consumerKey);
	}

	@Override
	public List<OAuthKeyVO> selectByUserId(String userId) throws Exception {
		return oAuthKeyDAO.selectByUserId(userId);
	}

}
