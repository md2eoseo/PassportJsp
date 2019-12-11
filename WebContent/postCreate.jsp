<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDVqpPheRu8sMG2fLszjv3I8F1HS-fvyjg"></script>
	<title> Passport | 글쓰기 </title>
    <style type="text/css">
    	body {
    		background: white;
    	}
        #title {
            text-align: center;
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
    <script type="text/javascript">
	    function check() {
	    	  if(post.board_subject.value == "") {
	    	    alert("제목을 입력해 주세요.");
	    	    post.board_subject.focus();
	    	    return false;
	    	  } else return true;
	    	}
    </script>
    <script>
	    function initialize() {
	    	
	        var LatLng = new google.maps.LatLng(37.6352621, 127.0760557);

			var mapProp = {
				center : LatLng,
				zoom : 12,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
	
			var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
			
			var markers = [];
			
			var test;
			
			function setMapOnAll(map) {
			    for (var i = 0; i < markers.length; i++) {
			      markers[i].setMap(map);
			    }
			  }
			
			function clearMarker(){
				setMapOnAll(null);
				}
			
			function deleteMarkers(){
				clearMarker();
				markers = [];
			}

			google.maps.event.addListener(map, "click", function (event) {
		        var latitude = event.latLng.lat();
		        var longitude = event.latLng.lng();
		        
		        LatLng = new google.maps.LatLng(latitude, longitude);
		        var marker = new google.maps.Marker({
					position : LatLng,
					map : map,
				});
		        
		        markers.push(marker);
		        // console.log(LatLng.toString());
		        console.log(marker.getPosition().toString());
		        test = markers[markers.length-1].getPosition().toString();
		        document.post.board_markers.value+=test + ' ';
				
		        map.panTo(LatLng);
		        /* radius = new google.maps.Circle({map: map,
		            radius: 100,
		            center: event.latLng,
		            fillColor: '#777',
		            fillOpacity: 0.1,
		            strokeColor: '#AA0000',
		            strokeOpacity: 0.8,
		            strokeWeight: 2,
		            draggable: true,    // Dragable
		            editable: true      // Resizable
		        }); */
		    });
		    
		    // 마커 제거 버튼
			document.getElementById("delete_marker").addEventListener("click", function(){
				markers.pop().setMap(null);
				for(var i=0; i<markers.length ; i++){
		        	test = markers[i].getPosition().toString();
		        	if(i==0)
		        		document.post.board_markers.value = test + ' ';
		        	else
		        		document.post.board_markers.value += test + ' ';
				}
	        });
			document.getElementById("delete_all_marker").addEventListener("click", function(){
				deleteMarkers();
				document.post.board_markers.value = '';
	        });
		}
	    
		google.maps.event.addDomListener(window, 'load', initialize);
		
    </script>
</head>
<body style="background: white;">
	<c:if test="${ sessionScope.userid == null }">
		<script type="text/javascript">
			alert("접근 권한이 없습니다.");
			location.href = "index.do";
		</script>
	</c:if>
	
	<jsp:include page="side.jsp" flush="false"/>
	
<section>
	<br>
    <b><font size="6">글쓰기</font></b>
    <br><br><br>
    <div style="float:left;">
    	<form name="post" action="postCreate.do" method="POST" enctype="multipart/form-data" onsubmit="return check()">
	    	<input type="hidden" name="board_id" value="${ sessionScope.userid }">
	    	<table border="2" bordercolor="lightgray" bgcolor="white" align="center" style="font: 16px '굴림';">
	        	<tr>
	        	    <td id="title" width="150">글쓴이</td>
		            <td>${ sessionScope.userid }</td>
	    	    </tr>
	        	<tr>
	            	<td id="title">제 목</td>
		            <td><input name="board_subject" type="text" size="87" maxlength="100" /></td>        
		        </tr>
	    	    <tr>
	        	    <td id="title">내 용</td>
		            <td><textarea name="board_content" cols="80" rows="30" maxlength="4000"></textarea></td>        
	        	</tr>
	        	<tr>
	            	<td id="title">파일첨부</td>
		            <td><input type="file" name="board_file" /><input type="text" name="board_markers" size="40" value=""></td>    
	        	</tr>
		        <tr align="center" valign="middle">
	    	        <td colspan="5">
	        	        <input type="reset" value="리셋" >
	            	    <input type="submit" value="등록" >         
	     	       </td>
	        	</tr>
	    	</table>    
	    </form>
    </div>
    <div>
	    <div id="googleMap"></div>
	    <button type="button" id="delete_marker" style="margin-top:50px;">최근 마커 제거</button>
	    <button type="button" id="delete_all_marker" style="margin-top:50px;">모든 마커 제거</button>
    </div>
    <div class="error_block">${ error }</div>
	<div class="info_block">${ info }</div>
</section>
</body>
</html>