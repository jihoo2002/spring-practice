package com.spring.myweb.snsboard.entity;
/*
 * -- 좋아요 테이블
CREATE TABLE sns_like (
    lno NUMBER PRIMARY KEY,
    user_id VARCHAR2(5) NOT NULL,
    bno NUMBER NOT NULL

);
-- ON DELETE CASCADE 
-- 외래키(FK)를 참조할 때 참조하는 데이터가 삭제되는 경우
-- 참조하고 있는 데이터도 함꼐 삭제를 진행하겠다. 
ALTER TABLE sns_like ADD FOREIGN KEY(bno)
REFERENCES snsboard(bno)
ON DELETE CASCADE;

CREATE SEQUENCE sns_like_seq 
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100000
    NOCYCLE
    NOCACHE;
 */

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SnsLike {

	
	private int lno, bno;
	private String userId;
	
	
	
	
	
	
	
	
}
