package com.dream.confirm.service;

import java.util.List;

import com.dream.confirm.dto.confirmDto;

public interface confirmService {
	public List<confirmDto> list(String userId);
	public confirmDto last(String userId);
}
