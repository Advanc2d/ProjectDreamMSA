package com.dream.manageservice.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class StatusMessage {
	private String orderNo;
}
