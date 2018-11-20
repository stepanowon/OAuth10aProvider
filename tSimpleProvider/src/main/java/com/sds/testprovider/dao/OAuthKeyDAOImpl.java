package com.sds.testprovider.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.testprovider.model.OAuthKeyVO;

@Repository("OAuthKeyDAO")
public class OAuthKeyDAOImpl implements OAuthKeyDAO {

	private static Logger logger = Logger.getLogger(OAuthKeyDAOImpl.class); 
	
    @Autowired
    private SqlSessionTemplate session;
	
	@Override
	public void createOAuthToken(OAuthKeyVO oAuthKeyVO) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### Create OAuth Key --- " + oAuthKeyVO);
		session.insert("oAuthKey.create", oAuthKeyVO);
	}

	@Override
	public void deleteOAuthToken(String consumerKey) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### DELETE OAuthKey : " + consumerKey);
		session.delete("oAuthKey.delete", consumerKey);
	}

	@Override
	public OAuthKeyVO selectByConsumerKey(String consumerKey) throws Exception  {
		logger.info("### Select User --- " + consumerKey);
		return session.selectOne("oAuthKey.selectByConsumerKey", consumerKey);
	}

	@Override
	public List<OAuthKeyVO> selectByUserId(String userId) throws Exception  {
		logger.info("### Select User --- " + userId);
		return session.selectList("oAuthKey.selectByUserID", userId);
	}

}
