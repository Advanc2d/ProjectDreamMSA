package com.dream.approveservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderVO {	
	private int orderNo;
	private int proNo;
	private String userId;
	private double orderPrice;
	private String orderDate;
	private String endDate;
	private int status;
	private double rate;
	private String proName;
	private double amount;
}
