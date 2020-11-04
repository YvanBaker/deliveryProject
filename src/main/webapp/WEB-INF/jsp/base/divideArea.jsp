<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/10/18
  Time: 13:50
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
    <script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        /*添加窗口*/
        function doAdd() {
            $('#addSubareaWindow').window("open");
        }

        /*修改*/
        function doEdit() {
            alert("修改...");
        }

        /*删除*/
        function doDelete() {
            alert("删除...");
        }

        /*查询窗口*/
        function doSearch() {
            $('#searchWindow').window("open");
            //清空查询条件
            document.getElementById("searchForm").reset();
        }

        //导出文件
        function doExport() {
            window.location.href = "subareaAction_exportXls.action";
        }

        /*导入*/
        function doImport() {
            alert("导入");
        }

        //工具栏
        var toolbar = [{
            id: 'button-search',
            text: '查询',
            iconCls: 'icon-search',
            handler: doSearch
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-edit',
            text: '修改',
            iconCls: 'icon-edit',
            handler: doEdit
        }, {
            id: 'button-delete',
            text: '删除',
            iconCls: 'icon-cancel',
            handler: doDelete
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'decidedzoneId',
            title: '分拣编号',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.decidedzoneId;
            }
        }, {
            field: 'province',
            title: '省',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.region.provinceName;
            }
        }, {
            field: 'city',
            title: '市',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.region.cityName;
            }
        }, {
            field: 'areas',
            title: '区',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.region.areasName;
            }
        }, {
            field: 'addresskey',
            title: '关键字',
            width: 120,
            align: 'center'
        }, {
            field: 'startnum',
            title: '起始号',
            width: 100,
            align: 'center'
        }, {
            field: 'endnum',
            title: '终止号',
            width: 100,
            align: 'center'
        }, {
            field: 'single',
            title: '单双号',
            width: 100,
            align: 'center',
            formatter: function (data, row, index) {
                /*if (data == 0) {
                    return "单双号";
                }*/
                if (data == 0) {
                    return "单号";
                }
                if (data == 1) {
                    return "双号";
                }
            }
        }, {
            field: 'position',
            title: '位置',
            width: 200,
            align: 'center'
        }]];
        /*主体加载*/
        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 收派标准数据表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: true,
                rownumbers: true,
                striped: true,
                pageSize: 10,
                pageList: [10, 50, 100],
                pagination: true,
                toolbar: toolbar,
                url: "/sys/subareaPageQuery",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加、修改分区
            $('#addSubareaWindow').window({
                title: '添加修改分区',
                width: 600,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            // 查询分区
            $('#searchWindow').window({
                title: '查询分区',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

        });

        function doDblClickRow() {
            alert("双击表格数据...");
        }

        $(function () {
            var msg = "${msg}";
            if (msg != "") {
                $.messager.alert("操作信息", msg, "info");
            }
        })

    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<!-- 添加 修改分区 -->
<div class="easyui-window" title="分区添加修改" id="addSubareaWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="javascript:void(0);" class="easyui-linkbutton" plain="true">保存</a>
            <script type="text/javascript">
                $(function () {
                    $("#save").click(function () {
                        var v = $("#addSubareaForm").form("validate");
                        if (v) {
                            $("#addSubareaForm").submit();
                        }
                    });
                });
            </script>
        </div>
    </div>

    <div style="overflow:auto;padding:5px;" border="false">
        <form id="addSubareaForm"
              action="/sys/subareaFromAdd"
              method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">分区信息</td>
                </tr>
                <tr>
                    <td>选择区域</td>
                    <td>
                        <input class="easyui-combobox" name="region.id"
                               data-options="valueField:'id',
                               textField:'name',
                               mode:'remote',
                               url:'/sys/regionListAjax'"/>
                    </td>
                </tr>
                <tr>
                    <td>关键字</td>
                    <td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>起始号</td>
                    <td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>终止号</td>
                    <td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>单双号</td>
                    <td>
                        <select class="easyui-combobox" name="single" style="width:150px;">
                            <option value="0">单双号</option>
                            <option value="1">单号</option>
                            <option value="2">双号</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>位置信息</td>
                    <td><input type="text" name="position" class="easyui-validatebox" required="true"
                               style="width:250px;"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 查询分区 -->
<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="searchForm">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>省</td>
                    <td><input type="text" name="region.provinceName" value=""/></td>
                </tr>
                <tr>
                    <td>市</td>
                    <td><input type="text" name="region.cityName" value=""/></td>
                </tr>
                <tr>
                    <td>区（县）</td>
                    <td><input type="text" name="region.areasName" value=""/></td>
                </tr>
                <tr>
                    <td>关键字</td>
                    <td><input type="text" name="addresskey" value=""/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
                           data-options="iconCls:'icon-search'">查询</a>
                        <script>
                            $(function () {
                                //工具方法，可以将指定的表单中的输入项目序列号为json数据
                                $.fn.serializeJson = function () {
                                    var serializeObj = {};
                                    var array = this.serializeArray();
                                    $(array).each(function () {
                                        if (serializeObj[this.name]) {
                                            if ($.isArray(serializeObj[this.name])) {
                                                serializeObj[this.name].push(this.value);
                                            } else {
                                                serializeObj[this.name] = [serializeObj[this.name], this.value];
                                            }
                                        } else {
                                            serializeObj[this.name] = this.value;
                                        }
                                    });
                                    return serializeObj;
                                };

                                //绑定事件
                                $("#btn").click(function () {
                                    var p = $("#searchForm").serializeJson();//{id:xx,name:yy,age:zz}
                                    //重新发起ajax请求，提交参数
                                    $("#grid").datagrid("load", p);
                                    //关闭查询窗口
                                    $("#searchWindow").window("close");
                                });
                            });
                        </script>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script>
    /*console.log("ssssssss");
    var url="/sys/regionListAjax";
    $(function () {
        $(".easyui-combobox:eq(0)").click(function () {
            $(this).combobox("reload",url);
        })
    })
*/
</script>
</body>
</html>
