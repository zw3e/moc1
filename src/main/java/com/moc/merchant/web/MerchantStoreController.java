package com.moc.merchant.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.moc.common.entity.ResultInfo;
import com.moc.common.utils.BeanMapper;
import com.moc.merchant.entity.PO.MerchantStore;
import com.moc.merchant.entity.VO.MerchantStoreVO;
import com.moc.merchant.service.IMerchantStoreService;


@RestController
@RequestMapping("/merchant")
public class MerchantStoreController {
	
	private final static Logger log =  LoggerFactory.getLogger(MerchantStoreController.class);
	@Autowired
	private IMerchantStoreService mStoreService;
	

	
	@PostMapping("/createStore")
	public ResultInfo openStore(@RequestBody MerchantStore mStoreVo,HttpServletRequest request) {
		log.debug("MStoreController - openStore()，Request parameters : MStoreVO = " + JSON.toJSON(mStoreVo));

		
		if(null == mStoreVo || StringUtils.isEmpty(mStoreVo.getStoreName())
				|| StringUtils.isEmpty(mStoreVo.getAddress())
				|| StringUtils.isEmpty(mStoreVo.getCreatorId())
				){
				
				return ResultInfo.failure("parameter is error");
		}

		MerchantStoreVO vo = new MerchantStoreVO();
		BeanMapper.copy(mStoreVo, vo);
		try {
			mStoreService.save(mStoreVo);
			
			return ResultInfo.success(mStoreVo);
			
		} catch (Exception e) {
			log.error("发生异常 : ", e);
			return ResultInfo.failure("入库失败");
		}

	}
	
	
	
	
	
	
	//cache缓存
	//http://localhost:8080/moc/product/getProductCategorys
	//@RequestMapping("/getProductCategorys")
	//@Cacheable(cacheNames = "product",key = "123" ,condition="#Id.length() > 3", unless="#result.getCode() != 0")
//	public ResultVO<List<ProductCategoryEntity>> getProductCategorys() {
//		
//		
//		List<ProductCategoryEntity> pes=productCategoryMapper.getAll();
//		
//		return ResultInfoUtil.success(pes);
//		
//		
//	}
//	
//    @RequestMapping("/getProductCategory")
//    public ProductCategoryEntity getProductCategory(Long id) {
//    	ProductCategoryEntity pe=productCategoryMapper.getOne(id);
//        return pe;
//    }
    
    
    
    
    
    
    
    
    
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