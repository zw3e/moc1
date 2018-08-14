package com.moc.product.entity.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductVO {

	@JsonProperty("name")
	private String categoryName;
	
	private String sortNo;
	private String resId;
	private String pid;
	
	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;
	
	
}
