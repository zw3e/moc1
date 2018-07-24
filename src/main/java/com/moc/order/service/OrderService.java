package com.moc.order.service;

import com.moc.order.entity.Order;

public interface OrderService {

	
    /** 创建订单. */
    Order create(Order order);
}
