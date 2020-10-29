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
	$(function(){
		// 对save按钮条件 点击事件
		$('#save').click(function(){
			// 对form 进行校验
			if($('#noticebillForm').form('validate')){
				$('#noticebillForm').submit();
			}
		});
	});


</script>
</head>
<body class="easyui-layout" >
	<div region="north" style="height:31px;overflow:hidden;" split="false"
		border="false">
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="javascript:void(0);" class="easyui-linkbutton"
				plain="true">保存新单</a>
			<a id="edit" icon="icon-edit" href="/sys/noticebillView" class="easyui-linkbutton"
				plain="true">工单操作</a>	
		</div>
	</div>
	<div region="center" style="overflow:auto;padding:5px;" border="false">
		<form id="noticebillForm" action="" method="post">
			<table class="table-edit" width="95%" align="center">
				<tr class="title">
					<td colspan="4">客户信息</td>
				</tr>
				<tr>
					<td>客户电话:</td>
					<td><input type="text" class="easyui-validatebox" name="telephone"
						required="true" /></td>
					<td>客户编号:</td>
					<td><input type="text" class="easyui-validatebox"  name="customerId"
						required="true" /></td>
				</tr>
				<tr>
					<td>客户姓名:</td>
					<td><input type="text" class="easyui-validatebox" name="customerName"
						required="true" /></td>
					<td>联系人:</td>
					<td><input type="text" class="easyui-validatebox" name="delegater"
						required="true" /></td>
				</tr>
				<tr class="title">
					<td colspan="4">货物信息</td>
				</tr>
				<tr>
					<td>物品名:</td>
					<td><input type="text" class="easyui-validatebox" name="product"
						required="true" /></td>
					<td>件数:</td>
					<td><input type="text" class="easyui-numberbox" name="num"
						required="true" /></td>
				</tr>
				<tr>
					<td>重量:</td>
					<td><input type="text" class="easyui-numberbox" name="weight"
						required="true" /><em>kg</em></td>
					<td>体积:</td>
					<td><input type="text" class="easyui-validatebox" name="volume"
						required="true" /></td>
				</tr>
				<tr>
					<td>取件地址</td>
					<td colspan="3"><input type="text" class="easyui-validatebox" name="pickaddress"
						required="true" size="144"/></td>
				</tr>
				<tr>
					<td>到达城市:</td>
					<td><input type="text" class="easyui-validatebox" name="arrivecity"
						required="true" /></td>
					<td>预约取件时间:</td>
					<td><input type="text" class="easyui-datebox" name="pickdate"
						data-options="required:true, editable:false" /></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td colspan="3"><textarea rows="5" cols="80" type="text" class="easyui-validatebox" name="remark"
						required="true" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
<script>
	$("input[name=telephone]").blur(function () {
		var phone = $(this).val();
		var url="/sys/billVerify";
				$.post(
						url,
						{"telephone":phone},
						function (data) {
							if (data != null) {
								$("input[name=customerId]").val(data.id);
								$("input[name=customerName]").val(data.name);
								$("input[name=delegater]").val(data.name);
							}
						},'json'
				);
				console.log(phone)
	})
</script>
</body>
</html>