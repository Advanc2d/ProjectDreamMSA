package com.dream.approveservice.dto;

import java.util.Date;

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
	private Date orderDate;
	private Date endDate;
	private int status;
	private String proName;
}
