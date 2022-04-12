package com.dream.manageservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.manageservice.dto.StatusDto;
import com.dream.manageservice.mapper.ManageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManageServiceImpl implements ManageService {
	private final ManageMapper mapper;

	@Override
	public List<StatusDto> getStatusList() throws Exception {
		log.info("---------------manage statuslist Mapper.xml -----------------");
		
		
		List<StatusDto> slistDto = mapper.getStatusList();
		log.info("---------------manage statuslist Mapper Complete -----------------");
		return slistDto;
	}
}