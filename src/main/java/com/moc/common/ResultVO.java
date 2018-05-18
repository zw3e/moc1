package com.moc.common;

import lombok.Data;

@Data
public class ResultVO<T> {
	
	 /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 数据对象 */
    private T data;

}
