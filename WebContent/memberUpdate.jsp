<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.passport.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Update</title>
</head>
<body>

	<h3>Update Member Search</h3>
	
	${ error }
	
	<form action="memberSearch.do" method="POST">
		ID : <input type="text" name="id">
		<input type="hidden" name="job" value="update">
		<input type="submit" value="search">
	</form>
	
	<% MemberVO member = (MemberVO) request.getAttribute("member");
		if(member != null) { %>
			<form action="memberUpdate.do" method="POST">
				ID : <input type="text" name="id" readonly value="${member.id}" }> <br>
				비밀번호 : <input type="password" name="password" value="${member.password}"> <br>
				이름 : <input type="text" name="name" value="${member.name}"> <br>
				Email : <input type="text" name="mail" value="${member.mail}"> <br>
		
				<input type="submit" value="Update">
			</form>
		<%} else { %>
			${result} <p>
		<%} %>

</body>
</html>