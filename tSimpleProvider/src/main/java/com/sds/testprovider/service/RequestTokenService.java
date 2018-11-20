package com.sds.testprovider.service;

import com.sds.testprovider.model.RequestTokenVO;

public interface RequestTokenService {
	void createRequestToken(RequestTokenVO requestTokenVO) throws Exception;
	void deleteRequestToken(String requestToken) throws Exception;
	RequestTokenVO getRequestToken(String requestToken) throws Exception;
	void updateUserNo(RequestTokenVO requestTokenVO) throws Exception;
}
