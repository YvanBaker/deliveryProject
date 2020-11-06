<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/10/18
  Time: 14:00
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
</head>
<script type="text/javascript">
    /*添加*/
    function doAdd() {
        $('#addDecidedzoneWindow').window("open");
        $('#addDecidedZoneFrom').form('clear');
    }

    /*修改*/
    function doEdit() {
        var selectrow = $('#grid').datagrid('getSelections');
        if (selectrow.length == 0) {
            //没有选中，提示
            $.messager.alert("提示信息", "请选择需要修改的记录！", "warning");
        }
        if (selectrow.length > 1) {
            //选中多个，提示
            $.messager.alert("提示信息", "请一条一条修改！", "warning");
        }
        if (selectrow.length == 1) {//这里有bug********虽然正确重现记录******但是如果不改变表单内容就不会正确修改
            $('#addDecidedzoneWindow').window({title: "修改定区信息"})
            $('#addDecidedzoneWindow').window("open");//打开修改窗口
            $("#addDecidedZoneFrom").form("load", {
                selectId: selectrow[0].id,
                decidedName: selectrow[0].decidedName,
                staffId: selectrow[0].staff.name,
                areaId: selectrow[0].region.name
            });
        }
    }

    /*删除*/
    function doDelete() {
        var selectrow = $('#grid').datagrid('getSelections');
        if (selectrow.length == 0) {
            //没有选中，提示
            $.messager.alert("提示信息", "请选择需要删除的记录！", "warning");
        } else {
            var array = new Array();
            //选中了记录,获取选中行的id
            var n = 0;
            for (var i = 0; i < selectrow.length; i++) {
                var id = selectrow[i].id;
                array.push(id);
            }
            var ids = array.join(",");
            //发送请求，传递ids参数
            $.post("/sys/delecte", {ids: ids}, function (data) {
                alert(data)
                $("#grid").datagrid("reload");
            }, 'json')
        }
    }

    /*查询*/
    function doSearch() {
        $('#searchWindow').window("open");
    }

    /* 关联动作*/
    var did;//分区id
    function doAssociations() {
        var rows = $("#grid").datagrid("getSelections");
        if (rows.length == 1) {
            did = rows[0].id;
            $('#customerWindow').window('open');
            //请求前清空
            $("#noassociationSelect").empty();
            $("#associationSelect").empty();
            //发送请求
            var url = "/sys/findAssociationsOrder";
            $.ajax({//左边加载
                url: url,
                method: 'post',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (index, item) {
                        $("#noassociationSelect").append("<option value='" + item.id + "'>" + item.clientName +"-"+item.arriveCity +"</option>");
                    })
                }
            });
            $.ajax({//右边加载
                url: "/sys/findHasAssociationsOrder",
                method: 'post',
                dataType: 'json',
                data: {id: did},
                success: function (data) {
                    console.log(data)
                    $.each(data, function (index, item) {
                        $("#associationSelect").append("<option value='" + item.id + "'>" + item.clientName +"-"+item.arriveCity +"</option>");
                    });
                }
            })
        } else {
            $.messager.alert("提示信息", "请先选中一个定区", "waring");
        }
    }

    /*关联顾客订单*/
    $(function () {
        //为左右移动按钮绑定事件
        $("#toRight").click(function () {
            $("#associationSelect").append($("#noassociationSelect option:selected"));
        });
        $("#toLeft").click(function () {
            $("#noassociationSelect").append($("#associationSelect option:selected"));
        });
        //为关联客户按钮绑定事件
        $("#associationBtn").click(function () {
            //在提交表单之前，选中右侧框中所有的选项
            $("#associationSelect option").attr("selected", "selected");
            //在提交表单之前设置隐藏域的值（定区id）
            $("input[name=id]").val(did);
            $("#associationForm").submit();
        });
    })
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
    }, {
        id: 'button-association',
        text: '关联客户订单',
        iconCls: 'icon-sum',
        handler: doAssociations
    }];
    // 定义列
    var columns = [[{
        field: 'id',
        title: '定区编号',
        width: 120,
        align: 'center'
    }, {
        field: 'decidedName',
        title: '定区名',
        width: 120,
        align: 'center'
    }, {
        field: 'region.name',
        title: '区域',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.region.name;
        }
    }, {
        field: 'staff.name',
        title: '负责人',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.staff.name;
        }
    }, {
        field: 'staff.telephone',
        title: '联系电话',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.staff.telephone;
        }
    }, {
        field: 'staff.station',
        title: '所属公司',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.staff.station;
        }
    }]];

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
            pageList: [10, 30, 50],
            pagination: true,
            toolbar: toolbar,
            url: "/sys/decidedZoneShow",
            idField: 'id',
            columns: columns,
            onDblClickRow: doDblClickRow
        });

        // 添加、修改定区
        $('#addDecidedzoneWindow').window({
            title: '添加修改定区',
            width: 600,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable: false
        });

        // 查询定区
        $('#searchWindow').window({
            title: '查询定区',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable: false
        });


    });

    /**
     * 关联定区
     */
    function doDblClickRow(rowIndex, rowData) {
        //alert("双击表格数据...");
        /*关联订单*/
        $('#association_order').datagrid({
            fit: true,
            border: true,
            rownumbers: true,
            striped: true,
            queryParams: {id:rowData.id},
            url: "/sys/findAssociationsOrderOnDbl",
            columns: [[{
                field: 'id',
                title: '订单编号',
                width: 120,
                align: 'center'
            }, {
                field: 'clientName',
                title: '客户名称',
                width: 120,
                align: 'center'
            }, {
                field: 'clientPhone',
                title: '客户电话',
                width: 120,
                align: 'center'
            }, {
                field: 'address',
                title: '取件地址',
                width: 120,
                align: 'center'
            }, {
                field: 'contacts',
                title: '收件人名称',
                width: 120,
                align: 'center'
            }, {
                field: 'conPhone',
                title: '收件人电话',
                width: 120,
                align: 'center'
            }, {
                field: 'arriveCity',
                title: '送达地址',
                width: 120,
                align: 'center'
            }, {
                field: 'remark',
                title: '顾客留言',
                width: 120,
                align: 'center'
            }, {
                field: 'product',
                title: '物品名',
                width: 120,
                align: 'center'
            }, {
                field: 'buildTime',
                title: '创建时间',
                width: 120,
                align: 'center'
            }, {
                field: 'product',
                title: '物品名',
                width: 120,
                align: 'center'
            }, {
                field: 'staff.name',
                title: '派送人',
                width: 120,
                align: 'center'
            }]]
        });
    }
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>

