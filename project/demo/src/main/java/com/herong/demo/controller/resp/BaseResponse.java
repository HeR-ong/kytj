package com.herong.demo.controller.resp;

import com.alibaba.fastjson.JSON;

/**
 * @author herong22384
 * @date 2020/3/22 14:17
 */
public class BaseResponse {
    private Integer errorNo = 0;
    private String errorInfo;
    private Boolean success;
    private Object result;

    public Integer getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(Integer errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Boolean getSuccess() {
        return 0 == errorNo;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
