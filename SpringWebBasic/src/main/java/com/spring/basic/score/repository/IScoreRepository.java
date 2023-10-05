package com.spring.basic.score.repository;

import java.util.List;

import com.spring.basic.score.entity.Score;

//역할 명세 (추상화) :
//성적 정보를 잘 저장하고, 잘 조회하고 잘 삭제하고 잘 수정해야 한다.
//Score에 관련된 여러가지 동작들을 명세하고 구현하는 클래스들이
//동일한 동작을 약속하게 하자
public interface IScoreRepository {
	
	//성적정보 전체 조회
	List<Score>findAll(); //전체 학생들의 정보 끌고 와야 해서
	
	//성적 정보 등록
	void save(Score score);

	//성적 정보 개별 조회
	Score findByStuNum(int stuNum);
	// 한명의 정보는 Score 타입으로 리턴한다. 

	//성적 정보 삭제 !
	void deleteByStuNum(int stuNum);


	void modify(Score modScore);
	
	
	
	
}
