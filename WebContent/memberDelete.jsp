<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.passport.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Member</title>
</head>
<body>

	<h3>Delete Member</h3>
	
	${ error }
	
	<form action="memberSearch.do" method="POST">
		ID : <input type="text" name="id">
		<input type="hidden" name="job" value="delete">
		<input type="submit" value="search">
	</form>
	
	<% MemberVO member = (MemberVO)request.getAttribute("member");
		if(member != null) { %>
			<h3>Member Search Result</h3>
			${member.id } / ${member.password } / ${member.name } / ${member.mail } <p>
			
			<form action="memberDelete.do" method="POST">
				<input type="hidden" name="id" value="${member.id }">
				<input type="submit" value="delete">
			</form>
			
		<%} else { %>
			${result} <p>
		<%} %>
		
</body>
</html>