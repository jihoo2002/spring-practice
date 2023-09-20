package com.spring.basic.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.score.dto.ScoreListResponseDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
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
	public String list(Model model) {
		List<ScoreListResponseDTO> dtoList=service.getList(); //서비스에게 리스트를 가지고 오라고 함
		model.addAttribute("sList", dtoList); //브라우저로 가져갈 수 있음
		return "score/score-list";
	}
	
	//2. 성적 정보 등록 처리 요청.
	@PostMapping("/register")
	public String register(ScoreRequestDTO dto) {//입력된 값 dto 넘어옴
		//단순 입력 데이터 읽기
		System.out.println("/score/register: POST!" +dto);
		
		//서비스한테 일 시켜야지
		service.insertScore(dto);
		
		
		/*
		 등록 요청이 완료되었다면, 목록을 불러오는 로직을 여기다 작성하는 것이 아닌,
		 갱신된 목록을 불러오는 요청이 다시금 들어올 수 있도록 유도를 하자 ->sendRedirect()
		 
		 "redirect:url경로"를 작성하면 내가 지정한 url로 자동 재 요청이 일어나면서
		 미리 준비해둔 로직을 수행
		 점수등록 완료-> 목록을 달라는 요청으로 유도 -> 목록 응답	
		 	*/
		return "redirect:/score/list";
		//목록 요청이 자동으로 들어와야 정보 목록을 띄울 수 있다. 
	}
	
	//3.성적 정보 상세 조회 요청
	@GetMapping("/detail")
	public String detail(int stuNum, Model model) {
		System.out.println("/score/detail: Get!");
		retrieve(stuNum, model);
		
		return "score/score-detail"; //detail.jsp로 가게 된다. 
	}
	
	//4.성적 정보 삭제
	@GetMapping("/remove")
	public String remove(int stuNum) {
		System.out.println("/score/remove: get!");
		service.delete(stuNum); //레파지토리에서 지우는 기능을 구현해야 함. 서비스는 레파지토리에게 명령
		return "redirect:/score/list";
	}
	
	
	//5.수정 화면 열어주기
	@GetMapping("/modify")
	public String modify(int stuNum, Model model) {
		retrieve(stuNum, model);
		return "score/score-modify";
	}
	
	
	private void retrieve(int stuNum, Model model) {
		Score score =service.retrieve(stuNum); //해당 번호에 맞는 객체를 끌고 옴
		model.addAttribute("s", score); 
	}
	//수정 완료하기 
	@PostMapping("/modify")
	public String modify(int stuNum, ScoreRequestDTO dto){
		service.modify(stuNum, dto);
	
		return "redirect:/score/detail?stuNum="+stuNum;
		//jsp로 가는게 아님 위에 상세정보로 가야한다. 
	}
	
	
	
	
	
	
	
	
	
	
}
