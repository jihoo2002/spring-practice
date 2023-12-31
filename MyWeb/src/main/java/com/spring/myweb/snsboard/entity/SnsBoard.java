package com.spring.myweb.snsboard.entity;
/*
 *--SNS 게시판
CREATE TABLE snsboard (
    bno NUMBER PRIMARY KEY,
    writer VARCHAR2(50) NOT NULL,
    upload_path VARCHAR2(100) ,
    file_location VARCHAR2(100),
    file_name VARCHAR2(100),
    file_real_name VARCHAR2(100),
    content VARCHAR2(4000),
    reg_date DATE DEFAULT sysdate
);

CREATE SEQUENCE snsboard_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100000
    NOCYCLE
    NOCACHE;
 * 
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class SnsBoard {
	private int bno;
	private String writer, uploadPath, fileLoca,
					fileName, fileRealName,content;
	private LocalDateTime regDate;
	
	//좋아요 개수가 몇개인지를 알려주는 변수 추가
	private int likeCnt;
	

	 static String  makePrettierDateString(LocalDateTime regDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dtf.format(regDate); //날짜를 문자열로 돌려준다.
	//같은 패키지 안에서만 사용할 수 있도록 default로!
	 }

	
	
	
	
	
	
	
	
	
	
	
	
}
