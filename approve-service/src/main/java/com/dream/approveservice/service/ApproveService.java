package com.dream.approveservice.service;

import com.dream.approveservice.dto.orderVO;

public interface ApproveService {
	public orderVO bringOrder(int orderNo);
	
	public void update(orderVO vo);
}
