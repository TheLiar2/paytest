package com.btba.utils;

import lombok.Data;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 23:29
 */
@Data
public class CommonReturn {
    private String status;
    private Object data;

    public static CommonReturn create(Object result){
        CommonReturn commonReturn = create("success", result);
        return commonReturn;
    }

    public static CommonReturn create(String status,Object result){
        CommonReturn commonReturn = new CommonReturn();
        commonReturn.setStatus(status);
        commonReturn.setData(result);
        return commonReturn;
    }
}
