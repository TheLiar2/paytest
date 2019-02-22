package com.btba.viewobject;

import lombok.Data;

/**
 * @author xiaokuli
 * @date 2019/2/22 - 0:07
 */
@Data
public class UserVO {
    private Integer id;

    private String name;

    private Byte gender;

    private Byte age;

    private String telephone;
}
