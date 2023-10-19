package com.spring.myweb.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myweb.reply.dto.ReplyRegistDTO;
import com.spring.myweb.reply.dto.ReplylListResponseDTO;
import com.spring.myweb.reply.service.IReplyService;

import lombok.RequiredArgsConstructor;

@RestController //모든 메서드의 비동기화 
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

	
	private final IReplyService service;
	
	 //댓글 등록 
	@PostMapping()
	public String replyRegist(@RequestBody ReplyRegistDTO dto) {
		
		System.out.println("댓글 등록 요청 들어옴" + dto);
		service.reployRegist(dto);
		
		return "regSuccess";
	}
	
	//목록 요청
	@GetMapping("/list/{bno}/{pageNum}")
	public Map<String, Object> getList(@PathVariable int bno, @PathVariable int pageNum) {
		
		/*
		 1.화면단에서 getList 메서드가 글 번호, 페이지 번호를 url을 통해 전달합니다.
		 2.mapper인터페이스에게 복수의 값을 전달하기 위해 map을 쓸지, @Param을 쓸지 결정
		 3. ReplyMapper.xml에 sql문을 페이징 쿼리로 작성.
		 4. 클라이언트 측으로 DB에 조회한 댓글 목록을 보낼 때,
		 페이징을 위한 댓글의 총 개수도 함께 보내줘야 합니다.
		 근데 우리는 return을 한 개만 쓸수 있으니까, 복수개의 값을 리턴하기 위해
		 리턴 타입을 Map으로 줄지 , 객체를 디자인 해서 줄 지를 결정합니다. 
		 (댓글 목록 리스트와 전체 댓글 수를 함께 전달할 예정.)-> 일회성으로 쓸거니까 Map으로 클라이언트에게 전달 
		
		 */
		
		
		System.out.println("/list/" + bno + "/" + pageNum);
		
		//한 사용자의 댓글 정보를 모두 list 형태로 리턴되어 list로 저장시킴
		List<ReplylListResponseDTO> list = service.getList(bno, pageNum); 
		 int total = service.getTotal(bno); //게시글에 달려있는 댓글의 총 개수를 total에 저장
		Map<String, Object> map = new HashMap<>();
		map.put("list", list); //한 사용자의 댓글 정보
		map.put("total", total); //한 게시글에 달려있는 댓글 총 개수
		return map;
	}
	
	
	
	
	
	
	
}
