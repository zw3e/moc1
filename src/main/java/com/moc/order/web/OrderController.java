package com.moc.order.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moc.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	

	//Logger log = Log
	
	@Autowired
	private OrderService orderService;
	   
//	 @GetMapping("/createOrder")
//	 public ResultInfo<OrderDTO> createOrder(@Valid OrderDTO od,BindingResult validResult) {
//		 
//	  if (validResult.hasErrors()) {
//	       log.error("【创建订单】参数不正确, OrderDTO={}", od);
//	       throw new MocException(Constants.PARAM_ERROR,
//	            validResult.getFieldError().getDefaultMessage());
//	  }
//		 
//		 orderService.create(null);
//		 
//		 UserVO userVO = new UserVO();
//		 return ResultInfoUtil.success(null);
//	 }
    
	 
//	 //订单列表
//	    @GetMapping("/list")
//	    public ResultInfo<List<OrderDTO>> list(@RequestParam("openid") String openid,
//	                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
//	                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
//	        if (StringUtils.isEmpty(openid)) {
//	            log.error("【查询订单列表】openid为空");
//	        }
//
//	        Page pages = new Page(page,size);
//	        //Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
//
//	        return ResultInfoUtil.success(null);
//	    }
    
}