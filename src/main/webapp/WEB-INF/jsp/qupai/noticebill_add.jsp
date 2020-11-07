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
        // 对save按钮条件 点击事件
        $('#save').click(function () {
            // 对form 进行校验
            if ($('#noticebillForm').form('validate')) {
                $('#noticebillForm').submit();
            }else {
                alert("请正确填写表单!");
            }
        });
    });
    $(function () {
            var message="${msg}";
        if (message!="") {
            $.messager.alert("提示信息", message, "info");
        }
    })
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height:31px;overflow:hidden;" split="false"
     border="false">
    <div class="datagrid-toolbar">
        <a id="save" icon="icon-save" href="javascript:void(0);" class="easyui-linkbutton"
           plain="true">保存新单</a>
        <%--<a id="edit" icon="icon-edit" href="/sys/noticebillView" class="easyui-linkbutton"
           plain="true">工单操作</a>--%>
    </div>
</div>
<div region="center" style="overflow:auto;padding:5px;" border="false">
    <form id="noticebillForm" action="/sys/noticebillAdd" method="post">
        <table class="table-edit" width="95%" align="center">
            <tr class="title">
                <td colspan="5" style="color: #0052A3"><em>客户信息</em></td>
                </br>
            </tr>
            <tr>
                <td colspan="6">
                    <hr style="color: #00ee00"/>
                </td>
            </tr>
            <tr>
                <td>客户电话:</td>
                <td><input type="text" class="easyui-validatebox" name="clientPhone" required="true" data-options="validType:'length[11]'"/></td>
                <td>客户编号:</td>
                <td><input type="text" class="easyui-validatebox" name="clientNum"/></td>
                <td>客户姓名:</td>
                <td><input type="text" class="easyui-validatebox" name="clientName"/></td>
            </tr>
            <tr></tr>
            <tr class="title">
                <td colspan="5" style="color: #0052A3"><em>货物信息</em></td>
                </br>
            </tr>
            <tr>
                <td colspan="6">
                    <hr style="color: #00ee00"/>
                </td>
            </tr>
            <tr>
                <td>物品名:</td>
                <td><input type="text" class="easyui-validatebox" name="product"
                /></td>
                <td>件数:</td>
                <td><input type="text" class="easyui-numberbox" name="number"/></td>
            </tr>
            <tr>
                <td>重量:</td>
                <td><input type="text" class="easyui-numberbox" name="weight"/><span>kg</span></td>
                <td>体积:</td>
                <td><input type="text" class="easyui-numberbox" name="volume"/></td>
            </tr>
            <tr></tr>
            <tr class="title">
                <td colspan="5" style="color: #0052A3">取件信息</td>
                </br>
            </tr>
            <tr>
                <td colspan="6">
                    <hr style="color: #00ee00"/>
                </td>
            </tr>
            <tr>
                <td>取件地址</td>
                <td colspan="1">
                    <input type="text" class="easyui-validatebox" name="address" placeholder="详细地址" required="true" size="80"/></td>
                <%--<td>预约取件时间:</td>
                <td><input type="text" class="easyui-datetimebox" name="proDate"
                           data-options="required:true, editable:false"/></td>--%>
            </tr>
            <tr></tr>
            <tr class="title">
                <td colspan="5" style="color: #0052A3">寄件信息</td>
            </tr>
            <tr>
                <td colspan="6">
                    <hr style="color: #00ee00"/>
                </td>
            </tr>
            <tr>
                <td>到达地址:</td>
                <td><input type="text" class="easyui-validatebox" name="arriveCity" placeholder="**省/**市/**区/小区名/**栋/**号/**室"
                           required="true" size="80"/></td>
                <td>联系人电话</td>
                <td><input type="text" class="easyui-validatebox" name="conPhone" required="true" data-options="validType:'length[11]'"/></td>
                <td>联系人姓名</td>
                <td><input type="text" class="easyui-validatebox" name="contacts"/></td>
            </tr>
            <tr></tr>
            <tr>
                <td colspan="6">
                    <hr style="color: #00ee00"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3"><textarea rows="5" cols="80" type="text" class="easyui-validatebox" name="remark"
                ></textarea></td>
            </tr>
        </table>
    </form>
</div>
<script>
    $("input[name=clientPhone]").blur(function () {
        var clientPhone = $(this).val();
        var url = "/sys/billVerify";
        $.post(
            url,
            {"clientPhone": clientPhone},
            function (data) {
                if (data != null) {
                    $("input[name=clientNum]").val(data.customer.id);
                    $("input[name=clientName]").val(data.customer.name);
                    $("input[name=address]").val(data.customerAddress[0].addressDetail);
                } else {
                    $("input[name=clientNum]").val();
                    $("input[name=clientName]").val();
                    $("input[name=pickaddress]").val();
                }
            }, 'json'
        );
    })
</script>
</body>
</html>