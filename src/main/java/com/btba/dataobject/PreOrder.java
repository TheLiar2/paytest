package com.btba.dataobject;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xiaokuli
 * @date 2019/5/30 - 16:39
 */
@Data
@XmlRootElement(name="xml")
public class PreOrder {

    private String appid;
    private String body;
    private String mch_id;
    private String mch_secret;
    private String notify_url;
    private String out_trade_no;
    private Integer total_fee;
    private String nonce_str;
    private String trade_type;
    private String spbill_create_ip;
    private String sign;

    private String code_url;
    private String return_code;
}
