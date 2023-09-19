package com.spring.basic.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor //: final 필드가 존재한다면 그것만을 초기화 해주는 생성자 !
public class ScoreController {
	
	
	private final ScoreService service; //객체 주입된 service
	//만약에 클래스의 생성자가 단 1개라면
	//자동으로 @Autowired를 작성해줌. |값에 null이 절대 올 수 없음
	
	
	//1.성적 등록화면 띄우기 + 정보 목록 조회
	@GetMapping("/list")
	public String list() {
		return "score/score-list";
	}
	
	//2. 성적 정보 등록 처리 요청.
	@PostMapping("/register")
	public String register(ScoreRequestDTO dto) {
		//단순 입력 데이터 읽기
		System.out.println("/score/register: POST!" +dto);
		
		//서비스한테 일 시켜야지
		service.insertScore(dto);
		
		return null;
	}
	
}
