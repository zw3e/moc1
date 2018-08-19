package com.moc.merchant.web;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.moc.common.Constant.CodeEnum;
import com.moc.common.Constant.RedisConstant;
import com.moc.common.entity.ResultInfo;
import com.moc.common.utils.BeanMapper;
import com.moc.common.utils.KeyUtil;
import com.moc.merchant.entity.PO.MerchantUser;
import com.moc.merchant.entity.VO.MerchantUserVO;
import com.moc.merchant.service.IMerchantUserService;


@RestController
@RequestMapping("/user")
public class MerchantUserController {
	
	private final static Logger log =  LoggerFactory.getLogger(MerchantUserController.class);
	@Autowired
	private IMerchantUserService mUserService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	 
	
	@PostMapping("/register")
	public ResultInfo register(@RequestBody MerchantUser mUser,HttpServletRequest request) {
		log.debug("MerchantUserController - register()，Request parameters : phone = " + JSON.toJSON(mUser));


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
	
	
	@PostMapping("/login")
	public ResultInfo login(@RequestBody MerchantUserVO mUser,HttpServletRequest request) {
		log.debug("MerchantUserController - login()，Request parameters : phone = " + JSON.toJSON(mUser));


		if(null == mUser || StringUtils.isEmpty(mUser.getPhone())
				 || StringUtils.isEmpty(mUser.getPassword())
				){
			
			return ResultInfo.failure("parameter is error");
		}
		
		//1.去数据库里查询该用户信息
		MerchantUser mupo = new MerchantUser();
		BeanMapper.copy(mUser, mupo);
		MerchantUser user = mUserService.getByEntity(mupo);
		
		if(user == null){
			//查无此人
			return ResultInfo.failure(CodeEnum.LOGIN_FAIL.getCode(), CodeEnum.LOGIN_FAIL.getMsg());
		}
		
		
		//2.设置token 至缓存 
		String token = KeyUtil.getUUID();
		
		 redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), 
				 user.getId()+"-"+user.getPhone(),
				 RedisConstant.TOKEN_EXPIRE_TIME,TimeUnit.SECONDS
				 );
		
		//3.返回token
		
		
		
		return null;
	}
	
	   @GetMapping("/logout")
	    public ModelAndView logout(@RequestBody MerchantUserVO mUser) {
		   
	        //1. 从缓存里里查询该token
		   
		   //2.如果有就删除
	        redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, 
	        		mUser.getToken()));


	        //3.返回登出成功提示
	        return null;
	    }
	
	

    
    
}