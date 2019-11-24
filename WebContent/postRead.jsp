<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title> ${post.board_subject} </title>
    <style type="text/css">
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
            font: 12px '돋움';
            margin-left: 280px;
        }
        #post {
            text-align :center;
        }
        #title {
            height : 16;
            font-family :'돋움';
            font-size : 12;
            text-align :center;
		}
    </style>
    <script type="text/javascript">
        function writeForm(){
            location.href="post.jsp";
        }
    </script>
</head>
<body>    

	<jsp:include page="side.jsp" flush="false"/>

	<div id="wrap">
		<br><br>
			<table id="post" width="800" border="2" bordercolor="black" bgcolor="white">
				<tr>
					<td id="title" width="100">작성일</td>
					<td>${post.board_date}</td>
				</tr>
				<tr>
					<td id="title">작성자</td>
					<td>${post.board_id}</td>
				</tr>
				<tr>
					<td id="title">제 목</td>
					<td>${post.board_subject}</td>
				</tr>
				<tr>
					<td id="title" height="400">내 용</td>
					<td>${post.board_content}</td>
				</tr>
				<tr>
					<td id="title">첨부파일</td>
					<td><a href='FileDownloadAction.bo?file_name=${post.board_file}'>${post.board_file}</a></td>
				</tr>
				<tr align="center" valign="middle">
					<td colspan="5">
						<input type="button" value="댓글">
						<c:if test="${ pageNum != null }">
							<input type="button" value="목록" onclick="javascript:location.href='postList.do?page=${pageNum}'">
						</c:if>
						<c:if test="${ pageNum == null }">
							<input type="button" value="목록" onclick="javascript:location.href='index.do'">
						</c:if>
					</td>
				</tr>
			</table>
	</div>
</body>
</html>