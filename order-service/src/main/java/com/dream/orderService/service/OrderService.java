package com.dream.orderService.service;

import com.dream.orderService.domain.OrderOrderVO;
import com.dream.orderService.domain.OrderProductVO;

public interface OrderService {
	public OrderProductVO bringLoan(int proNo);
	
	public OrderOrderVO saveOrder();
}
