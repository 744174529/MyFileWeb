<%@page import="com.peng.Bean.MyFile"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		List<MyFile> list = (List)request.getAttribute("filelist");
		for(int i=0;i<list.size();i++){
			MyFile file = list.get(i);
			out.print(file.getName());
	%>
		<form action="/FileTest/download" method="POST">
			<input type="hidden" name="path" value="<%=file.getPath() %>">
			<input type="submit" value="下载">
		</form>
		
	<% 		
		}
	%>
		
</body>
</html>