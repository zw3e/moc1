package com.moc.customer.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserVO {


	private Long id;
	private String iconUrl;
	private String phone;
	
	@JsonProperty("name")
	private String weichatName;
	private String regTime;
	
	
	
}
