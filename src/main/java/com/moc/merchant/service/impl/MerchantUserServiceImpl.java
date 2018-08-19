package com.moc.merchant.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moc.common.service.impl.CommonServiceImpl;
import com.moc.merchant.entity.PO.MerchantUser;
import com.moc.merchant.mapper.MerchantUserMapper;
import com.moc.merchant.service.IMerchantUserService;

@Service
@Transactional
public class MerchantUserServiceImpl extends CommonServiceImpl<MerchantUser> implements IMerchantUserService {

		
		@Autowired
		private MerchantUserMapper mUserMapper;

		protected void init() {
			super.mapper = this.mUserMapper;
		}

	
}
