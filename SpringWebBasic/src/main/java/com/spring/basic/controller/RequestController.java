package com.spring.basic.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.basic.model.UserVO;


//자동으로 스프링 컨테이너에 해당 클래스의 빈을 등록하는 아노테이션
//빈을 등록해놔야 (객체가 생성되어 있어야) HandlerMapping이  
//이 클래스의 객체를 검색할 수 있을 것 이다. 

@Controller   //("merong") //빈 등록 끝! 
@RequestMapping("/request") //컨트롤러 자체에 공통된 URI 맵핑
//()열고 빈 아이디 직접 지정할 수 있음(빈 아이디 지목 할 떄 유용)
public class RequestController {
			
	public RequestController() {
	System.out.println("RequestCon 생성됨!");
	}
	@RequestMapping("/test")
//     /request/test으로 요청이 들어오면 메서드 실행
	public String testCall() {
		System.out.println("/request/test요청이 들어옴");
		return "test";
	}
	@RequestMapping("/req")
	public String req() {
	System.out.println("요청 들어옴!");
		return "request/req-ex01"; 
	}
	
//	@RequestMapping(value="/request/basic01", method = RequestMethod.GET)
	
	@GetMapping("/basic01")
	public String basicGet() {
		System.out.println("/basic01 요청이 들어옴~!: get 방식!");
		return "request/req-ex01";
	}//상수로 선언하면 get만 받을 수 있음
	
	//@RequestMapping(value="/request/basic01", method = RequestMethod.POST)
	@PostMapping("/basic01") //4버전부터 사용 가능.
	public String basicPost() {
		System.out.println("/basic01 요청이 들어옴~!: post 방식!");
		return "request/req-ex01";
	}
	
	////////////////////////////////////
	/*
	 * 컨트롤러 내의 메서드 타입을 void로 선언하면
	 * 요청이 들어온 URL값을 뷰 리졸버에게 전달.
	 */
	@GetMapping("/join") //void는 맵핑 url를 보고 파일 경로로 연결시킴
	public void register() {
		System.out.println("/request/join: Get");
	
	}
	//요청 URI 주소가 같더라도 전송 방식에 따라 맵핑을 다르게 하기 때문에
	// 같은 주소를 사용하는 것이 가능합니다. (Get ->화면처리, POST->입력값 처리)
	/* 
	 # 스프링에서 요청과 함께 전달된 데이터를 처리하는 방식
	 1. 전통적인 jsp/servlet 방식의 파라미터 읽기 처리 방법.
	 - HttpServletRequest 객체를 확용(우리가jsp에서 사용하던 방식)
	  ->스프링에서는 잘 사용하지 않음
	  
	@PostMapping("/join")
	public void register(HttpServletRequest request) {
		//메서드 실행될 때 request를 보내 request를 쓸 수 있게 해줌
		System.out.println("/request/join: Post");
		
		System.out.println("ID: " + request.getParameter("userId"));
		System.out.println("PW: " + request.getParameter("userPw"));
		System.out.println("HOBBY: " + Arrays.toString(request.getParameterValues("hobby")));
		//hobby 배열이 한꺼번에 넘어올 수 있어야 하기 때문에..
		
	} */
	
	/*
	 * 2. @requestParam 아노 테이션을 이용한 요청 파라미터 처리.
	 * @requestParam ("파라미터 변수명")값을 받아서 처리할  변수
	 * 파라미터 변수명과 값을 받을 변수명을 동일하게 작성하면 RequestParam 생략가능
	 */
	
	@PostMapping("/join")
	public void register( //데이터 타입을 여러개 받을 수 있음
			 String userId,//RequestParam을 자동으로 넣어줌
			 String userPw,
			 @RequestParam(value="hobby", required = false, defaultValue = "no hobby person") List<String> hobbies) {
			
		
			// * requestParam은 디폴트가 true이기에 required를 통해 false로 바꿔야 오류 안남
			// 만약 사용자가 hobby를 선택하지 않았다면 defaultValue의 값이 콘솔 창으로 오게됨.
			// */
		System.out.println("Id: " + userId);
		System.out.println("pw: " + userPw);
		System.out.println("hobby: " + hobbies);
	
}
 

	/*
	 * 3. 커멘드 객체를 활용한 파라미터 처리
	 * 파라미터 데이터와 연동되는 VO 클래스 필요. 
	 - VO 클래스의 필드는 파라미터 변수명과 동일하게 작성합니다.(setter 메서드를 호출)
	 
	 # 커멘드 객체 : 사용자의 입력을 담기 위해 설계되고, 특정 검증 로직이나 비즈니스 로직을
	 수행할 수 있음. (VO 보다는 역할이 좀 더 많고, 특정 목적을 가진 객체) 
	 */
	
//	@PostMapping("/join")
//	public void register(UserVO vo) {
//		System.out.println(vo);
//		//디스패처가 vo객체를 만들어 UserVO 안 setter를 통해 필드 변수를 가져옴
//		//UserVO에서 toString을 오버라이딩 했기에 tostring 틀로 전달됨.
//		
//	}

}
