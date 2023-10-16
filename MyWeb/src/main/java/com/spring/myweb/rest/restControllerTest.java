package com.spring.myweb.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //모든 메서드에 responsebody아노테이션이 붙을 수 있는 아노테이션 
@RequestMapping("/rest")
public class restControllerTest {

	@GetMapping("/view")
	public String viewPage() {
		return "test/test1";
	}
	
	/*@RequestBody
	 * json으로 날라오는 데이터를 바로 자바 언어로 변환할 수 없기에
	 * 클라이언트 쪽에서 전송하는 json 데이터를 
	 * 서버에서 사용하는 자바 언어에 맞게 변환하여 받을 수 있는 아노테이션
	 
	 
	 @ResponseBody
	 *메서드가 리턴하는 데이터를 viewresolver에게 전달하지 않고,
	 *클라이언트에게 해당 데이터를 바로 응답하게 합니다,
	 *비동기 통신에서 주로 많이 사용합니다.
	 
	 
	 *@RestController 아노테이션을 통해 빈으로 등록한 컨트롤러는
	 *모든 메서드가 리턴 값을 클라이언트로 직접 리턴합니다(viewResolver를 사용하지 않습니다.)
	 *rest 방식의 통신 전용 컨트롤러로 빈을 등록하는 것입니다. 
	 *
	 *
	 */
	
	
	
	@PostMapping("/object")
//	@ResponseBody
	public Person object(@RequestBody Person p) {
		System.out.println("비동기 방식의 요청이 들어옴");
		System.out.println(p);
		
		p.setName("변경이름");
		p.setAge(100);
		
		return p;
		//새로운 페이지로 응답이 아닌 새로운 데이터만 전달하고 싶다. 
		
		//뷰리졸버한테 객체를 리턴하면 오류나기때문에 responsebody 아노테이션을 불여줘야 함
	}
	
	////////////////////////////////////////////////////////////////
	
	
	@GetMapping("/hello")
//	@ResponseBody //뷰리졸버가 아닌 클라이언트에게 직접 값이 리턴된다.
	public String hello() {
		return "hello world!";
	}
	
	
	@GetMapping("/hobby")
//	@ResponseBody
	public List<String> hobby() {
		return Arrays.asList("축구", "영화감상", "수영");
	}
	
	@GetMapping("/study")
//	@ResponseBody
	public Map<String, Object> study() {
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "java");
		subject.put("jsp", "java server pages");
		subject.put("spring", "spring framework");
		
		return subject;
	}
	
	
	
}
