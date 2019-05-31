package com.btba.service.Impl;

import com.btba.dao.WxUserDOMapper;
import com.btba.dataobject.WxUserDO;
import com.btba.enums.CommonEnum;
import com.btba.error.CommonException;
import com.btba.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaokuli
 * @date 2019/5/17 - 11:23
 */
@Slf4j
@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDOMapper wxUserDOMapper;


    @Override
    public WxUserDO selectByPrimaryKey(Integer id) throws CommonException {
        WxUserDO wxUserDO = wxUserDOMapper.selectByPrimaryKey(id);
        if(wxUserDO==null){
            throw new CommonException(CommonEnum.USER_NOT_EXIST);
        }
        return wxUserDO;
    }

    @Override
    public WxUserDO searchByWxName(String wxName) throws CommonException {
        WxUserDO wxUserDO = wxUserDOMapper.searchByWxName(wxName);
        if(wxUserDO==null){
            throw new CommonException(CommonEnum.USER_NOT_EXIST);
        }
        return wxUserDO;
    }
}
