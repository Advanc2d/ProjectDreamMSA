package com.dream.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.confirm.dto.confirmDto;
import com.dream.confirm.mapper.confirmMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class confirmServiceImpl implements confirmService{
	
	private final confirmMapper mapper;
	
	public confirmDto list(String userId){
		return mapper.list(userId);
	}

	@Override
	public confirmDto last(String userId) {
		return mapper.last(userId);
	}
}
