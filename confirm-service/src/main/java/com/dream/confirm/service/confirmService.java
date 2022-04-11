package com.dream.confirm.service;

import java.util.List;

import com.dream.confirm.dto.confirmDto;

public interface confirmService {
	public confirmDto check(String userId);

	public List<confirmDto> list(String userId); 

}
