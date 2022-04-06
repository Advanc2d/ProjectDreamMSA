package com.dream.manageservice.service;

import java.util.List;

import com.dream.manageservice.dto.Datadto;
import com.dream.manageservice.dto.StatusDto;

public interface ManageService {
	public List<StatusDto> getStatusList() throws Exception;
	public int getUpdate(Datadto dto);
}
