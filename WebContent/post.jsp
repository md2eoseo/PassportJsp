<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	<title> Passport | 글쓰기 </title>
    <style type="text/css">
    	body {
    		background: white;
    	}
        #title {
            height : 16;
            font-family :'돋움';
            font-size : 12;
            text-align :center;
        }
    </style>
    <script type="text/javascript">
    function check() {
    	  if(post.board_subject.value == "") {
    	    alert("제목을 입력해 주세요.");
    	    post.board_subject.focus();
    	    return false;
    	  } else return true;
    	}
    </script>
</head>
<body style="background: white;">
	<c:if test="${ sessionScope.userid == null }">
		<script type="text/javascript">
			alert("접근 권한이 없습니다.");
			location.href = "index.do";
		</script>
	</c:if>
	
	<jsp:include page="side.jsp" flush="false"/>
	
<section>
	<br>
    <b><font size="6">글쓰기</font></b>
    <br><br><br>
    
    <form name="post" action="postCreate.do" method="POST" enctype="multipart/form-data" onsubmit="return check()">
    	<input type="hidden" name="board_id" value="${ sessionScope.userid }">
    	<table width="100%" border="1" bordercolor="lightgray" align="center" style="font: 15px '굴림';">
        	<tr>
        	    <td id="title" width="10%">글쓴이</td>
	            <td>${ sessionScope.userid }</td>
    	    </tr>
        	    <tr>
            	<td id="title">제 목</td>
	            <td><input name="board_subject" type="text" size="100%" maxlength="100" /></td>        
	        </tr>
    	    <tr>
        	    <td id="title">내 용</td>
	            <td><textarea name="board_content" cols="150" rows="30" maxlength="2000"></textarea></td>        
        	</tr>
        	<tr>
            	<td id="title">파일첨부</td>
	            <td><input type="file" name="board_file" /></td>    
        	</tr>
	        <tr align="center" valign="middle">
    	        <td colspan="5">
        	        <input type="reset" value="리셋" >
            	    <input type="submit" value="등록" >         
     	       </td>
        	</tr>
    	</table>    
    </form>
    <div class="error_block">${ error }</div>
	<div class="info_block">${ info }</div>
</section>
</body>
</html>