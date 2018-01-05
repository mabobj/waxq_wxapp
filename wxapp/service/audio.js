//切換頁面重播
var timer
module.exports.timer = timer

function reload_play(_this,_app) {
  const backgroundAudioManager = wx.getBackgroundAudioManager();
  var _type;
  var _title;

  if (backgroundAudioManager.paused == false) {//正在播放
    _title = _app.globalData.play_title;
    _type = "/assets/img/stop.png";

    this.timer = setInterval((function () {
      this.time_start(_this);
    }).bind(this), 1000);


  } else {
    if (typeof (backgroundAudioManager.paused) == "undefined") {//播放器已停止
      console.log("undefined");
    } else {//正在暂停
      _title = _app.globalData.play_title;
      _type = "/assets/img/play.png";
    }
  }

  _this.setData({
    play_type: _type,
    play_title: _title
  });
}

module.exports.reload_play = reload_play




//播放时间，启动
function time_start (_this) {
  const backgroundAudioManager = wx.getBackgroundAudioManager()
  var _duration = this.secondToDate(parseInt(backgroundAudioManager.duration));
  var _currentTime = this.secondToDate(parseInt(backgroundAudioManager.currentTime));

  console.log(_duration + " == " + _currentTime);
  _this.setData({
    duration: _duration,
    currentTime: _currentTime
  });
}

module.exports.time_start = time_start

//播放时间，停止
function time_stop(_this) {
  this.timer & clearInterval(this.timer);
}

module.exports.time_stop = time_stop


//播放时间格式化
function secondToDate (msd) {
  var time = msd
  if (null != time && "" != time) {
    if (time > 60) {
      time = parseInt(time / 60.0) + ":" + parseInt((parseFloat(time / 60.0) -
        parseInt(time / 60.0)) * 60) + "";
    }
    else {
      time = parseInt(time) + "";
    }
  }
  return time;
}

module.exports.secondToDate = secondToDate