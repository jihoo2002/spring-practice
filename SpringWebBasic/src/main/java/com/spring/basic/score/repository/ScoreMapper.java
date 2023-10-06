package com.spring.basic.score.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

//jdbcTemplate에서 SELETE쿼리를 위한 resultset 사용을 편하게 하기 위해
//클래스 생성 (jdbcTemplate한테 조회된 내용을 어떻게 가공해야하는지 알려주는 클래스)
//RowMapper 인터페이스를 구현해야 합니다. 
public class ScoreMapper implements RowMapper<Score>{
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
