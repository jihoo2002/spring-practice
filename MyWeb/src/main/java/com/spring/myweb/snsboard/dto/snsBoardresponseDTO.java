package com.spring.myweb.snsboard.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.snsboard.entity.SnsBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode

public class snsBoardresponseDTO {
	private int bno;
	private String writer, uploadPath, fileLoca,
					fileName, content;
	private String regDate;
	private int likeCnt;
	
	
	
	public snsBoardresponseDTO(SnsBoard board) {
		this.bno = board.getBno();
		this.writer = board.getWriter();
		this.uploadPath = board.getUploadPath();
		this.fileLoca = board.getFileLoca();
		this.fileName = board.getFileName();
		this.content = board.getContent();
		this.regDate = makePrettierDateString(board.getRegDate());
		this.likeCnt = board.getLikeCnt();
		
	}
	
    static String makePrettierDateString(LocalDateTime regDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf.format(regDate);
    }
}
