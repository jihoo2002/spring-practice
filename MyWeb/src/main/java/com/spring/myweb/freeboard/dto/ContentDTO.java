package com.spring.myweb.freeboard.dto;

import java.time.LocalDateTime;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter @ToString
@EqualsAndHashCode
public class ContentDTO {

	private int bno;
	private String title, writer, content;
	private LocalDateTime regDate;
//	private LocalDateTime updateDate;
	
	
	public ContentDTO(FreeBoard board) {
		super();
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content =board.getContent();
		this.regDate = detailDate(board);
		
		
	
	}
	
	

	private LocalDateTime detailDate(FreeBoard board) {
			
		return board.getUpdateDate().equals(null)? board.getRegDate(): board.getUpdateDate();
		
	}





}
