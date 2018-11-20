package org.thinker.oauth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thinker.oauth.AccessTokenVO;
import org.thinker.oauth.TokenSender;

/**
 * Servlet implementation class TwitterCallBackServlet
 */
public class TwitterCallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterCallBackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String token = (String)session.getAttribute("RT");
		String tokenSecret = (String)session.getAttribute("RTS");
		 
		AccessTokenVO vo = new AccessTokenVO(request.getQueryString());
		vo.setRequestURL(Setup.ACCESS_TOKEN_URL);
		vo.setConsumerKey(Setup.CONSUMER_KEY);
		vo.setConsumerSecretKey(Setup.CONSUMER_SECRET);
		//vo.setVerifier(accessVO.getVerifier());
		vo.setRequestOauthToken(token);
		vo.setRequestOauthTokenSecret(tokenSecret);
		vo.setMethod("GET");
		vo.sign();
		TokenSender sender = new TokenSender(TokenSender.TYPE_PARAM);
		
		try {
		    sender.sendHttpClient(vo);
		    session.setAttribute("AT", vo.getRequestOauthToken());
		    session.setAttribute("ATS", vo.getRequestOauthTokenSecret());
		    response.sendRedirect("resource");	
		} catch (Exception e) {
		    e.printStackTrace();
		}
		

	}


}

