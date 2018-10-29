<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>간단한 지도 표시하기</title>
<!-- <script type="text/javascript" src="../../docs/js/examples-base.js"></script> -->
<!-- <script type="text/javascript" src="../../docs/js/highlight.min.js"></script> -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ri4vEtl3RpdjfQ36bd_C&amp;submodules=panorama"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ri4vEtl3RpdjfQ36bd_C&submodules=geocoder"></script>
<!-- <script src='http://dev.openapi.map.naver.com/openapi/maps3.max.js?submodules=drawing'></script> -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ri4vEtl3RpdjfQ36bd_C&amp;submodules=drawing"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/map.css">
</head>
<div id="wrap" class="section">
	<div id="map" style="width: 100%; height: 600px;">
		<div class="buttons">
			<input id="startM" type="button" value="출발지로" class="control-btn" /> 
			<input id="endM" type="button" value="도착지로" class="control-btn" />
		</div>
	</div>
	<code id="snippet" class="snippet"></code>
</div>
<body>
	<script type="text/javascript">
    var sx = Number(parent.document.getElementById("sx").value);
    var sy = Number(parent.document.getElementById("sy").value);
    var ex = Number(parent.document.getElementById("ex").value);
    var ey = Number(parent.document.getElementById("ey").value);

    var map = new naver.maps.Map("map", {
    center : new naver.maps.LatLng(37.3595316, 127.1052133),
    zoom : 10,
    mapTypeControl : true
    });
    
	var coord1 = {lat: sy, lng: sx};
	var coord2 = {lat: ey, lng: ex};


    $("#endM").on("click", function(e) {
      e.preventDefault();
      map.panTo(coord2);
    });
  
    $("#startM").on("click", function(e) {
      e.preventDefault();
      map.panTo(coord1);
    });


    function initGeocoder() {

      polyline = new naver.maps.Polyline({
      map : map,
      path : [ [ sx, sy ], [ ex, ey ] ],
      strokeColor : '#5347AA',
      strokeWeight : 2
      });
      var point1 = new naver.maps.Point(sx, sy);
      var point2 = new naver.maps.Point(ex, ey);

      mark1 = new naver.maps.Marker({
      map : map,
      position : point1
      });
      mark2 = new naver.maps.Marker({
      map : map,
      position : point2
      });

      mark1.setIcon({
      url : '../img/start.png',
      size : new naver.maps.Size(70, 60),
      });
      mark2.setIcon({
      url : '../img/end.png',
      size : new naver.maps.Size(70, 60),
      });
    }

    naver.maps.onJSContentLoaded = initGeocoder;
  </script>
	<div>
		<input type="hidden" id="src1"> <input type="hidden" id="des1"> <input type="hidden" id="src2"> <input type="hidden" id="des2">
	</div>
</body>
</html>