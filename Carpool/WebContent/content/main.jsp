<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file="/header/header.jsp"%>
<script type= "text/javascript">
	var imgs = ["../img/title1.png", "../img/title2.png", "../img/title3.png"];
	var i = 1;
	$(document).ready(function() {
		setInterval(function() {
		$('#b1').attr("src", imgs[i % 3]);
			i++;
		}, 4000);
});		
</script>
<body>
	<div id="mainPage">
		<table>
			<tr>
				<td><a href =""><img src="../img/title1.png" id="b1" style="width:100%;hight:auto"></img></a></td>
			</tr>
		</table>
		<table>
			<tr>
				<td style="background-color: #333f50; width: 20%; text-align:center; text-size:30pt; color:#ffffff">Quick Menu</td>
				<td style="background-color: #e0e9f3; width: 20%; text-align:center; margin-top:20px"><a href ="intro.jsp"><img src="../img/introduce.png" ></a></td>
				<td style="background-color: #e0e9f3; width: 20%; text-align:center"><a href ="${pageContext.request.contextPath }/CarChkController"><img src="../img/registration.png" ></a></td>
				<td style="background-color: #e0e9f3; width: 20%; text-align:center"><a href ="recruit.jsp"><img src="../img/recruit1.png" ></a></td>
				<td style="background-color: #e0e9f3; width: 20%; text-align:center"><a href ="${pageContext.request.contextPath }/Board2FreeBoardListController"><img src="../img/community.png" ></a></td>
			</tr>
		</table>
		<table >
			<tr>
			<td id="b2"><a href ="http://www.kma.go.kr"><img src="../img/weather.png" ></img></a></td>
			<td id="b2"><a href ="https://www.carhistory.or.kr"><img src="../img/accident.png" ></img></a></td>
			<td id="b2"><a href ="http://www.kitri.re.kr"><img src="../img/kitri.png" ></img></a></td>
			</tr>
		</table>
	</div>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>