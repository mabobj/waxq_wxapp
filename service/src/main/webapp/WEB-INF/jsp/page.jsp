<%--
  Created by IntelliJ IDEA.
  User: lovesuper_ao
  Date: 2017/11/16
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <title>${movieTitle}</title>
    <style>
        html,
        body {
            height: 100%;
            margin: 0px;
            padding: 0px;
        }

        iframe {
            width: 100%;
            height: 100%;
        }
        .zd{
            width: 100%;
            height: 20%;
            background-color:#000000;
            z-index: 99999;
            position: fixed;
            bottom: 0px;
            color: #fff;
        }
        .zd >div:first-child{
            width: 60%;
            font-size: 20px;
            float: left;
        }
        .zd >div:last-child{
            width: 40%;
            float: right;
        }
    </style>
</head>

<body>
<iframe id="pagebox" src="${uri}" scrolling="auto" frameborder="0"></iframe>
<div class="zd">
    <div>
        点击播放后请等待5至10秒，随即开始播放。<br>想观看全部VIP视频，请扫描右方二维码
    </div>
    <div>
        <img src="/QRcode/${QRcode}" width="80%">
    </div>
</div>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?9c9dd5067d40240e212d0f8add663cf2";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>

</html>