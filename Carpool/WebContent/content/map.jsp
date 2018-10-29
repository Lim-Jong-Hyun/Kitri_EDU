<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>간단한 지도 표시하기</title>
<!-- <script type="text/javascript" src="../../docs/js/examples-base.js"></script> -->
<!-- <script type="text/javascript" src="../../docs/js/highlight.min.js"></script> -->
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ri4vEtl3RpdjfQ36bd_C&submodules=geocoder"></script>
<!-- <script src='http://dev.openapi.map.naver.com/openapi/maps3.max.js?submodules=drawing'></script> -->
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ri4vEtl3RpdjfQ36bd_C&amp;submodules=drawing"></script>
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/map.css">
</head>
<body>
	<div id="wrap" class="section">
		<div id="map" style="width: 100%; height: 500px;">
			<div class="search" style="">
				<input id="address1" type="text" placeholder="출발할 주소" value="" /> <input
					id="submit1" type="button" value="주소 검색" /><br> <input
					id="address2" type="text" placeholder="도착할 주소" value="" /> <input
					id="submit2" type="button" value="주소 검색" /><br> <input
					id="submit3" type="submit" value="주소사용하기"> <input
					type="hidden" id="s_x"> <input type="hidden" id="s_y">
				<input type="hidden" id="e_x"> <input type="hidden" id="e_y">
			</div>
		</div>
	</div>

	<script type="text/javascript">
    var map = new naver.maps.Map("map", {
    center : new naver.maps.LatLng(37.3595316, 127.1052133),
    zoom : 10,
    mapTypeControl : true
    });

    var infoWindow = new naver.maps.InfoWindow({
      anchorSkew : true
    });

    function searchAddressToCoordinate(address, num) {

      naver.maps.Service.geocode({
        address : address
      }, function(status, response) {
        if (status === naver.maps.Service.Status.ERROR) {
          return alert('검색결과가 없습니다');
        }

        var item = response.result.items[0];
        var addrType = item.isRoadAddress ? '[도로명 주소]' : '[지번 주소]';
        var point = new naver.maps.Point(item.point.x, item.point.y);

        infoWindow.setContent([ '<div style="padding:10px;min-width:200px;line-height:150%;">', '<h4 style="margin-top:5px;">검색 주소 : ' + response.result.userquery + '</h4><br />', addrType + ' ' + item.address + '<br />', '</div>' ].join('\n'));
        map.setCenter(point);
        infoWindow.open(map, point);

        if (num == 1) {

          $('#src1').val(item.address);
          $('#address1').val(item.address);
          $('#src2').val(point);

        } else {
          $('#des1').val(item.address);
          $('#address2').val(item.address);
          $('#des2').val(point);

        }

      });

    }

    function initGeocoder() {

      $('#submit1').on('click', function(e) {
        e.preventDefault();
        searchAddressToCoordinate($('#address1').val(), 1);
      });

      $('#submit2').on('click', function(e) {
        e.preventDefault();
        searchAddressToCoordinate($('#address2').val(), 2);
      });

      var cnt = 0;
      var polyline;
      var mark1;
      var mark2;

      $('#submit3').on('click', function(e) {

        infoWindow.close();
        if (cnt != 0) {
          polyline.setMap(null);
        }

        if ($('#src2').val() == "" || $('#des2').val() == "") {
          alert("경로설정 오류");
          return;

        } else {
          cnt++;
          var sub1 = $('#src2').val().replace('(', '');
          var real_src = sub1.replace(')', '');

          var sub2 = $('#des2').val().replace('(', '');
          var real_des = sub2.replace(')', '');

          var src_position1 = Number(real_src.split(",")[0]);
          var src_position2 = Number(real_src.split(",")[1]);

          var des_position1 = Number(real_des.split(",")[0]);
          var des_position2 = Number(real_des.split(",")[1]);

          $('#s_x').val(src_position1);
          $('#s_y').val(src_position2);
          $('#e_x').val(des_position1);
          $('#e_y').val(des_position2);

          polyline = new naver.maps.Polyline({
          map : map,
          path : [ [ src_position1, src_position2 ], [ des_position1, des_position2 ] ],
          strokeColor : '#5347AA',
          strokeWeight : 2
          });
        }
        //var distance = polyline.getDistance();
        var point1 = new naver.maps.Point(src_position1, src_position2);
        var point2 = new naver.maps.Point(des_position1, des_position2);

        if (mark1 != null) {
          mark1.setMap(null);
        }
        if (mark2 != null) {
          mark2.setMap(null);
        }
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

        $(top.document).find("#sp1").attr("value", $('#address1').val());
        $(top.document).find("#ep1").attr("value", $('#address2').val());
        $(top.document).find("#spx").attr("value", $('#s_x').val());
        $(top.document).find("#spy").attr("value", $('#s_y').val());
        $(top.document).find("#epx").attr("value", $('#e_x').val());
        $(top.document).find("#epy").attr("value", $('#e_y').val());

        //alert("거리는"+distance);
      });
    }

    naver.maps.onJSContentLoaded = initGeocoder;
  </script>
	<div>
		<input type="hidden" id="src1"> <input type="hidden" id="des1">
		<input type="hidden" id="src2"> <input type="hidden" id="des2">

	</div>
</body>
</html>