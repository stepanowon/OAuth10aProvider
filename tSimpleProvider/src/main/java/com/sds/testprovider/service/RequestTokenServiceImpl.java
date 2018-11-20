package com.sds.testprovider.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sds.testprovider.dao.RequestTokenDAO;
import com.sds.testprovider.model.RequestTokenVO;

@Service("RequestTokenService")
public class RequestTokenServiceImpl implements RequestTokenService {

	@Resource(name="RequestTokenDAO")
	private RequestTokenDAO requestTokenDAO;
	
	@Override
	public void createRequestToken(RequestTokenVO requestTokenVO)
			throws Exception {
		requestTokenDAO.createRequestToken(requestTokenVO);
	}

	@Override
	public void deleteRequestToken(String requestToken) throws Exception {
		requestTokenDAO.deleteRequestToken(requestToken);

	}

	@Override
	public RequestTokenVO getRequestToken(String requestToken) throws Exception {
		return requestTokenDAO.selectRequestToken(requestToken);
	}

	@Override
	public void updateUserNo(RequestTokenVO requestTokenVO) throws Exception {
		requestTokenDAO.updateUserNo(requestTokenVO);
	}

}
