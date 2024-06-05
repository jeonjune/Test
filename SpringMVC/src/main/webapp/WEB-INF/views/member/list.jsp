<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/list.jsp</h1>
	${ml }<hr>
	${ml.size() }<hr>
	
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>회원가입일</td>
			<td>정보수정일</td>
		</tr>
	<c:forEach var="ml" items="${ml }">
		<tr>
			<td>${ml.userid }</td>
			<td>${ml.userpw }</td>
			<td>${ml.username }</td>
			<td>${ml.useremail }</td>
			<td>${ml.regdate }</td>
			<td>${ml.updatedate }</td>
		</tr>
	</c:forEach>
	</table>
	
	<h2><a href="/member/main">메인 페이지 이동</a></h2>
	
</body>
</html>