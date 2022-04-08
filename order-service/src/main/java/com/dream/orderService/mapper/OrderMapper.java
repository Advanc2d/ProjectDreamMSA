package com.dream.orderService.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dream.orderService.domain.OrderOrderVO;
import com.dream.orderService.domain.OrderProductVO;

@Repository
@Mapper
public interface OrderMapper {	
	public OrderProductVO bringLoan(int proNo);	
	public List<OrderProductVO> getProductList() throws Exception;
	public void saveOrder(OrderOrderVO vo);

}
