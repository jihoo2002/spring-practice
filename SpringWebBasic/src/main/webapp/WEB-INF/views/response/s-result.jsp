<%@page import="javax.swing.text.html.HTML.Tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
<c:if test = "${m == 'f-id'}">아이디가 존재하지 않아요</c:if>
<c:if test = "${m == 'f-pw'}">비밀번호 틀림!</c:if>
<c:if test = "${m == 'success'}">로그인 성공 !</c:if>
 -->
<h2 class = "result-title">

</h2>
<script>
/*
 브라우저가 HTML을 해석, css를 해석, javasvript 코드를 실행해서
 화면에 표현하는 과정을 렌더링이라고 합니다. 
 지금 우리가 작성하는 파일 형태 ->jsp ->서버 내에서 클래스로 변환 -> 응답은 HTML
 jsp파일이 클래스로 변환하는 과정에서 작성한 EL문법은 자바코드로 변환 -> 값을 표현
 ->응답은 HTML로 표현
 EL 표현식이 서버에서 먼저 평가되고 그 결과를 클라이언트에게 전달하여 브라우저로 표현하기 때문에
 script에서도 EL 표현이 가능합니다.
 javaScript에서 EL 표현식을 작성할 때는 문자열로 감싸주세요.
 */
	const result = '${m}';
	console.log('result:' + result);

	const $h1 = document.querySelector('.result-title');
	switch(result) {
	case 'f-id':
		$h1.textContent = '아이디가 존재하지 않습니다.';
		break;
		
	case 'f-pw':
		$h1.textContent = '비밀번호가 틀렸습니다.';
		break;
		
	case 'success':
		$h1.textContent = '로그인 성공';
		break;
	}
</script>
</body>

</html>