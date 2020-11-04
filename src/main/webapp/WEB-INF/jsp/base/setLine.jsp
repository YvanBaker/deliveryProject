<%--
  Created by IntelliJ IDEA.
  User: LEO15
  Date: 2020/10/29
  Time: 9:49
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
    <title>班车设置</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">

        //查寻方法
        function doView() {
            $('#lookLineWindow').window("open");
        }

        //增加方法
        function doAdd() {
            $('#addLineWindow').window("open");
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 1) {
                console.log(rows[0]);
                $('#addLineForm').form("load", rows[0]);
                if (rowData.haspda == 'Y') {
                    $("#clock_pda").prop("checked", true);
                }
            }
            $('#addLineForm').form('clear');
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
                window.location.href = '/sys/lineDelete?ids=' + ids;
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
            field: 'lineType',
            title: '线路类型',
            width: 120,
            align: 'center'
        }, {
            field: 'lineName',
            title: '线路名称',
            width: 120,
            align: 'center'
        }, {
            field: 'licensePlate',
            title: '车牌',
            width: 120,
            align: 'center'
        }, {
            field: 'carModel',
            title: '车型',
            width: 120,
            align: 'center'
        }, {
            field: 'driver',
            title: '司机',
            width: 120,
            align: 'center'
        }, {
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
            field: 'weightControl',
            title: '车载重量',
            width: 120,
            align: 'center'
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

            // 标准班车表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,//显示行号
                striped: true,
                pageList: [5, 10, 20],
                pagination: true,
                toolbar: toolbar,//工具栏
                url: "/sys/lineShow",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow//指定数据表格的双击行事件
            });
            // 查询班车窗口
            $('#lookLinedWindow').window({
                title: '查询标准',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 添加班车窗口
            $('#addLineWindow').window({
                title: '添加标准',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 修改班车窗口
            $('#editLineWindow').window({
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
            $('#editLineWindow').window({title: "修改派送标准信息"})
            $('#editLineWindow').window("open");//打开修改窗口
            $("#editLineForm").form("load", rowData);
            if (rowData.status == '1') {
                $("#clock_pda").prop("checked", true);
            }
        }

        //校验规则
        $(function () {
            var reg = /^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/;
            $.extend($.fn.validatebox.defaults.rules, {
                weightAndVolume: {
                    validator: function (value, param) {
                        return reg.test(value);
                    },
                    message: '输入有误！'
                }
            });
        });

        //添加表单提交
        $(function () {
            $("#save").click(function () {
                //校验表单输入项
                var v = $("#addLineForm").form("validate");
                if (v) {
                    //校验通过，提交表单
                    $("#addLineForm").submit();
                }
            });
        });

        //查询表单提交 TODO 查询表单提交
        $(function () {
            $("#find").click(function () {
                //校验表单输入项
                var v = $("#lookLineForm").form("validate");
                if (v) {
                    $('#lookLineForm').form({
                        success: function (data1) {
                            console.log(data1)
                            $('#grid').datagrid({data: [data1]});
                            $('#lookLineWindow').window("close");
                        }
                    });
                    //校验通过，提交表单
                    $("#lookLineForm").submit();
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
            {"text":"干线(省/直辖市-省/直辖市)",'value':'干线'},
            {"text":"支线(地级市-地级市)",'value':'支线'},
            {"text":"分线(地级市市内)",'value':'分线'},
        ]
        $(function () {
            $('#lineType').combobox({
                valueField:'value',
                textField:'text',
                panelHeight : 'auto',
                data:data,
            });
        })
        var data1=[
            {"text":"拖挂货车",'value':'拖挂货车'},
            {"text":"厢式货车",'value':'厢式货车'},
            {"text":"面包车",'value':'面包车'},
        ]
        //下拉菜单测试
        $(function () {
            $('#carModel').combobox({
                valueField:'value',
                textField:'text',
                panelHeight : 'auto',
                data:data1,
            });
        })

    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<%-- 表格显示--%>
<div region="center" border="false">
    <table id="grid"></table>
</div>

<div class="easyui-window" title="对路线设置添加或者修改" id="addLineWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <%--添加标准信息--%>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="addLineForm" action="/sys/addLine"
              method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">班车信息</td>
                </tr>
                <!-- 添加派收标准 table -->
                <tr>
                    <td>线路类型</td>
                    <%--<td><input type="text" name="lineType" class="easyui-validatebox" required/></td>--%>
                    <td><input id="lineType" name="lineType" class="easyui-combobox" required><td>
                </tr>
                <tr>
                    <td>线路名称</td>
                    <td><input type="text" name="lineName" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>牌照</td>
                    <td><input type="text" name="licensePlate" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>车型</td>
                    <%--<td><input type="text" name="carModel" class="easyui-validatebox" required/></td>--%>
                    <td><input id="carModel" name="carModel" class="easyui-combobox" required><td>
                </tr>
                <tr>
                    <td>司机</td>
                    <td><input type="text" name="driver" class="easyui-validatebox" required></td>
                </tr>
                <tr>
                    <td>车辆载重</td>
                    <td><input type="text" name="weightControl" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<%-- 对line进行查询--%>
<div class="easyui-window" title="对班车进行查询" id="lookLineWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="find" icon="icon-save" class="easyui-linkbutton" plain="true">查询</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="lookLineForm">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询信息</td>
                </tr>
                <!-- 完善收派员查询 table -->
                <tr>
                    <td>线路类型</td>
                    <td><input type="text" name="lineType" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>车型</td>
                    <td><input type="text" name="driver" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>车辆载重</td>
                    <td><input type="text" name="weightControl" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<!-- 修改窗口 -->
<div class="easyui-window" title="对收派员进行修改" id="editLineWindow" collapsible="false"
     minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="edit" icon="icon-save" href="javascript:void (0);" class="easyui-linkbutton" plain="true">保存</a>
            <script type="text/javascript">
                $(function () {
                    //绑定事件
                    $("#edit").click(function () {
                        //校验表单输入项
                        var v = $("#editLineForm").form("validate");
                        if (v) {
                            //校验通过，提交表单
                            $("#editLineForm").submit();
                        }
                    });
                });
            </script>
        </div>
    </div>
    <%--编辑表单--%>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="editLineForm" action="/sys/lineEdit"
              method="post">
            <input type="hidden" name="id">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">班车信息</td>
                </tr>
                <!-- 修改派收标准 table -->
                <tr>
                    <td>线路类型</td>
                    <td><input type="text" name="lineType" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>线路名称</td>
                    <td><input type="text" name="lineName" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>牌照</td>
                    <td><input type="text" name="licensePlate" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>车型</td>
                    <td><input type="text" name="driver" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td><input type="text" name="status" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>车辆载重</td>
                    <td><input type="text" name="weightControl" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
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
        var from = $("#lookLineForm").serializeJson();
        $("#grid").datagrid("load", from);
        $("#lookLineWindow").window("close");
    })
</script>

</body>
</html>
