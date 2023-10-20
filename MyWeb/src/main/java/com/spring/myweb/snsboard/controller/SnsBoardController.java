package com.spring.myweb.snsboard.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.snsboard.service.SnsBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/snsboard")
@RequiredArgsConstructor
public class SnsBoardController {

	private final SnsBoardService service;
	
	@GetMapping("/snsList")
	public ModelAndView snsList() {
		ModelAndView mv = new ModelAndView();
		//mv.addObject("name", "value");
		mv.setViewName("/snsboard/snsList");
		return mv; 
		//restcontroller에서 클라이언트에게 직접 데이터 던지는 것이 아닌 
		//jsp 쪽으로 가고 싶다면 모델앤 뷰 객체를 사용하면 jsp쪽으로 갈수 이ㅛ다.
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
