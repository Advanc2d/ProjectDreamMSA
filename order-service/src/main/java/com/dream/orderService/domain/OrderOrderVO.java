package com.dream.orderService.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderOrderVO {	
	private int proNo;
	private String userId;
	private double orderPrice;
	private String purpose;
	private double amount;
	private int period;
	private Date endDate;
}
