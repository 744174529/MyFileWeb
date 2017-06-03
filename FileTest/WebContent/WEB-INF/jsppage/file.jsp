<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/FileTest/upload" method="post" enctype="multipart/form-data">  
         文件1: <input type="file" name="myfiles"/><br/>     
          文件2: <input type="file" name="myfiles"/><br/>     
          文件3: <input type="file" name="myfiles"/><br/>     
        	<input type="submit" value="上传">  
	</form> 
	
	<form action="/FileTest/select">
		<input type="submit" value="查看所有资源"/>
	</form> 
	<br><br>
	<a href="/FileTest/loginout">退出登录</a>
</body>
</html>