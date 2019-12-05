<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- 각 버전 중 가장 최신 표준 모드를 선택하는 문서 모드 -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <!-- 모바일기기의 물리적 화면 해상도를 웹페이지가 인식할 수 있게 해준다 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/main_style.css?hi" />
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
    <style>
    	@import url('https://fonts.googleapis.com/css?family=Noto+Serif+KR|Roboto:700i&display=swap');
	</style>
</head>
<body>
	<header>
        <!-- logo -->
        <h1>
            <div id="logo"><a href="/Passport/index.do"><i class="fa fa-book"></i> Passport</a></div>
        </h1>

        <!-- sign in & up -->
        <div class="user_block">
            <div class="user_do">
            	<c:if test="${ sessionScope.userid == null }">
            		<a href='/Passport/memberLogin.jsp' class='link'><div><i class='fa fa-sign-in'></i> 로그인</div></a>
            		<a href='/Passport/memberInsert.jsp' class='link'><div>/ 회원가입</div></a>
            	</c:if>
            	<c:if test="${ sessionScope.userid != null }">
            		<a href='/Passport/memberLogout.do' class='link'><div><i class='fa fa-sign-out'></i> 로그아웃</div></a>
            		<a href='/Passport/post.jsp' class='link'><div><i class='fa fa-book'></i> 글쓰기</div></a>
            	</c:if>
            </div>
            <div class="user_info">
	            <c:if test="${ sessionScope.userid != null }">
	            	<br>
					<div>${ sessionScope.userid }님, 반갑습니다!</div>
					<div>${ sessionScope.user_postnum }개의 글</div>
				</c:if>
            </div>
        </div>

        <!-- search bar -->
        <form class="searchbar" action="postList.do" method="GET">
        	<input type="hidden" name="opt" value="2">
            <div>
                <input type="text" size="19" name="condition" placeholder="제목+내용 검색"/>&nbsp;
                <button type="submit"><i class="fa fa-search"></i></button>
            </div>
        </form>

        <!-- menu -->
        <div class="menu_group">
            <a href="/Passport/postList.do" class="link"><i class="nav-icon fa fa-columns"></i> 전체 글</a>
            <c:if test="${ sessionScope.userid == null }">
            	<a onclick="alert('로그인된 사용자가 없습니다!');return false;" class="link"><i class="nav-icon fa fa-list-ul"></i> 내 글</a>
            </c:if>
            <c:if test="${ sessionScope.userid != null }">
            	<a href="/Passport/postMyList.do" class="link"><i class="nav-icon fa fa-list-ul"></i> 내 글</a>
            </c:if>
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