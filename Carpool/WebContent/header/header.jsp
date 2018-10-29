<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath }/img/favicon.ico"
	type="image/x-icon" />
<link rel="icon"
	href="${pageContext.request.contextPath }/img/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/view.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/view.js"></script>
<script type="text/javascript">
  $('document').ready(function() {
  	$('#div1').append("<iframe id='m' src='${pageContext.request.contextPath }/content/map.jsp' scrolling='no' frameborder='0' framespacing='0' margin='0' padding='0' vspace='0'></iframe>");
  	$('#div2').append("<iframe id='m' src='${pageContext.request.contextPath }/content/map2.jsp' scrolling='no' frameborder='0' framespacing='0' margin='0' padding='0' vspace='0'></iframe>");

  	$('#car').click(function(){
  	  window.open("${pageContext.request.contextPath}/CarDetailController?owner="+$('#w').val(),"","width=740, height=130, top=100, left=100, scrollbars=no");
  	});


    $('#noticeWrite').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2NoticeAddController");
      $('#f').submit();
    });
    $('#noticeEdit').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2NoticeEditController");
      $('#f').submit();
    });
    $('#noticeDel').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2NoticeDelController");
      $('#f').submit();
    });
    $('#noticeList').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2NoticeListController");
      $('#f').submit();
    });


    $('#freeBoardWrite').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2FreeBoardAddController");
      $('#f').submit();
    });
    $('#freeBoardEdit').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2FreeBoardEditController");
      $('#f').submit();
    });
    $('#freeBoardDel').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2FreeBoardDelController");
      $('#f').submit();
    });
    $('#freeBoardList').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board2FreeBoardListController");
      $('#f').submit();
    });
  	
  	
  	

    $('#passengerWrite').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1PassengerAddController");
      $('#f').submit();
    });
    $('#passengerList').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1PassengerListController");
      $('#f').submit();
    });
    $('#passengerDel').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1PassengerDelController");
      $('#f').submit();
    });
    $('#passengerEdit').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1PassengerEditController");
      $('#f').submit();
    });
    
    

    $('#driverWrite').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1DriverAddController");
      $('#f').submit();
    });
    $('#driverEdit').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1DriverEditController");
      $('#f').submit();
    });
    $('#driverDel').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1DriverDelController");
      $('#f').submit();
    });
    $('#driverList').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1DriverListController");
      $('#f').submit();
    });
    

    
    $('#driverRequestAdd').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1DriverRequestAddController");
      $('#f').submit();
    });
    $('#driverRequestDel').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1DriverRequestDelController");
      $('#f').submit();
    });
    
    
    
    $('#passengerRequestAdd').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1PassengerRequestAddController");
      $('#f').submit();
    });
    $('#passengerRequestDel').click(function() {
      $('#f').attr("action", "${pageContext.request.contextPath}/Board1PassengerRequestDelController");
      $('#f').submit();
    });
    
    

    $('#menu1').hover(function() {
      $('#menu1').css("background-color", "#9f9f9f");
    }, function() {
      $('#menu1').css("background-color", "#333f50");
    });
    $('#menu2').hover(function() {
      $('#menu2').css("background-color", "#9f9f9f");
    }, function() {
      $('#menu2').css("background-color", "#333f50");
    });
    $('#menu3').hover(function() {
      $('#menu3').css("background-color", "#9f9f9f");
    }, function() {
      $('#menu3').css("background-color", "#333f50");
    });
    $('#menu4').hover(function() {
      $('#menu4').css("background-color", "#9f9f9f");
    }, function() {
      $('#menu4').css("background-color", "#333f50");
    });
    $('#menu5').hover(function() {
      $('#menu5').css("background-color", "#9f9f9f");
    }, function() {
      $('#menu5').css("background-color", "#333f50");
    });

    
    
    $('#logout').click(function() {
      $('#info').attr("action", "${pageContext.request.contextPath }/MemberLogoutController");
      $('#info').submit();
    });
    $('#carAdd').click(function() {
      $('#info').attr("action", "${pageContext.request.contextPath }/CarChkController");
      $('#info').submit();
    });
    $('#carDel').click(function() {
      $('#info').attr("action", "${pageContext.request.contextPath }/CarDelController");
      $('#info').submit();
    });
    $('#out').click(function() {
      $('#info').attr("action", "${pageContext.request.contextPath }/content/out.jsp");
      $('#info').submit();
    });
    $('#edit').click(function() {
      $('#info').attr("action", "${pageContext.request.contextPath }/content/editInfo.jsp");
      $('#info').submit();
    });
    $('#partner').click(function() {
      $('#info').attr("action", "${pageContext.request.contextPath }/Board1PartnerListController");
      $('#info').submit();
    });
    $('#join').click(function() {
      window.location.href = "${pageContext.request.contextPath }/CaptchaImageController";
    });
  	$('#partnerList').click(function(){
  	  window.location.href = "${pageContext.request.contextPath }/Board1PartnerListController";
  	});
  });

  function menuClick(num) {
    switch (num) {
    case 1:
      window.location.href = "${pageContext.request.contextPath }/content/intro.jsp";
      break;
    case 2:
      window.location.href = "${pageContext.request.contextPath }/Board1PassengerListController";
      break;
    case 3:
      window.location.href = "${pageContext.request.contextPath }/Board1DriverListController";
      break;
    case 4:
      window.location.href = "${pageContext.request.contextPath }/Board2NoticeListController";
      break;
    case 5:
      window.location.href = "${pageContext.request.contextPath }/Board2FreeBoardListController";
      break;
    }
  };
