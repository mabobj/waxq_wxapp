// pages/parenting/parenting.js
var _audio = require('../../service/audio.js')
var appInstance = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    play_type: "/assets/img/stop.png",
    play_title: "",
    duration: "",
    currentTime: "",
    dataList: [
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _this = this;

    wx.request({
      url: 'https://wxapp.saiwangame.com/parenting/list',
      method: 'GET',
      success: function (res) {
        console.log(res.data);
        _this.setData({
          dataList:res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    console.log("p = onReady");
    _audio.reload_play(this, appInstance);
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    console.log("p = onHide");
    _audio.time_stop(this);
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },


  //暂停启动
  play_stop: function (e) {
    const backgroundAudioManager = wx.getBackgroundAudioManager();
    var _type;
    var self = this;

    backgroundAudioManager.onStop(function () {
      console.log("背景音乐停止了");
      _audio.time_stop(this);
      appInstance.globalData.play_title = "";
      self.setData({
        play_title: ""
      });
    });

    if (backgroundAudioManager.paused == false) {
      //暂停
      backgroundAudioManager.pause();
      _audio.time_stop(this);
      _type = "/assets/img/stop.png";
    } else {
      //播放器已停止
      if (typeof (backgroundAudioManager.paused) == "undefined") {
        console.log("undefined");
      } else {
        //暂停状态下重启
        backgroundAudioManager.play();

        _audio.timer = setInterval((function () {
          _audio.time_start(this);
        }).bind(this), 1000);

        _type = "/assets/img/play.png";
      }
    }


    this.setData({
      play_type: _type
    })
  }

})