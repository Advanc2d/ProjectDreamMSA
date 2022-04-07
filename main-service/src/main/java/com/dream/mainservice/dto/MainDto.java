package com.dream.mainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainDto {
	private int proNo;	//대출 상품 번호
	private String proName;	//대출 상품 이름
	private String detail;	//대출 상품 설명
	private String need;	//대출 자격 조건
	private int term;	//대출 상품 기간
	private double proLimit;	//대출 상품 한도
	private double rate;	//대출 상품 이자율
}