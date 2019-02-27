package com.hua.base.entity;

import java.io.Serializable;

/**
 * 返回
 */
public class Result implements Serializable {
    private boolean result;//返回状态
    private String message;//返回结果
    private Object data;//返回数据
    private int resultCode;//返回状态码

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
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

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

}
