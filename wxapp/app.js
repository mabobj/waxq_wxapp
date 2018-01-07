//app.js
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


      // 获取用户信息
      wx.getSetting({
        success: res => {
          console.log("wx.getSetting success res => " + JSON.stringify(res));
          if (res.authSetting['scope.userInfo']) {
            // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
            wx.getUserInfo({
              success: res => {
                console.log("wx.getUserInfo success res => " + JSON.stringify(res));
                // 可以将 res 发送给后台解码出 unionId
                this.globalData.userInfo = res.userInfo

                let _openid = this.globalData.openid;
                let _userInfo = JSON.stringify(this.globalData.userInfo);
                console.log("_userInfo => " + _openid)
                wx.request({
                  url: "https://wxapp.saiwangame.com/user/setUserInfo",
                  method: "POST",
                  header: { "Content-Type": "application/x-www-form-urlencoded;charset=utf-8" },
                  data: {
                    openId: _openid,
                    userInfoByJson: _userInfo
                  }
                })
                // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
                // 所以此处加入 callback 以防止这种情况
                if (this.userInfoReadyCallback) {
                  this.userInfoReadyCallback(res)
                }
              }
            })
          } else {
            wx.authorize({
              scope: 'scope.userInfo',
              success() {

              }
            })
          }

        }
      })

    }
  },
  globalData: {
    userInfo: null,
    play_title: "",
    openid: ""
  }
})