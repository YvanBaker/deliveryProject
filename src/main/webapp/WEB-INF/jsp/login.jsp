<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆页面</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/style_grey.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <style>
        input[type=text] {
            width: 80%;
            height: 25px;
            font-size: 12pt;
            font-weight: bold;
            margin-left: 45px;
            padding: 3px;
            border-width: 0;
        }

        input[type=password] {
            width: 80%;
            height: 25px;
            font-size: 12pt;
            font-weight: bold;
            margin-left: 45px;
            padding: 3px;
            border-width: 0;
        }

        #loginform\:codeInput {
            margin-left: 1px;
            margin-top: 1px;
        }

        #loginform\:vCode {
            margin: 0px 0 0 60px;
            height: 34px;
        }
    </style>
    <%--<script type="text/javascript">
        if(window.self != window.top){
            window.top.location = window.location;
        }
    </script>--%>
</head>
<body>
<div
        style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: -280px;">
    <img src="/img/logo.png" style="border-width: 0; margin-left: 0;height: 77px;
    width: 287px;" />
    <span style="float: right; margin-top: 35px; color: #488ED5;">新BOS系统以宅急送开发的ERP系统为基础，致力于便捷、稳定等方面的客户体验</span>
</div>
<div class="main-inner" id="mainCnt"
     style="width: 900px; height: 480px; overflow: hidden; position: absolute; left: 50%; top: 50%; margin-left: -450px; margin-top: -220px; background-image: url('/img/login1.jpg')">
    <div id="loginBlock" class="login"
         style="margin-top: 186px;height: 255px;margin-right: 20px;">
        <div class="loginFunc">
            <div id="lbNormal" class="loginFuncMobile">员工登录</div>
        </div>
        <div class="loginForm">
            <form id="loginform" name="loginform" method="post" class="niceform"
                  action="/admin/login">
                <div id="idInputLine" class="loginFormIpt showPlaceholder"
                     style="margin-top: 5px;">
                    <label for="idInput" class="placeholder" id="idPlaceholder">帐号：</label>
                    <input id="loginform:idInput" type="text" name="userName"
                           class="loginFormTdIpt" maxlength="50" />
                </div>
                <div class="forgetPwdLine"></div>
                <div id="pwdInputLine" class="loginFormIpt showPlaceholder">
                    <input id="loginform:pwdInput" class="loginFormTdIpt" type="password"
                           name="password" autocomplete />
                    <label for="pwdInput" class="placeholder" id="pwdPlaceholder">密码：</label>
                </div>
                <div class="loginFormIpt loginFormIptWiotTh"
                     style="margin-top:58px;">
                    <div id="codeInputLine" class="loginFormIpt showPlaceholder"
                         style="margin-left:0px;margin-top:-40px;width:50px;">
                        <input id="loginform:codeInput" style="width: 50px;" class="loginFormTdIpt" type="text"
                               name="checkcode" title="请输入验证码" />
                        <img id="loginform:vCode" src="/validatecode.jsp"
                             onclick="javascript:document.getElementById('loginform:vCode').src='../validatecode.jsp?'+Math.random();" />
                    </div>
                    <a onclick="document.forms[0].submit()" id="loginform:j_id19" name="loginform:j_id19">
						<button
                                id="loginform:loginBtn" class="btn btn-login"
                                style="margin-top:-32px;">登录</button>
                    </a>
                    <span style="color: #ff0000" >
                        ${msg}
                    </span>
                </div>
                <div>
                </div>
            </form>
        </div>
    </div>
</div>
<div
        style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: 258px;">
    <span style="color: #488ED5;">Powered By www.delivery.cn</span><span
        style="color: #488ED5;margin-left:10px;">推荐浏览器（右键链接-目标另存为）：<a
        href="http://download.firefox.com.cn/releases/full/23.0/zh-CN/Firefox-full-latest.exe">Firefox</a>
		</span><span style="float: right; color: #488ED5;">程心服务BOS系统</span>
</div>
<script>
    var manager='${msg2}';
    if (manager!=''){
            $.messager.show({
                title: "提示信息",
                msg:manager ,
                timeout: 3000,
                showTimer: "slide"
            },1000)
    }

</script>
</body>
</html>