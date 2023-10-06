package com.spring.basic.board.repository;

import java.util.List;

import com.spring.basic.board.entity.Board;

public interface IBoardMapper {
	
	//게시글 등록
	void insertAricle(Board board);
	
	//게시글 전체목록
	List<Board> getArticles();
	
	//게시글 상세
	Board getArticle(int bno);
	
	//게시글 수정
	void updateArticle(Board board);
	
	//게시글 삭제
	void deleteAricle(int bno);
	
	
	
	
	
	
	
	
}
