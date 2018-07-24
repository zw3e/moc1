package com.moc.order.mapper;

import java.util.List;

import com.moc.order.entity.Order;

public interface OrderMapper {

	List<Order> getAll();
	
	Order getOne(Long id);

	void insert(Order user);

	void update(Order user);

	void delete(Long id);
}
