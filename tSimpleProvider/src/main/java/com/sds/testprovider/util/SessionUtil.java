package com.sds.testprovider.util;

import javax.servlet.http.HttpSession;

import com.sds.testprovider.model.UsersVO;

public class SessionUtil {
	public static void loginUser(HttpSession session, UsersVO usersVO)
			throws Exception {
		session.setAttribute("usersVO", usersVO);
	}

	public static void logoutUser(HttpSession session) throws Exception {
		session.removeAttribute("usersVO");
	}

	public static boolean isLoginned(HttpSession session)
			throws Exception {
		UsersVO vo = (UsersVO)session.getAttribute("usersVO");
		if (vo != null)
			return true;
		else
			return false;
	}
	
	public static UsersVO getUserInfo(HttpSession session) throws Exception {
		UsersVO vo = (UsersVO)session.getAttribute("usersVO");
		return vo;
	}
	
}
