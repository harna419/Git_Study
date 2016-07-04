<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board2 예제 수정폼</title>
<style type="text/css">
table{border:10px; background-color: margetnta; width:70%;
  line-height: 40px;
}

</style>
</head>
<body bgcolor="orange">
<form method="post" action="edit.do">
	
<table>
<tr>
   <td>글쓴이</td>
   <td>
   <input type="hidden" name="num" id="num" value="${boardDto.num}"/>
   <input type="text" name="writer" id="writer" value="${boardDto.writer}" size="10"/>
   </td>
   </tr>

 <tr>
 <td>글제목</td>
   <td>
   <input type="text" name="subject" id="subject" value="${boardDto.subject}" size="30"/>
   </td>
 </tr>
 
 <tr>
 <td>이메일</td>
   <td>
   <input type="text" name="email" id="email" value="${boardDto.email}" size="30"/>
   </td>
 </tr>
 
 <tr>
 <td>글내용</td>
   <td>
   <textArea name="content" id="content" rows="10" cols="50">${boardDto.content}</textArea>
   </td>
 </tr>
 
 <tr>
 <td>암호</td>
   <td>
   <input type="password" name="pwd" id="pwd" size="10"/>
   </td>
 </tr>
 
 <tr>
  <td colspan=2 align=center>
  <input type="submit" value="수정">
  <input type="reset" value="리셋"></td>
</tr>
 
</table>
</form>
</body>
</html>