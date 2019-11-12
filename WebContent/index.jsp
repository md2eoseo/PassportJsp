<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@	page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- 각 버전 중 가장 최신 표준 모드를 선택하는 문서 모드 -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <!-- 모바일기기의 물리적 화면 해상도를 웹페이지가 인식할 수 있게 해준다 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title> Passport </title>

    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/main_style.css?after">
    <!-- <script type="text/javascript" src="js/prefixfree.min.js"></script> -->

    <style>
        @import url('https://fonts.googleapis.com/css?family=Poiret+One&display=swap');
    </style>
    </head>
<body>

	<header>
        <!-- logo -->
        <h1>
            <div id="logo"><a href="/Passport/index.jsp"><i class="fa fa-book"></i> Passport</a></div>
        </h1>

        <!-- sign in & up -->
        <div id="user_info">
            <div id="user_sign">
                <div><a href="/Passport/memberLogin.jsp" class="link"><i class="fa fa-sign-in"></i> 로그인</a></div>
                <div><a href="/Passport/memberInsert.jsp" class="link">/회원 가입</a></div>
            </div>
            <div id="signinuser_info">

            </div>
        </div>

        <!-- search bar -->
        <form id="searchbar" name="searchbar" class="searchbar" action="https://www.google.com/search">
            <div>
                <input type="text" name="qt" id="search_text" placeholder="Google 검색">
                <input type="hidden" name="q">
                <span>
                    <button id="search_submit" type="submit"><i class="fa fa-search"></i></button>
                </span>
            </div>
        </form>

        <!-- menu -->
        <div class="menu_group">
            <a href="#" class="link"><i class="nav-icon fa fa-columns"></i> 전체 글</a>
            <a href="#" class="link"><i class="nav-icon fa fa-list-ul"></i> 내 글</a>
            <a href="#" class="link"><i class="nav-icon fa fa-bandcamp"></i> 랜덤</a>
            <a href="#" class="link"><i class="nav-icon fa fa-info-circle"></i> 정보</a>
        </div>

        <!-- sns -->
        <div class="sns_group">
            <a href="/Passport/index.jsp" class="link"><i class="nav-icon fa fa-home"></i></a>
            <a href="#" class="link"><i class="nav-icon fa fa-facebook"></i></a>
            <a href="#" class="link"><i class="nav-icon fa fa-instagram"></i></a>
        </div>
    </header>

    <section>
        <div class="">

            <article class="article_block article_top">#1</article>
            <article class="article_block article_top">#2</article>
            
            <article class="member_block">
            
            	<div class="welcome_block">웰컴웰컴</div>
            	<div class="error_block">${ error }</div>
            	<div class="info_block">${ id }${ info }</div>
            	
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