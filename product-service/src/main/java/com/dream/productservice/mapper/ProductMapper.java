package com.dream.productservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dream.productservice.dto.ManageDto;
import com.dream.productservice.dto.ProductDto;

@Repository
@Mapper
public interface ProductMapper {
	public void register(ProductDto dto);
	
	public List<ProductDto> getProductList() throws Exception;
	
	public ProductDto getProduct(int proNo);
	
	public void update(ProductDto dto);
	
	public void delete(int proNo);
}
