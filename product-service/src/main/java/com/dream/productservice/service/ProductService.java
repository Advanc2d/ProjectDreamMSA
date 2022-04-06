package com.dream.productservice.service;

import java.util.List;

import com.dream.productservice.dto.ManageDto;
import com.dream.productservice.dto.ProductDto;

public interface ProductService {
	public void register(ProductDto dto);
	
	public List<ProductDto> getProductList() throws Exception;
	
	public ProductDto getProduct(int proNo);
	
	public void update(ProductDto dto);
	
	public void delete(int proNo);
}
