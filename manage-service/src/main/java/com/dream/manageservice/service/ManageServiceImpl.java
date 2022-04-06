package com.dream.manageservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.manageservice.dto.Datadto;
import com.dream.manageservice.dto.StatusDto;
import com.dream.manageservice.mapper.ManageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {
	private final ManageMapper mapper;

	@Override
	public List<StatusDto> getStatusList() throws Exception {
		return mapper.getStatusList();
	}
	
	@Override
	public int getUpdate(Datadto dto){
		return mapper.getUpdate(dto);
	}
}