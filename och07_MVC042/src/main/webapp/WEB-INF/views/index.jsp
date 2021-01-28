<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		String context = request.getContextPath();
	%>
	
	context : <%=context %>	<P>
	<%-- <form action="<%=context %>/studentView" method="get"> --%>
	<form action="<%=context %>/studentView3" method="get">
		이름 : <input type="text" name = "name"><br/>
		나이 : <input type="text" name="age"><br/>
		학년 : <input type="text" name="gradeNum"><br/>
		반 : <input type="text" name="classNum"><br/>
		<input type="submit" value="전송"> 
	
	</form>
	<%-- <form action="<%=context%>/studentView2.jsp" method="post --%>
</body>
</html>