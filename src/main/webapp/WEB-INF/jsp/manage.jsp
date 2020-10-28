<%@ page import="com.delivery.entity.Admin" %>
<%--Created by IntelliJ IDEA.
  User: admin
  Date: 2020/10/17
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <!-- 导入ztree类库 -->
    <link rel="stylesheet"
          href="/js/ztree/zTreeStyle.css"
          type="text/css"/>
    <script
            src="/js/ztree/jquery.ztree.all-3.5.js"
            type="text/javascript"></script>

</head>
<body>
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
    <%-- 头部--%>
    <div data-options="region:'north' " style="height:100px; ">
        <div style="height: 100px;width: 100%; background-size:100% 100%; background-image: url('/img/head11.png')">

            <div style="position: absolute;right: 50px;bottom:8px;">
                <a href="javascript:void(0);" class="easyui-menubutton"
                   data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
            </div>

            <div id="layout_north_kzmbMenu" style=" width: 100px;display: none;">
                <div onclick="editPassword()">修改密码</div>
                <div onclick="connectUs()">联系我们</div>
                <div onclick="exitLogin()">退出登录</div>
            </div>
        </div>
    </div>

    <%--左边--%>
    <div data-options="region:'west',title:'系统菜单'" style="width:200px;">
        <div class="easyui-accordion" data-options="fit:true">
            <div title="基础功能">
                <ul id="ztree1" class="ztree"></ul>
            </div>
            <div title="系统管理" id="ztree2" class="ztree"></div>
        </div>
    </div>
    <%--中间--%>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <div id="tt" class="easyui-tabs" data-options="fit:true">
            <%-- <div data-options="closable:true,iconCls:'icon-edit'" title="面版1">Aaa</div>--%>
        </div>
    </div>
    <!--修改密码窗口-->
    <div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true"
         closed="true" resizable="false"
         maximizable="false" icon="icon-save" style="width: 350px; height: 200px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <form id="editPasswordForm">
                    <table cellpadding=3>
                        <tr>
                            <td>新密码：</td>
                            <td><input id="txtNewPass" type="Password" class="txt01 easyui-validatebox"
                                       required="true" data-options="validType:'length[4,8]'"
                            /></td>
                        </tr>
                        <tr>
                            <td>确认密码：</td>
                            <td><input id="txtRePass" type="Password" class="txt01 easyui-validatebox"
                                       required="true" data-options="validType:'length[4,8]'"
                            /></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 34px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)">确定</a>
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
    <%--尾部--%>
    <div data-options="region:'south'">
    </div>
</div>
</div>
<script>
    /** 控制面板**/
    /*修改密码*/
    function editPassword() {
        $("#editPwdWindow").window("open");
        //var item = sessionStorage.getItem("admin");
        //console.log(item);
    }
    //为“确定”按钮绑定事件
    $("#btnEp").click(function () {
        //进行表单校验
        var v = $("#editPasswordForm").form("validate");//对应表单中的所有输入框进行校验
        if (v) {//表单校验通过
            //判断两次输入是否一致
            var v1 = $("#txtNewPass").val();
            var v2 = $("#txtRePass").val();
            if (v1 == v2) {
                //输入一致，发送ajax请求，修改当前用户的密码
                var url = "/admin/admin_editPassword";
                $.post(url, {"password": v1}, function (data) {
                    if (data == '1') {
                        //修改密码成功
                        $.messager.alert("提示信息", "密码修改成功！", "info");
                    } else {
                        //修改失败
                        $.messager.alert("提示信息", "密码修改失败！", "warning");
                    }
                    //关闭修改密码的窗口
                    $("#editPwdWindow").window("close");
                });
            } else {
                //输入不一致，提示用户输入不一致
                $.messager.alert("提示信息", "两次输入密码不一致！", "warning");
            }
        }
    });
    $("#btnCancel").click(function () {
        $("#editPwdWindow").window("close");
    });
    /*联系我们*/
    /*退出登录*/
    function exitLogin() {
        document.location.href = "/admin/exitLogin";
    };

    /*基础功能菜单列表*/
    $(function () {
        var setting1 = {
            data: {
                simpleData: {
                    enable: true
                    //启用简单json数据描述节点数据
                }
            },
            callback: {//绑定事件
                onClick: function (a, b, treeNode) {
                    var page = treeNode.page;
                    if (page != undefined) {//需要打开选项卡
                        //判断当前选项卡是否已经打开
                        var e = $("#tt").tabs("exists", treeNode.name);
                        if (e) {
                            //已经打开
                            $("#tt").tabs("select", treeNode.name);
                        } else {
                            $("#tt")
                                .tabs(
                                    "add",
                                    {
                                        title: treeNode.name,
                                        content: '<iframe frameborder="0" width="100%" height="100%" src="' + page + '"/>',
                                        closable: true,
                                        iconCls: 'icon-edit'
                                    });
                        }
                    }
                }
            }
        };//设置ztree相关的属性
        //发送ajax请求获取json数据构造ztree
        var url = "/json/menu.json";
        $.get(
            url,
            {},
            function (data) {
                //创建ztree
                $.fn.zTree.init($("#ztree1"), setting1, data);
            },
            'json');
    });

    /*系统管理菜单*/
    $(function () {
        var setting2 = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (a, b, treeNode) {
                    var page = treeNode.page;
                    if (page != undefined) {
                        var e = $("#tt").tabs("exists", treeNode.name);
                        if (e) {
                            //已经打开
                            $("#tt").tabs("select", treeNode.name);
                        } else {
                            $("#tt").tabs(
                                "add",
                                {
                                    title: treeNode.name,
                                    content: '<iframe frameborder="0" width="100%" height="100%" src="' + page + '"/>',
                                    closable: true,
                                    iconCls: 'icon-edit'
                                });
                        }
                    }
                }
            }
        };
        //发送ajax请求获取json数据构造ztree
        var url = "/json/menu2.json";
        $.get(url, {}, function (data) {
            //创建ztree
            $.fn.zTree.init($("#ztree2"), setting2, data);
        }, 'json');
    })


    /*function doAdd() {
        $("#tt").tabs('add', {
            title: "面版1",
            content: '<iframe frameborder="0" width="80%" height="50%" src="loginView"></iframe>',
            iconCls: 'icon-edit',
            closable: true
        })
    }*/

    /*欢迎界面*/
    <%Admin admin=(Admin) session.getAttribute("admin");%>
    var username = "<%=admin.getUserName()%>"
    $(
        $.messager.show({
            title: "欢迎信息",
            msg: "欢迎" + username + "登录管理系统",
            timeout: 3000,
            showTimer: "slide"
        })
    )
</script>
</body>
</html>
