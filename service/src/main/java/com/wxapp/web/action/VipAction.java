package com.wxapp.web.action;


import com.alibaba.fastjson.JSONObject;
import com.wxapp.frame.util.RedisTools;
import com.wxapp.frame.util.SystemUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/v")
public class VipAction {

    @RequestMapping(value = "/g", method = RequestMethod.GET)
    @ResponseBody
    public String getUrl(String url, String movieTitle) {

        try {
            url = URLDecoder.decode(url, "UTF-8");
            movieTitle = URLDecoder.decode(movieTitle, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Jedis j = RedisTools.getJedis();
        String key = j.get(url);
        if (key != null && !key.equals("")) {
            //model.addAttribute("key", key);
            RedisTools.returnResource(j);
            //return "goBackKey";
            return key;
        }

        key = SystemUtil.randomUUID();
        Map map = new HashMap();
        map.put("movieTitle", movieTitle);
        map.put("uri", url);
        j.set(key, JSONObject.toJSONString(map));

        j.set(url, key);

        //model.addAttribute("key", key);

        RedisTools.returnResource(j);
        return key;
    }

    @RequestMapping(value = "/p", method = RequestMethod.GET)
    public String playMovie(Model model, String key,String c) {
        Jedis j = RedisTools.getJedis();

        String json = j.get(key);
        if (json != null && !json.equals("")) {
            model.addAttribute("movieTitle", JSONObject.parseObject(json).getString("movieTitle"));
            model.addAttribute("uri", "http://vip.youku.com.660r.cn/v.html?uri=" + JSONObject.parseObject(json).getString("uri"));
            //model.addAttribute("uri", "http://api.baiyug.cn/vip/index.php?url=" + JSONObject.parseObject(json).getString("uri"));
        }

        String img = "a.jpg";
        if (c != null) {
            if (c.equals("m")) {
                img = "b.jpg";
            }
        }
        model.addAttribute("QRcode", img);

        RedisTools.returnResource(j);
        return "page";
    }


}
