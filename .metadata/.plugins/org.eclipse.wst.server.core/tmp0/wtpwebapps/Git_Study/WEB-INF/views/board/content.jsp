<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board2 예제 글내용 보기 </title>
<style type="text/css">
 table{
  width:70%;
  line-height: 40px;
 }
</style>
</head>
<body>
    <h2>글내용보기</h2>
    <table align="center" width="800">
      <tr>
        <td height="40px" align="center">
          <a href="edit.do?num=${boardDto.num }">글수정</a>&nbsp;
          <a href="delete.do?num=${boardDto.num }">글삭제</a>	&nbsp;
          <a href="reply.do?num=${boardDto.num }&flag=1">답글 쓰기</a>	&nbsp;
          <a href="WriteForm.do?flag=0">글쓰기</a>&nbsp;
          <a href="list.do">리스트</a>
        </td>
      </tr>
    </table>
	
	<table border="10" bgColor="margenta">
	
	  <tr height="40px">
        <td>글쓴이</td>
        <td>${boardDto.writer}</td>
      </tr>
      
      <tr height="40px">
        <td>글제목</td>
        <td>${boardDto.subject}</td>
      </tr>
	 
	 <tr>
        <td>이메일</td>
        <td>${boardDto.email}</td>
      </tr>
      <tr>
        <td>글내용</td>
        <td><pre>${boardDto.content}</pre></td>
      </tr>
      
      <tr>
        <td>날짜</td>
        <td>
          <%-- <fmt:formatDate value="${boardDto.wdate}" type="date" dateStyle="default"/> --%>
          <fmt:formatDate value="${boardDto.wdate}" pattern="yyyy년 MM월 dd일"/>
        </td>
      </tr>
      
	  <tr>
        <td>조회수</td>
        <td>${boardDto.readcount}</td>
      </tr>
	</table>
</body>
</html>