package com.moc.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultVO<T> implements Serializable{
	
	 /**
	 * 保证唯一 
	 */
	private static final long serialVersionUID = 2221205391897235118L;

	/** 错误码. */
    private String code;

    /** 提示信息. */
    private String msg;

    /** 数据对象 */
    private T data;

}
