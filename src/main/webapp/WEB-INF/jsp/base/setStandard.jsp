<%--
  Created by IntelliJ IDEA.
  User: LEO15
  Date: 2020/10/28
  Time: 11:29
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
    <title>设置标准</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">

        //查寻方法
        function doView() {
            $('#lookStandardWindow').window("open");
        }

        //增加方法
        function doAdd() {
            $('#addStandardWindow').window("open");
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 1) {
                console.log(rows[0]);
                $('#addStandardForm').form("load", rows[0]);
                if (rowData.haspda == 'Y') {
                    $("#clock_pda").prop("checked", true);
                }
            }
            $('#addStandardForm').form('clear');
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
                window.location.href = '/sys/standardDelete?ids=' + ids;
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
            field: 'standardName',
            title: '标准名称',
            width: 120,
            align: 'center'
        }, {
            field: 'minWeight',
            title: '最小重量',
            width: 120,
            align: 'center'
        }, {
            field: 'maxWeight',
            title: '最大重量',
            width: 120,
            align: 'center'
        }, {
            field:'minVolume',
            title:'最小体积',
            width:120,
            align:'center'
        }, {
            field:'maxVolume',
            title:'最大体积',
            width:120,
            align:'center'
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
                url: "/sys/findStandardView",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow//指定数据表格的双击行事件
            });
            // 查询标准窗口
            $('#lookStandardWindow').window({
                title: '查询标准',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 添加标准窗口
            $('#addStandardWindow').window({
                title: '添加标准',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 修改取派员窗口
            $('#editStandardWindow').window({
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
            $('#editStandardWindow').window({title: "修改派送标准信息"})
            $('#editStandardWindow').window("open");//打开修改窗口
            $("#editStandardForm").form("load", rowData);
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
                var v = $("#addStandardForm").form("validate");
                if (v) {
                    //校验通过，提交表单
                    $("#addStandardForm").submit();
                }
            });
        });

        //查询表单提交 TODO 查询表单提交
        $(function () {
            $("#find").click(function () {
                //校验表单输入项
                var v = $("#lookStandardForm").form("validate");
                if (v) {
                    $('#lookStandardForm').form({
                        success:function(data1){
                            console.log(data1)
                            $('#grid').datagrid({data:[data1] });
                            $('#lookStandardWindow').window("close");
                        }
                    });
                    //校验通过，提交表单
                    $("#lookStandardForm").submit();
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


    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<%-- 表格显示--%>
<div region="center" border="false">
    <table id="grid"></table>
</div>

<div class="easyui-window" title="对派收标准进行添加或者修改" id="addStandardWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <%--添加标准信息--%>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="addStandardForm" action="/sys/addStandard"
              method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">派收标准信息</td>
                </tr>
                <!-- 添加派收标准 table -->
                <tr>
                    <td>标准名称</td>
                    <td><input type="text" name="standardName" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>最小重量</td>
                    <td><input type="text" name="minWeight" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
                <tr>
                    <td>最大重量</td>
                    <td><input type="text" name="maxWeight" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
                <tr>
                    <td>最小体积</td>
                    <td><input type="text" name="minVolume" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
                <tr>
                    <td>最大体积</td>
                    <td><input type="text" name="maxVolume" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<div class="easyui-window" title="对派收标准进行查询" id="lookStandardWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="find" icon="icon-save" class="easyui-linkbutton" plain="true">查询</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="lookStandardForm" action="/sys/findStandardView"
              method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询信息</td>
                </tr>
                <!-- 完善收派标准查询 table -->
                <tr>
                    <td>标准名称</td>
                    <td><input type="text" name="standardName" class="easyui-validatebox" required/></td>
                </tr>
                <%--<tr>--%>
                    <%--<td>标准状态</td>--%>
                    <%--<td><input type="text" name="status" class="easyui-validatebox" required/></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>最小重量</td>--%>
                    <%--<td><input type="text" name="minWeight" class="easyui-validatebox" required/></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>最大重量</td>--%>
                    <%--<td><input type="text" name="maxWeight" class="easyui-validatebox" required/></td>--%>
                <%--</tr>--%>
            </table>
        </form>
    </div>
</div>

<!-- 修改窗口 -->
<div class="easyui-window" title="对收派员进行修改" id="editStandardWindow" collapsible="false"
     minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="edit" icon="icon-save" href="javascript:void (0);" class="easyui-linkbutton" plain="true">保存</a>
            <script type="text/javascript">
                $(function () {
                    //绑定事件
                    $("#edit").click(function () {
                        //校验表单输入项
                        var v = $("#editStandardForm").form("validate");
                        if (v) {
                            //校验通过，提交表单
                            $("#editStandardForm").submit();
                        }
                    });
                });
            </script>
        </div>
    </div>
    <%--编辑表单--%>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="editStandardForm" action="/sys/updateStandard"
              method="post">
            <input type="hidden" name="id">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派标准信息</td>
                </tr>
                <tr>
                    <td>标准名称</td>
                    <td><input type="text" name="standardName" class="easyui-validatebox" required/></td>
                </tr>
                <tr>
                    <td>最小重量</td>
                    <td><input type="text" name="minWeight" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
                <tr>
                    <td>最大重量</td>
                    <td><input type="text" name="maxWeight" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
                <tr>
                    <td>最小体积</td>
                    <td><input type="text" name="minVolume" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
                <tr>
                    <td>最大体积</td>
                    <td><input type="text" name="maxVolume" class="easyui-validatebox" required
                               data-options="validType:'weightAndVolume'"/></td>
                </tr>
            </table>
        </form>
    </div>

<script>
        $.fn.serializeJson=function(){
            var serializeObj={};
            var array=this.serializeArray();
            $(array).each(function(){
                if(serializeObj[this.name]){
                    if($.isArray(serializeObj[this.name])){
                        serializeObj[this.name].push(this.value);
                    }else{
                        serializeObj[this.name]=[serializeObj[this.name],this.value];
                    }
                }else{
                    serializeObj[this.name]=this.value;
                }
            });
            return serializeObj;
        };
        $("#find").click(function () {
            var from=$("#lookStandardForm").serializeJson();
            $("#grid").datagrid("load", from);
            $("#lookStandardWindow").window("close");
        })
</script>
</body>
</html>
