package com.dream.manageservice.service;

import java.util.List;

import com.dream.manageservice.dto.StatusDto;

public interface ManageService {
	public List<StatusDto> getStatusList() throws Exception;
}
