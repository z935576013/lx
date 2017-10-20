package com.sdx.lx.common.utils;

import java.io.Serializable;

import com.sdx.lx.common.constants.ErrorMessage;


public class Result implements Serializable {

    private static final long serialVersionUID = -1765922262613294441L;

    /** 是否成功，默认成功 */
    protected boolean success = true;

    /** 错误码 */
    protected String errorCode = "";

    /** 结果信息 */
    protected String message = "";

    /**
     * 拷贝结果
     * 
     * @param result 拷贝目标结果
     * @return 拷贝源结果
     */
    public Result copy(Result result) {
        if (result != null) {
            this.success = result.success;
            this.errorCode = result.errorCode;
            this.message = result.message;
        }

        return this;
    }

    /**
     * 失败
     */
    public void fail() {
        this.success = false;
    }

    /**
     * 失败
     * 
     * @param errorCode 错误码
     */
    public void fail(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    /**
     * 失败
     * 
     * @param errorCode 错误码
     * @param message 结果信息
     */
    public void fail(String errorCode, String message) {
        this.success = false;
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * 获取是否成功
     * 
     * @return 是否成功
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置是否成功
     * 
     * @param success 是否成功
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取错误码
     * 
     * @return 错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置错误码
     * 
     * @param errorCode 错误码
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 获取结果信息
     * 
     * @return 结果信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置结果信息
     * 
     * @param message 结果信息
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public void setErrorMessage(ErrorMessage errorMessage)
    {
    	
    	this.success = false ;
    	
    	this.errorCode = errorMessage.getErrorCode() ;
    	this.message = errorMessage.getErrorMessage() ;
    }

}
