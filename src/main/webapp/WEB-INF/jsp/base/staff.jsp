<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/10/18
  Time: 13:34
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
    <script type="text/javascript">
        //增加方法
        function doAdd() {
            $('#addStaffWindow').window("open");
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 1) {
                console.log(rows[0]);
                $('#addStaffForm').form("load", rows[0]);
                if (rowData.haspda == 'Y') {
                    $("#clock_pda").prop("checked", true);
                }
            }
            $('#addStaffForm').form('clear');
        }

        //查寻方法
        function doView() {
            $('#lookStaffWindow').window("open");
        }

        //批量删除取派员
        function doDelete() {
            //获得选中的行
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                //没有选中，提示
                $.messager.alert("提示信息", "请选择需要删除的记录！", "warning");
            } else {
                var array = new Array();
                //选中了记录,获取选中行的id
                var n=0;
                for (var i = 0; i < rows.length; i++) {
                    var id = rows[i].id;
                    if (rows[i].deltag === 'N') {
                        n++;
                    }
                    array.push(id);
                }
                if (n > 0) {
                    alert("只能废除正常使用的")
                }
                var ids = array.join(",");
                //发送请求，传递ids参数
                if (n === 0) {
                    window.location.href = '/sys/staffDelete?ids=' + ids;
                }
            }
        }

        //批量还原
        function doRestore() {
            var rows=$("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                $.messager.alert("提示信息","请选择需要还原的记录","warning")
            }
            var n=0;
            var array = new Array();
            for (var i = 0; i < rows.length; i++) {
                var deltag = rows[i].deltag;
                var id = rows[i].id;
                array.push(id);
                if(deltag==='Y'){
                   n++;
                }
            }
            var ids = array.join(",");
            if (n > 0) {
                alert("只能还原已废除的记录")
            }
            if (n === 0) {
                //都是已废除的记录，可以发起还原
                $.post("/sys/staffRenew",{ids:ids},function (data) {
                    $("#grid").datagrid("load");
                },'json')
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
        }, {
            id: 'button-save',
            text: '还原',
            iconCls: 'icon-save',
            handler: doRestore
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'name',
            title: '姓名',
            width: 120,
            align: 'center'
        }, {
            field: 'telephone',
            title: '手机号',
            width: 120,
            align: 'center',
        }, {
            field: 'haspda',
            title: '是否有PDA',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "Y") {
                    return "有";
                } else {
                    return "无";
                }
            }
        }, {
            field: 'deltag',
            title: '是否作废',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "Y") {
                    return "正常使用"
                } else {
                    return "已作废";
                }
            }
        }, {
            field: 'standard',
            title: '取派标准',
            width: 120,
            align: 'center'
        }, {
            field: 'station',
            title: '所属单位',
            width: 200,
            align: 'center'
        }]];
        //窗口
        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});
            // 取派员信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,//显示行号
                striped: true,
                pageList: [3, 5, 10],
                pagination: true,
                toolbar: toolbar,//工具栏
                url: "/sys/staffShow",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow//指定数据表格的双击行事件
            });
            // 查询取派员窗口
            $('#lookStaffWindow').window({
                title: '查询取派员',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
            // 修改取派员窗口
            $('#editStaffWindow').window({
                title: '修改取派员',
                width: 400,
                modal: true,//遮罩效果
                shadow: true,//阴影效果
                closed: true,//关闭状态
                height: 400,
                resizable: false//是否可以调整大小
            });
        });

        //双击修改事件处理函数
        function doDblClickRow(rowIndex, rowData) {//{id:xxx,name:xx,}
            $('#editStaffWindow').window({title: "修改取派员信息"})
            $('#editStaffWindow').window("open");//打开修改窗口
            $("#editStaffForm").form("load", rowData);
            if (rowData.haspda == 'Y') {
                $("#clock_pda").prop("checked", true);
            }
        }

        //校验规则
        $(function () {
            var reg = /^1[3|4|5|7|8|9][0-9]{9}$/;
            $.extend($.fn.validatebox.defaults.rules, {
                phonenumber: {
                    validator: function (value, param) {
                        return reg.test(value);
                    },
                    message: '手机号输入有误！'
                }
            });
        });
        //添加表单提交
        $(function () {
            $("#save").click(function () {
                //校验表单输入项
                var v = $("#addStaffForm").form("validate");
                if (v) {
                    //校验通过，提交表单
                    $("#addStaffForm").submit();
                }
            });
        });
        //查询表单提交 TODO 查询表单提交

        /*$(function () {
            $("#find").click(function () {
                //校验表单输入项
                var v = $("#lookStaffForm").form("validate");
                if (v) {
                    $('#lookStaffForm').form({
                        success:function(data1){
                            console.log(data1)
                            $('#grid').datagrid({data:[data1] });
                            $('#lookStaffWindow').close();
                           /!* $('#grid').datagrid({
                                iconCls: 'icon-forward',
                                fit: true,
                                border: false,
                                rownumbers: true,//显示行号
                                striped: true,
                                pageList: [3, 5, 10],
                                pagination: true,
                                toolbar: toolbar,//工具栏
                                url:'datagrid_data.json',
                                columns:[[
                                    {field:'code',title:'代码',width:100},
                                    {field:'name',title:'名称',width:100},
                                    {field:'price',title:'价格',width:100,align:'right'}
                                ]]
                                idField: 'id',
                                columns: columns,
                                onDblClickRow: doDblClickRow//指定数据表格的双击行事件
                            });*!/
                        }
                    });

                    //校验通过，提交表单
                    $("#lookStaffForm").submit();
                    /!*$('#grid').datagrid({
                        iconCls: 'icon-forward',
                        fit: true,
                        border: false,
                        rownumbers: true,//显示行号
                        striped: true,
                        pageList: [3, 5, 10],
                        pagination: true,
                        toolbar: toolbar,//工具栏
                        url: "/sys/staffShow",
                        idField: 'id',
                        columns: columns,
                        onDblClickRow: doDblClickRow//指定数据表格的双击行事件
                    });*!/
                }
            });
        })*/
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
<%-- 对收派员进行添加或者修改--%>
<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="addStaffForm" action="/sys/staffAdd"
              method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <!-- 完善收派员添加 table -->
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"
                               data-options="validType:'phonenumber'"
                    /></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input id="clock_pda" type="checkbox" name="haspda" value="Y"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>
