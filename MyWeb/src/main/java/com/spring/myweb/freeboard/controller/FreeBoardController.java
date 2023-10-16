package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.page.PageCreator;
import com.spring.myweb.freeboard.dto.request.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.request.updateDTO;
import com.spring.myweb.freeboard.dto.response.ContentDTO;
import com.spring.myweb.freeboard.dto.response.FreeListResponseDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;
import oracle.ucp.common.ConnectionSource.CreateMode;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor //서비스 생성자 주입
public class FreeBoardController {
	
	private final IFreeBoardService service;
	
	//페이징이 들어간 목록 화면
	@GetMapping("/freeList")
	public void freeList(Page page, Model model) {
		System.out.println("/freeboard/freeList: get!");
		PageCreator creator;
		int totalCount = service.getTotal(page); // 페이지 전체 수
		if(totalCount == 0) {
			//사용자의 조회 결과가 없음
			page.setKeyword(null);
			page.setCondition(null); //조회가 없다면  
			creator = new PageCreator(page, service.getTotal(page));
			//조회결과가 없다면 전체 게시물을 끌고 온다. 
			model.addAttribute("msg", "searchFail");
		}else {
			creator = new PageCreator(page, totalCount); //아니라면 검색된 total개수를 creator에게 넘겨줌
			//조회된 totalCount와 page를 creator에 넘겨주면
			//이전, 다음, 버튼의 개수를 조정해줌.
		}
		
//		PageCreator creator = new PageCreator(page, service.getTotal(page));
		
		model.addAttribute("boardList", service.getList(page));
		//page 정보와 총 게시물 정보 !
		model.addAttribute("pc", creator);		
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
	public String content(int bno,
							Model model,
							@ModelAttribute("p") Page page) {
		System.out.println("content : get!");
		model.addAttribute("content", service.getContent(bno));
		return "freeboard/freeDetail";
	}
	
	//글 수정 페이지 이동 요청
	@PostMapping("/modPage")//form으로만 요청을 받기위해(보안성 강화)
	//getMapping이면 url 등을 통해 당사자가 아닌 사람도 요청할 수 있기에 
	public String modPage(@ModelAttribute("content") updateDTO dto) {
		//dto를 content라는 이름으로 freeModify에게 넘겨주기 위해서 !
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
