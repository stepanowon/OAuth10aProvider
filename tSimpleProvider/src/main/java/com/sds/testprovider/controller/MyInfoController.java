package com.sds.testprovider.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.thinker.oauth.parameter.OAuthTokenParam;

import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.ConsumerService;
import com.sds.testprovider.service.UsersService;

@Controller
public class MyInfoController {
	
	@Autowired
	private UsersService usersService;
	
	@Resource(name="jsonTemplate")
	private View jsonView;

	@Resource(name="xmlTemplate")
	private View xmlView;

	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping(value="/myinfo")
	public ModelAndView getMyInfo(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			UsersVO usersVO = validateOAuthToken(request);
			mav.setView(jsonView);
			mav.addObject("data", usersVO);
		} catch (Exception e) {
			throw new Exception (e.getMessage());
		}
		return mav;
	}
	
	//Session으로 이미 인증이 되었거나  OAuthToken이 유효하다면 접근 가능!
	private UsersVO validateOAuthToken(HttpServletRequest request) throws Exception {
		//아래 return 문을 주석 처리하고 코드를 작성합니다.
        OAuthTokenParam param = new OAuthTokenParam(request);
		
        long userNo = param.getUserNo();
        String consumerKey = param.getConsumerKey();
        UsersVO usersVO = usersService.selectUserByUserNo(userNo);
        ConsumerVO consumerVO = consumerService.selectByConsumerKey(consumerKey);		
        param.validateRequestToken(consumerVO.getConsumerSecret(), usersVO.getPassword());
		
        return usersVO;

	}
}
