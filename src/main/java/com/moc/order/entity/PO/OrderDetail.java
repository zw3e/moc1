package com.moc.order.entity.PO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDetail {

	private String id;
	private String order_id;
	private String menu_id;
	private BigDecimal need_pay;
	private BigDecimal real_pay;
	
	
	

}