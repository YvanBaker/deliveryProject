<%--
  Created by IntelliJ IDEA.
  User: LEO15
  Date: 2020/11/2
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
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
    <script type="text/javascript">
        //查寻方法
        function doView() {
            $('#lookTimeWindow').window("open");
        }

        //增加方法
        function doAdd() {
            $('#addTimeWindow').window("open");
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 1) {
                console.log(rows[0]);
                $('#addTimeForm').form("load", rows[0]);
                if (rowData.status == 'Y') {
                    $("#clock_pda").prop("checked", true);
                }
            }
            $('#addTimeForm').form('clear');
        }

        //批量删除
        function doDelete() {
            //获得选中的行
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                //没有选中，提示
                $.messager.alert("提示信息", "请选择需要删除的记录！", "warning");
            } else {
                var array = new Array();
                //选中了记录,获取选中行的id
                for (var i = 0; i < rows.length; i++) {
                    var id = rows[i].id;
                    array.push(id);
                }
                var ids = array.join(",");
                //发送请求，传递ids参数
                window.location.href = '/sys/timeDelete?ids=' + ids;
            }
        }
        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '作废',
            iconCls: 'icon-cancel',
            handler: doDelete
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'timeName',
            title: '标准名称',
            width: 120,
            align: 'center'
        }, {
            field: 'station',
            title: '派收单位',
            width: 120,
            align: 'center'
        }, {
            field: 'normalWorkTime',
            title: '平时上班时间',
            width: 120,
            align: 'center'
        }, {
            field:'normalOffWorkTime',
            title:'平时下班时间',
            width: 120,
            align:'center'
        }, {
            field:'weekWorkTime',
            title:'周末上班时间',
            width: 120,
            align:'center'
        }, {
            field:'weekOffWorkTime',
            title:'周末下班时间',
            width:120,
            align:'center'
        },{
            field: 'status',
            title: '是否作废',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "1") {
                    return "正常使用"
                } else {
                    return "已作废";
                }
            }
        }, {
            field: 'operator',
            title: '操作人员',
            width: 120,
            align: 'center'
        }, {
            field: 'oStation',
            title: '操作单位',
            width: 120,
            align: 'center'
        }, {
            field: 'oTime',
            title: '操作时间',
            width: 200,
            align: 'center'
        }]];
        //窗口
        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 标准信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,//显示行号
                striped: true,
                pageList: [5,10,20],
                pagination: true,
                toolbar: toolbar,//工具栏
                url: "/sys/findTimeView",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow//指定数据表格的双击行事件
            });
            // 查询标准窗口
            $('#lookTimeWindow').window({
                title: '查询标准',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 添加标准窗口
                $('#addTimeWindow').window({
                title: '添加标准',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 修改取派员窗口
            $('#editTimeWindow').window({
                title: '修改标准',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
        });

        // 双击修改事件处理函数
        function doDblClickRow(rowIndex, rowData) {//{id:xxx,name:xx,}
            $('#editTimeWindow').window({title: "修改派送标准信息"})
            $('#editTimeWindow').window("open");//打开修改窗口
            $("#editTimeForm").form("load", rowData);
            if (rowData.status == '1') {
                $("#clock_pda").prop("checked", true);
            }
        }
//        //校验规则
//        $(function () {
//            var reg = /^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/;
//            $.extend($.fn.validatebox.defaults.rules, {
//                weightAndVolume: {
//                    validator: function (value, param) {
//                        return reg.test(value);
//                    },
//                    message: '输入有误！'
//                }
//            });
//        });

        //添加表单提交
        $(function () {
            $("#save").click(function () {
                //校验表单输入项
                var v = $("#addTimeForm").form("validate");
                if (v) {
                    //校验通过，提交表单
                    $("#addTimeForm").submit();
                }
            });
        });

        //查询表单提交 TODO 查询表单提交
        $(function () {
            $("#find").click(function () {
                //校验表单输入项
                var v = $("#lookTimeForm").form("validate");
                if (v) {
                    $('#lookTimeForm').form({
                        success:function(data1){
                            console.log(data1);
                            $('#grid').datagrid({data:[data1] });
                            $('#lookTimeWindow').window("close");
                        }
                    });
                    //校验通过，提交表单
                    $("#lookTimeForm").submit();
                }
            });
        });

        //操作提示信息
        $(function () {
            var msg = "${msg}";
            if (msg != "") {
                $.messager.alert("操作信息", msg, "info");
            }
        })

        //下拉菜单
        var data=[
            {"text":"早班",'value':'早班'},
            {"text":"早中班",'value':'早中班'},
            {"text":"中班",'value':'中班'}
        ]
        $(function () {
            $('#timeName').combobox({
                valueField:'value',
                textField:'text',
                panelHeight : 'auto',
                data:data,
            });
        })
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<%-- 表格显示--%>
<div region="center" border="false">
    <table id="grid"></table>
</div>

<div class="easyui-window" title="对派收时间进行查询" id="lookTimeWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="find" icon="icon-save" class="easyui-linkbutton" plain="true">查询</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="lookTimeForm" method="post">
            <%--action="/sys/findTimeView"--%>
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询信息</td>
                </tr>
                <!-- 完善收派时间查询 table -->
                <tr>
                    <td>标准名称</td>
                    <td><input type="text" name="timeName" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>派收单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<div class="easyui-window" title="对时间设置添加或者修改" id="addTimeWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <%--添加标准信息--%>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="addTimeForm" action="/sys/addTime" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">派收时间信息</td>
                </tr>
                <!-- 添加派收标准 table -->
                <tr>
                    <td>上班时间名称</td>
                    <%--<td><input type="text" name="timeName" class="easyui-validatebox" required/></td>--%>
                    <td><input id="timeName" name="timeName" class="easyui-combobox" required><td>
                </tr>
                <tr>
                    <td>工作单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>工作日上班时间</td>
                    <td><input type="time" name="normalWorkTime" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>工作日下班时间</td>
                    <td><input type="time" name="normalOffWorkTime" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>双休日上班时间</td>
                    <td><input type="time" name="weekWorkTime" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>双休日下班时间</td>
                    <td><input type="time" name="weekOffWorkTime" class="easyui-validatebox" required/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<!-- 修改窗口 -->
<div class="easyui-window" title="对收派员进行修改" id="editTimeWindow" collapsible="false"
     minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="edit" icon="icon-save" href="javascript:void (0);" class="easyui-linkbutton" plain="true">保存</a>
            <script type="text/javascript">
                $(function () {
                    //绑定事件
                    $("#edit").click(function () {
                        //校验表单输入项
                        var v = $("#editTimeForm").form("validate");
                        if (v) {
                            //校验通过，提交表单
                            $("#editTimeForm").submit();
                        }
                    });
                });
            </script>
        </div>
    </div>
    <%--编辑表单--%>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="editTimeForm" action="/sys/timeEdit"
              method="post">
            <input type="hidden" name="id">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">上班时间信息</td>
                </tr>
                <!-- 修改上班时间标准 table -->
                <tr>
                    <td>上班时间名称</td>
                    <td><input type="text" name="timeName" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>工作单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>工作日上班时间</td>
                    <td><input type="time" name="normalWorkTime" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>工作日下班时间</td>
                    <td><input type="time" name="normalOffWorkTime" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>双休日上班时间</td>
                    <td><input type="time" name="weekWorkTime" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>双休日下班时间</td>
                    <td><input type="time" name="weekOffWorkTime" class="easyui-validatebox" required/></td>
                </tr>
            </table>
        </form>
    </div>
</div>




<script>
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
    $("#find").click(function () {
        var from = $("#lookTimeForm").serializeJson();
        $("#grid").datagrid("load", from);
        $("#lookTimeWindow").window("close");
    })
</script>
</body>
</html>
