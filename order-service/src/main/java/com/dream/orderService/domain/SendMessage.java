package com.dream.orderService.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class SendMessage {
	private String userId;
}
