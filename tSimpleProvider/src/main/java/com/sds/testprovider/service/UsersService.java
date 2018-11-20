package com.sds.testprovider.service;

import com.sds.testprovider.model.UsersVO;

public interface UsersService {
	void createUsers(UsersVO usersVO) throws Exception;
	UsersVO selectUsers(UsersVO usersVO) throws Exception;
	UsersVO selectUserByUserNo(long userno) throws Exception;
}
