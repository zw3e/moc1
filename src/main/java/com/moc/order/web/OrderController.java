package com.moc.order.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moc.common.ResultInfoUtil;
import com.moc.common.ResultVO;
import com.moc.common.Constant.Constant;
import com.moc.common.exception.MocException;
import com.moc.common.page.Page;
import com.moc.order.DTO.OrderDTO;
import com.moc.order.service.OrderService;
import com.moc.user.VO.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
	

	
	@Autowired
	private OrderService orderService;
	   
	 @GetMapping("/createOrder")
	 public ResultVO<OrderDTO> createOrder(@Valid OrderDTO od,BindingResult validResult) {
		 
	  if (validResult.hasErrors()) {
	       log.error("【创建订单】参数不正确, OrderDTO={}", od);
	       throw new MocException(Constant.PARAM_ERROR,
	            validResult.getFieldError().getDefaultMessage());
	  }
		 
		 orderService.create(null);
		 
		 UserVO userVO = new UserVO();
		 return ResultInfoUtil.success(null);
	 }
    
	 
	 //订单列表
	    @GetMapping("/list")
	    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
	                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
	                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
	        if (StringUtils.isEmpty(openid)) {
	            log.error("【查询订单列表】openid为空");
	        }

	        Page pages = new Page(page,size);
	        //Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

	        return ResultInfoUtil.success(null);
	    }
    
}