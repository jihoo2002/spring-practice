package com.spring.myweb.freeboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor @ToString

public class updateDTO {
	private int bno;
	private String writer, title, content;
}
