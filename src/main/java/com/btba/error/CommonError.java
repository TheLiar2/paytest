package com.btba.error;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 23:35
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
