package com.dream.approveservice.service;

import org.springframework.stereotype.Service;

import com.dream.approveservice.dto.orderVO;
import com.dream.approveservice.mapper.ApproveMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApproveServiceImpl implements ApproveService{
	
	private final ApproveMapper mapper;
	
	@Override
	public orderVO bringOrder(int orderNo) {
		log.info("---------------status detail bringOrder Mapper.xml -----------------");
		return mapper.bringOrder(orderNo);
	}
	
	@Override
	public void update(int orderNo, int status) {
		log.info("---------------status detail change Mapper.xml -----------------");

		mapper.update(orderNo, status);
	}
}
