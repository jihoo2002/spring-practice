package com.spring.myweb.snsboard.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.snsboard.dto.snsBoardRequestDTO;
import com.spring.myweb.snsboard.dto.snsBoardresponseDTO;
import com.spring.myweb.snsboard.service.SnsBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/snsboard")
@RequiredArgsConstructor
@Slf4j
public class SnsBoardController {

	private final SnsBoardService service;
	
	//snsboard 페이지 열어주기
	@GetMapping("/snsList")
	public ModelAndView snsList() {
		ModelAndView mv = new ModelAndView();
		//mv.addObject("name", "value");
		mv.setViewName("/snsboard/snsList");
		return mv; 
		//restcontroller에서 클라이언트에게 직접 데이터 던지는 것이 아닌 
		//jsp 쪽으로 가고 싶다면 모델앤 뷰 객체를 사용하면 jsp쪽으로 갈수있다. 
		
	}
	
	//snsboard 글 등록 하기 
	@PostMapping()
	public String upload(snsBoardRequestDTO dto) {
		service.insert(dto);
		return "uploadSuccess";
	}
	
	//snsboard 글 목록 끌고 오기
	@GetMapping("/{page}")
	public List<snsBoardresponseDTO> getList(@PathVariable int page) {
//		System.out.println("/snsboard/getList: get");
		log.info("/snsboard/getList: get");
	return	service.getList(page); 
	}
	
