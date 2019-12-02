<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("br", "<br/>"); pageContext.setAttribute("cn", "\n"); %>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	<title> Passport | ${post.board_subject} </title>
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
				<c:if test="${ post.board_modate != null }">
					<tr>
						<td id="title">수정일</td>
						<td>${post.board_modate}</td>
					</tr>
				</c:if>
				<tr>
					<td id="title">글쓴이</td>
					<td>${post.board_id}</td>
				</tr>
				<tr>
					<td id="title">제 목</td>
					<td>${post.board_subject}</td>
				</tr>
				<tr>
					<td id="title" height="400">내 용</td>
					<td style="word-break: break-all;">${fn:replace(post.board_content, cn, br)}</td>
				</tr>
				<tr>
					<td id="title">첨부파일</td>
					<td><a href='FileDownload.do?file_name=${post.board_file}'>${post.board_file}</a></td>
				</tr>
				<tr align="center" valign="middle">
					<td colspan="5">
						<c:if test="${ sessionScope.userid == post.board_id }">
							<input type="button" value="수정" onclick="javascript:location.href='postUpdateForm.do?num=${post.board_num}&page=${pageNum}'">
							<input type="button" value="삭제" onclick="javascript:location.href='postDelete.do?post=${post.board_num}'">
						</c:if>
						<input type="button" value="댓글">
						<input type="button" value="목록" onclick="javascript:location.href='postMyList.do?page=${pageNum}'">
					</td>
				</tr>
			</table>
	</div>
</body>
</html>