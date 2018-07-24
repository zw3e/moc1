package com.moc.order.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moc.common.utils.KeyUtil;
import com.moc.order.entity.Order;
import com.moc.order.mapper.OrderMapper;
import com.moc.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private OrderMapper orderMapper;
	
	
	@Override
	@Transactional
	public Order create(Order order) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

//        List<CartDTO> cartDTOList = new ArrayList<>();

        //1. 查询商品（数量, 价格）

            //2. 计算订单总价

            //订单详情入库


        //3. 写入订单数据库（orderMaster和orderDetail）

        //4. 扣库存

        //发送websocket消息
      //  webSocket.sendMessage(orderDTO.getOrderId());

        return null;
    
	}

}
