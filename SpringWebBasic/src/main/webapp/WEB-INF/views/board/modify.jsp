<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>${article.boardNo}번 게시물 수정</h1>
 <form method="post"><!-- 페이지 재활용한다. 똑같은 경로니까 ! -->
                    <input type="hidden" name="boardNo" value="${article.boardNo}"> <!--글번호 같이 가야 수정 가능-->

                    <ul>
                        <li>#작성자: <input type = "text" name = "writer" value="${article.writer}"></li>
                        <li>#제목: <input type = "text" name = "title" value="${article.title}"></li>
                        <li>#내용: <input type = "text" name = "content" value="${article.content}"></li>
                        <div class="btn-group">
                            <button type="submit" >수정완료</button>
                            <button type="button" onclick="history.back()">이전으로</button>
                        </div>
                    </ul>
                </form>
 
</body>
</html>