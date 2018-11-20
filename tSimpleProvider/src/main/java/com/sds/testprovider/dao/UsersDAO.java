package com.sds.testprovider.dao;

import com.sds.testprovider.model.UsersVO;

public interface UsersDAO {
	void createUser(UsersVO usersVO) throws Exception;
	UsersVO selectUser(UsersVO usersVO) throws Exception;
	UsersVO selectUserByUserNo(long userno) throws Exception;
	int nextUserNo() throws Exception;
	
}
