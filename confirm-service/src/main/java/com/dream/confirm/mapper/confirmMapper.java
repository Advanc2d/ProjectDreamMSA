package com.dream.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dream.confirm.dto.confirmDto;

@Repository
@Mapper
public interface confirmMapper {
	public List<confirmDto> list(String userId);
}
