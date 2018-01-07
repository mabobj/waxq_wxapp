//app.js
var _user = require('/service/appService.js');

App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        console.log("wx.login success res => " + JSON.stringify(res));
        if (res.code) {
          //发起网络请求
          wx.request({
            url: 'https://wxapp.saiwangame.com/user/getOid',
            data: {
              code: res.code
            },
            success: res => {
              console.log("获取OPENID => " + JSON.stringify(res));
              this.globalData.openid = res.data.openid;

              if (this.userLoginReadyCallback) {
                this.userLoginReadyCallback(res)
              }

            }
          })
        } else {
          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    })

    this.userLoginReadyCallback = res => {


      // 获取并更新用户信息
      _user.upUserInfo();

    }
  },
  globalData: {
    userInfo: null,
    play_title: "",
    openid: ""
  }
})