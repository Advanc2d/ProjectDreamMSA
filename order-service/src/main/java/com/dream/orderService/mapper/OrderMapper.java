package com.dream.orderService.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dream.orderService.domain.OrderOrderVO;
import com.dream.orderService.domain.OrderProductVO;

@Repository
@Mapper
public interface OrderMapper {	
	public OrderProductVO bringLoan(int proNo);
	public OrderOrderVO saveOrder();
}
