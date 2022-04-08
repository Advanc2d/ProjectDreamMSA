package com.dream.orderService.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderOrderVO {	
	private int orderNo;
	private int proNo;
	private String userId;
	private double orderPrice;
	private String purpose;
	private double amount;
	private int period;
	private Date orderDate;
	private Date endDate;
}
