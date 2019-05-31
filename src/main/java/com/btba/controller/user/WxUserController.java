package com.btba.controller.user;

import com.btba.controller.BaseController;
import com.btba.dataobject.WxUserDO;
import com.btba.enums.CommonEnum;
import com.btba.error.CommonException;
import com.btba.service.WxUserService;
import com.btba.utils.CommonReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;

/**
 * @author xiaokuli
 * @date 2019/5/17 - 10:56
 */
@Controller()
@RequestMapping("/wxuser")
@Slf4j
public class WxUserController extends BaseController {

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("/index")
    public String wxUser(){
        return "111";
    }

    @RequestMapping("/search")
    @ResponseBody
    public CommonReturn search(String wxName) throws CommonException {
        System.out.println(wxName);
        WxUserDO wxUserDO = wxUserService.searchByWxName(wxName);
        if(wxUserDO==null){
            throw new CommonException(CommonEnum.USER_NOT_EXIST);
        }
        return CommonReturn.create("success",wxUserDO);
    }




    @Value("${domain}")
    private String callBack;

    @Value("${appid}")
    private String appid;

    @Value("${appsecret}")
    private String appsecret;

    @Value("${scope}")
    private String scope;


    @RequestMapping("/wxindex")
    public String wxIndex(Model model) throws Exception {
        String oauthUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        String redirect_uri = URLEncoder.encode(callBack, "utf-8");
        oauthUrl =  oauthUrl.replace("APPID",appid).replace("REDIRECT_URI",redirect_uri).replace("SCOPE",scope);
        model.addAttribute("name","theliar");
        model.addAttribute("oauthUrl",oauthUrl);
        return "index";
    }

    @RequestMapping("/back")
    public String back(Model model) throws Exception {
        System.out.println("啊哈哈");
        return "hehe";
    }
}
