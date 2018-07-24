package com.moc.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order {

	private String id;
	private String order_id;
	private String res_id;
	private Date order_time;
	private BigDecimal need_pay;
	private BigDecimal real_pay;
	private String order_state;
	private String user_id;
	private String order_bz;
	private String is_package;

	private List<OrderDetail> orderDetailList;
	

}