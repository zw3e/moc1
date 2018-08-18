package com.moc.common.entity;

import java.io.Serializable;

import com.moc.common.Constant.CodeEnum;

public class ResultInfo implements Serializable{
	
	 /**
	 * 保证唯一 
	 */
	private static final long serialVersionUID = 2221205391897235118L;

	/** 返回码. */
    private Integer code;

    /** 提示信息. */
    private String message;

    /** 数据对象 */
    private Object data;

    /** 数据总数 */
	private Integer total;
	
	public ResultInfo(Integer code) {
		this.code = code;
	}

	public ResultInfo(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultInfo(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public ResultInfo(Integer code, String message, Object data, Integer total) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.total = total;
	}
	
	/** 通用成功 0 成功 */
	public static ResultInfo success() {
		return new ResultInfo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), null);
	}
	
	public static ResultInfo success(Object data) {
		return new ResultInfo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
	} 
	
	
	/** 通用失败  1001 系统异常  */
	public static ResultInfo failure() {
		return new ResultInfo(CodeEnum.SYSTEM_EXCEPTION.getCode(), CodeEnum.SYSTEM_EXCEPTION.getMsg(), null);
	}
	
	public static ResultInfo failure(String failMessage) {
		return new ResultInfo(CodeEnum.SYSTEM_EXCEPTION.getCode(), failMessage, null);
	}
	
	public static ResultInfo failure(int code,String failMessage) {
		return new ResultInfo(code, failMessage, null);
	}

	
//    public static <T> CommResult<T> success(T data){
//        return new CommResult<>(data);
//    }
	
	
	  
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}

	
    
    
    
    
    
    
}
