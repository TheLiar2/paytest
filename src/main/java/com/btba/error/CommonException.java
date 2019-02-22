package com.btba.error;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 23:47
 */
public class CommonException extends Exception implements CommonError{

    private CommonError commonError;

    public CommonException(CommonError commonError) {
        this.commonError = commonError;
    }

    public CommonException(CommonError commonError,String errMsg) {
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return this.commonError.setErrMsg(errMsg);
    }
}
