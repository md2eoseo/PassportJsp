<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Member Result</title>
</head>
<body>
	<h3>Search Member Result</h3>
	
	<%
		String result = (String) request.getAttribute("result");
		if(result != null){
			out.print(result + "<p>");
		} else {
	%>
		<h3>${member.id } / ${member.password } / ${member.name } / ${member.mail }</h3>
	<%	} %>
	
	<%@ include file="home.jsp" %>
</body>
</html>