package com.spring.basic.board.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private int boardNo;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime regDate;
	
	
}
