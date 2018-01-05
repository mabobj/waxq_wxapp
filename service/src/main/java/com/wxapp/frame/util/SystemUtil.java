package com.wxapp.frame.util;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class SystemUtil {
    /*public static void setSession(String key, Object obj) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute(key, obj);
    }

    public static Object getSession(String key) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        return session.getAttribute(key);
    }

    public static Map getUserMap() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Map map = (Map) session.getAttribute("USER_MAP");
        if (map == null) {
            return new HashMap();
        }
        return map;
    }*/


    public static int getRandom(int start, int end) {
        if (start > end || start < 0 || end < 0) {
            return -1;
        }
        return (int) (Math.random() * (end - start + 1)) + start;
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * yyyy-MM-dd HH:ss:mm
     *
     * @return
     */
    public static SimpleDateFormat sdfDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormat sdfDate() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }


    public static void main(String[] args) {

        for (int i = 0; i < 6; i++) {
            System.out.println(SystemUtil.getRandom(100000, 999999));
        }
    }
}
