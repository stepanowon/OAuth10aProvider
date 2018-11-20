package com.sds.testprovider.dao;

import com.sds.testprovider.model.RequestTokenVO;

public interface RequestTokenDAO {
	void createRequestToken(RequestTokenVO requestTokenVO) throws Exception;
	void deleteRequestToken(String requestToken) throws Exception;
	RequestTokenVO selectRequestToken(String requestToken) throws Exception;
	void updateUserNo(RequestTokenVO requestTokenVO) throws Exception;
}
