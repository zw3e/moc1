package com.moc.common.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.moc.common.entity.ResultInfo;
import com.moc.common.sms.SMSSend;
import com.moc.common.utils.CommonUtil;


@RestController
@RequestMapping("/SMS")
public class SMSController {
	
	private final static Logger log =  LoggerFactory.getLogger(SMSController.class);

	
	
	@GetMapping("/sendCode")
	public ResultInfo sendCode(@RequestParam String phone,HttpServletRequest request) {
		log.debug("UserController - register()，Request parameters : mUser = " + JSON.toJSON(phone));

		try {
			String rn = CommonUtil.getRandomNum(5);
			//"[\"369751\"]"
			String templateParas = "[" + rn + "]";
			SMSSend.SendSMS(phone, SMSSend.validateCodeSMS, templateParas);
			
			ServletContext application = request.getSession().getServletContext();
			application.setAttribute(phone,rn);
			
		} catch (Exception e) {
			log.error("发生异常 : ", e);
			return ResultInfo.failure("入库失败");
		}

		return ResultInfo.success();

	}
	
	
	
	
	

    
    
}