</script>
</head>
<header>
	<div id="navibar">
		<table id="loginBar">
			<tr>
				<td>
					<p id="title">
						<a href="${pageContext.request.contextPath }/content/main.jsp"><img
							src="${pageContext.request.contextPath }/img/kitri_carpool.png"></a>
					</p>
				</td>
				<td style="text-align: right" colspan="2"><c:choose>
						<c:when test="${empty sessionScope.m }">
							<form id="login"
								action="${pageContext.request.contextPath }/MemberLoginController"
								method="post">
								<input type="text" name="id" placeholder="아이디"><br>
								<input type="password" name="pwd" placeholder="비밀번호"><br><br>
								<input type="submit" value="Login"> <input type="button"
									id="join" value="Join">
							</form>
						</c:when>
						<c:when
							test="${!empty sessionScope.m && sessionScope.m.type eq 1}">
							<c:choose>
								<c:when test="${empty sessionScope.c}">
									<form id="info" action="" method="post">
										${sessionScope.m.name } 님 안녕하세요.<br>
										${sessionScope.c.carName } 차량정보가 없습니다. 차량을 등록해주세요.<br><br><input type="button" id="partner" value="Partner"> <input
											type="button" id="carAdd" value="CarAdd"> <input
											type="button" id="edit" value="Edit"> <input
											type="button" id="out" value="Out"> <input
											type="button" id="logout" value="Logout">
									</form>
								</c:when>
								<c:when test="${!empty sessionScope.c}">
									<form id="info" action="" method="post">
										${sessionScope.m.name } 님 안녕하세요.<br> 차량정보 [번호 :
										${sessionScope.c.numberPlate} / 연식 : ${sessionScope.c.age}년 /
										차명 : ${sessionScope.c.carName}]<br><br><input type="button" id="partner" value="Partner"> <input type="button"
											id="carDel" value="CarDel"> <input type="button"
											id="edit" value="Edit"> <input type="button" id="out"
											value="Out"> <input type="button" id="logout"
											value="Logout">
									</form>
								</c:when>
							</c:choose>
						</c:when>
						<c:when
							test="${!empty sessionScope.m && sessionScope.m.type eq 0}">
							<form id="info" action="" method="post">
								${sessionScope.m.name } 님 안녕하세요.<br><br><input type="button"
									id="edit" value="Edit"> <input type="button" id="out"
									value="Out"> <input type="button" id="logout"
									value="Logout">
							</form>
						</c:when>
						<c:when
							test="${!empty sessionScope.m && sessionScope.m.type eq 2}">
							<form id="info" action="" method="post">
								${sessionScope.m.name } 님 안녕하세요.<br><br><input type="button" id="partner" value="Partner"> <input type="button"
									id="edit" value="Edit"> <input type="button" id="out"
									value="Out"> <input type="button" id="logout"
									value="Logout">
							</form>
						</c:when>
					</c:choose></td>
			</tr>
		</table>
		<table id="menu">
			<tr>
				<td id="menu1" onclick="menuClick(1)"><img
					src="${pageContext.request.contextPath }/img/menu1.png"></td>
				<td id="menu2" onclick="menuClick(2)"><img
					src="${pageContext.request.contextPath }/img/menu2.png"></td>
				<td id="menu3" onclick="menuClick(3)"><img
					src="${pageContext.request.contextPath }/img/menu3.png"></td>
				<td id="menu4" onclick="menuClick(4)"><img
					src="${pageContext.request.contextPath }/img/menu4.png"></td>
				<td id="menu5" onclick="menuClick(5)"><img
					src="${pageContext.request.contextPath }/img/menu5.png"></td>
			</tr>
		</table>
	</div>
</header>