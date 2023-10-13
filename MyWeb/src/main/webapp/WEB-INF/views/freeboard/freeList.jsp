<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
    <%@ include file="../include/header.jsp" %>
    
    
    <section>
        <div class="container-fluid">
            <div class="row">
                <!--lg에서 9그리드, xs에서 전체그리드-->   
                <div class="col-lg-9 col-xs-12 board-table">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    <hr>
                    
                    <!--form select를 가져온다 -->
            <form action="/myweb/freeboard/freeList">
		    		<div class="search-wrap">
                       <button type="submit" class="btn btn-info search-btn">검색</button>
                       <input type="text" name="keyword" class="form-control search-input" value="${pc.page.keyword}">
                       <select  name="condition" class="form-control search-select">
                            <option value="title" ${pc.page.condition == 'title' ? 'selected': '' }>제목</option>
                            <option value="content" ${pc.page.condition == 'content' ? 'selected': '' }>내용</option>
                            <option value="writer" ${pc.page.condition == 'writer' ? 'selected': '' }>작성자</option>
                            <option value="titleContent" ${pc.page.condition == 'titleContent' ? 'selected': '' }>제목+내용</option>
                       </select> <!-- 사용자가 검색한 값이 검색 버튼을 눌러도 지워지지 않고 유지된다 ! -->
                    </div>
		    </form>
                   
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th class="board-title">제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                           
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="vo" items="${boardList}">
	                            <tr>
	                                <td>${vo.bno}</td>
	                                <td><a href="/myweb/freeboard/content?bno=${vo.bno}&pageNo=${pc.page.pageNo}&amount=${pc.page.amount}&keyword=${pc.page.keyword}&condition=${pc.page.condition}">${vo.title}</a></td>
	                                <td>${vo.writer}</td>
	                                <td>${vo.date}</td>
	                               <!-- 게시물의 수만큼 반복문을 돌려서 리스트를 보여준다.  -->
	                            </tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>


                    <!--페이지 네이션을 가져옴-->
                    <!-- creator이 pc라는 이름으로 넘어옴  -->
		    <form action="/myweb/freeboard/freeList" name="pageForm">
                    <div class="text-center">
                    <hr>
                    <ul id = "pagination" class="pagination pagination-sm">
                    
                    <c:if test="${pc.prev}"><!-- true면 if문 실행으로 이전 버튼이 보인다. -->
                        <li><a href="#" data-pagenum="${pc.begin-1}">이전</a></li>
                    </c:if>
                        
          				<c:forEach var="num"  begin="${pc.begin}" end="${pc.end}" >          
                        	<li class="${pc.page.pageNo == num? 'active' : ''}"><a href="#" data-pagenum ="${num}">${num}</a></li>
                            <!--사용자가 요청한 페이지 넘버가 반복문 돌리고 있는 num의 값과 일치한다면 active 발동-->
                        </c:forEach> 
                        
                        
                    <c:if test="${pc.next}">
                        <li><a href="#" data-pagenum="${pc.end+1}">다음</a></li>
                   </c:if>
                    </ul>
                    <button type="button" class="btn btn-info" onclick="location.href='/myweb/freeboard/freeRegist'">글쓰기</button>
                    </div>

                    <input type="hidden" name="pageNo" value="${pc.page.pageNo}">
                    <input type="hidden" name="amount" value="${pc.page.amount}">
                    <input type="hidden" name="keyword" value="${pc.page.keyword}">
                    <input type="hidden" name="condition" value="${pc.page.condition}">
                    <!--기본값을 히든으로 숨겨놓음, 그래야 데이터가 유지된다.-->


		    </form>

                </div>
            </div>
        </div>
	</section>
	
	<%@ include file="../include/footer.jsp" %>


    <script>
        //브라우저 창이 로딩이 완료된 후에 실행할 것을 보장하는 이벤트
        window.onload = function() {
            //우리가 이벤트 걸어야 할 7개의 버튼의
            // 부모 버튼 ul에 이벤트 전파 시켜야함 !

            //사용자가 페이지 관련 버튼을 클릭했을 때 (이전, 다음, 1,2,3...)
            //a태그에 href에다가 각각 다른 url을 작성해서 요청을 보내기가 귀찮다
            //클릭한 버튼이 무엇인지를 확인해서 그 버튼에 맞는 페이지 정보를 
            //자바스크립트로 끌고와서 요청 보내주겠다.
            document.getElementById('pagination').addEventListener('click', e =>{
                //e->이벤트가 발생하면 e쪽으로 오게됨.
                if(!e.target.matches('a')) {//a태그가 아니면 중지
                    return;
                }
                //a태그의 고유 기능 href를 중지시킨다. 
                e.preventDefault(); //a태그의 고유 기능 중지

                //이쪽으로 왔다면 a태그가 맞다
                //현재 이벤트가 발생한 요소(버튼)의
                //data-pagenum의 값을 얻어서 변수에 저장

                //data- 으로 시작하는 속성값을 dataset 프로퍼티로 쉽게 끌고 올 수 있다.
                const value = e.target.dataset.pagenum;

                //페이지 버튼들을 감싸고 있는 form태그를 지목하여
                //그 안에 숨겨져 있는 input 태그의 value에
                //위에서 얻은 data-pagenum의 값을 삽입한 후 submit

                document.pageForm.pageNo.value = value;
                document.pageForm.submit(); //페이지 번호와 한화면에 보여질 페이지 수가 넘어가게 된다.


            });


            const msg = '${msg}'; //모델에 담긴 msg 변수에 넣어줌
            if(msg == 'searchFail') { //msg의 값이 searchFail이라면.
                alert('검색 결과가 없었습니다.');
            }


        }


    </script>
