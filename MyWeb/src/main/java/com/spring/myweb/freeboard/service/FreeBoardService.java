package com.spring.myweb.freeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.ContentDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor //final 붙은 mapper객체의 생성자 생성
public class FreeBoardService implements IFreeBoardService {

	private final IFreeBoardMapper mapper;
	
	
	
	@Override
	public void regist(FreeRegistRequestDTO dto) {
		mapper.regist(FreeBoard.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				
				.build());
		
		

	}

	@Override
	public List<FreeListResponseDTO> getList() {
		List<FreeListResponseDTO> dtoList = new ArrayList<>();
		List<FreeBoard> list = mapper.getList();
		for(FreeBoard board:list) {
			dtoList.add(new FreeListResponseDTO(board));
		}
		return dtoList;
	}

	
	public ContentDTO getContent(int bno) {
		FreeBoard board=mapper.getContent(bno);
		ContentDTO dto = new ContentDTO(board);
				
		return dto;
	}

	@Override
	public void update(FreeBoard freeboard) {
	

	}

	@Override
	public void delete(int bno) {
		

	}

}
