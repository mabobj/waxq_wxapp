// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    linkcss: "link",
    play_type:"/assets/img/stop.png",
    play_title: "",
    dataList: [
      { new: false, img: "/assets/img/img001.jpg", title: "猪八戒背媳妇1", reciter: "千寻妈妈", time: "6分30秒", src:"http://ws.stream.qqmusic.qq.com/M500001VfvsJ21xFqb.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=6292F51E1E384E061FF02C31F716658E5C81F5594D561F2E88B854E81CAAB7806D5E4F103E55D33C16F3FAC506D1AB172DE8600B37E43FAD&fromtag=46"},
      { new: true, img: "/assets/img/img001.jpg", title: "猪八戒背媳妇2", reciter: "千寻妈妈", time: "6分30秒" },
      { new: true, img: "/assets/img/img001.jpg", title: "猪八戒背媳妇3", reciter: "千寻妈妈", time: "6分30秒" },
      { new: true, img: "/assets/img/img001.jpg", title: "猪八戒背媳妇4", reciter: "千寻妈妈", time: "6分30秒" },
      { new: true, img: "/assets/img/img001.jpg", title: "猪八戒背媳妇5", reciter: "千寻妈妈", time: "6分30秒" },
      { new: true, img: "/assets/img/img001.jpg", title: "猪八戒背媳妇6", reciter: "千寻妈妈", time: "6分30秒" }
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


  navClick: function (e) {
    console.log(e.target);

    if (e.target.id == "nav1") {
      this.data.linkcss = "link";
    } else if (e.target.id == "nav2") {
      this.data.linkcss = "link nav2";
    }

    this.setData({
      linkcss: this.data.linkcss
    })
  },

  play_btn: function(e){
    console.log(this.data.dataList[e.currentTarget.dataset.item]);
    const play_data = this.data.dataList[e.currentTarget.dataset.item];
    const backgroundAudioManager = wx.getBackgroundAudioManager()

    backgroundAudioManager.title = play_data.title;
    backgroundAudioManager.epname = play_data.title;
    backgroundAudioManager.singer = play_data.reciter;
    backgroundAudioManager.coverImgUrl = play_data.img;
    backgroundAudioManager.src = play_data.src;



    this.setData({
      play_type: "/assets/img/play.png",
      play_title: play_data.title
    })
  },

  play_stop:function(e){
    const backgroundAudioManager = wx.getBackgroundAudioManager();
    var _type;
    var self = this; 

    backgroundAudioManager.onStop(function(){
      console.log("背景音乐停止了");
      self.setData({
        play_title:""
      })
    });

    if (backgroundAudioManager.paused==false){
      backgroundAudioManager.pause();
      _type = "/assets/img/stop.png";
    }else{
      //播放器已停止
      if (typeof (backgroundAudioManager.paused) == "undefined"){
        console.log("undefined");
      }else{
        //暂停状态下重启
        backgroundAudioManager.play();
        _type = "/assets/img/play.png";
      }
    }


    this.setData({
      play_type: _type
    })
  }
})