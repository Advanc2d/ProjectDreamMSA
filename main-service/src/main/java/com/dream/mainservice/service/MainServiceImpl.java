package com.dream.mainservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.mainservice.dto.MainDto;
import com.dream.mainservice.mapper.MainMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService {
	private final MainMapper productMapper;

	@Override
	public List<MainDto> getProductList() throws Exception {
		log.info("---------------main productlist Mapper.xml -----------------");
		List<MainDto> mlistDto = productMapper.getProductList();
		log.info("---------------main productlist Mapper Complete -----------------");
		return mlistDto;
	}
}