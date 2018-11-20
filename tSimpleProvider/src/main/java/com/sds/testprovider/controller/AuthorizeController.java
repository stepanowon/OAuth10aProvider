package com.sds.testprovider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.RequestTokenService;
import com.sds.testprovider.service.UsersService;
import com.sds.testprovider.util.SessionUtil;

@Controller
@RequestMapping(value = "/authorize")
public class AuthorizeController {

	@Autowired
	private RequestTokenService requestTokenService;

	@Autowired
	private UsersService usersService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView authorizeGet(HttpServletRequest request) throws Exception {
		// �Ʒ� return ���� �ּ� ó���ϰ� �ڵ带 �ۼ��մϴ�.
		ModelAndView mav = new ModelAndView();
		String oauth_token = (String) request.getParameter("oauth_token");
		if (oauth_token != null) {
			// 1. oauth_token ������ tbl_request_token ��ȸ�Ͽ� ���ڵ尡 �������� ������ ������.
			RequestTokenVO requestTokenVO = requestTokenService.getRequestToken(oauth_token);
			if (requestTokenVO != null) {
				mav.setViewName("authorize");
				mav.addObject("requestTokenVO", requestTokenVO);
			}
		} else {
			mav.setViewName("authorize_error");
			mav.addObject("errorMessage", "invalid oauth_token!");
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView authorizePost(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// �Ʒ� return ���� �ּ� ó���ϰ� �ڵ带 �ۼ��մϴ�.
		// 1. QueryString �� �Ľ�
		String allow_deny = request.getParameter("allow_deny");
		String oauth_token = request.getParameter("oauth_token");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		// 2. �ӽ� ������ RequestToken �� �о����(from tbl_request_token)
		RequestTokenVO tokenVO = (RequestTokenVO) requestTokenService.getRequestToken(oauth_token);
		ModelAndView mav = new ModelAndView();
		if (tokenVO == null) {
			mav.setViewName("authorize_error");
			mav.addObject("errorMessage", "��ȿ���� ���� ��ū�Դϴ�.");
			return mav;
		}
		
		mav.addObject("requestTokenVO", tokenVO);
		mav.setViewName("authorize");
		
		if (allow_deny.equals("allow")) {
			UsersVO usersVO = null;
			if (!SessionUtil.isLoginned(session)) {
				UsersVO inputVO = new UsersVO(userid, password, "", 0);
				usersVO = usersService.selectUsers(inputVO);
				if (usersVO != null) {
					SessionUtil.loginUser(session, usersVO);
				} else {
					// ���� ��ư�� �������� ����� ���� ������ �ùٸ��� �ʴٸ� �ٽ� authorize �������� �̵�
					mav.addObject("loginResult", "false");
					mav.setViewName("authorize");
					return mav;
				}
			}
			// RequestTokenTable�� UserNo �ʵ尪�� ���ø����̼��� ������ ����� ������� UserNo�� ����
			tokenVO.setUserNo(SessionUtil.getUserInfo(session).getUserno());
			requestTokenService.updateUserNo(tokenVO);

			// �α��ε� ���¿��� App�� �����ϸ� Callback URL�� �̵�
			response.sendRedirect(tokenVO.getCallback() + "?oauth_token=" + tokenVO.getRequestToken()
					+ "&oauth_verifier=" + tokenVO.getVerifier());
		} else {
			// ���ΰźθ� �Ͽ��ٸ� �ӽ� ������ RequestToken���� �����ϰ� ���ΰź� ȭ�� ���
			requestTokenService.deleteRequestToken(oauth_token);
			mav.setViewName("authorize_error");
			mav.addObject("errorMessage", "����ڰ� ���ø����̼��� ������ ������� �ʽ��ϴ�.");
			SessionUtil.logoutUser(session);
		}
		return mav;

	}

}
