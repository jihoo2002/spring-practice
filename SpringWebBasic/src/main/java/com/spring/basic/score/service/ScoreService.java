package com.spring.basic.score.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.basic.score.dto.ScoreListResponseDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.IScoreRepository;

import lombok.RequiredArgsConstructor;


//컨트롤러와 레파지토리 사이에 배치되어 기타 비즈니스 로직 처리
//ex)값을 가공, 예외 처리, dto로 변환 트랜잭션 등등..

@Service //빈 등록 
//@RequiredArgsConstructor 
// ->  qualifier로 직접 지목해서 레파지토리로 가야하기 때문에 주석 처리하고 직접 생성자 생성한것임

public class ScoreService {
	private final IScoreRepository scoreRepository;
	
	@Autowired
    public ScoreService(@Qualifier("jdbc") IScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

	//등록 중간처리 
	//컨트롤러는 나에게 DTO를 줬엉
	//하지만 온전한 학생의 정보를 가지는 객체는 Score(Entity)
	//내가 Entity를 만들어서 넘겨야겠다..
	public void insertScore(ScoreRequestDTO dto) {
				
		//컨트롤러가 부른 서비스 
		//서비스는 컨트롤러에 의존적이며 서비스를 통해 레파지토리와 db에 값을 넘겨준다.
		Score score = new Score(dto);
		//Entity를 완성했으니, Repository에게 전달해서 DB에 넣자..
		scoreRepository.save(score); //입력값 받은 dto를 포함한 score가 전달
		
	}
	
	/*
	 컨트롤러는 나(서비스)에게 데이터베이스를 통해
	 성적 정보 리스트를 가져오길 원하고 있어.
	 근데 Repository는 학생 정보가 모두 포함된 리스트를 주네?
	 현재 요청에 어울리는 응답 화면에 맞는 DTO로 변경해서 주자. 
	 */
	public List<ScoreListResponseDTO> getList() { //리스트로 값을 넘겨줘야 하기 때문에
		List <ScoreListResponseDTO> dtoList = new ArrayList<>();
		List<Score> scoreList =scoreRepository.findAll(); //모두 포함된 리스트 리턴됨
		for(Score s:scoreList) {
			ScoreListResponseDTO dto = new ScoreListResponseDTO(s); //Entity를 DTO로 변환
			dtoList.add(dto); //변경된 값들을 리스트에 담아준다. 
		}
		return dtoList; 
		
	}
	/*
	 학생 점수 개별 조회 
	 응답하는 화면에 맞는 DTO를 선언해서 주어야 하는 것이 원칙
	 만약에 Score 전체 데이터가 필요한 것이 아니라면
	 몇개만 추리고 가공할 수 있는 DTO를 설계해서 리턴하는 것이 맞음
	 */
	
	public Score retrieve(int stuNum) {
	return scoreRepository.findByStuNum(stuNum); 
		//학생의 학번을 받아야 그 학생 정보를 조회할 수 있음
		//리턴값이 여기로 옴 entity의 값이 바로 옴 (원래는 DTO를 생성해서 줘야하는 게 맞음)
		
	}

	public void delete(int stuNum) {
		scoreRepository.deleteByStuNum(stuNum);
	}
	
	public void modify(int stuNum, ScoreRequestDTO dto) {
		Score score = scoreRepository.findByStuNum(stuNum);//원래 벨류값을 넘겨줌
		System.out.println("원래 존재하던 객체: " + score);
		score.changeScore(dto); ///원래 존재하던 객체에 과목 점수만 바꿔주는 거임 즉 이름을 끌고 올 필요가 읎다.
		
		scoreRepository.modify(score);
	}
}
