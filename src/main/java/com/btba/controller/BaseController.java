package com.btba.controller;

import com.btba.enums.CommonEnum;
import com.btba.error.CommonException;
import com.btba.utils.CommonReturn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 22:12
 */

public class BaseController {

    /*抛出异常时返回页面结果经过此handler返回CommonReturn格式,否则页面很不友好*/
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object ExceptionHandler(HttpServletRequest request,Exception ex){
        Map<String,Object> resultData = new HashMap<>();
        if(ex instanceof CommonException){
            CommonException e = (CommonException) ex;
            resultData.put("errCode",e.getErrCode());
            resultData.put("errMsg",e.getErrMsg());
        }else{
            resultData.put("errCode", CommonEnum.UNKNOWM_WHAT.getErrCode());
            resultData.put("errMsg",CommonEnum.UNKNOWM_WHAT.getErrMsg());
        }
        return CommonReturn.create("fail",resultData);

    }
}
