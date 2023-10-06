package com.spring.basic.score.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

import lombok.RequiredArgsConstructor;

@Repository("spring")
@RequiredArgsConstructor //온리 파이널 생성자 하나 만들어줌
public class ScoreSpringRepository implements IScoreRepository {
    //내부(중첩) 클래스 (inner class)
    //두 클래스가 굉장히 긴밀한 관계가 있을 때 주로 선언.
    //해당 클래스 안에서만 사용할 클래스를 굳이 새 파일로 선언하지 않고 만들 수 있습니다.
	
	class ScoreMapper implements RowMapper<Score>{
		//rowmapper의 타입 score타입으로 제네릭 사용해서 맞춰줌
			@Override
			public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
				//resultset 값을 Score 객체에 저장.
				System.out.println("maprow메서드 호출");
				System.out.println("rownum" + rowNum);
				Score score = new Score(
						rs.getInt("stu_num"),
						rs.getString("stu_name"),
						rs.getInt("kor"),
						rs.getInt("eng"),
						rs.getInt("math"),
						rs.getInt("total"),
						rs.getDouble("average"),
						Grade.valueOf(rs.getString("grade"))
						);
				return score;
			}
		}


	private final JdbcTemplate template;
	
	
	
	@Override
	public List<Score> findAll() {
		String sql = "SELECT * FROM score";
		return template.query(sql, new ScoreMapper());
		//sql, sql문에 물음표, 가공된 생성자 부름,
		// query는 리스트 타입임.여러개의 행을 끌고 올수 있다.
		
	
	}

	@Override
	public void save(Score score) {
		String sql = "INSERT INTO score" + 
				" VALUES(score_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, score.getStuName(),
				score.getKor(),score.getEng(),
				score.getMath(),score.getTotal(),
				score.getAverage(),
				String.valueOf(score.getGrade()));
		//jdbc쪽에서 예외처리, 데베 연결 알아서 해줌.		
	}

	@Override
	public Score findByStuNum(int stuNum) {
		String sql = "SELECT * FROM score WHERE stu_num = ?";
		try {
			//queryForObject는 조회 결과가 없을 시 예외가 발생합니다. 
			return template.queryForObject(sql, new ScoreMapper(), stuNum);
		} catch (Exception e) {
			//조회 결과가 없다면 catch 문 실행
			return null;
		}
		//객체 하나만 갖고 오려면 queryForObject 순서는 위와 같이 !
		
		
	
	}

	@Override
	public void deleteByStuNum(int stuNum) {
		String sql = "DELETE FROM score WHERE stu_num = ?";
		template.update(sql, stuNum);
		//executeUpdate와 같은 역할을 하는 update
	}

	@Override
	public void modify(Score modScore) {
		String sql = "UPDATE score SET kor = ?, eng = ?, math= ?, total=?, average = ?, grade =?  WHERE stu_num = ?";
		template.update(sql, 
				modScore.getKor(),
				modScore.getEng(),
				modScore.getMath(),
				modScore.getTotal(),
				modScore.getAverage(),
				String.valueOf(modScore.getGrade()),
				modScore.getStuNum());
	}

}
