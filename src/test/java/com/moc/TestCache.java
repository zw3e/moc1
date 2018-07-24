package com.moc;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.moc.common.redis.RedisCacheService;



public class TestCache {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private RedisCacheService redisCacheService;
	
	
	private static final String CACHE_KEY = "roy";
	
	private CountDownLatch cd1 = new CountDownLatch(10);
	
	
	@Before
	public void before(){
		redisTemplate.delete(CACHE_KEY);
	}
	
	@Test
	public void testcache(){
		
		//模拟10个线程请求
		for (int i = 0; i < 10; i++) {
			new Thread(new QueryTask()).start();
			cd1.countDown();
		}
	}
	
	/**
	 * 
	 * @author zyt
	 *
	 */
	private  class QueryTask implements Runnable{

		@Override
		public void run() {
			
			try {
				cd1.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			List<Object> l = redisCacheService.query();
			System.out.println(l);
		}
	}
	
}
