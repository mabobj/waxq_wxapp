// pages/parenting/p_show.js
var WxParse = require('../../wxParse/wxParse.js');
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
    title: "不同年龄段宝宝如何正确刷牙？",
    article: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'https://wxapp.saiwangame.com/t.html',
      method: 'GET',
      success: function (res) {
        var article = res.data;
        WxParse.wxParse('article', 'html', article, that, 5);
      }
    })
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

  }
})