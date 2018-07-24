package com.moc.common.Constant;

import lombok.Getter;

@Getter
public enum ConstantEnum {

	 SUCCESS(0, "成功"),
	PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
	
	;
	
 
    private Integer code;

    private String message;

    ConstantEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
