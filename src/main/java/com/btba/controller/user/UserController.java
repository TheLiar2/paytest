package com.btba.controller.user;

import com.btba.controller.BaseController;
import com.btba.enums.CommonEnum;
import com.btba.error.CommonException;
import com.btba.service.UserService;
import com.btba.service.modelobject.UserMO;
import com.btba.utils.CommonReturn;
import com.btba.viewobject.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaokuli
 * @date 2019/2/22 - 0:04
 */
@Controller("/user")
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturn get(@RequestParam("id")Integer id) throws CommonException {
        UserMO userMO = userService.getUserById(id);
        if(userMO==null){
            throw new CommonException(CommonEnum.USER_NOT_EXIST);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userMO,userVO);
        return CommonReturn.create(userVO);
    }
}
