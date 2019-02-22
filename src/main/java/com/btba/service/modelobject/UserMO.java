package com.btba.service.modelobject;

import lombok.Data;

/**
 * @author xiaokuli
 * @date 2019/2/21 - 22:55
 */
@Data
public class UserMO {

    private Integer id;

    private String name;

    private Byte gender;

    private Byte age;

    private String telephone;

    private String registerMode;

    private String thirdPartyId;
}
