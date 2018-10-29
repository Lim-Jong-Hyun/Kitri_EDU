<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file="/header/header.jsp"%>
<script type="text/javascript">
$('document').ready(function() {
	if(${chk != true} ) {
		window.location.replace("${pageContext.request.contextPath }/content/main.jsp");
	}
});
</script>
<body>
	<form action="${pageContext.request.contextPath }/CarAddController" method="post">
	<br>
		<table id="joinTable">
			<tr>
				<td style="width: 150px">라이센스 번호<br>차량번호<br>주행거리(km)<br>구입년도<br>차명<br>차종</td>
				<td>
				<input type="text" name="license" placeholder="서울11-111111-11"><br> 
				<input type="text" name="numberPlate" placeholder="11가1234"><br>
				<input type="text" name="mileage" placeholder="1000"> km<br>
				<input type="text" name="age" placeholder="1900"> 년<br>
				<input type="text" name="carName" placeholder="아반떼"><br>
				<input type="radio" name="carSize" value="0">소형
				<input type="radio" name="carSize" value="1">중형
				<input type="radio" name="carSize" value="2">대형<br>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="REGISTER" style="height: 100%; width: 100%"></td>
			</tr>
		</table>
		<br>
	</form>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>