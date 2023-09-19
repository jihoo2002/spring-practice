<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>@ModelAttribute로 여러개의 값을 전송하기</h2>
	<p>
	 	아이디: ${info.userId}<br>
	 	이름: ${info.userName} <!-- vo 객체가 저장된 info는 .을 통해 값을 꺼낸다. -->
	 	
	</p>
	
</body>
</html>