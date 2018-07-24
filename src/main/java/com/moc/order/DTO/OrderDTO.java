package com.moc.order.DTO;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moc.common.Serializer.Date2LongSerializer;

import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {


	private Long id;
	private String icon_url;
	
	@NotEmpty(message = "手机号必填")
	private String phone;
	
	  /** 创建时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
	
	
}
