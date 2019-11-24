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
        		<a href="postRead.do?num=${post.board_num}"><article class="article_block article_top">
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
						<form action="memberLogin.do" method="POST">
							ID : <input type="text" name="id"> <br>
							비밀번호 : <input type="password" name="password"> <br>
							<input type="submit" value="로그인" class="submit_btn">
						</form>
					</div>
					<hr />

					<div class="signup_block">
						<h3>회원가입</h3>
						<form action="memberInsert.do" method="POST">
							ID : <input type="text" name="id"> (10자 제한)<br>
							비밀번호 : <input type="password" name="password"> (10자 제한)<br>
							이름 : <input type="text" name="name"> (10자 제한)<br>
							Email : <input type="text" name="mail" class="input_mail"> (20자 제한)<br> 
							<input type="submit" value="회원가입" class="submit_btn">
						</form>
					</div>
				</article>
			</c:if>
			<c:if test="${ sessionScope.userid != null }">
				<article class="member_block">
					<div class="welcome_block">어서오세요.<br><span style="font-size:18px;">패스포트에서 추억을 공유하세요.</span></div>
					<div class="error_block">${ error }</div>
					<div class="info_block">${ info }</div>
				</article>
			</c:if>
			
			<c:forEach var="post" items="${requestScope.list}" begin="2" end="3">
        		<a href="postRead.do?num=${post.board_num}"><article class="article_block">
        		<span class="article_subject">${post.board_subject}</span><br>
        		<span class="article_id">${post.board_id}, ${post.board_date}</span></article></a>
			</c:forEach>

        </div>
    </section>

</body>
</html>