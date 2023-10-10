package com.spring.basic.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.basic.board.dto.BoardListResponseDTO;
import com.spring.basic.board.dto.BoardRequestDTO;
import com.spring.basic.board.entity.Board;
import com.spring.basic.board.repository.IBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final IBoardMapper mapper;

	//글 등록
	public void insertAricle(String writer, String title, String content) {
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content); //사용자가 입력한 값을 board setter를 이용해 넣어줌
	
		mapper.insertAricle(board);
		
	}

	//글 목록 
	public List<BoardListResponseDTO> getArticles() {
		List<BoardListResponseDTO> dtoList= new ArrayList<>();
		
		List<Board> boardList = mapper.getArticles();
		for(Board b:boardList) {
			BoardListResponseDTO dto = new BoardListResponseDTO(b);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	public Board retrieve(int boardNo) {
		return mapper.getArticle(boardNo);
		
	}

	

	public void modify(BoardRequestDTO dto) {
//		Board board =  mapper.getArticle(boardNo);
//		board.setWriter(writer);
//		board.setTitle(title);
//		board.setContent(content);
//		mapper.updateArticle(board);
		//
		
		mapper.updateArticle(Board.builder()
								.boardNo(dto.getBoardNo())
								.writer(dto.getWriter())
								.title(dto.getTitle())
								.content(dto.getContent())
								.build()); 
			
	}

	
	
	
	public void delete(int boardNo) {
		mapper.deleteAricle(boardNo);
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
