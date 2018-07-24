package com.moc.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.druid.util.StringUtils;

public class RedisLock {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
	/**
	 * 
	 * @param key
	 * @param value 当前时间+超时时间
	 * @return
	 */
	public boolean lock(String key,String value){
		
		//就是setnx
		
		if(redisTemplate.opsForValue().setIfAbsent(key, value)){
			return true;
		}
		
		String currentValue = redisTemplate.opsForValue().get(key);
		//如果锁过期,currentValue 就是 该过期时间, currentValue=A , 两个线程的value 多是B,其中一个线程拿到锁
		if(!StringUtils.isEmpty(currentValue) 
				&& System.currentTimeMillis() > Long.parseLong(currentValue)  ){
			//获取上一个锁的时间
			String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
			//再判断一次, 如果有多个进来, 如果不相等,说明其它线程 也 获取到了锁
			if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
				return true;
			}
			
		}
		
		return false;
	}
}
