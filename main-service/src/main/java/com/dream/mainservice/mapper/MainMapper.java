package com.dream.mainservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dream.mainservice.dto.MainDto;

@Repository
@Mapper
public interface MainMapper {
	List<MainDto> getProductList() throws Exception;
}
