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
<h1>loginForm.jsp</h1>
<c:url value="j_spring_security_check" var="loginUrl"></c:url>

${loginUrl }
<form action="${loginUrl }" method="post">
	<c:if test="${param.error != null }">
	<p>
		LogIn Error! <br>
		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL }">
			message : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message }"/>
		</c:if>
	</p>
	</c:if>
	ID : <input type="text" name="j_username"> <br />
	PW : <input type="text" name="j_password"> <br />
	<input type="submit" value="LOGIN"> <br />

</form>
</body>
</html>