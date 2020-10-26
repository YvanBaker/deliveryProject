<%--
  Created by IntelliJ IDEA.
  User: Yvan
  Date: 2020/10/9
  Time: 19:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<%--<a href="admin/loginView">userindex</a>--%><jsp:forward page="admin/loginView"></jsp:forward>
</body>
</html>
