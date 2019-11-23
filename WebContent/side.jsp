<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- 각 버전 중 가장 최신 표준 모드를 선택하는 문서 모드 -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <!-- 모바일기기의 물리적 화면 해상도를 웹페이지가 인식할 수 있게 해준다 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/main_style.css?aa">
    <style>
    	@import url('https://fonts.googleapis.com/css?family=Poiret+One&display=swap');
	</style>
</head>
<body>
	<header>
        <!-- logo -->
        <h1>
            <div id="logo"><a href="/Passport/index.do"><i class="fa fa-book"></i> Passport</a></div>
        </h1>

        <!-- sign in & up -->
        <div class="user_info">
            <div class="user_do">
            	<c:if test="${ sessionScope.userid == null }">
            		<div><a href='/Passport/memberLogin.jsp' class='link'><i class='fa fa-sign-in'></i> 로그인</a></div>
            		<div><a href='/Passport/memberInsert.jsp' class='link'>/회원 가입</a></div>
            	</c:if>
            	<c:if test="${ sessionScope.userid != null }">
            		<div><a href='/Passport/memberLogout.do' class='link'><i class='fa fa-sign-out'></i> 로그아웃</a></div>
            		<div><a href='/Passport/post.jsp' class='link'><i class='fa fa-book'></i> 글쓰기</a></div>
            	</c:if>
            </div>
            <div class="signinuser_info">

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
            <a href="/Passport/postList.do" class="link"><i class="nav-icon fa fa-columns"></i> 전체 글</a>
            <a href="/Passport/postMyList.do" class="link"><i class="nav-icon fa fa-list-ul"></i> 내 글</a>
            <a href="#" class="link"><i class="nav-icon fa fa-bandcamp"></i> 랜덤</a>
            <a href="#" class="link"><i class="nav-icon fa fa-info-circle"></i> 정보</a>
        </div>

        <!-- sns -->
        <div class="sns_group">
            <a href="/Passport/index.do" class="link"><i class="nav-icon fa fa-home"></i></a>
            <a href="#" class="link"><i class="nav-icon fa fa-facebook"></i></a>
            <a href="#" class="link"><i class="nav-icon fa fa-instagram"></i></a>
        </div>
    </header>
</body>
</html>