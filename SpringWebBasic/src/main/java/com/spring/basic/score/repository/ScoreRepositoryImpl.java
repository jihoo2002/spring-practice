package com.spring.basic.score.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;

//@Component ->3계층 이외의 객체를 빈으로 등록할 때 사용
@Repository //빈등록 해서 다른 클래스에서 객체 주입해 사용할 수 있게 하자
//service에서 사용해야 하니까(service가 의존하는 객체임)

//Score 클래스의 entity의 값을 받아서 처리하는 레파지토리

public class ScoreRepositoryImpl implements IScoreRepository {
	//key :학번, value :성적 정보를 담은 객체
	private static final Map<Integer, Score> SCORE_MAP;
	
	
	//학번에 사용할 일련번호
	private static int sequence;
	
	//static 초기화
	static {
		SCORE_MAP = new HashMap<>();
		Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 34, 91));
		Score stu2 = new Score(new ScoreRequestDTO("춘식이", 77, 99, 87));
		Score stu3 = new Score(new ScoreRequestDTO("대길이", 98, 66, 85));
		
		stu1.setStuNum(++sequence);//하나씩 올려야 함.
		stu2.setStuNum(++sequence); 
		stu3.setStuNum(++sequence);
		
		SCORE_MAP.put(stu1.getStuNum(), stu1); //map안에 학번과 객체(이름, 점수 ...)를 넣는다.
		SCORE_MAP.put(stu2.getStuNum(), stu2);
		SCORE_MAP.put(stu3.getStuNum(), stu3);
	}
	@Override
	public List<Score> findAll() {
		//SCORE_MAP은 번호가 key, 객체가 value로 이루어져 있음
		//객체들만 전부 뽑아서 List로 만들 것이기 때문에
		//SCORE_MAP에서 value들만 전부 뽑아 낸뒤, arrayList의 생성자의 매개값으로 전달해서
		//List로 포장
		List<Score> scoreList = new ArrayList<Score>(SCORE_MAP.values()); 
		return scoreList; //이미 score 안에 번호의 값이 있으니 key의 값은 필요없음 즉 벨류만 모으면 된다. 
	}

	@Override
	public void save(Score score) { //dto가 담긴 score가 전달됨.
		score.setStuNum(++sequence);//번호 세팅
		SCORE_MAP.put(score.getStuNum(), score);//디비역할하는 스코어 맵에 값을 넣어줌
	}

	@Override
	public Score findByStuNum(int stuNum) {
		return SCORE_MAP.get(stuNum); //키값 주면 벨류 값줌
		
		
	}

	@Override
	public void deleteByStuNum(int stuNum) {
		SCORE_MAP.remove(stuNum);
		
	}

	@Override
	public void modify(Score modScore) {
		SCORE_MAP.put(modScore.getStuNum(), modScore);
		
	}

	
}
