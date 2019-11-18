<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@	page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head><title> Passport </title></head>
<body>

	<jsp:include page="side.jsp" flush="false"/>

    <section>
        <div class="">

            <article class="article_block article_top">#1</article>
            <article class="article_block article_top">#2</article>
            
            <article class="member_block">
            
            	<div class="welcome_block">웰컴웰컴</div>
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
			
            <article class="article_block">#3</article>
            <article class="article_block">#4</article>

        </div>
    </section>

</body>
</html>