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
    }


    /*修改*/
    function doEdit() {
        alert("修改...");
    }

    /*删除*/
    function doDelete() {
        alert("删除...");
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
            $("#noassociationSelect").empty();
            //发送请求
            var url = "/sys/findAssociationsCustomer";
            $.ajax({
                url: url,
                method: 'post',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (index, item) {
                        $("#noassociationSelect").append("<option value='" + item.id + "'>" + item.name + "</option>");
                    })
                }
            });
            $.ajax({
                url: "/sys/findHasAssociationsCustomer",
                method: 'post',
                dataType: 'json',
                data: {id: did},
                success: function (data) {
                    $.each(data, function (index, item) {
                        $("#associationSelect").append("<option value='" + item.id + "'>" + item.name + "</option>");
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
            $("#customerForm").submit();
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
        $("#btn").click(function () {
            alert("执行查询...");
        });

    });

    /**
     * 关联定区
     */
    function doDblClickRow() {
        alert("双击表格数据...");
        $('#association_subarea').datagrid({
            fit: true,
            border: true,
            rownumbers: true,
            striped: true,
            url: "/sys/associationSubarea",
            columns: [[{
                field: 'id',
                title: '分拣编号',
                width: 120,
                align: 'center'
            }, {
                field: 'province',
                title: '省',
                width: 120,
                align: 'center',
                formatter: function (data, row, index) {
                    return row.region.province;
                }
            }, {
                field: 'city',
                title: '市',
                width: 120,
                align: 'center',
                formatter: function (data, row, index) {
                    return row.region.city;
                }
            }, {
                field: 'district',
                title: '区',
                width: 120,
                align: 'center',
                formatter: function (data, row, index) {
                    return row.region.district;
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
                align: 'center'
            }, {
                field: 'position',
                title: '位置',
                width: 200,
                align: 'center'
            }]]
        });
        /*关联客户*/
        $('#association_customer').datagrid({
            fit: true,
            border: true,
            rownumbers: true,
            striped: true,
            url: "/sys/findAssociationsCustomer",
            columns: [[{
                field: 'id',
                title: '客户编号',
                width: 120,
                align: 'center'
            }, {
                field: 'name',
                title: '客户名称',
                width: 120,
                align: 'center'
            }, {
                field: 'station',
                title: '所属单位',
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
        <form>
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>定区编码</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>所属单位</td>
                    <td><input type="text" name="staff.station" class="easyui-validatebox" required="true"/></td>
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
        <form id="customerForm" action="/sys/assignCustomersToDecidedzone" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="3">关联客户</td>
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
                        <select id="associationSelect" name="customerIds" multiple="multiple" size="10"></select>
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><a id="associationBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                       data-options="iconCls:'icon-save'">关联客户</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<script type="text/javascript">
    var flog = -1;
    /*验证名字判重*/
    $("#deciname").blur(function () {
        var deciname = $(this).val();
        $.post("/verify/deciname", {deciName: deciname}, function (data) {
            if (data != 'Y') {
                flog = 1
            } else {
                flog = 0;
            }
        }, 'json')
    })
    /*提交定区*/
    $("#save").click(function () {
        var v = $("#addDecidedZoneFrom").form("validate");
        if (v) {
            //校验通过，提交表单
            if (flog === 1) {
                alert("定区名已存在!!");
            }
            if (flog === 0) {
                //判重通过
                $("#addDecidedZoneFrom").form('submit', {
                    url: "/sys/addDecided",
                    method: "post",
                    success: function (data) {
                        $.messager.alert("提示", data, "info");
                        $("#grid").datagrid("reload");
                        $("#addDecidedzoneWindow").window('close');
                    }
                });
            }
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
