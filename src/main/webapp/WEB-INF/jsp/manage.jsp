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
    <div data-options="region:'north' " style="height:100px;"></div>

    <%--左边--%>
    <div data-options="region:'west',title:'系统菜单'" style="width:200px;">
        <div class="easyui-accordion" data-options="fit:true">
            <div title="基础功能">
                <ul id="ztree1" class="ztree"></ul>
            </div>
            <div title="系统管理" id="ztree2" class="ztree"> </div>
        </div>
    </div>

    <%--中间--%>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <div id="tt" class="easyui-tabs" data-options="fit:true">
            <%-- <div data-options="closable:true,iconCls:'icon-edit'" title="面版1">Aaa</div>--%>
        </div>
    </div>
    <%--尾部--%>
    <div data-options="region:'south',title:'South Title'" style="height:100px;"></div>
</div>
<script>
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
            $.fn.zTree.init($("#ztree1"), setting1, data);},
            'json');
    });

    /*系统管理菜单*/
    $(function () {
        var setting2 ={
            data:{
                simpleData: {
                    enable: true
                }
            },
            callback:{
                onClick:function (a,b,treeNode) {
                    var page=treeNode.page;
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
