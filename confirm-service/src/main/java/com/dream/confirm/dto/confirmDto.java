package com.dream.confirm.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class confirmDto {
	private String userId; // kafka or accessToken 
	private int orderNo;
	private String proName;
	private double orderPrice;
	private int rate; // 생략
	private String purpose;
	private double amount;
	private int period;
	private int status;
	private Date orderDate;
	private Date endDate;
}