<div region="south" border="false" style="height:150px">
    <div id="tabs" fit="true" class="easyui-tabs">
        <div title="关联分区" id="subArea"
             style="width:100%;height:100%;overflow:hidden">
            <table id="association_subarea"></table>
        </div>
        <div title="关联订单" id="order"
             style="width:100%;height:100%;overflow:hidden">
            <table id="association_order"></table>
        </div>
    </div>
</div>


<!-- 添加定区窗口 -->
<div class="easyui-window" title="定区添加" id="addDecidedzoneWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="javascript:void(0);" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="addDecidedZoneFrom">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">定区信息</td>
                    <input type="hidden" name="selectId" value="-1"/>
                </tr>
                <tr>
                    <td>定区名称</td>
                    <td><input id="deciname" type="text" name="decidedName" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>选则取派员</td>
                    <td>
                        <input id="staff" class="easyui-combobox" name="staffId" required="true"
                               data-options="valueField:'id',textField:'name',url:'sys/staffIsY'"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">关联地区</td>
                    <td>
                        <input id="area" class="easyui-combobox" name="areaId" mode="remote" required="true"
                               data-options="valueField:'id',textField:'name',url:'sys/regionAjax'"/>
                    </td>

                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 查询定区窗口 -->
<div class="easyui-window" title="查询定区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="searchFrom" method="post" action="/sys/decidedZoneShow">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>定区编码</td>
                    <td><input type="text" name="id" class="easyui-numberbox"/></td>
                </tr>
                <tr>
                    <td>所属单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td colspan="2"><a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
                                       data-options="iconCls:'icon-search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 关联客户窗口 -->
