package com.btba.service.Impl;

import com.btba.dao.UserDOMapper;
import com.btba.dataobject.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 23:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserDOMapper userDOMapper;

    @Test
    public void getUserById() {
        Integer id = 1;
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        System.out.println(userDO);
        Assert.assertNotNull(userDO);
    }
}