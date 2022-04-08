package com.dream.orderService.service;

import org.springframework.stereotype.Service;

import com.dream.orderService.domain.OrderOrderVO;
import com.dream.orderService.domain.OrderProductVO;
import com.dream.orderService.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
	private final OrderMapper mapper;
	
	@Override
	public OrderProductVO bringLoan(int proNo) {
		log.info("----------------------order-service mapper.xml 작동-----------------------");
		return mapper.bringLoan(proNo);
	}
	
	@Override
	public OrderOrderVO saveOrder() {
		log.info("----------------------order-service mapper.xml 작동-----------------------");
		return mapper.saveOrder();
	}
}
