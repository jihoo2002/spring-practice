package com.spring.basic.score.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;
@Repository("jdbc")
public class ScoreJdbcRepository implements IScoreRepository {
	
	//데이터 베이스 접속에 필요한 정보들을 변수화.(데이터베이스 주소, 계정명, 비밀번호)
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "hr"; //데이터베이스에 접속하기 위한 계정 명
	private String password = "hr";//데이터 베이스 계정 비번
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	//데이터 베이스 연동을 전답하는 객체는 무분별한 객체 생성을 막기위해 싱글톤 
	//디자인 패턴을 구축하는것이 일반적
	//but 우리는 Spring framework를 사용중 -> 컨테이너 내의 객체들을 기본적으로 Singleton으로 유지.
	
	//객체가 생성될 때 오라클 데이터 베이스 커넥터 드라이버 강제 구동해서 연동 준비
	public ScoreJdbcRepository() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//이 경로를 불러와서 강제 구동 시켜야만 데이터 베이스에 접속해서 조회결과를 끌어올 수 있다. 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	
	
	}
	
	
	
	@Override
	public List<Score> findAll() {
		//조회된 정보를 모두 담아서 한번에 리턴하기 위한 리스트.
		List<Score> scoreList = new ArrayList<>();
		
		//1.
		String sql = "SELECT * FROM score";
		
		try {
		//2.
			conn =DriverManager.getConnection(url, username, password);
		
		//3.
			pstmt =conn.prepareStatement(sql);
		
		//4->sql속 물음표 없으므로 생략
		
		//5. ->실행하고자 하는 sql이 select인 경우에는 INSERT, UPDATE, DELETE와는
			//다른 메서드를 호출합니다.
		//메서드의 실행 결과 sql의 조회결과를 들고 있는 객체 ResultSet이 리턴됩니다. 
			//resultset이 데이터베이스의 삽입된 값들을 다 가지고 있다. 
			 rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) { //조회된 행이 하나라도 존재한다면 true, 존재하지 않는다면 false
								//next()는 boolean타입 false면 반복문 종료됨
				//타켓으로 잡힌 행의 데이터를 얻어옵니다. (한 행씩 옴) 
				//score에 있는 allargConstructor를 이용해서 생성자에 담을 거임
				Score s = new Score(
							rs.getInt("stu_num"), //데이터베이스 속 이름과 동일하게 !!
							rs.getString("stu_name"),
							rs.getInt("kor"),
							rs.getInt("eng"),
							rs.getInt("math"),
							rs.getInt("total"),
							rs.getDouble("average"),
							Grade.valueOf(rs.getString("grade"))
							//String타입을 enum타입으로 변환시켜준다. 
						);
				scoreList.add(s); //포장한 s를 리스트에 담음
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//6.
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return scoreList; //while반복문을 통해 담은 리스트를 리턴한다. 
	}

	@Override
	public void save(Score score) {
	//num빼고 모든 값들이 score에 담겨 전달됨
		
		
		try {
			//1.sql을 문자열로 준비해주세요.
			//변수 또는 객체에 들어있는 값으로 sql을 완성해야 한다면, 해당 자리에 ?를 찍어주세요.
			String sql = "INSERT INTO score" + 
						" VALUES(score_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			
			//2.데이터 베이스에 접속을 담당하는 connection객체를 메서드를 통해 받아옵니다. 
			//접속 정보를 함께 전달합니다. 
		conn =DriverManager.getConnection(url, username, password);
			//conn이 db로 들어간다.(접속만!) 
		
		//3. 이제 접속을 할 수 있게 됐으니,SQL을 실행할 수 있는 prepardestatement를 받아옵시다.
		//직접 생성하지 않고, 메서드를 통해 받아옵니다.
		pstmt =conn.prepareStatement(sql);
			//conn이 접속성공하면 prepareStatement를 통해 작성된 sql을 pstmt에게 전달한다.
			
			
		//4.sql이 아직 완성되지 않았기 때문에, 물음표를 채워서 sql을 완성 시킵시다.
		//sql을 pstmt에게 전달했기 때문에 pstmt객체를 이용해서 ?를 채웁시다. 
		pstmt.setString(1, score.getStuName()); 
		pstmt.setInt(2, score.getKor());
		pstmt.setInt(3, score.getEng());
		pstmt.setInt(4, score.getMath());
		pstmt.setInt(5, score.getTotal());
		pstmt.setDouble(6, score.getAverage());
		pstmt.setString(7, String.valueOf(score.getGrade())); 
		//enum타입을 스트링 타입으로 변환
		
		//5.sql을 다 완성했다면 pstmt에게 sql을 실행하라는 명령을 내립니다.
		int rn = pstmt.executeUpdate(); //리턴타입이 int -> sql실행 성공시 1, 실패시 0
		if(rn ==1) {
			System.out.println("INSERT 성공");
		}else {
			System.out.println("INSERT 실패");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//6. sql실행까지 마무리가 되었다면, 사용했던 객체들을 해제합니다.
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	@Override
	public Score findByStuNum(int stuNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByStuNum(int stuNum) {
		//1.
		String sql = "DELETE FROM score WHERE stu_num = ?";
		
		//2.
		try {
			conn =DriverManager.getConnection(url, username, password);
			pstmt =conn.prepareStatement(sql);
			
			
			pstmt.setInt(1,stuNum );
			
			int rn = pstmt.executeUpdate();
			
			
			if(rn ==1) {
				System.out.println("INSERT 성공");
			}else {
				System.out.println("INSERT 실패");
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//6. sql실행까지 마무리가 되었다면, 사용했던 객체들을 해제합니다.
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		
	}

	@Override
	public void modify(Score modScore) {
		// TODO Auto-generated method stub

	}

}
