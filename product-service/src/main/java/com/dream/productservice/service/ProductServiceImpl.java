package com.dream.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.productservice.dto.ManageDto;
import com.dream.productservice.dto.ProductDto;
import com.dream.productservice.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
	private final ProductMapper mapper;

	@Override
	public void register(ProductDto dto) {
		log.info("---------------------- product-service register.xml  -----------------------");
		mapper.register(dto);
	}
	
	@Override
	public List<ProductDto> getProductList() throws Exception {
		log.info("---------------------- product-service getProductList.xml  -----------------------");
		return mapper.getProductList();
	}
	
	@Override
	public ProductDto getProduct(int proNo) {
		log.info("---------------------- product-service getProduct.xml  -----------------------");
		
		return mapper.getProduct(proNo);
	}
	
	@Override
	public void update(ProductDto dto) {
		log.info("---------------------- product-service update.xml -----------------------");
		mapper.update(dto);
	}
	
	@Override
	public void delete(int proNo) {
		log.info("---------------------- manage-service delete.xml -----------------------");
		
		mapper.delete(proNo);
	}
}