	/*
    # 게시글의 이미지 파일 전송 요청
    이 요청은 img 태그의 src 속성을 통해서 요청이 들어오고 있습니다.
    snsList.jsp 페이지가 로딩되면서, 글 목록을 가져오고 있고, JS를 이용해서 화면을 꾸밀 때
    img 태그의 src에 작성된 요청 url을 통해 자동으로 요청이 들어오게 됩니다.
    요청을 받아주는 메서드를 선언하여 경로에 지정된 파일을 보낼 예정입니다.
    */
	@GetMapping("/display/{fileLoca}/{fileName}")
	public ResponseEntity<?> getImage(@PathVariable String fileLoca, @PathVariable String fileName) {
		//정상적인 상황에서는 byte 타입의 responseEntity
		//비정상적인 상황에서는 String 타입의 리턴을 할 거라서 데이터 타입을 ?로 잡음
		
//		System.out.println("fileLoca: " + fileLoca);
//		System.out.println("fileName: " + fileName);
		log.info("fileLoca: " + fileLoca);
		log.info("fileName: {}", fileName);
		
		File file = new File("C:/test/upload/" + fileLoca + "/" + fileName);
		System.out.println("파일경로: " + file.toString()); //완성된 경로
		
		//응답에 대한 여러가지 정보를 전달할 수 있는 객체 ResponseEntity
		//응답 내용, 응답이 성공했는 지에 대한 여부, 응답에 관련된 여러 설정들을 지원합니다.
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers =  new HttpHeaders(); //응답해더 객체 생성
		try {
            //probeContentType: 매개값으로 전달받은 파일의 타입이 무엇인지를 문자열로 반환.
            //사용자에게 보여주고자 하는 데이터가 어떤 파일인지에 따라 응답 상태 코드를 다르게 리턴하고
            //컨텐트 타입을 제공해서 화면단에서 판단할 수 있게 처리할 수 있다.
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			headers.add("value", "hello"); //응답에 대한 여러가지 정보를 주는 객체
			
			//ResponseEntity 객체에 전달하고자 하는 파일을 byte[]로 변환해서 전달.(파일의 손상을 막기 위해)
			//header 내용도 같이 포함, 응답 상태 코드를 원하는 형태로 전달이 가능
			
			//생성자를 이용하여 ResponseEntity 생성하는 법
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			//file을 byteArray로 담아서 result에 담음
			//객체 생성할떄 <>
		} catch (IOException e) {
			e.printStackTrace();
			//에러가 발생했을 때
			
			//static 메서드를 활용하여 ResponseEntity 객체를 생성하는 법
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return result;
	}
	
	//다운로드 요청
	@GetMapping("/download/{fileLoca}/{fileName}")
	public ResponseEntity<?> download(@PathVariable String fileLoca, 
											@PathVariable String fileName) {
		File file = new File("C:/test/upload/" + fileLoca + "/" + fileName); //파일 다운로드 시킬 경로
		ResponseEntity<byte[]> result = null;// byte로 해야  파일의 손상이 없다
		HttpHeaders header = new HttpHeaders();
		
        //응답하는 본문을 브라우저가 어떻게 표시해야 할 지 알려주는 헤더 정보를 추가합니다.
        //inline인 경우 웹 페이지 화면에 표시되고, attachment인 경우 다운로드를 제공합니다.
        
        //request객체의 getHeader("User-Agent") -> 단어를 뽑아서 확인 
        //ie: Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko  
        //chrome: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36
    
        //파일명한글처리(Chrome browser) 크롬
        //header.add("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") );
        //파일명한글처리(Edge) 엣지 
        //header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //파일명한글처리(Trident) IE
        //Header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " "));
	
	
		header.add("Content-Disposition", "attachment; filename=" + fileName ); //inline은 기본값, 
		//attachment로 주면 다운로드 용
		
		try {
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	
	
	}
	
	@GetMapping("/content/{bno}")
	public ResponseEntity<?> getContent(@PathVariable int bno) {
	return ResponseEntity.ok().body(service.getContent(bno));
		//이미지 파일만 엔터티에 담을 수 있는 건 아니다. 
	//응답상태코드와 데이터를 둘다 던질 수 있는 장점이 있다. 그냥 service를 리턴하는 것은 데이터만 던지는 것임
		
		
	}
	
	@DeleteMapping("/{bno}")
	public ResponseEntity<?> delete(@PathVariable int bno, HttpSession session) {
		//만약 jsp쪽에서 session을 전달받는다면?? 
		//-> 어차피 서버쪽에서 생성한다면 굳이 객체 생성해서 넘겨줄 필요가 없다 
		
		
		//글이 삭제되었다면 더이상 이미지도 존재할 필요가 없으므로
		//이미지도 함께 삭제
		//file객체 생성해서 생성자에 지우고자하는 경로 지정
		//메서드 delete()호출 -> 리턴타입이  boolean, 삭제 성공시 true, 실패시 false
		System.out.println("글번호: " +bno);
		String id = (String) session.getAttribute("login");
		snsBoardresponseDTO dto =  service.getContent(bno);
		
		if(id == null || !id.equals(dto.getWriter())) {
			//이부분이 true라면  //return "noAuth";
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		service.delete(bno);
		File file = new File(dto.getUploadPath() + dto.getFileLoca() + "/" + dto.getFileName());
		return file.delete() ? 
				ResponseEntity.status(HttpStatus.OK).build() 
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
		//file.delete()는 boolean 형식이기에 true or false를 뱉음 , 따라서 그에 따라 
		//success, 혹은 fail을 리턴한다.
		
		//응답상태만으로 리턴
		}
	
	//좋아요 버튼 클릭 처리
	@PostMapping("/like")
	public String likeConfirm(@RequestBody Map<String, String> params) {
		log.info("/like: post, params : {}", params);
		
		//이미 좋아요를 눌렀던 게시글을 취소하고 싶은 경우도 존재
		//좋아요 누르기와 좋아요 취소하기 위해 누른 경우를 구별하여 판단해야 한다. 
		
		return service.searchLike(params);
		
	}
	
	
	//회원이 글 목록으로 진입 시 좋아요 게시물 리스트 체크
	@GetMapping("/likeList/{userId}")
	public List<Integer> likeList(@PathVariable String userId) {
		System.out.println("사용자 아이디: " + userId);
		log.info("/snsboard/likeList: get!, userId : {}", userId);
	return	service.likeList(userId);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
