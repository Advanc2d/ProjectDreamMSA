package com.dream.orderService.service;

import java.util.List;

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
		log.info("----------------------order-service bringLoan mapper.xml 작동-----------------------");
		OrderProductVO vo = mapper.bringLoan(proNo);
		log.info("----------------------order-service bringLoan Success -----------------------");
		return vo;
	}


	@Override
	public List<OrderProductVO> getProductList() throws Exception {
		log.info("----------------------order-service ProductList mapper.xml 작동-----------------------");
		List<OrderProductVO> olistVO = mapper.getProductList();
		log.info("----------------------order-service ProductList Finish -----------------------");
		return olistVO;
	}
	
	@Override
	public void saveOrder(OrderOrderVO vo) {
		log.info("----------------------order-service  Save mapper.xml-----------------------");
		mapper.saveOrder(vo);
		log.info("----------------------order-service  Save Success -----------------------");
	}
}
