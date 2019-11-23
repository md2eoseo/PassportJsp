<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title> 전체 글 </title>
    <style type="text/css">
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
            font: 12px '돋움';
            margin-left: 280px;
        }
        #topForm{
            text-align: right;
        }
        #board, #pageForm, #searchForm{
            text-align: center;
        }
    </style>
</head>
<body>    

	<jsp:include page="side.jsp" flush="false"/>

	<div id="wrap">
		<br>
		<div class="error_block">${ error }</div>
		<div class="info_block">${ info }</div>
		<div id="topForm">
			<%  if(session.getAttribute("userid") != null){
            	out.println("<input type='button' value='글쓰기' onclick='writeForm()'>");
            } %>
		</div>
		<br>
		<div id="board">
			<table width="800" border="2" bordercolor="black">
				<tr height="30">
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>조회수</td>
				</tr>
				<c:if test="${maxPage == 0}">
					<tr>
						<td colspan="5">검색된 글이 없습니다...</td>
					</tr>
				</c:if>
				<c:forEach var="post" items="${requestScope.list}">
					<tr>
						<td>${post.board_num}</td>
						<td><a href="postRead.do?num=${post.board_num}&pageNum=${spage}">${post.board_subject}</a></td>
						<td><a href="#">${post.board_id}</a></td>
						<td>${post.board_date}</td>
						<td>${post.board_count}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<div id="pageForm">
			<c:if test="${startPage > 1}">
				<a href='postList.do?page=${startPage-1}'>[이전]</a>
			</c:if>

			<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
				<c:if test="${pageNum == spage}">${pageNum}&nbsp;</c:if>
				<c:if test="${pageNum != spage}"><a href='postList.do?page=${pageNum}'>${pageNum}</a>&nbsp;</c:if>
			</c:forEach>

			<c:if test="${endPage != maxPage}">
				<a href='postList.do?page=${endPage+1}'>[다음]</a>
			</c:if>
		</div>
		<br>
		<div id="searchForm">
			<form action="postList.do" method="GET">
				<select name="opt">
					<option value="0">제목</option>
					<option value="1">내용</option>
					<option value="2">제목+내용</option>
					<option value="3">글쓴이</option>
				</select>
				<input type="text" size="20" name="condition" />&nbsp;
				<input type="submit" value="검색" />
			</form>
		</div>
	</div>
</body>
</html>