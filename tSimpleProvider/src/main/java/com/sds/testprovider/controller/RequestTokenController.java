package com.sds.testprovider.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.generator.TokenGenerator;
import org.thinker.oauth.parameter.RequestTokenParam;

import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.service.ConsumerService;
import com.sds.testprovider.service.RequestTokenService;

@Controller
public class RequestTokenController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private RequestTokenService requestTokenService;

	@RequestMapping(value = "request_token")
	public ModelAndView requestToken(HttpServletRequest request) throws Exception {
		// 이곳에 코드를 작성합니다. 아래 코드는 삭제하시고 작성하세요.
		// return null;

		// 1. QueryString 또는 Post 파라미터 파싱
		RequestTokenParam param = new RequestTokenParam(request);

		// 2. tbl_oauth_key 테이블에서 ConsumerSecret 정보 읽어옴.
		ConsumerVO consumerVO = consumerService.selectByConsumerKey(param.getConsumerKey());
		String consumerSecret = consumerVO.getConsumerSecret();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("request_token");
		try {
			// 3. signature validation!! 유효하지 않으면 예외 발생!
			param.validateRequestToken(consumerSecret);

			// 4. 유효하다면 RequestToken 생성하여 tbl_request_token 테이블에 저장!!
			RequestTokenVO tokenVO = new RequestTokenVO();
			tokenVO.setConsumerKey(consumerVO.getConsumerKey());
			tokenVO.setCallback(param.getCallback());
			TokenGenerator.generateRequestToken(tokenVO);
			requestTokenService.createRequestToken(tokenVO);

			String oauth_callback_confirmed = "true";

			StringBuilder builder = new StringBuilder();
			builder.append("oauth_token=" + tokenVO.getRequestToken() + "&");
			builder.append("oauth_token_secret=" + tokenVO.getRequestTokenSecret() + "&");
			builder.append("oauth_callback_confirmed=" + oauth_callback_confirmed);

			mav.addObject("isValid", true);
			mav.addObject("message", builder.toString());
		} catch (Exception e) {
			// Exception 발생하면 유효하지 않음을 View Page를 통해 알림.
			mav.addObject("isValid", false);
			mav.addObject("message", e.getMessage());
		}

		return mav;

	}
}
