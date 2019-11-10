<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
</head>
<body>
	<h3>Sign up</h3>
	
	${ error }
	
	<form action="memberInsert.do" method="POST">
		ID : <input type="text" name="id"> <br>
		비밀번호 : <input type="password" name="password"> <br>
		이름 : <input type="text" name="name"> <br>
		Email : <input type="text" name="mail"> <br>
		
		<input type="submit" value="Sign up">
	</form>
</body>
</html>