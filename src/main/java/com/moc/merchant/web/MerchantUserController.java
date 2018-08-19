package com.moc.merchant.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.moc.common.entity.ResultInfo;
import com.moc.common.sms.SMSSend;
import com.moc.common.utils.BeanMapper;
import com.moc.common.utils.CommonUtil;
import com.moc.merchant.entity.PO.MerchantUser;
import com.moc.merchant.entity.VO.MerchantUserVO;
import com.moc.merchant.service.IMerchantUserService;


@RestController
@RequestMapping("/user")
public class MerchantUserController {
	
	private final static Logger log =  LoggerFactory.getLogger(MerchantUserController.class);
	@Autowired
	private IMerchantUserService mUserService;
	
	
	@PostMapping("/register")
	public ResultInfo register(@RequestBody MerchantUser mUser,HttpServletRequest request) {
		log.debug("UserController - getAuthCode()，Request parameters : phone = " + JSON.toJSON(mUser));


		if(null == mUser || StringUtils.isEmpty(mUser.getPhone())){
			
			return ResultInfo.failure("parameter is error");
		}
		
		ServletContext application = request.getSession().getServletContext();
		//String code = (String)application.getAttribute(mUser.getPhone());
		String code = "123456";
		if(!StringUtils.isEmpty(code)){
			if(mUser.getAuthCode().equals(code)){
				mUserService.save(mUser);
				
				//mUserService.getByEntity(mUser);
				//MUserVO mUserVo =new MUserVO();
				//BeanMapper.copy(mUser, mUserVo);
				return ResultInfo.success(mUser);
			}
		}
		
		
		return null;
		
	}
	
	
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