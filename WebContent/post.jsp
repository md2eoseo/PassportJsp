<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title> 글쓰기 </title>
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
    	  }
    	  else if(post.board_content.value == "") {
    	    alert("내용을 입력해 주세요.");
    	    post.board_content.focus();
    	    return false;
    	  }
    	  else return true;
    	}
    </script>
</head>
<body style="background: white;">
	<jsp:include page="side.jsp" flush="false"/>
	
<section>
	<br>
    <b><font size="6" color="gray">글쓰기</font></b>
    <br>
    
    <form name="post" action="postCreate.do" method="POST" onsubmit="return check()">
    	<input type="hidden" name="board_id" value='<%=session.getAttribute("userid")%>'>
    	<table width="100%" border="0" bordercolor="lightgray" align="center">
        	<tr>
        	    <td id="title">글쓴이</td>
	            <td><%=session.getAttribute("userid")%></td>
    	    </tr>
        	    <tr>
            	<td id="title">제 목</td>
	            <td><input name="board_subject" type="text" size="100%" maxlength="100" /></td>        
	        </tr>
    	    <tr>
        	    <td id="title">내 용</td>
	            <td><textarea name="board_content" cols="72" rows="20"></textarea></td>        
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