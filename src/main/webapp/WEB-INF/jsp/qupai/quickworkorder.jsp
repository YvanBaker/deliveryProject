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
	var editIndex ;

	/*
	* 增行
	* */
	function doAdd(){
		console.log(editIndex);
		if(editIndex != undefined){
			$("#grid").datagrid('endEdit',editIndex);
		}
		if(editIndex==undefined){
			//快速添加单
			$("#grid").datagrid('insertRow',{
				index : 0,
				row : {}
			});
			$("#grid").datagrid('beginEdit',0);
			editIndex = 0;
		}
	}

	/**
	 * 保存
	 */
	function doSave(){
		$("#grid").datagrid('endEdit',editIndex );
	}

	/**
	 *取消编辑
	 */
	function doCancel(){
		if(editIndex!=undefined){
			$("#grid").datagrid('cancelEdit',editIndex);
			if($('#grid').datagrid('getRows')[editIndex].id == undefined){
				$("#grid").datagrid('deleteRow',editIndex);
			}
			editIndex = undefined;
		}
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-add',	
		text : '新增一行',
		iconCls : 'icon-edit',
		handler : doAdd
	}, {
		id : 'button-cancel',
		text : '取消编辑',
		iconCls : 'icon-cancel',
		handler : doCancel
	}, {
		id : 'button-save',
		text : '保存',
		iconCls : 'icon-save',
		handler : doSave
	}];
	// 定义列
	var columns = [ [ {
		field : 'workorId',
		title : '工作单号',
		width : 120,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'arrivecity',
		title : '到达地',
		width : 120,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	},{
		field : 'product',
		title : '产品',
		width : 120,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'num',
		title : '件数',
		width : 120,
		align : 'center',
		editor :{
			type : 'numberbox',
			options : {
				required: true
			}
		}
	}, {
		field : 'weight',
		title : '重量',
		width : 120,
		align : 'center',
		editor :{
			type : 'numberbox',
			options : {
				required: true
			}
		}
	}, {
		field : 'floadreqr',
		title : '配载要求',
		width : 220,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}] ];
	/*标准数据表格*/
	$(function(){
		//$("body").css({visibility:"visible"});
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageSize: 30, //页容量，必须和pageList对应起来，否则会报错
			pageNumber: 1, //默认显示第几页
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url: "/sys/workorAll",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow,
			onAfterEdit : function(rowIndex, rowData, changes){
				editIndex = undefined;
				//发送ajax请求，提交当前结束编辑行的数据到服务器，完成保存操作
				var url = "/sys/workorderAdd";
				$.post(url,rowData,function(data){
					console.log(data)
					if(data === "Y"){
						//录入成功
						$.messager.alert("提示信息","工作单信息录入成功！","info");
					}else{
						//录入失败
						$.messager.alert("提示信息","工作单信息录入失败！","warning");
					}
				});
			}
		});
	});

	function doDblClickRow(rowIndex, rowData){
		$('#grid').datagrid('beginEdit',rowIndex);
		editIndex = rowIndex;
	}
</script>
</head>
<body class="easyui-layout">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>