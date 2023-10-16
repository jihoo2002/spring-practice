package com.spring.myweb.user.mapper;

import com.spring.myweb.user.entity.User;

public interface IUserMapper {

	//아이디 중복 확인
	int idCheck(String id); //쿼리문에서 count사용해서 개수 셀것이기때문에 !
	
	//회원 가입
	void join(User user);
	
	//로그인
	//id를 통해 pw를 조회해서 사용자가 입력하 pw와 같은지 판단할거임
	String login(String id); 
	
	//회원 정보 얻어오기
	User getInfo(String id);
	
	//회원 정보 수정
	void updateUser(User user);
	
	
	
	
}
