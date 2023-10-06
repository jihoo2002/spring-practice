package com.spring.basic.score.repository;

import java.util.List;

import com.spring.basic.score.entity.Score;

public interface IScoreMapper {
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
