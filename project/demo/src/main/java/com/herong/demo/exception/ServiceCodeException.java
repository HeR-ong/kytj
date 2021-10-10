package com.herong.demo.exception;

public class ServiceCodeException extends ServiceException {
	
	private static final long serialVersionUID = 5836113930429357844L;
	
	private int errorCode;
	private String errorDesc;

	public ServiceCodeException(){
		this(-999,"未知异常");
	}
	
	public ServiceCodeException(String errorDesc){
		this(-999,errorDesc);
	}
	
	public ServiceCodeException(int errorCode,String errorDesc){
		this(errorCode,errorDesc,null);
	}
	
	public ServiceCodeException(int errorCode,String errorDesc , Throwable cause){
		super(errorDesc, cause);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
}
