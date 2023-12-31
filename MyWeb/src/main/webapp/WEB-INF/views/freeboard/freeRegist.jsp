﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
    <section>
       <div class="container">
            <div class="row">
                <div class="col-xs-12 content-wrap">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    
                    <form method="post"> <!--글쓰기 페이지와 글 등록 요청 페이지가 동일한 경우 액션에 경로 따로 지정 안해줘도 됨.-->
                        <table class="table">
                            <tbody class="t-control">
                                <tr>
                                    <td class="t-title">NAME</td>
                                    <td><input class="form-control input-sm " name = "writer" value="${login}" readonly></td>
                                </tr>
                                <tr>
                                    <td class="t-title">TITLE</td>
                                    <td><input class="form-control input-sm" name = "title"></td>
                                </tr>
                                <tr>
                                    <td class="t-title">COMMENT</td>
                                    <td>
                                    <textarea class="form-control" rows="7"  name = "content"></textarea>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        
                        <div class="titlefoot">
                            <button type="submit" class ="btn">등록</button>
                            <button type="button" class="btn" onclick="location.href='${pagecontext.request.contextpath}/freeboard/freeList'">목록</button>
                        </div>
                    </form>
                </div>
            </div>    
       </div>
    </section>
    
    
    <%@ include file="../include/footer.jsp" %>
    
  