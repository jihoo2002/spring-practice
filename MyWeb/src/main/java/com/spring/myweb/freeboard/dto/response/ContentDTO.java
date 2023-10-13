package com.spring.myweb.freeboard.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @ToString @EqualsAndHashCode
public class ContentDTO {

	private int bno;
	private String title, writer, content;
	private String date;
//	private LocalDateTime updateDate;
	
	
	public ContentDTO(FreeBoard board) {
		super();
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content =board.getContent();
		if(board.getUpdateDate() == null) {
			this.date = FreeListResponseDTO.makePrettierDateString(board.getRegDate());
		} else {
			this.date 
			= FreeListResponseDTO.makePrettierDateString(board.getUpdateDate()) + " (수정됨)";
		}
		
	}
	
//	private String detailDate(LocalDateTime regDate, LocalDateTime updateDate) {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//		if(updateDate == null) {
//			return dtf.format(regDate);
//		}else {
//			return dtf.format(updateDate) +"(수정됨)";
//		}
//	--> jsp랑 연동이 안됨
	}
	
	

	

	





