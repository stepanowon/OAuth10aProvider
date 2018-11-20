package com.sds.testprovider.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.generator.TokenGenerator;

import com.sds.testprovider.dao.ConsumerDAOImpl;
import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.ConsumerService;

@Controller
public class AppListController {
	
	private static Logger logger = Logger.getLogger(ConsumerDAOImpl.class); 
	
	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping(value="/viewAppList", method=RequestMethod.GET)
	public ModelAndView viewAppList(HttpSession session, HttpServletResponse response) throws Exception {
		
		UsersVO usersVO = (UsersVO)session.getAttribute("usersVO");
		if (usersVO == null) 
			response.sendRedirect("login");
		
		List<ConsumerVO> list =  consumerService.selectByUserId(usersVO.getUserid());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewAppList");
		mav.addObject("list", list);
		
		return mav;
	}
	
	@RequestMapping(value="/createApp", method=RequestMethod.GET)
	public String goCreateAppPage() throws Exception {
		
		return "createApp";
	}
	
	@RequestMapping(value="/createApp", method=RequestMethod.POST)
	public void createApp(ConsumerVO oAuthKeyVO, HttpSession session, HttpServletResponse response) throws Exception {
		
		logger.info("### createApp : " + oAuthKeyVO);
		
		UsersVO usersVO  = (UsersVO)session.getAttribute("usersVO");
		oAuthKeyVO.setUserId(usersVO.getUserid());
		//이제 키를 생성해서 부여해야 함.(ConsumerKey&Secret, AccessToken & Secret)
		TokenGenerator.generateConsumerKey(oAuthKeyVO);
		logger.info("### createApp : " + oAuthKeyVO);
		//그후 데이터베이스에 등록
		consumerService.createConsumer(oAuthKeyVO);		
		
		response.sendRedirect("viewApp?consumerkey=" + oAuthKeyVO.getConsumerKey());
	}
	
	@RequestMapping(value="/viewApp", method=RequestMethod.GET) 
	public ModelAndView viewApp(@RequestParam("consumerkey") String consumerKey) throws Exception {
		ConsumerVO oAuthKeyVO = consumerService.selectByConsumerKey(consumerKey);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewApp");
		mav.addObject("oAuthKeyVO", oAuthKeyVO);
		return mav;
	}
	
	@RequestMapping(value="/deleteApp", method=RequestMethod.GET) 
	public void deleteApp(@RequestParam("consumerkey") String consumerKey,
			HttpServletResponse response) throws Exception {
		consumerService.deleteConsumer(consumerKey);
		response.sendRedirect("viewAppList");
	}
}
