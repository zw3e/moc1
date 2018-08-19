package com.moc.merchant.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moc.common.service.impl.CommonServiceImpl;
import com.moc.merchant.entity.PO.MerchantStore;
import com.moc.merchant.mapper.MerchantStoreMapper;
import com.moc.merchant.service.IMerchantStoreService;

@Service
@Transactional
public class MerchantStoreServiceImpl extends CommonServiceImpl<MerchantStore> implements IMerchantStoreService {

		// 日志记录属性
		private final static Logger log =  LoggerFactory.getLogger(MerchantStoreServiceImpl.class);
		
		@Autowired
		private MerchantStoreMapper mStoreMapper;

		protected void init() {
			super.mapper = this.mStoreMapper;
		}

	
}
