package com.btba.service;

import com.btba.error.CommonException;
import com.btba.service.modelobject.UserMO;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 22:54
 */

public interface UserService {

   UserMO getUserById(Integer id) throws CommonException;


}
