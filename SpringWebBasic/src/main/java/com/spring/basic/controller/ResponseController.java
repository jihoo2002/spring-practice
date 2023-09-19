package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.basic.model.UserVO;

@Controller
@RequestMapping("/response")
public class ResponseController {
	
	
	@GetMapping("/res-ex01")
	public void resEx01() {
		
	}


//	/*
//	 1. Model 객체를 사용하여 응답할 화면에 데이터를 전송하기 .
//	 스프링에서 제공하는 Model 타입의 객체를 활용하여 Jsp 파일과 같은 View 템플릿으로
//	 데이터를 전송할 수 있습니다. 
//	 Model 객체는 기본적으로 Request 객체의 attribute 로 설정되어 전송되므로,
//	  기존에 알고계시던 Forward 방식의 응답을 생각하시면 된다. 
//	 */
//	@GetMapping("/test")
//	public void test(int age, Model model) {
//		//변수명 같으면 @requestParam 생략 가능해서 !
//		//jsp 파일로 갈때 age값도 같이 가져가고 싶다.
//		model.addAttribute("age", age);
//		model.addAttribute("nick", "멍멍이"); //응답과 동시에 끝남
//	//model이 test.jsp로 값을 가져감.
//	}

	
	
	
	//2 @modelAttribute를 사용한 화면에 데이터 전송처리
	//@requestParam + model.addattribute 처럼 동작
	@GetMapping("/test")
	public void test(@ModelAttribute("age") int age, Model model) {
		//int age의 값을 "age"로 바로 저장시킴 addAttri~할 필요 x
		model.addAttribute("nick" , "짹짹이"); 
	}
	
	@GetMapping("/test2")
	public void test2(@ModelAttribute("info") UserVO vo) {
		//폼으로 온 name과 id를 저장한 vo객체를 info로 저장시킨다. 
		System.out.println("메서드 내의 콘솔 출력 : " +vo);
	}
	
	//3. ModelAndView 객체를 활용한 처리
	//데이터를 view 템플릿으로 전달하는 model의 역할과, 사용자에게 응답하고자 하는 페이지를
	//지정하는 역할을 동시에 진행할 수 있다. 
	@GetMapping("/test3")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView(); //쓰려면 객체 생성해야함
		mv.addObject("userName", "김철수");
		mv.addObject("userAge", 25);
		mv.setViewName("/response/test3"); //이 경로로 데이터 전송을 하고 싶다.
		
		return mv;
	}
	
}
