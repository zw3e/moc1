package com.moc.weixin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

	/**
	 * 获取code后，请求以下链接获取access_token
	 * @param code
	 */
	@GetMapping("/checkAuth")
	public void checkAuth(@RequestParam("code") String code){
   
		WxMpService wxMpService = new WxMpServiceImpl();
		
		
    }
	
	
	
	
}
