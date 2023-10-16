package com.spring.myweb.rest;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Person {

	//test1.jsp 속 data 변수 명과 똑같아야 자바에서 아노테이션을 통해 받을 수 있다.
	private String name;
	private int age;
	private List<String> hobby;
	
	
}
