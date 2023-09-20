<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri= "http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>Insert title here</title>
<style>
        label {
            display: block;
        }

        .score-list>li {
            margin-bottom: 10px;
        }

        .score-list>li:first-child {
            font-size: 1.2em;
            color: blue;
            font-weight: 700;
            border-bottom: 1px solid skyblue;
        }

        .del-btn {
            width: 10px;
            height: 10px;
            background: red;
            color: #fff;
            border-radius: 5px;
            margin-left: 5px;
            text-decoration: none;
            font-size: 0.7em;
            padding: 6px;
        }

        .del-btn:hover {
            background: orangered;
        }

        section.score {
            padding: 200px 50px 100px;
            font-size: 1.5em;
        }

        .list-header {
            display: flex;
            justify-content: space-between;

            width: 50%;
        }
        .list-header .sort-link-group {
            display: flex;

        }
        .list-header .sort-link-group div {
            margin-right: 20px;
        }

    </style>
    
    
    
</head>
<body>

	<div class = "wrap">
	
		<section class = "score">
			<h1>시험 점수 등록</h1>
            <form action="/basic/score/register" method="POST">
                <label>
                    # 이름: <input type="text" name="name">
                </label>
                <label>
                    # 국어: <input type="text" name="kor">
                </label>
                <label>
                    # 영어: <input type="text" name="eng">
                </label>
                <label>
                    # 수학: <input type="text" name="math">
                </label>
                <label>
                    <button type="submit">확인</button>
                    <button id="go-home" type="button">홈화면으로</button>
                </label>
            </form>
            
            <hr>
            
          <ul class="score-list">
            <li class = "list-header">
                <div class="count">총 학생 수 :${sList.size()}명</div>
                <div class="sort-link-group">
                    <div><a href="#">학번순</a></div>
                    <div><a href="#">이름순</a></div>
                    <div><a href="#">평균순</a></div>
                </div>
            </li>

           <c:forEach var="s" items= "${sList}">
            <li>
                # 학번 : ${s.stuNum}, 이름 :<a href="/basic/score/detail?stuNum=${s.stuNum}"> ${s.maskingName} </a>,
                평균 : ${s.average}, 학점: ${s.grade}
                <a href="/basic/score/remove?stuNum=${s.stuNum}" class="del-btn" >삭제</a> <!--a.del-btn-->
            </li>
           </c:forEach>

          </ul>
		
		</section>
	
	</div>	
		
		<script>
            
            const $ul = document.querySelector('.score-list');


            $ul.addEventListener('click', e => {
             //  e->  매개변수 하나 받을라고..
             //이벤트가 발생한 주체가 del-btn 클래스를 가진 a태그가 아니라면!
             if(!e.target.matches('a.del-btn')) {
                return; //이벤트 강제 종료.
             }


             e.preventDefault(); //a태그는 경로를 가지고 가는 기본 속성이 있기 때문에 
                                //a태그의 기본 기능 정지해야함 ->취소를 누르면 경로가 보이지 않아야 한다. 

             if(confirm('정말 삭제하시겠습니까?')) {
                //사용자가 확인을 누르면 삭제 진행
                //location.href == sendRedirect() 요청이 다시 들어오도록 재요청 하는 것과 같음
                location.href = e.target.getAttribute('href'); //삭제버튼 누른 그 경로 
             }else {
                return; //삭제 취소
             }
                
            });



        </script>
		
		
		
</body>
</html>