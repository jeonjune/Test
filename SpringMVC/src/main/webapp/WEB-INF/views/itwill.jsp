<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/WEB-INF/views/itwill.jsp</h1>
	
	msg(jsp) = <%=request.getParameter("msg") %> <hr>
	msg(el) = ${param.msg } <hr>
	
	msg(el) = ${msg } <hr>
	msg(el) = ${requestScope.msg } <hr>
	msg(el) = ${sessionScope.msg } <hr>
<%-- 	msg(el) = ${(영역객체.)msg } <hr> --%>

	age : ${age }<hr>
	
	<h2> 전달받은 객체 정보 출력 </h2>
	VO : ${vo }<hr>
	VO : ${memberVO }<hr>
	userid : ${memberVO.userid }<hr>
	VO : ${requestScope.memberVO }<hr>
<%-- 	VO : ${requestScope }<hr> --%>
	<hr>
	${DBVO }<hr>
	
	<h2> /doC2 호출 결과 </h2>
	파라메터 VO : ${memberVO }<hr>
	DB VO : ${memberVO }<hr>
	
	
</body>
</html>