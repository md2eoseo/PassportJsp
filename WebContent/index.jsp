<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
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
    <link rel="stylesheet" href="css/main_style.css">
    <!-- <script type="text/javascript" src="js/prefixfree.min.js"></script> -->

    <style>
        @import url('https://fonts.googleapis.com/css?family=Poiret+One&display=swap');
    </style>
    </head>
<body>

	<header>
        <!-- logo -->
        <h1>
            <div id="logo"><a href="#"><i class="fa fa-book"></i> Passport</a></div>
        </h1>

        <!-- sign in & up -->
        <div id="user_info">
            <div id="user_sign">
                <div><a href="/login/auth?redirectUrl=%2F" class="link"><i class="fa fa-sign-in"></i> Log In</a></div>
                <div><a href="/user/register" class="link">/sign up</a></div>
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
            <a href="#" class="link"><i class="nav-icon fa fa-home"></i></a>
            <a href="#" class="link"><i class="nav-icon fa fa-facebook"></i></a>
            <a href="#" class="link"><i class="nav-icon fa fa-instagram"></i></a>
        </div>
    </header>

    <section>
        <div class="">

            <article class="article_block article_top">#1</article>
            <article class="article_block article_top">#2</article>
            <article class="signup_block">Sign up!!</article>
            <article class="article_block">#3</article>
            <article class="article_block">#4</article>

        </div>
    </section>

</body>
</html>