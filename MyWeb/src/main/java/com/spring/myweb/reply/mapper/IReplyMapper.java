package com.spring.myweb.reply.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.reply.entity.Reply;

public interface IReplyMapper {

	//댓글 등록
	void replyRegist(Reply reply);
	
	
	/*
	- mybatis로 DB 연동을 진행할 때, 파라미터 값이 2개 이상일 떄 그냥 보내시면
	에러가 발생하기 때문에 조치가 필요합니다.
	
	1. @Param 을 이용해서 이름을 붙여주는 방법 (mapper.xml에서 해당 값을 지목할 수 있는 이름 붙이기)
	//변수명으로 찾아가는 것이 아니다. //이름이 같다고 생략해서는 안된다. 
	// List<Reply> getList(@Param("boardNo")int bno, @Param("paging")Page page);
	
	2. Map으로 포장해서 보내는 방법
	3.클래스를 디자인해서 객체 하나만 매개값으로 보내는 방법(bno와 page가 같이 있는 클래스 만들면 됨)
	
	중 하나를 상황에 맞게 적절하게 선택하시면 됩니다. 
	 */
	//목록 요청
	List<Reply> getList(Map<String, Object> data);
	
	
	//댓글 개수(페이징, PageCreator는 사용하지 않습니다.)
	int getTotal(int bno); //이 게시글에 몇개의 댓글 달려있는지 확인하기 위해서
	
	//비밀번호 확인
	String pwCheck(int rno); //댓글 번호를 줄거임
	
	//댓글 수정
	void update(Reply reply);
	
	//댓글 삭제
	void delete(int rno);
	
	
	
	
	
}
