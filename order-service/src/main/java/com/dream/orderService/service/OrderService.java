package com.dream.orderService.service;

import java.util.List;

import com.dream.orderService.domain.OrderProductVO;

public interface OrderService {
	
	public OrderProductVO bringLoan(int proNo);
	
	public List<OrderProductVO> getProductList() throws Exception;
}
