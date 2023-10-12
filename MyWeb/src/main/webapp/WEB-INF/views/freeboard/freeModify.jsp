<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp" %>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>수정하기</p>
                        </div>
                        
                        <form action="/myweb/freeboard/modify" method="post" name="updateForm">
                              
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name='bno' value="${content.bno}"readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='writer' value= "${content.writer}"readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='title' value= "${content.title}">
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content'>${content.content}</textarea>
                            </div>

                            <button id="list-btn" type="button" class="btn btn-dark">목록</button>    
                            <button id="update-btn" type="button" class="btn btn-primary">변경</button>
                            <button id="del-btn" type="button" class="btn btn-info">삭제</button>
                            <!--자바스크립트를 통해 submit 요청을 보낼 것이기에 따로 submit적용 안시킴-->
                        </form>
                                    
                </div>
            </div>
        </div>
        </section>
        
        <%@include file="../include/footer.jsp" %>
      
<script> //유효성 검증으로 위한 스크립트 작성

    //목록 이동 처리
    document.getElementById("list-btn").onclick = function() {
        location.href='/myweb/freeboard/freeList';

    }
    //form 태그는 메서드 없이 form 태그의 name으로 요소를 바로 취득할 수 있습니다.
    const $form = document.updateForm; //updateForm을 $form으로 저장!

    //수정버튼 이벤트
    document.getElementById("update-btn").onclick = function() {
        //폼 내부의 요소를 지목할 땐 name 속성으로 바로 지목이 가능합니다.
        if($form.title.value === '') { //updateForm의 title 값이 비었니?
            alert('제목은 필수 항목입니다.');
            return;
        }else if($form.content.value ==='') { //내용이 비어있다면!
            alert('내용을 뭐라도 작성해주시길 바랍니다.');
            return;
        }
        //문제가 없다면 form을 submit! -> 따로 submit 버튼을 설정안해줘도 된다.
        $form.submit();
    }
    //삭제 버튼 이벤트 처리
    document.getElementById('del-btn').onclick = () => {
        //사용자가 삭제 버튼을 클릭하면 실행시킬 문법
        if(confirm('정말 삭제하시겠습니까?')) { //confirm -> 확인과 취소 버튼을 포함시킴
            $form.setAttribute('action', '/myweb/freeboard/delete');
            //확인(true)로 온다면 액션 경로를 delete쪽으로 보낸다.
            $form.submit();
        }

    }
</script>





