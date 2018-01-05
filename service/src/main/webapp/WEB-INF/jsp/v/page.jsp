<%--
  Created by IntelliJ IDEA.
  User: lovesuper_ao
  Date: 2017/11/18
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <title></title>
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

        .zd {
            width: 100%;
            height: 20%;
            background-color: #000000;
            z-index: 99999;
            position: fixed;
            bottom: 0px;
        }
    </style>
</head>

<body>
<iframe id="pagebox" src="" scrolling="auto" frameborder="0"></iframe>
<div class="zd"></div>
</body>
<script language="JavaScript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

<script type="application/javascript">
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    $("#pagebox").attr("src", "http://api.baiyug.cn/vip/index.php?url=" + getUrlParam("uri"));
</script>

</html>
