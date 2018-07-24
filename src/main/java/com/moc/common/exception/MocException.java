package com.moc.common.exception;

public class MocException extends RuntimeException{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;


    public MocException(Integer code, String message) {
        super(message);
        this.setCode(code);
    }


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}
	
}
