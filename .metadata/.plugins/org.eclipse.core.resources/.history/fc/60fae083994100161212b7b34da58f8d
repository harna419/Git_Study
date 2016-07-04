<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board2 예제 리스트</title>

<style type="text/css">
  table{
  line-height: 40px;
  background-color: orange;
  
  }
  
  th{
  background-color: cyan;
  line-height: 50px;
  }
  
</style>

</head>
<body>
<%-- ${list } --%>

<h2 align="center">게시판 리스트</h2>

<table width="800" border="1" align="center">
<tr>
<td align="right">
<a href="WriteForm.do">글쓰기</a>
</td>
</tr>
</table>

<table width="800" border=1 align="center">
	<tr>
	 <th width="100">글쓴이</th>
	 <th width="350">글제목</th>
	 <th width="150">이메일</th>
	 <th width="200">날짜</th>
	 <th width="100">조회수</th>
	 
	</tr>

	<c:forEach var="map" items="${listReturn}">
		<tr>
		<td align="center">${map.writer}</td>
		
		<td align="left">
            <a href="content.do?num=${map.num }">  ${map.subject}</a>
        </td>
       
         
       <td align="center">${map.email}</td>
       <td align="right"><fmt:formatDate value="${map.wdate}" pattern="yyyy년 MM월 dd일"/></td>
       <td align="right">${map.readcount}</td>
       
			
		</tr>
	</c:forEach>
</table>

</body>
</html>