<div class="easyui-window" title="关联客户订单窗口" id="customerWindow" collapsible="false" closed="true" minimizable="false"
     maximizable="false" style="top:20px;left:200px;width: 400px;height: 300px;">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="associationForm" action="/sys/assignOrderToDecidedzone" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="3">关联客户订单</td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="id" id="customerDecidedZoneId"/>
                        <select id="noassociationSelect" multiple="multiple" size="10"></select>
                    </td>
                    <td>
                        <input type="button" value="》》" id="toRight"><br/>
                        <input type="button" value="《《" id="toLeft">
                    </td>
                    <td>
                        <select id="associationSelect" name="OrderIds" multiple="multiple" size="10"></select>
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><a id="associationBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                       data-options="iconCls:'icon-save'">关联订单</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%-- 提交关联--%>
<script type="text/javascript">
    $(function () {//工具方法，可以将指定的表单中的输入项目序列号为json数据
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
            var p = $('#searchFrom').serializeJson();
            $("#grid").datagrid("load", p);
            $('#searchWindow').window('close');
        })
    })

    /*添加定区*/
    $("#save").click(function () {
        var v = $("#addDecidedZoneFrom").form("validate");
        if (v) {
            //校验通过，提交表单
            $("#addDecidedZoneFrom").form('submit', {
                url: "/sys/addDecided",
                method: "post",
                success: function (data) {
                    if (data === "0") {//判重
                        alert("定区名已存在!!");
                        $("#deciname").focus();
                    }
                    if (data === "1") {//添加
                        $.messager.alert("提示", "添加成功", "info");
                        $("#grid").datagrid("reload");
                        $("#addDecidedzoneWindow").window('close');
                    }
                    if (data === "2") {//修改
                        $.messager.alert("提示", "修改成功", "info");
                        $("#grid").datagrid("reload");
                        $("#addDecidedzoneWindow").window('close');
                    }

                }
            });
        }
    })
    /*
<%--<td>
                        <table id="subareaGrid" class="easyui-datagrid" border="false" style="width:300px;height:300px"
                               data-options="url:'/sys/decidedzoneSubarea',fitColumns:true,singleSelect:false">
                            <thead>
                            <tr>
                                <th data-options="field:'id',width:30,checkbox:true">编号</th>
                                <th data-options="field:'addresskey',width:150">关键字</th>
                                <th data-options="field:'position',width:200,align:'right'">位置</th>
                            </tr>
                            </thead>
                        </table>
                    </td>--%>
        $("#addDecidedZoneFrom").form('submit',{
            url: "/sys/addDecided",
            /!*onSubmit: function(){
                var dname=$("#deciname").val();
                var staffid=$("#staff").val();
                var area = $("#area").val();
                if (dname === '' || staffid === '' || area === '') {
                    alert()
                }
                // do some check
                // return false to prevent submit;
            },*!/
            success:function(data){
                alert(data)
            }*/


    /*var ids = new Array();
    if (sele.length == 0) {
        alert("请选则要关联的定区")
    }
    if (sele.length != 0) {
        for (var i = 0; i < sele.length; i++) {
            ids.push(sele[i].id);
        }
        ids.join(",");
        ids=ids.toString();

        var decname = $("#deciname").val();
        var staffbox = $("#staffbox").val();
        if (staffbox == '') {
            alert("请选则取派员!")
        } else {
            $.post({
                url: "/sys/addDecided",
                data: {
                    DecidedId: id,
                    decidedName: decname,
                    staff_id:staffbox
                },
                success: function (data) {
                    $.messager.show({
                        title:'消息',
                        msg:data,
                        timeout:3000,
                        showType:'slide'
                    });
                }
            });
            $("#addDecidedzoneWindow").window("close");
            $("#grid").datagrid("loader");
        }
    }
*/

    /*$.post("/sys/addDecidedZone", {}, function () {

    })*/
</script>
</body>
</html>