<%-- 对收派员进行查询--%>
<div class="easyui-window" title="对收派员进行查询" id="lookStaffWindow"
     collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="find" icon="icon-save" class="easyui-linkbutton" plain="true">查询</a>
            <script>
                $(function(){//工具方法，可以将指定的表单中的输入项目序列号为json数据
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
                    //绑定事件
                    $("#find").click(function(){
                        var p = $("#lookStaffForm").serializeJson();//{id:xx,name:yy,age:zz}
                        //重新发起ajax请求，提交参数
                        $("#grid").datagrid("load",p);
                        //关闭查询窗口
                        $("#lookStaffWindow").window("close");
                    });
                });
            </script>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="lookStaffForm">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询信息</td>
                </tr>
                <!-- 完善收派员查询 table -->
                <tr>
                    <td>工号</td>
                    <td><input type="text" name="staffNum" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>所属定区</td>
                    <td><input type="text" name="region" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>收派标准</td>
                    <td><input type="text" name="standard" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>所属单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 修改窗口 -->
<div class="easyui-window" title="对收派员进行修改" id="editStaffWindow" collapsible="false"
     minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="edit" icon="icon-save" href="javascript:void (0);" class="easyui-linkbutton" plain="true">保存</a>
            <script type="text/javascript">
                $(function () {
                    //绑定事件
                    $("#edit").click(function () {
                        //校验表单输入项
                        var v = $("#editStaffForm").form("validate");
                        if (v) {
                            //校验通过，提交表单
                            $("#editStaffForm").submit();
                        }
                    });
                });
            </script>
        </div>
    </div>
    <%--编辑表单--%>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="editStaffForm" action="/sys/staffEdit"
              method="post">
            <input type="hidden" name="id">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"
                               data-options="validType:'phonenumber'"
                    /></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="Y"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%--<script>
    //页面分页
    (function ($) {
        function pagerFilter(data) {
            if ($.isArray(data)) {    // is array
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var target = this;
            var dg = $(target);
            var state = dg.data('datagrid');
            var opts = dg.datagrid('options');
            if (!state.allRows) {
                state.allRows = (data.rows);
            }
            if (!opts.remoteSort && opts.sortName) {
                var names = opts.sortName.split(',');
                var orders = opts.sortOrder.split(',');
                state.allRows.sort(function (r1, r2) {
                    var r = 0;
                    for (var i = 0; i < names.length; i++) {
                        var sn = names[i];
                        var so = orders[i];
                        var col = $(target).datagrid('getColumnOption', sn);
                        var sortFunc = col.sorter || function (a, b) {
                            return a == b ? 0 : (a > b ? 1 : -1);
                        };
                        r = sortFunc(r1[sn], r2[sn]) * (so == 'asc' ? 1 : -1);
                        if (r != 0) {
                            return r;
                        }
                    }
                    return r;
                });
            }
            var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = state.allRows.slice(start, end);
            return data;
        }

        var loadDataMethod = $.fn.datagrid.methods.loadData;
        var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
        $.extend($.fn.datagrid.methods, {
            clientPaging: function (jq) {
                return jq.each(function () {
                    var dg = $(this);
                    var state = dg.data('datagrid');
                    var opts = state.options;
                    opts.loadFilter = pagerFilter;
                    var onBeforeLoad = opts.onBeforeLoad;
                    opts.onBeforeLoad = function (param) {
                        state.allRows = null;
                        return onBeforeLoad.call(this, param);
                    }
                    var pager = dg.datagrid('getPager');
                    pager.pagination({
                        onSelectPage: function (pageNum, pageSize) {
                            opts.pageNumber = pageNum;
                            opts.pageSize = pageSize;
                            pager.pagination('refresh', {
                                pageNumber: pageNum,
                                pageSize: pageSize
                            });
                            dg.datagrid('loadData', state.allRows);
                        }
                    });
                    $(this).datagrid('loadData', state.data);
                    if (opts.url) {
                        $(this).datagrid('reload');
                    }
                });
            },
            loadData: function (jq, data) {
                jq.each(function () {
                    $(this).data('datagrid').allRows = null;
                });
                return loadDataMethod.call($.fn.datagrid.methods, jq, data);
            },
            deleteRow: function (jq, index) {
                return jq.each(function () {
                    var row = $(this).datagrid('getRows')[index];
                    deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                    var state = $(this).data('datagrid');
                    if (state.options.loadFilter == pagerFilter) {
                        for (var i = 0; i < state.allRows.length; i++) {
                            if (state.allRows[i] == row) {
                                state.allRows.splice(i, 1);
                                break;
                            }
                        }
                        $(this).datagrid('loadData', state.allRows);
                    }
                });
            },
            getAllRows: function (jq) {
                return jq.data('datagrid').allRows;
            }
        })
    })(jQuery);

    function getData() {
        var rows = [];
        for (var i = 1; i <= 800; i++) {
            var amount = Math.floor(Math.random() * 1000);
            var price = Math.floor(Math.random() * 1000);
            rows.push({
                inv: 'Inv No ' + i,
                date: $.fn.datebox.defaults.formatter(new Date()),
                name: 'Name ' + i,
                amount: amount,
                price: price,
                cost: amount * price,
                note: 'Note ' + i
            });
        }
        return rows;
    }

    $(function () {
        $('#grid').datagrid({data: getData()}).datagrid('clientPaging');
    });
</script>--%>
</body>
</html>
