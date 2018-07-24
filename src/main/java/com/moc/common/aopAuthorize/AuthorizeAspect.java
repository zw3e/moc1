package com.moc.common.aopAuthorize;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moc.common.Constant.CookieConstant;
import com.moc.common.Constant.RedisConstant;
import com.moc.common.utils.CookieUtil;

import lombok.extern.slf4j.Slf4j;

/**
 */
//@Aspect
//@Component
@Slf4j
public class AuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Pointcut("execution(public * com.moc.controller.*(..))" +
//    "&& !execution(public * com.moc.controller.UserController.*(..))")
    public void verify() {
    	
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
           // throw new SellerAuthorizeException();
        }

        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
          //  throw new SellerAuthorizeException();
        }
    }
}
