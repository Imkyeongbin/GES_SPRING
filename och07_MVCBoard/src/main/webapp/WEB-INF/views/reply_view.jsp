<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="reply" method="post">
		<input type="hidden" name="bId" value="${mvc_board.bId }">
		<input type="hidden" name="bGroup" value="${mvc_board.bGroup }">
		<input type="hidden" name="bStep" value="${mvc_board.bStep }">
		<input type="hidden" name="bIndent" value="${mvc_board.bIndent }">
		<table  border = "1">
			<tr>
				<td> 번호 </td><td>${mvc_board.bId }</td>
			</tr>
			<tr>
				<td> 히트 </td><td> ${mvc_board.bHit }</td>
			</tr>
			<tr>
				<td> 이름 </td><td><input type="text" name="bName" value="${mvc_board.bName }"></td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" value="답변"+"${mvc_board.bTitle }"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea rows="10" name="bContent">${mvc_board.bContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"> <input type="submit" value="답변저장">
				<a href="list">목록보기</a></td>
			</tr>
		</table>
	</form>
	
</body>
</html>