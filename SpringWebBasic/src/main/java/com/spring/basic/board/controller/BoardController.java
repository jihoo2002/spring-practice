package com.spring.basic.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.board.dto.BoardRequestDTO;
import com.spring.basic.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/board") //공통 url 맵핑
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	//글 작성 화면을 열어주는 메서드
	@GetMapping("/write")
	public void write() {
		System.out.println("/board/write : get");
		//return 타입 없으니 경로 이름이 똑같은 write.jsp 로 감
	}
	
	//글 등록 요청 메서드
	@PostMapping("/write")
	public String write(String writer, String title, String content) {
		 System.out.println("/board/write : post");
		 service.insertAricle(writer, title, content);
		//글 등록 완료 후 /board/list(글 목록) 요청이 다시 들어올 수 있게끔 redirect 처리
		 return "redirect:/board/list";
	}
	
	//글 목록 화면 요청
	@GetMapping("/list")
	public void list(Model model) {
		System.out.println("/board/list: get");
		model.addAttribute("articles",service.getArticles());
		//목록이 담긴 list를 model에 담아서 jsp로 넘겨야 하기 때문에!
	}
	
	
	
	//글 내용 상세보기 요청 처리 메서드
	@GetMapping("/content")
	public void content(int boardNo,Model model) {
		System.out.println("/board/content?boardNo=" + boardNo);
		retrieve(boardNo, model);
		
	}
    // 글 수정하기 화면으로 이동 요청
    // 메서드 이름은 modify(), url: /board/modify -> GET
    // 수정하고자 하는 글 정보를 모두 받아와서 modify.jsp로 보내 주세요.
    
    
    //modify.jsp를 생성해서, form태그에 사용자가 처음에 작성했던 내용이 드러나도록
    //배치해 주시고 수정을 받아 주세요.
    //수정 처리하는 메서드: modify(), 요청 url: /board/modify -> POST
    // 수정 처리 완료 이후 방금 수정한 글의 상세보기 요청이 다시 들어올 수 있도록 작성하세요.
    
    
    //삭제는 알아서 작성해 주세요. (삭제 클릭하면 해당 글이 삭제될 수 있도록)
	
	//글 수정하기 화면으로 이동 요청
	@GetMapping("/modify")
	public void modify(int boardNo, Model model) {
		System.out.println("/board/modify.jsp로 이동하기" +boardNo);
		retrieve(boardNo, model);
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardRequestDTO dto) {
		service.modify(dto);
		return "redirect:/board/content?boardNo="+dto.getBoardNo();
	}
	//상세보기, 수정 페이지 공통 로직을 메서드화
	private void retrieve(int boardNo, Model model) {
		model.addAttribute("article",service.retrieve(boardNo));
		//서비스 불러서 리턴된 값을 모델에 담아서 주는 역할
		
	}
	
	@GetMapping("/remove")
	public String remove(int boardNo) {
		//변수명 다르게 하고 싶으면 requestparam 사용!
		service.delete(boardNo);
		return "redirect:/board/list";
	}

	
	
	
	
	
	
}
