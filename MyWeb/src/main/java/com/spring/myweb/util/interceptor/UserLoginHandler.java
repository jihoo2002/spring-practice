package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//인터셉터로 사용할 클래스는 HandlerInterceptor 인터페이스를 구현합니다. 
public class UserLoginHandler implements HandlerInterceptor {

	//preHandle은 컨트롤러로 요청이 들어가기 전 처리해야 할 로직을 작성
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("나는 preHandle이다!!!");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	//postHandle은 컨트롤러를 나갈 때 공통 처리해야할 내용을 작성.
	// /userLogin이라는 요청이 컨트롤러에서 마무리 된 후, viewResolver로 전달되기 전
	//로그인 성공 or 실패 여부에 따라 처리할 로직을 작성할 계획입니다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("로그인 인터셉터가 동작합니다!");
		System.out.println("요청 방식: " + request.getMethod()); //요청된 방식이 get, post인지 알수있다.
		
		//get은 단순 로그인 페이지만 열어주기에 post방식에서만 동작할 수 있게 하겠움
		if(request.getMethod().equals("POST")){ //요청 방식이 post라면 로그인 요청임!
			//대문자로 POST 써야함 
			ModelMap map = modelAndView.getModelMap(); //모델 객체 꺼내기
			//model 객체 전달하고 있다면 getModelMap 사용
			String result = (String) map.get("result");
			
			
			if(result!= null) { 
				//dbPw.equals(inputPw) --> nullpointerEx 발생할 가능성 있음 db안 pw가 없을수도 있으니까!
				System.out.println("로그인 성공 로직이 동작합니다.");
				//로그인 성공 시 로직 실행
				
				//로그인 성공한 회원에게는 세션 데이터를 생성해서 로그인 유지를 하게 해줌.
				HttpSession session = request.getSession();
				
				session.setAttribute("login", result); //세션은 브라우저 끌때까지 데이터가 유지된다. 
				response.sendRedirect(request.getContextPath() + "/"); //메인 페이지로 이동
				//request.getContextPath()가 변경되면 변경된 컨텍스트 경로를 동적으로 바꿔줌
			}else {
				//로그인 실패 시 로직 실행
				modelAndView.addObject("msg", "loginFail"); 
				
			}
			
			
			
		}
		
		
		
	}
	
	
	}
	

