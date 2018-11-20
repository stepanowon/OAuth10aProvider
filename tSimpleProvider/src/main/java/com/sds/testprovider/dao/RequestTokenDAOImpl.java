package com.sds.testprovider.dao;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.testprovider.model.RequestTokenVO;

@Repository("RequestTokenDAO")
public class RequestTokenDAOImpl  implements RequestTokenDAO {

	private static Logger logger = Logger.getLogger(RequestTokenDAOImpl.class); 
	
    @Autowired
    private SqlSessionTemplate session;
    
	@Override
	public void createRequestToken(RequestTokenVO requestTokenVO)  throws Exception{
		// TODO Auto-generated method stub
		session.insert("requestToken.create", requestTokenVO);
	}

	@Override
	public void deleteRequestToken(String requestToken) throws Exception {
		// TODO Auto-generated method stub
		logger.info("### Delete RequestToken ---- " + requestToken);
		session.delete("requestToken.delete", requestToken);
		session.delete("requestToken.deleteExpired");
	}

	@Override
	public RequestTokenVO selectRequestToken(String requestToken) throws Exception {
			return session.selectOne("requestToken.select", requestToken);
	}

	@Override
	public void updateUserNo(RequestTokenVO requestTokenVO) throws Exception {
			session.update("requestToken.updateUserNo", requestTokenVO);
	}

}
