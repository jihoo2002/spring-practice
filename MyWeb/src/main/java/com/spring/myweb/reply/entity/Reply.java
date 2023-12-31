package com.spring.myweb.reply.entity;
/*
 * CREATE TABLE tbl_reply (
    reply_no NUMBER PRIMARY KEY,
    reply_text VARCHAR2(1000) NOT NULL,
    reply_writer VARCHAR2(100) NOT NULL,
    reply_pw VARCHAR2(100) NOT NULL,
    reply_date DATE DEFAULT sysdate,
    bno NUMBER ,
    update_date DATE DEFAULT NULL,
    
    CONSTRAINT reply_bno_fk FOREIGN KEY(bno) REFERENCES freeboard(bno)
    ON DELETE CASCADE  --참조하고 있는 부모값이 삭제될 때 자식의 값도 같이 삭제!
    --게시글 삭제되면 댓글도 삭제될 수 있는 거임
);

CREATE SEQUENCE reply_seq 
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    NOCYCLE
    NOCACHE;

 */

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

	private int replyNo;
	private int bno;
	private String replyText, replyWriter, replyPw;
	private LocalDateTime replyDate;
	private LocalDateTime updateDate;
	
	
	
	
	
	
	
	
	
	
	
	
}
