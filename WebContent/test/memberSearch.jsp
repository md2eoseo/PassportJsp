<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Search</title>
</head>
<body>

	<h3>Member Search</h3>
	
	${ error }
	
	<form action="memberSearch.do" method="POST">
		ID : <input type="text" name="id">
		<input type="hidden" name="job" value="search">
		<input type="submit" value="search">
	</form>
	
</body>
</html>