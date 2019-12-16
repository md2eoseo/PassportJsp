<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<title> Passport | 글 수정 </title>
<style type="text/css">
    	body {
    		background: white;
    	}
        #title {
            height : 16;
            font-family :'Roboto';
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
	<c:if test="${ sessionScope.userid } == ${ post.board_id }">
		<script type="text/javascript">
			alert("접근 권한이 없습니다.");
			location.href = "index.do";
		</script>
	</c:if>

	<jsp:include page="side.jsp" flush="false" />

	<section>
		<br> <b><font size="6">글 수정</font></b> <br><br><br>

		<form name="postUpdate" action="postUpdate.do?type=${type}&num=${boardNum}&page=${page}"
			method="POST" enctype="multipart/form-data" onsubmit="return check()">
			<input type="hidden" name="board_num" value="${post.board_num}" /> <input
				type="hidden" name="existing_file" value="${post.board_file}" />
			<table width="100%" border="1" bordercolor="lightgray" align="center"style="font: 15px '굴림';">
				<tr>
					<td id="title">글쓴이</td>
					<td>${post.board_id}</td>
				</tr>
				<tr>
					<td id="title">제 목</td>
					<td><input name="board_subject" type="text" size="100%"
						maxlength="100" value="${post.board_subject}" /></td>
				</tr>
				<tr>
					<td id="title">내 용</td>
					<td><textarea name="board_content" cols="150" rows="30">${post.board_content}</textarea></td>
				</tr>
				<tr>
					<td id="title">기존파일</td>
					<td>${post.board_file}</td>
				</tr>
				<tr>
					<td id="title">첨부파일</td>
					<td><input type="file" name="board_file" /></td>
				</tr>
				<tr align="center" valign="middle">
					<td colspan="5">
						<input type="reset" value="리셋">
						<input type="submit" value="수정">
						<input type="button" value="목록" onclick="javascript:location.href='postRead.do?type=${type}&num=${boardNum}'">
					</td>
				</tr>
			</table>
		</form>
		<div class="error_block">${ error }</div>
		<div class="info_block">${ info }</div>
	</section>
</body>
</html>