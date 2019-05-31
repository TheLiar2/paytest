package com.btba.service;

import com.btba.dataobject.WxUserDO;
import com.btba.error.CommonException;

/**
 * @author xiaokuli
 * @date 2019/5/17 - 11:00
 */
public interface WxUserService {
    WxUserDO selectByPrimaryKey(Integer id) throws CommonException;

    WxUserDO searchByWxName(String wxName) throws CommonException;
}
