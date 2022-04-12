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
		log.info("---------------approve bringOrder Mapper.xml -----------------");
		orderVO vo = new orderVO();
		vo = mapper.bringOrder(orderNo);
		log.info("---------------approve bringOrder Mapper Compelete -----------------");

		return vo;
	}
	
	@Override
	public void update(orderVO vo) {
		log.info("---------------approve change Mapper.xml -----------------");
		
		log.info("status : "+vo.getStatus());

		mapper.update(vo);
		
		log.info("---------------approve change Mapper Complete -----------------");
	}
}
