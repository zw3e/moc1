package com.moc.common.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import lombok.extern.slf4j.Slf4j;

/**
 * 避免缓存穿透 直接到数据库
 * @author zyt
 *
 */
@Slf4j
public class RedisCacheService {

	@Autowired
	private RedisTemplate redisTemplate;
	
	private static final String CACHE_KEY = "roy";
	
	//mybatis mapper 会动态代理生成一个对象
	@Autowired
	private RedisCacheServiceTemplate cacheSedisServiceTemplate;
	
	/**
	 * 方法上不能加synchronized ,查询 缓存 变成了 排队 查询,性能 一下子 下降了,等于没有缓存,缓存不能加锁
	 * @return
	 */
/*	@Cacheable
 * 	@Nullable
	public Cache getCache(String name) {
		Cache cache = this.cacheMap.get(name);
		if (cache == null && this.dynamic) {
			synchronized (this.cacheMap) {
				cache = this.cacheMap.get(name);
				if (cache == null) {
					cache = createConcurrentMapCache(name);
					this.cacheMap.put(name, cache);
				}
			}
		}
		return cache;
	}
	@Cacheput
	@CacheEvict*/
	public List<Object> query(){
		
		//1.查询缓存
		ValueOperations ops = redisTemplate.opsForValue();
		String json = String.valueOf(ops.get(CACHE_KEY));
		
		//2.判断缓存中是否有数据,如果有数据 则返回
		if(StringUtils.isNotEmpty(json) && json.equalsIgnoreCase("null")){
			log.info("------------query from cache --------------");
			return JSON.parseArray(json, Object.class);
		}
		
		//spring cache ,单例模式,在synchronized 里面 再检查一次 在 实例化
		//double check lock
		synchronized (this){
			
			//再做一次check
			json = String.valueOf(ops.get(CACHE_KEY));
			
			//2.判断缓存中是否有数据,如果有数据 则返回
			if(StringUtils.isNotEmpty(json) && json.equalsIgnoreCase("null")){
				log.info("------------query from cache --------------");
				return JSON.parseArray(json, Object.class);
			}
			
			
			//3.一如果没有,则从DB中获取,并且同步到缓存---如果并发,数据 从数据库写到 REDIS的过程中,
			//(假设需要2秒),缓存还没有, 就直接击穿到 数据库了 . 二还有种情况 是 缓存刚好 失效时间到了, 一个并发 访问 就 又击穿到 数据库
			String result = "from db";  // 2s
			ops.set(CACHE_KEY, JSON.toJSON(result),10,TimeUnit.MINUTES);
			List<Object> l = new ArrayList<Object>();
			l.add(result);
			return l;
		}
		
	}
	
	
	
	public List<Object> queryByTemplate(){
		
//		Object o = new Object(){
//		private void sysout() {
//	}}; 
		
		return cacheSedisServiceTemplate.findCache(CACHE_KEY, 10, 
				TimeUnit.MINUTES, new TypeReference<List<Object>>(){},
				new CacheLoadable<List<Object>>(){
					@Override
					public List<Object> load() {
						String result = "from db";  
						List<Object> l = new ArrayList<Object>();
						l.add(result);
						return l;
					}
			
			
		});
	}
	

}
