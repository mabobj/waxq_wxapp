package com.wxapp.service.iface;

public interface UserIface {
    public void setUserInfo(String encryptedData, String sessionKey, String iv);

    public void setUserInfo(String openId,String userInfoByJson);
}
