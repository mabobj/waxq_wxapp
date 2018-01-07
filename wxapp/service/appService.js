function upUserInfo(res) {
  var _app = getApp();
  let _obj = res;
  let _this = this;
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
            _app.globalData.userInfo = res.userInfo

            let _openid = _app.globalData.openid;
            let _userInfo = JSON.stringify(_app.globalData.userInfo);
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
            if (_app.userInfoReadyCallback) {
              _app.userInfoReadyCallback(res)
            }
          }
        })
      } else {
        wx.authorize({
          scope: 'scope.userInfo',
          success() {
            _this.upUserInfo(_obj)
          }
        })
      }

    }
  })

}


module.exports.upUserInfo = upUserInfo