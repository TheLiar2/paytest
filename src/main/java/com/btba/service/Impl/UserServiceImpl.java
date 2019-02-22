package com.btba.service.Impl;

import com.btba.dao.UserDOMapper;
import com.btba.dataobject.UserDO;
import com.btba.enums.CommonEnum;
import com.btba.error.CommonException;
import com.btba.service.UserService;
import com.btba.service.modelobject.UserMO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 22:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public UserMO getUserById(Integer id) throws CommonException {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO == null){
            throw new CommonException(CommonEnum.USER_NOT_EXIST);
        }
        UserMO userMO = new UserMO();
        BeanUtils.copyProperties(userDO,userMO);

        return userMO;

    }
}
