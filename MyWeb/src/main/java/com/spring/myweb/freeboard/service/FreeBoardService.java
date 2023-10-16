package com.spring.myweb.freeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.request.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.request.updateDTO;
import com.spring.myweb.freeboard.dto.response.ContentDTO;
import com.spring.myweb.freeboard.dto.response.FreeListResponseDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor //final 붙은 mapper객체의 생성자 생성
public class FreeBoardService implements IFreeBoardService {

	private final IFreeBoardMapper mapper;
	
	
	//글 등록 
	@Override
	public void regist(FreeRegistRequestDTO dto) {
		mapper.regist(FreeBoard.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())	
				.build());
		

	}

	@Override
	public List<FreeListResponseDTO> getList(Page page) {
		List<FreeListResponseDTO> dtoList = new ArrayList<>();
		List<FreeBoard> list = mapper.getList(page);
		for(FreeBoard board:list) {
			dtoList.add(new FreeListResponseDTO(board));
		}
		return dtoList;
	}
	
	@Override
	public int getTotal(Page page) {
		
		return mapper.getTotal(page);
	}

	
	public ContentDTO getContent(int bno) {
		FreeBoard board=mapper.getContent(bno);
		ContentDTO dto = new ContentDTO(board);
				
		return dto;
	}

	@Override
	public void update(updateDTO dto) {
	mapper.update(FreeBoard.builder()
			.title(dto.getTitle())
			.content(dto.getContent())
			.bno(dto.getBno())
			.build());

	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno);

	}

}
