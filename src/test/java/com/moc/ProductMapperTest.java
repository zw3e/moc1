package com.moc;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moc.product.entity.PO.ProductCategoryEntity;
import com.moc.product.mapper.ProductCategoryMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductMapperTest {
	
	@Autowired
	ProductCategoryMapper pcMapper;
	
	@Autowired
	DataSource ds ;
	
	@Test
	public void mybatisSelect(){
		
		List<ProductCategoryEntity> f = pcMapper.getAll();
		for (int i = 0; i < f.size(); i++) {
			log.info(f.get(i).toString());
		}
		
	}
	
	@Test
	public void t2(){
		log.info(ds.getClass().toString());
		
	}
	
	
}
