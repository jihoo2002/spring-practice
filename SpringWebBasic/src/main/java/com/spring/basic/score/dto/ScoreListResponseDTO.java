package com.spring.basic.score.dto;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//Entity는 요청이나 응답을 담는 값으로 쓰리말라 했쪄?
//데이터 베이스에서 조회된 값을 화면으로 응답할 때, 해당 화면이 무엇이냐에 따라
//조회된 값을 가공하거나 추가하거나 제외하고 전달해야 할 필요성이 있기 때문에
//그에 맞는 응답 DTO를 생성해서 전달하는 것이 각자의 역할을 침해하지않는 설계임.
@Getter @ToString @EqualsAndHashCode 
//setter 안부르는 이유 : setter를 부르면 값이 변형됨, 값을 변형하면 안되는 클래스를 만들기 위해 
public class ScoreListResponseDTO {
	
	private int stuNum;
	private String maskingName;
	private double average;
	private Grade grade;
	
	public ScoreListResponseDTO(Score s) { //생성자
		this.stuNum = s.getStuNum();
		this.maskingName = makeMaskingName(s.getStuName());
		this.average = s.getAverage();
		this.grade = s.getGrade();
	}

	//이름을 첫 글자만 빼고 나머지를 * 로 처리하기
	private String makeMaskingName(String originalName) {
		//valueOf() :다른 타입을 문자열로 변환하는 메서드 ->originalName이 char타입이라서!
		String maskingName = String.valueOf(originalName.charAt(0)); //성만 뽑아내기
		for(int i = 1; i<originalName.length(); i++) {
			maskingName += "*"; //나머지 이름에 별을 붙이기
			
		}
		return maskingName;
	}
	
}
