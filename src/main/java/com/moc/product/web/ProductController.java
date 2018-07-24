package com.moc.product.web;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moc.common.ResultInfoUtil;
import com.moc.common.ResultVO;
import com.moc.product.VO.ProductInfoVO;
import com.moc.product.entity.ProductCategoryEntity;
import com.moc.product.mapper.ProductCategoryMapper;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	
	
	
	 @GetMapping("/list")
	 public ResultVO list() {
		 ProductInfoVO  productInfoVO = new ProductInfoVO();
		 
		 
		 //1.先查出所有的上架商品 2 .循环写入 类别 封装成VO
		 
		 
		 return null;
		 
	 }
	
	//cache缓存
	//http://localhost:8080/moc/product/getProductCategorys
	@RequestMapping("/getProductCategorys")
	//@Cacheable(cacheNames = "product",key = "123" ,condition="#Id.length() > 3", unless="#result.getCode() != 0")
	public ResultVO<List<ProductCategoryEntity>> getProductCategorys() {
		
		
		List<ProductCategoryEntity> pes=productCategoryMapper.getAll();
		
		return ResultInfoUtil.success(pes);
		
		
	}
	
    @RequestMapping("/getProductCategory")
    public ProductCategoryEntity getProductCategory(Long id) {
    	ProductCategoryEntity pe=productCategoryMapper.getOne(id);
        return pe;
    }
    
    
    
    
    
    
    
    
    
/*    @RequestMapping("/add")
    public void save(UserEntity user) {
    	userMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(UserEntity user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userMapper.delete(id);
    }*/
    
    
}