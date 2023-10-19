<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>상세보기</p>
                        </div>
                        
                        <form action="${pagecontext.request.contextpath}/freeboard/modPage" method="post">
                            <div>
                                <label>DATE</label>
                                <p>${content.date}</p>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name='bno' value="${content.bno}" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='writer' value="${content.writer}" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='title' value="${content.title}" readonly>
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content' readonly>${content.content}</textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">변경</button>
                            <button type="button" class="btn btn-dark" onclick="location.href='${pagecontext.request.contextpath}/freeboard/freeList?pageNo=${p.pageNo}&amount=${p.amount}&keyword=${p.keyword}&condition=${p.condition}'">목록</button>
                    </form>
                </div>
            </div>
        </div>
        </section>
        
        <section style="margin-top: 80px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-9 write-wrap">
                        <form class="reply-wrap">
                            <div class="reply-image">
                                <img src="${pageContext.request.contextPath}/img/profile.png">
                            </div>
                            <!--form-control은 부트스트랩의 클래스입니다-->
	                    <div class="reply-content">
	                        <textarea class="form-control" rows="3" id="reply"></textarea>
	                        <div class="reply-group">
	                              <div class="reply-input">
	                              <input type="text" class="form-control" id="replyId" placeholder="이름">
	                              <input type="password" class="form-control" id="replyPw" placeholder="비밀번호">
	                              </div>
	                              
	                              <button type="button" class="right btn btn-info" id="replyRegist">등록하기</button>
	                        </div>
	
	                    </div>
                        </form>

                     <div id="replyList">
                        <!--자바 스크립트를 활용하여 반복문을 이용해서 댓글의 개수만큼 반복 표현
                        <div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='${pageContext.request.contextPath}/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class='left'>honggildong</strong> 
                                    <small class='left'>2019/12/10</small>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                </div>
                                <p class='clearfix'>여기는 댓글영역</p>
                            </div>
                        </div> -->
                        </div>
                        <button type="button" class="form-control" id="moreList" style="display: none;">더보기(페이징)</button>
                    </div>
                </div>
            </div>
        </section>
        
	<!-- 모달 -->
	<div class="modal fade" id="replyModal" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
					<h4 class="modal-title">댓글수정</h4>
				</div>
				<div class="modal-body">
					<!-- 수정폼 id값을 확인하세요-->
					<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
						    <input type="hidden" id="modalRno">
							<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
					</div>
					</div>
					<!-- 수정폼끝 -->
				</div>
			</div>
		</div>
	</div>
	
	
	 <%@ include file="../include/footer.jsp" %>

     <script>
       window.onload = function() {
        //페이지가 로딩 끝나면 실행 시킬것이다.

        document.getElementById('replyRegist').onclick = () => {
        
            //등록하기 버튼 누르면 실행
            console.log('댓글 등록 이벤트가 발생함');

            const reply = document.getElementById('reply').value; //text
            const replyId = document.getElementById('replyId').value; //id
            const replyPw = document.getElementById('replyPw').value; //pw

            //몇번 게시글인지 같이 보내줘야 함
            const bno = '${content.bno}';  //현재 게시글 번호도 보내야 한다. 
            console.log(bno);

            if(reply === '' || replyId === '' || replyPw === '') {
                return;
            }

            //reply객체로 보낼것이기에 하나로 감싸서 보낸다.
            //요청에 관련된 정보 객체 생성

            const reqObj = {
                method: 'post',
                headers : {
                    'Content-Type': 'application/json' //json형식으로 보내겠따
                },
                body: JSON.stringify( //객체를 json 형태로 바꾸어 controller쪽으로 보내쥼
                    {
                    'bno' : bno,
                    'replyText' : reply,
                    'replyId' : replyId,
                    'replyPw' : replyPw
                })
            };


            fetch('${pageContext.request.contextPath}/reply', reqObj) //이 경로로 reqObj를 보냄
                .then(res => res.text())
                .then(data => {
                    console.log('통신 성공: ', data);
                    //등록 성공했다면, 다음 등록을 위해 입력창을 비워주자.
                    document.getElementById('reply').value ='';
                    document.getElementById('replyId').value ='';
                    document.getElementById('replyPw').value ='';
                    //등록 완료 후 댓글 목록 함수를 호출해서 비동기식으로 목록 표현
                    getList(1, true); //매개변수 1보냄(pageNum임)

                });
        } // 댓글 등록 이벤트 끝.


        //더보기 버튼 클릭 이벤트 
        document.getElementById('moreList').onclick = () => {
            getList(++page, false); //현재 사용자가 존재하는 댓글 페이지보다 하나 크게, 누적시켜야 하니까 false
                                    //페이지 먼저 올려서 보내야하니까 ++먼저!
            //왜 false를 주냐?
            //더보기니까 -> 누적해서 보여줘야 하니까 -> 초기화하면 안됨!
        }

        let page = 1; //전역의 의미로 사용할 페이지 번호
        let strAdd = ''; //화면에 그려넣을 태그를 문자열의 형태로 추가할 변수.
        const $replyList = document.getElementById('replyList');

        //게시글 상세보기 화면에 처음 진입했을 시 댓글 리스트를 한번 불러오자.
        getList(1, true);  //맨 처음의 초기값이다. 

        //댓글 목록을 가져올 함수
        //getList에 매개값으로 뭘 줄거냐?
        //요청된 페이지 번호와 화면을 리셋할 것인지의 여부를 bool타입의 reset으로 받겠습니다. 

        //비동기 방식이기 때문에 페이지가 그대로 계속 머물면서 댓글이 밑에 쌓입니다.
        //때에 따라서는 댓글을 계속 누적시키는 것이 아닌, 화면을 초기화하고 새롭게 보여줘야 할 때가 있다.
        //reset -> true: 페이지를 리셋해서 새롭게 그려내기 reset-> false: 누적해서 쌓기
        function getList(pageNum, reset) {
            console.log('getList 함수 호출!');
            strAdd = ''; //새로운 내용을 작성하기 위해 변수의 값 비우기

            const bno ='${content.bno}'; //현재 게시글 번호
          
            //get 방식으로 댓글 목록을 요청(비동기) | 게시글 번호와 댓글 페이지 번호를 던져줌
            fetch('${pageContext.request.contextPath}/reply/list/' + bno + '/' + pageNum) 
            .then(res => res.json())
            .then(data => {
                console.log('서버가 전달한 map: ', data);
                
                let total = data.total; //총 댓글 수
                let replyList = data.list; // 한 사용자의 댓글 정보

                
                //응답데이터의 길이가 0과 같거나 더 작으면 함수를 종료
                if(replyList.length <= 0) return; 
                //댓글 개수 ???  답변: total도 써도 상관읎다 

                //reset을 설정해준다, true라면
                if(reset) {
                    while($replyList.firstChild) {
                        //while에 null, 0이 오면 false로 바뀜
                        $replyList.firstChild.remove();
                    }
                    page =1;
                }

                //화면 그리기 전 더보기 버튼 배치 판단
                //현재페이지 번호 * 이번 요청으로 받은 댓글 수 > 전체 댓글 수 -> 더보기 없어도 됨
                console.log('현재 페이지: ' , page);
                if(total <= page*5) {
                    //더보기 버튼이 필요없음
                    document.getElementById('moreList').style.display = 'none';
                }else {
                    document.getElementById('moreList').style.display = 'block';
                }


                //  한 화면에 보여질 양이 정해진 list 개수만큼 태크를 문자열 형태로 직접 그림.
                //중간중간에 들어가야 할 글쓴이 , 날짜, 댓글 내용은 목록에서 꺼내서 표현

                for(let i=0; i<replyList.length; i++) {
                    //계속 반복해서 붙어넣어야 하기 때문에 += 사용
                    strAdd += ` <div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='${pageContext.request.contextPath}/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class= 'left'>`+ replyList[i].replyWriter +`</strong> 
                                    <small class='left'>`+ parseTime(replyList[i].date)+`</small>
                                    <a href='`+replyList[i].replyNo + `' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                    <a href='`+replyList[i].replyNo + `' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                </div>
                                <p class='clearfix'>`+ replyList[i].replyText +`</p>
                            </div>
                        </div> `;

                }
                //댓글 수정할 때 수정 페이지로 이동하는 것이 아닌 팝업창을 하나 띄워줄 것이다. 그래서 replyNo이 필요

                //id가 replyList라는 div영역에 문자열 형식으로 모든 댓글을 추가.
                //insertAdjacentHTML: 인접한 곳에 HTML을 삽입하는 함수.
                //position문자열, 문자열 형태의 HTML)

                //reset의 결과가 false라면
               if(!reset) {
                //댓글을 누적해라 (밑으로 댓글이 누적된다.)
                    $replyList.insertAdjacentHTML('beforeend', strAdd); //삽입할 위치, 삽입할 값

               }else {
                //페이지가 리셋된 상태(댓글이 아무것도 남아있지 않은 상태)
                //위부터 댓글을 채워라
                $replyList.insertAdjacentHTML('afterbegin', strAdd);
               }


            });
        }//end getList()


        //댓글 날짜 변환 함수
        function parseTime(regDate) {
            //원하는 날짜로 객체를 생성.
            const now = new Date(); //date 객체를 생성 | 현재 날짜
            const regTime = new Date(regDate); //입력한 시간

            //getTime(): 현재 시간을 1970 1월1일 자정을 기준으로 현재까지의 시간을 밀리초로 리턴
            const gap = now.getTime() - regTime.getTime(); 
            
            let time;
            if(gap<60*60*24*1000) {
                //하루보다 작니?
                if(gap < 60*60*1000) {
                    //1시간보다 작니?
                    time = '방금 전';
                }else {
                    //1시간이 넘어갔다면
                    time = parseInt(gap/(1000*60*60)) + '시간 전';
                    //parseInt ->소수점 떨어내기
                }
            }else if(gap <60*60*24*30*1000) {
                   //한달 안쪽이라면
                    time = parseInt(gap/(1000*60*60*24)) + '일 전';
                }else {
                    //한달 바깥쪽이라면 
                    time = `\${regTime.getFullYear()}년 \${regTime.getMonth()+1}월 \${regTime.getDate()}일`;
                }


            return time;
        
        }

        



       } //window.onload




     </script>