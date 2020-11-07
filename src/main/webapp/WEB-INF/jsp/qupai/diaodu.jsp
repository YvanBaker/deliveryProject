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
</head>
<script type="text/javascript">
    $(function () {
        $("#grid").datagrid({
            singleSelect: true,
            toolbar: [{
                id: 'diaodu',
                text: '人工调度',
                iconCls: 'icon-edit',
                handler: function () {
                    var selectItem = $("#grid").datagrid("getSelections");
                    if (selectItem.length < 1) {
                        alert("请先选中一条单子")
                    }
                    if (selectItem.length == 1) {
                        // 弹出窗口
                        $("#diaoduWindow").window('open');
                        $("#noticebillId").val(selectItem[0].id);
                        $("#noticebillIdView").text(selectItem[0].uuid);
                        // 点击保存按钮，为通知单 进行分单 --- 生成工单
                        $("#save").click(function () {
                            $("#diaoduForm").form('submit',{
                                url:"/sys/diaoduForm",
                                success: function(data){
                                        alert(data)
                                }
                            })
                        });
                    }
                    if (selectItem.length > 1) {
                        alert("请一条一条来");
                    }
                }
            }],
            columns: [[{
                field: 'uuid',
                title: '编号',
                width: 100
            }, {
                field: 'clientName',
                title: '客户',
                width: 100
            }, {
                field: 'clientPhone',
                title: '客户电话',
                width: 100
            }, {
                field: 'address',
                title: '取件地址',
                width: 100
            }, {
                field: 'product',
                title: '商品名称',
                width: 100
            }, {
                field: 'remark',
                title: '顾客留言',
                width: 100,
                /*formatter : function(data, row, index) {
                    return data.replace("T", " ");
                }*/
            }]],
            url: '/sys/noticebillFindNoassociations'
        });


    });
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <table id="grid"></table>
</div>
<div class="easyui-window" title="人工调度" id="diaoduWindow" closed="true"
     collapsible="false" minimizable="false" maximizable="false"
     style="top:100px;left:200px;width: 500px; height: 300px">
    <div region="north" style="height:31px;overflow:hidden;" split="false"
         border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="javascript:void(0);" class="easyui-linkbutton"
               plain="true">保存</a>
        </div>
    </div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="diaoduForm" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">人工调度</td>
                </tr>
                <tr>
                    <td>通知单编号</td>
                    <td><input type="hidden" name="OrderId" id="noticebillId"/> <span
                            id="noticebillIdView"></span>
                </tr>
                <tr>
                    <td>选择取派员</td>
                    <td><input class="easyui-combobox" required="true"
                               name="StaffId"
                               data-options="valueField:'id',textField:'name',url:'/sys/staffAjax'"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script>

</script>
</body>
</html>