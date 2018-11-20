package com.sds.testprovider.dao;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.testprovider.model.UsersVO;

@Repository("UsersDAO")
public class UsersDAOImpl  implements UsersDAO {

	private static Logger logger = Logger.getLogger(UsersDAOImpl.class); 
	
    @Autowired
    private SqlSessionTemplate session;

	@Override
	public void createUser(UsersVO usersVO) throws Exception {
		// TODO Auto-generated method stub
		logger.info("### Create User ---- " + usersVO);
		session.insert("users.createUser", usersVO);
	}

	@Override
	public UsersVO selectUser(UsersVO usersVO) throws Exception {
		logger.info("### Select User --- " + usersVO);
		return session.selectOne("users.selectUserByUserID", usersVO);
	}

	@Override
	public int nextUserNo() throws Exception {
		return  (Integer)session.selectOne("users.getNextNo");
	}

	@Override
	public UsersVO selectUserByUserNo(long userno) throws Exception {
		logger.info("### Select User By UserNo --- " + userno);
		return session.selectOne("users.selectUserByUserNo", userno);
	}
}
