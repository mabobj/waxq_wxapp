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
      { img: "https://wxapp.saiwangame.com/assets/img/text1.png", title: "不同年龄段宝宝如何正确刷牙？", synopsis: "预防宝宝蛀牙一定要让宝宝坚持刷牙，不同年龄段的宝宝，刷牙方式也有所不同，你知道不同年龄段的宝宝该如何正确刷牙吗？39育儿编辑为大家总结了宝宝成长4个阶段的刷牙时间表，家长们可以按照这个时间表来给宝宝刷牙，让宝宝的牙齿健康又漂亮。" },
      { img: "/assets/img/img001.jpg", title: "给宝宝洗手不是小事，洗错了反而会得病", synopsis: "手是致病菌重要的传播媒介，经常洗手可以预防各种消化道疾病。因此，很多家长会注重给孩子洗手。但洗手也讲究方法，洗得不对可能反而有损健康。" },
      { img: "/assets/img/img001.jpg", title: "不同年龄段宝宝如何正确刷牙？", synopsis: "预防宝宝蛀牙一定要让宝宝坚持刷牙，不同年龄段的宝宝，刷牙方式也有所不同，你知道不同年龄段的宝宝该如何正确刷牙吗？39育儿编辑为大家总结了宝宝成长4个阶段的刷牙时间表，家长们可以按照这个时间表来给宝宝刷牙，让宝宝的牙齿健康又漂亮。" },
      { img: "/assets/img/img001.jpg", title: "不同年龄段宝宝如何正确刷牙？", synopsis: "预防宝宝蛀牙一定要让宝宝坚持刷牙，不同年龄段的宝宝，刷牙方式也有所不同，你知道不同年龄段的宝宝该如何正确刷牙吗？39育儿编辑为大家总结了宝宝成长4个阶段的刷牙时间表，家长们可以按照这个时间表来给宝宝刷牙，让宝宝的牙齿健康又漂亮。" },
      { img: "/assets/img/img001.jpg", title: "不同年龄段宝宝如何正确刷牙？", synopsis: "预防宝宝蛀牙一定要让宝宝坚持刷牙，不同年龄段的宝宝，刷牙方式也有所不同，你知道不同年龄段的宝宝该如何正确刷牙吗？39育儿编辑为大家总结了宝宝成长4个阶段的刷牙时间表，家长们可以按照这个时间表来给宝宝刷牙，让宝宝的牙齿健康又漂亮。" }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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