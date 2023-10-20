package com.spring.myweb.reply.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.reply.entity.Reply;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
public class ReplylListResponseDTO {

	private int replyNo;
	private String replyText, replyWriter;
	private String date;
	
	public ReplylListResponseDTO(Reply reply) {
		this.replyNo = reply.getReplyNo();
		this.replyWriter = reply.getReplyWriter();
		this.replyText = reply.getReplyText();
		if(reply.getUpdateDate() == null) {
			this.date = makePrettierDateString(reply.getReplyDate());
		}else {
			this.date = makePrettierDateString(reply.getUpdateDate()) + "  (수정됨)"; //분초로 해석됨
		}
	}
	
	 static String  makePrettierDateString(LocalDateTime regDate) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			return dtf.format(regDate); //날짜를 문자열로 돌려준다.

		 }

	
	
	
}
