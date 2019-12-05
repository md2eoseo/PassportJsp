<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	<title> Passport </title>
</head>
<body>

	<jsp:include page="side.jsp" flush="false"/>

    <section>
        <div class="">
        
        	<c:forEach var="post" items="${requestScope.list}" begin="0" end="1">
        		<a href="postRead.do?num=${post.board_num}">
        		<c:choose>
	        		<c:when test="${ post.board_file == null }"><article class="article_block article_top"></c:when>
	        		<c:when test="${ post.board_file != null }"><article class="article_block article_top" 
	        		style="background-image: url('FileDownload.do?file_name=${post.board_file}');background-size: cover;"></c:when>
        		</c:choose>
        		<span class="article_subject">${post.board_subject}</span><br>
        		<span class="article_id">${post.board_id}, ${post.board_date}</span></article></a>
			</c:forEach>
            
            <c:if test="${ sessionScope.userid == null }">
				<article class="member_block">
					<div class="welcome_block">어서오세요.<br><span style="font-size:18px;">패스포트에서 추억을 공유하세요.</span></div>
					<div class="error_block">${ error }</div>
					<div class="info_block">${ info }</div>

					<div class="login_block">
						<h3>로그인</h3>
						<form action="memberLogin.do" method="POST" class="login_form">
							<table>
								<tr>
									<td>아이디</td>
								    <td><input type="text" name="id" value=${ id }></td>
								</tr>
								<tr>
									<td>비밀번호</td>
								    <td><input type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="로그인" class="submit_btn"></td>
								</tr>
							</table>
						</form>
					</div>
					<hr />

					<div class="signup_block">
						<h3>회원가입</h3>
						<form action="memberInsert.do" method="POST" class="signup_form">
							<table>
								<tr>
									<td>아이디</td>
								    <td><input type="text" name="id"></td>
								    <td>(10자 제한)</td>
								</tr>
								<tr>
									<td>비밀번호</td>
								    <td><input type="password" name="password"></td>
								    <td>(10자 제한)</td>
								</tr>
								<tr>
									<td>이름</td>
								    <td><input type="text" name="name"></td>
								    <td>(10자 제한)</td>
								</tr>
								<tr>
									<td>이메일</td>
								    <td><input type="text" name="mail"></td>
								    <td>(20자 제한)</td>
								</tr>
								<tr>
									<td colspan="3"><input type="submit" value="회원가입" class="submit_btn"></td>
								</tr>
							</table>
						</form>
					</div>
				</article>
			</c:if>
			<c:if test="${ sessionScope.userid != null }">
				<article class="member_block">
					<div class="welcome_block">어서오세요.<br><span style="font-size:18px;">패스포트에서 추억을 공유하세요.</span></div>
					<div class="error_block">${ error }</div>
					<div class="info_block">${ info }</div>
					<c:forEach var="mypost" items="${requestScope.mylist}" begin="0" end="5">
		        		<a href="postMyRead.do?num=${mypost.board_num}"><article class="myarticle_block">
		        		<span class="myarticle_subject">${mypost.board_subject}</span><br>
		        		<span class="myarticle_id">${mypost.board_id}, ${mypost.board_date}</span></article></a>
					</c:forEach>
				</article>
			</c:if>
			
			<c:forEach var="post" items="${requestScope.list}" begin="2" end="3">
        		<a href="postRead.do?num=${post.board_num}">
        		<c:choose>
	        		<c:when test="${ post.board_file == null }"><article class="article_block"></c:when>
	        		<c:when test="${ post.board_file != null }"><article class="article_block" 
	        		style="background-image: url('FileDownload.do?file_name=${post.board_file}');background-size: cover;"></c:when>
        		</c:choose>
        		<span class="article_subject">${post.board_subject}</span><br>
        		<span class="article_id">${post.board_id}, ${post.board_date}</span></article></a>
			</c:forEach>

        </div>
    </section>

</body>
</html>