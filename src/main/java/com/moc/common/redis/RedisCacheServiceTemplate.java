package com.moc.common.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个模板模式
 * @author zyt
 * 
 */
@Slf4j
@Component
public class RedisCacheServiceTemplate {

	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 
	 * @param key
	 * @param expireTeime
	 * @param unit
	 * @param clazz
	 * @param cacheLoadable 如果缓存没有被取到,怎么来来获取
	 * @return
	 */
	public <T> T findCache(String key ,long expireTeime,TimeUnit unit,
			TypeReference<T> clazz,CacheLoadable<T> cacheLoadable)
	{
		//1.查询缓存
		ValueOperations ops = redisTemplate.opsForValue();
		String json = String.valueOf(ops.get(key));
		
		//2.判断缓存中是否有数据,如果有数据 则返回
		if(StringUtils.isNotEmpty(json) && json.equalsIgnoreCase("null")){
			log.info("------------query from cache --------------");
			return JSON.parseObject(json, clazz);
		}
		
		//spring cache ,单例模式,在synchronized 里面 再检查一次 在 实例化
		//double check lock
		synchronized (this){
			
			//再做一次check
			json = String.valueOf(ops.get(key));
			
			//2.判断缓存中是否有数据,如果有数据 则返回
			if(StringUtils.isNotEmpty(json) && json.equalsIgnoreCase("null")){
				log.info("------------query from cache --------------");
				return JSON.parseObject(json, clazz);
			}
			
			//3.一如果没有,则从DB中获取,并且同步到缓存---如果并发,数据 从数据库写到 REDIS的过程中,
			//(假设需要2秒),缓存还没有, 就直接击穿到 数据库了 . 二还有种情况 是 缓存刚好 失效时间到了, 一个并发 访问 就 又击穿到 数据库
			//核心业务
			
//			String result = "from db";  // 2s
//			ops.set(key, JSON.toJSON(result),expireTeime,unit);
//			List<Object> l = new ArrayList<Object>();
//			l.add(result);
			
			T result = cacheLoadable.load();
			ops.set(key, JSON.toJSON(result),expireTeime,unit);
			return result;
		}
		
	}
	
	
}
