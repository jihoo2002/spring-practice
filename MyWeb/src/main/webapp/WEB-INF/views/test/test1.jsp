<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    이름: <input type="text" name="name"><br>
    나이: <input type="text" name="age"><br>
    취미: 
    <input type="checkbox" name="hobby" value="soccer">축구
    <input type="checkbox" name="hobby" value="music">음악감상
    <input type="checkbox" name="hobby" value="game">게임

    <button type="button" id="send">요청보내기!</button>

    <script>
        const $sendBtn = document.getElementById('send');

        $sendBtn.onclick = function(e) {

            const name = document.querySelector('input[name=name]').value;
            //input 태그 중 name이 name인 태그의 값을 name이라는 변수로 저장
            
            const age = document.querySelector('input[name=age]').value;

            const $hobby = document.querySelectorAll('input[name=hobby]'); //값 모두를 얻어옴
            //html 요소를 얻어온 것이기에 $ 요소 붙여줌

            //querySeletorAll의 리턴값은 NodeList라는 유사 배열 형태
            //배열의 메서드를 사용하기 위해 실제 배열로 변환하는 문법.
          

            const arr = []; //체크가 된 요소값를 넣기 위한 배열.
            //= for(let h :$hobby)
            //...$유사 배열 ->  실제 배열로 바꿔준다. 
            [...$hobby].forEach($check => { //얻어온 요소들 / 요소를 하나씩 비교함
                //향상 for문과 비슷함
                if($check.checked) {
                    // hobby가 체크가 되어있다면 true
                    arr.push($check.value);
                }
            });

                console.log(name); //확인하려고 !
                console.log(age);
               console.log(arr);

                //#http 요청을 서버로 보내는 방법
                
               // 1.XMLHTTPRequest 객체 생성
                const xhr =new XMLHttpRequest();

                //2. HTTP요청 설정(요청 방식, 요청 URL)
                /*
                -요청 방식(전송 방식임 get/post이외에도 더 있움)
                A. GET -조회
                B. POST -등록
                C. PUT - 수정
                D. DELETE - 삭제
                */

                xhr.open('POST', '/myweb/rest/object'); //등록과 등록을 보낼 경로


                //3.서버로 전송할 데이터를 제작합니다.
                //제작하는 데이터의 형식은 JSON 형태여야 합니다.
	//json은 데이터를 표기하기 위한 수단이지 단어가 아님. 
                const data = {
                    'name': name,
                    'age': age,
                    'hobby' : arr

                }; //객체를 생성 {변수명 : 값}
                //아직 json 아니라 javascript객체임


                //js ->json으로 변경 : JSON.stringify(arg)
                const sendData = JSON.stringify(data);

                //전송할 데이터의 형태가 어떠한지를 요청 헤더에 지정
                xhr.setRequestHeader('content-type', 'application/json');

                //4. 서버에 요청 전송
                xhr.send(sendData);

                //5. 응답된 정보 확인
                xhr.onload = function() {
                    //응답 코드
                    console.log(xhr.status);
                    //응답 데이터 확인
                    console.log(xhr.response);
                }
                

        
            };

        

    
    </script>
</body>
</html>




