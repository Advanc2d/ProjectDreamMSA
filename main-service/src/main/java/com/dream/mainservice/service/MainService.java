package com.dream.mainservice.service;

import java.util.List;

import com.dream.mainservice.dto.MainDto;

public interface MainService {
	public List<MainDto> getProductList() throws Exception;
}
