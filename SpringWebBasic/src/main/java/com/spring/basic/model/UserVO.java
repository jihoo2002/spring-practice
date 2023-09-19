package com.spring.basic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //중복허용하지 않는 Hashset
public class UserVO {
	
	private String userId; //jsp의 name과 똑같이 맞춰줘야 함. 
	private String userPw;
	private String userName;
	private List<String> hobby;
	
	//데이터를 모두 받을 필요는 없다. 안받은 데이터는 null이 됨. 
}
