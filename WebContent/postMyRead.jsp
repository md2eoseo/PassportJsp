<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("br", "<br/>"); pageContext.setAttribute("cn", "\n"); %>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDVqpPheRu8sMG2fLszjv3I8F1HS-fvyjg"></script>
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
		#googleMap {
			border: 3px solid #000;
			width: 420px;
			height: 300px;
		    position: fixed;
			top: 30px;    
		    left: 10px;
		    margin: 0 auto 0 auto;
		    margin-left: 280px;
		}
    </style>
    <c:if test="${ post.board_markers != null }">
	    <script>
			function initialize() {
		
				var markerstring = "${post.board_markers}";
				var markers = markerstring.split(') ');
				for(var i = 0; i < markers.length; i++ )
					markers[i] = markers[i].substr(1, markers[i].length-1);
				var LatLng = new google.maps.LatLng(markers[0].split(', ')[0], markers[0].split(', ')[1]);
				
				var mapProp = {
					center : LatLng,
					zoom : 12,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};
		
				var map = new google.maps.Map(document.getElementById("googleMap"),
						mapProp);
		
				var board_marker = [];
				for(var i = 0; i < markers.length ; i++ ){
					board_marker += new google.maps.Marker({
						position : new google.maps.LatLng(markers[i].split(', ')[0], markers[i].split(', ')[1]),
						map : map,
					});
				}
			}
			google.maps.event.addDomListener(window, 'load', initialize);
		</script>
	</c:if>
    <script type="text/javascript">
        function writeForm(){
            location.href="post.jsp";
        }
    </script>
</head>
<body>    

	<jsp:include page="side.jsp" flush="false"/>

	<div id="wrap" style="float: left;">
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
					<td colspan="2"><img src="FileDownload.do?file_name=${post.board_file}" alt="${post.board_file}" height="200px"></td>
				</tr>
				<tr>
					<td colspan="2" style="word-break: break-all;" height="100">${fn:replace(post.board_content, cn, br)}</td>
				</tr>
				<tr>
					<td id="title">다운로드</td>
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
	<c:if test="${ post.board_markers != null }">
		<div id="googleMap"></div>
	</c:if>
</body>
</html>