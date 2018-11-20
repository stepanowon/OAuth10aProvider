package com.sds.testprovider.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.testprovider.dao.ConsumerDAOImpl;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.UsersService;
import com.sds.testprovider.util.SessionUtil;

@Controller
@RequestMapping(value="/login") 
public class LoginController {
	
	private static Logger logger = Logger.getLogger(ConsumerDAOImpl.class); 
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String redirectLoginPage() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView loginProcess(UsersVO usersVO, HttpSession session, HttpServletResponse response) throws Exception {
		logger.info("### Login Controller (POST) - " + usersVO);
		ModelAndView mav = new ModelAndView();
		
		UsersVO vo  = usersService.selectUsers(usersVO);
		if (vo != null) {
			//로그인 성공
			SessionUtil.loginUser(session, vo);
			response.sendRedirect("viewAppList");
		} else {
			mav.addObject("isLogin", false);
			mav.setViewName("login");
		}
		return mav;
	}
}
