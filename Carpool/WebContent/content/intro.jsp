<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file="/header/header.jsp"%>
<body>
	<table id="introTable">
		<tr>
			<td colspan="2" style="text-align: left;font-size: 16pt; font-weight: bold"><br><img src="${pageContext.request.contextPath }/img/point.png"> KITRI Carpool?<br><br></td>
		</tr>
		<tr>
			<td rowspan="2"><img src="${pageContext.request.contextPath }/img/intro1.jpg" style="width:450px;height:auto"></td>
			<td style="font-size: 20pt; font-weight: bold; color: #316e73"><img src="${pageContext.request.contextPath }/img/d.png"	style="height: 70%; width: auto; vertical-align:middle"> Driver</td>
		</tr>
		<tr>
			<td style="vertical-align:top">You are	happy to have a co-worker to<br>accompany me on my way to work.</td>
		</tr>
		<tr>
			<td colspan="2"><br><br></td>
		</tr>
		<tr>
			<td style="font-size: 20pt; font-weight: bold; color: #b29c85"><img src="${pageContext.request.contextPath }/img/p.png"	style="height: 70%; width: auto; vertical-align:middle"> Passenger</td>
			<td rowspan="2"><img src="${pageContext.request.contextPath }/img/intro2.jpg" style="width:450px;height:auto"></td>
		</tr>
		<tr>
			<td style="vertical-align:top">Select the desired origin and destination<br>and click on the matching option.</td>
		</tr>
		<tr>
			<td colspan="2"><br><br></td>
		</tr>
	</table>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>