package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.ContentDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.updateDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor //서비스 생성자 주입
public class FreeBoardController {
	
	private final IFreeBoardService service;
	
	//목록 화면
	@GetMapping("/freeList")
	public void freeList(Model model) {
		System.out.println("/freeboard/freeList: get!");
		
		model.addAttribute("boardList", service.getList());
	}
	//글 쓰기 페이지를 열어주는 메서드
	@GetMapping("/freeRegist")
	public void regist() {
		
	}
	
	//글 등록 처리
	@PostMapping("/freeRegist")
	public String regist(FreeRegistRequestDTO dto) {
		service.regist(dto);
		return "redirect:/freeboard/freeList";
	}
	
	//글 상세 보기
	@GetMapping("/content")
	public String content(int bno, Model model) {
		System.out.println("content : get!");
		
		
		model.addAttribute("content", service.getContent(bno));
		return "freeboard/freeDetail";
	}
	
	//글 수정 페이지 이동 요청
	@PostMapping("/modPage")//form으로만 요청을 받기위해(보안성 강화)
	//getMapping이면 url 등을 통해 당사자가 아닌 사람도 요청할 수 있기에 
	public String modPage(@ModelAttribute("content") updateDTO dto) {
		return "freeboard/freeModify";
	}
	//글 수정 
	@PostMapping("/modify")
	public String modify(updateDTO dto) {
		
		service.update(dto);
		return "redirect:/freeboard/content?bno="+dto.getBno();
	}
	
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeboard/freeList";
	}
	
	
	
}
