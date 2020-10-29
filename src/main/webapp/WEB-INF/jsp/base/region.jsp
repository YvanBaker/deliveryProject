<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/10/18
  Time: 13:56
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
    <script type="text/javascript" src="/js/ocupload/jquery.ocupload-1.1.2.js"></script>
</head>
<script type="text/javascript">
//    function doAdd() {
//        $('#addRegionWindow').window("open");
//    }
//
//    function doView() {
//        alert("修改...");
//    }
//
//    function doDelete() {
//        alert("删除...");
//    }

    //工具栏
    var toolbar = [{
//        id: 'button-edit',
//        text: '修改',
//        iconCls: 'icon-edit',
//        handler: doView
//    }, {
//        id: 'button-add',
//        text: '增加',
//        iconCls: 'icon-add',
//        handler: doAdd
//    }, {
//        id: 'button-delete',
//        text: '删除',
//        iconCls: 'icon-cancel',
//        handler: doDelete
//    }, {
        id: 'button-import',
        text: '导入',
        iconCls: 'icon-redo'
    }];
    // 定义列
    var columns = [[{
        field: 'id',
        checkbox: false,
    }, {
        field: 'provinceName',
        title: '省',
        width: 120,
        align: 'center'
    }, {
        field: 'cityName',
        title: '市',
        width: 120,
        align: 'center'
    }, {
        field: 'areasName',
        title: '区',
        width: 120,
        align: 'center'
    }, {
            field: 'areasId',
            title: '地区编码/分拣编码',
            width: 200,
            align: 'center'
        }]];

    $(function () {
        // 先将body隐藏，再显示，不会出现页面刷新效果
        $("body").css({visibility: "visible"});

        // 收区位表格
        $('#grid').datagrid({
            iconCls: 'icon-forward',
            fit: true,
            border: false,
            rownumbers: true,
            striped: true,
            pageSize:30,
            pageList: [30, 50, 100],
            pagination: true,
            toolbar: toolbar,
            url: "/sys/RegionQuery",
            idField: 'id',
            columns: columns,
            //onDblClickRow: doDblClickRow
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $("#button-import").upload({
            action: 'importFile.do',
            name: 'myFile',
            onComplete: function (data) {
                if (data == '1') {
                    //上传成功
                    $.messager.alert("提示信息", "区域数据导入成功！", "info");
                } else {
                    //失败
                    $.messager.alert("提示信息", "区域数据导入失败！", "warning");
                }
            }
        });
    });
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<%-- 表体--%>
<div region="center" border="false">
    <table id="grid"></table>
</div>
</body>
</html>
