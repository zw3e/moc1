package com.moc.product.mapper;

import java.util.List;

import com.moc.product.entity.ProductCategoryEntity;

public interface ProductCategoryMapper {

	List<ProductCategoryEntity> getAll();
	
	ProductCategoryEntity getOne(Long id);

	void insert(ProductCategoryEntity user);

	void update(ProductCategoryEntity user);

	void delete(Long id);
}
