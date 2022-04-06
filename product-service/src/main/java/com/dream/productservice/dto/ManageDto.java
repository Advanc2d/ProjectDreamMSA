package com.dream.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageDto {
//	private int proId;
	private String proName;
	private double proLimit;
	private String description;
	private int term;
}
