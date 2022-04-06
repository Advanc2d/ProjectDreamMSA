package com.dream.mainservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.mainservice.dto.MainDto;
import com.dream.mainservice.mapper.MainMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
	private final MainMapper productMapper;

	@Override
	public List<MainDto> getProductList() throws Exception {
		return productMapper.getProductList();
	}
}