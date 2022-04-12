package com.dream.manageservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dream.manageservice.dto.StatusDto;

@Repository
@Mapper
public interface ManageMapper {
	List<StatusDto> getStatusList() throws Exception;
}
