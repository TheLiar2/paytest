package com.btba.enums;

import com.btba.error.CommonError;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 23:38
 */
public enum CommonEnum implements CommonError {

    UNKNOWM_WHAT(10001,"未知错误"),

    /*2开头为用户信息*/
    USER_NOT_EXIST(20001,"用户不存在"),
    ;

    private int errCode;
    private String errMsg;

    CommonEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }}
