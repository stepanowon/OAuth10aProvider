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
		// �̰��� �ڵ带 �ۼ��մϴ�. �Ʒ� �ڵ�� �����Ͻð� �ۼ��ϼ���.
		// return null;

		// 1. QueryString �Ǵ� Post �Ķ���� �Ľ�
		RequestTokenParam param = new RequestTokenParam(request);

		// 2. tbl_oauth_key ���̺��� ConsumerSecret ���� �о��.
		ConsumerVO consumerVO = consumerService.selectByConsumerKey(param.getConsumerKey());
		String consumerSecret = consumerVO.getConsumerSecret();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("request_token");
		try {
			// 3. signature validation!! ��ȿ���� ������ ���� �߻�!
			param.validateRequestToken(consumerSecret);

			// 4. ��ȿ�ϴٸ� RequestToken �����Ͽ� tbl_request_token ���̺� ����!!
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
			// Exception �߻��ϸ� ��ȿ���� ������ View Page�� ���� �˸�.
			mav.addObject("isValid", false);
			mav.addObject("message", e.getMessage());
		}

		return mav;

	}
}
