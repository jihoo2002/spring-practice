package com.spring.basic;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //빈 등록 자동으로 시켜주는 태그.
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	//get방식으로 "/"요청이 들어왔을 때 메서드를 실행시킴. 
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		//리턴값을 디스패처 서블릿에게 리턴함
	}
	
}
