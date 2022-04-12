package com.dream.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.confirm.dto.confirmDto;
import com.dream.confirm.mapper.confirmMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class confirmServiceImpl implements confirmService{
	private final confirmMapper mapper;

	@Override
	public confirmDto check(String userId) {
		log.info("---------------cofirm check Mapper.xml -----------------");
		confirmDto dto = new confirmDto();   
		dto =mapper.check(userId);
		log.info("---------------cofirm check Mapper Complete -----------------");
		return dto;
	}
	
	@Override
	public List<confirmDto> list(String userId){
		log.info("---------------cofirm list Mapper.xml -----------------");
		List<confirmDto> listDto = mapper.list(userId);
		log.info("---------------cofirm list Mapper Complete -----------------");
		return listDto;
	}
}
