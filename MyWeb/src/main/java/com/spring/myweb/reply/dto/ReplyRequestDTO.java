package com.spring.myweb.reply.dto;

import com.spring.myweb.reply.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequestDTO {

	private int bno;
	private String replyText, replyId, replyPw;
	
	//컨트롤러에서 이미 값 채우고 주고 있어서 this. ~요렇게 해도 상관없었다
	public Reply toEntity(ReplyRequestDTO dto) {
	
		//dto를 entity으로 변환
		return Reply.builder()
				.bno(dto.getBno())
				.replyText(dto.getReplyText())
				.replyWriter(getReplyId())
				.replyPw(dto.getReplyPw())
				.build();
	}
	
	
}
