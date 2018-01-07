package com.wxapp.web.action;

import com.wxapp.frame.base.BaseAction;
import com.wxapp.frame.util.HttpTools;
import com.wxapp.service.iface.UserIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

    @Autowired
    private UserIface excelService;


    @RequestMapping(value = "/getOid", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String getOpenId(String code, Model model) {
        HttpTools ht = new HttpTools();
        ht.setUrl("https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=wx23df0ea82b2e1eb0" +
                "&secret=86c3ad2a062499d41b0befcab85fd11b" +
                "&js_code=" + code +
                "&grant_type=authorization_code");
        return ht.getInnerHTML();
    }

    @RequestMapping(value = "/setUserInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String setUserInfo(String openId, String userInfoByJson, Model model) {
        logger.debug("setUserInfo => " + openId + " == " + userInfoByJson);
        excelService.setUserInfo(openId, userInfoByJson);
        return "ok";
    }

}
