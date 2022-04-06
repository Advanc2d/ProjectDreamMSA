package com.dream.manageservice.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DataListdto implements Serializable {
	private List<Datadto> datalist;
}
