package com.moc.order.entity.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderVO {


	private Long id;
	private String icon_url;
	private String phone;
	
	@JsonProperty("name")
	private String weichat_name;
	private String reg_time;
	
	
	
}
