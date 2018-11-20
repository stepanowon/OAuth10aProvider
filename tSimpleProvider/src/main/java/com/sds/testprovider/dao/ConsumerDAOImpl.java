package com.sds.testprovider.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.testprovider.model.ConsumerVO;

@Repository("ConsumerDAO")
public class ConsumerDAOImpl  implements ConsumerDAO {

	private static Logger logger = Logger.getLogger(ConsumerDAOImpl.class); 
	
    @Autowired
    private SqlSessionTemplate session;
	
	@Override
	public void createOAuthToken(ConsumerVO oAuthKeyVO) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### Create Consumer --- " + oAuthKeyVO);
		session.insert("Consumer.create", oAuthKeyVO);
	}

	@Override
	public void deleteOAuthToken(String consumerKey) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### DELETE Consumer : " + consumerKey);
		session.delete("Consumer.delete", consumerKey);
	}

	@Override
	public ConsumerVO selectByConsumerKey(String consumerKey) throws Exception  {
		logger.info("### Select By ConsumerKey --- " + consumerKey);
		return session.selectOne("Consumer.selectByConsumerKey", consumerKey);
	}

	@Override
	public List<ConsumerVO> selectByUserId(String userId) throws Exception  {
		logger.info("### Select By UserID --- " + userId);
		return session.selectList("Consumer.selectByUserID", userId);
	}

}
