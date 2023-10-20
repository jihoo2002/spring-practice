package com.spring.myweb.reply.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.reply.dto.ReplyRequestDTO;
import com.spring.myweb.reply.dto.ReplyUpdateRequestDTO;
import com.spring.myweb.reply.dto.ReplylListResponseDTO;
import com.spring.myweb.reply.entity.Reply;
import com.spring.myweb.reply.mapper.IReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ReplyService implements IReplyService {

	private final IReplyMapper mapper;
	private final BCryptPasswordEncoder encoder;
	
	//댓글 등록
	@Override
	public void reployRegist(ReplyRequestDTO dto) {
		dto.setReplyPw(encoder.encode(dto.getReplyPw())); //비밀번호 암호화
		mapper.replyRegist(dto.toEntity(dto));

	}

	@Override
	public List<ReplylListResponseDTO> getList(int bno, int pageNum) {
		Page page = Page.builder()
				.pageNo(pageNum)
				.amount(5)
				.build();
		 //댓글은 한 화면에 5개씩
		Map<String, Object> map = new HashMap<>();
		
		map.put("paging", page);//요청한 댓글 버튼과 한 화면에 나올 댓글 양이 담긴 page
		map.put("boardNo", bno); //댓글 목록이 보일 그 게시글의 번호 
		
		List<ReplylListResponseDTO> dtoList =  new ArrayList<>();
		for(Reply reply:mapper.getList(map)) {  //mapper는 사용자 한명의 댓글 정보 모두를 리턴
			dtoList.add(new ReplylListResponseDTO(reply));
		}
		
		return dtoList;	
	}

	@Override
	public int getTotal(int bno) {
		
		return mapper.getTotal(bno); //한 게시물에 달린 댓글의 총개수 리턴됨 
	}

	@Override
	public String pwCheck(int rno) {
		
		return null;
	}
	//댓글 수정
	@Override
	public String update(ReplyUpdateRequestDTO dto) {
		if(encoder.matches(dto.getReplyPw(), mapper.pwCheck(dto.getReplyNo()))) {
			//사용자가 입력한 비밀번호와 sql에 등록된 비번이 같다면 실행될 구문
			mapper.update(dto.toEntity(dto));
			return "updateSuccess";
		}else {
			return "pwFail";
		}
	

	}
	//댓글 삭제
	@Override
	public String delete(int rno, String replyPw) {
		if(encoder.matches(replyPw, mapper.pwCheck(rno))) {
			mapper.delete(rno);
			return "success";
		}else {
			return "deleteFail";
		}

	}

}
