package com.spring.basic.score.dto;

import lombok.*;

/*
 DTO(Data Transfer Object): 데이터 전송(이동) 객체라는 의미
 -계층 간 데이터 교환을 위한 객체. 
 -로직을 갖고 있지 않은 순수한 데이터 객체로 활용. getter/setter메서드만 갖는다.
 
 
 VO(Value Object):좀 더 특정한 결과 값을 담는 객체.
 값 자체를 표현하기 때문에 객체의 불변성을 보장해야 하며 setter 메서드는 갖지 않는것을 권장.
 
 */
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
//정보등록을 요청했을 때 데이터를 운반하는 클래스 (요청 들어올때만 사용)
public class ScoreRequestDTO {
//정보를 요청할때 데이터 운반하는 클래스
	private String name; //학생 이름
	private int kor, eng, math; //국영수
	
	
}
