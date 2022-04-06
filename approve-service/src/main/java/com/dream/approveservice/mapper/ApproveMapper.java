package com.dream.approveservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dream.approveservice.dto.orderVO;

@Repository
@Mapper
public interface ApproveMapper {
	public orderVO bringOrder(int orderNo);
	
	public void update(int orderNo, int status);
}
