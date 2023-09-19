package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.model.UserVO;
/*
1번요청: 로그인 폼 화면 열어주기
- 요청 URL : /hw/s-login-form : GET
- 포워딩 jsp파일 경로:  /WEB-INF/views/response/s-form.jsp
- html form: 아이디랑 비번을 입력받으세요.

2번요청: 로그인 검증하기
- 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공
- 요청 URL : /hw/s-login-check : POST
- 포워딩 jsp파일 경로:  /WEB-INF/views/response/s-result.jsp
- jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우
- 로그인 여부를 "success", "f-pw", "f-id"문자열로 전송
s-result.jsp에서 전송된 로그인 여부를 확인해서 적절한 화면 꾸며서 응답.
*/
@Controller
@RequestMapping("/hw")
public class LoginController {
	
	//화면 열기
	@GetMapping("/s-login-form")
	public String loginForm() {
		return "/response/s-form";
	}
	
	//로그인 검증 /model.addAttribute("nick" , "짹짹이"); 
	@PostMapping("/s-login-check")
	public String loginCheck(String id, String pw, Model model) {
		 String m ="success";
		if(id.equals("grape111")){
			if(pw.equals("ggg9999")) {
				
			}else {
				m = "f-pw";
				model.addAttribute("m", m);
				return "/response/s-result";
			}
				
		} else {
			m = "f-id";
			model.addAttribute("m", m);
			return "/response/s-result";
		}
			model.addAttribute("m", m);
			return "/response/s-result";
		
		
	}
}
