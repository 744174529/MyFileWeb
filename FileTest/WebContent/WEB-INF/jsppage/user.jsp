<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	Hello:
	<br>
	<%
		out.print(session.getAttribute("name"));
	%>
	<a href="/FileTest/userselect">点击进入下载界面</a>
	<br>
	<a href="/FileTest/loginout">退出登录</a>
</body>
</html>