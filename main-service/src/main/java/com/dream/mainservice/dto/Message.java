package com.dream.mainservice.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect //데이터 전송 객체 클래스
public class Message {
	//order-service로 전송할 객체
	private String proNo; //상품 번호
}