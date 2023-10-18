package com.spring.myweb.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.spring.myweb.freeboard.dto.response.FreeListResponseDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponseDTO {

	private String userName;
	private String userPhone1;
	private String userPhone2;
	private String userEmail1;
	private String userEmail2;
	private String addrBasic;
	private String addrDetail;
	private String addrZipNum;
	
	private List<FreeListResponseDTO> userBoardList;
	
	
	
	public static UserInfoResponseDTO toDTO(User user) {
		List<FreeListResponseDTO> list = new ArrayList<>();
		for(FreeBoard board :user.getUserBoardList()) {//한 사람의 게시물 글 전체를 끌고와 한행씩 board에 저장시킨다.
			list.add(new FreeListResponseDTO(board));
			//FreeListResponseDTO를 통해 board를 변환시켜 list에 넣음
		}
		
		
	return UserInfoResponseDTO.builder()
		.userName(user.getUserName())
		.userPhone1(user.getUserPhone1())
		.userPhone2(user.getUserPhone2())
		.userEmail1(user.getUserEmail1())
		.userEmail2(user.getUserEmail2())
		.addrBasic(user.getAddrBasic())
		.addrDetail(user.getAddrDetail())
		.addrZipNum(user.getAddrZipNum())
		.userBoardList(list)
		.build();
	}
	
	
	
}
