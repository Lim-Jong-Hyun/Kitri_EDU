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
	<form action="${pageContext.request.contextPath }/MemberJoinController" method="post" enctype="multipart/form-data">
	<br>
		<table id="joinTable">
			<tr>
				<td style="width: 150px">아이디<br>비밀번호<br>이름<br>이메일<br>전화번호<br>사진<br>가입유형<br>성별<br>흡연여부</td>
				<td>
				<input type="text" name="id" placeholder="hong"><br> 
				<input type="password" name="pwd" placeholder="****"><br>
				<input type="text" name="name" placeholder="홍길동"><br> 
				<input type="email" name="email" placeholder="a@a.com"><br>
				<input type="tel" name="tel" placeholder="010-1111-1111"><br>
				<input type="file" name="profile"><br>
				<input type="radio" name="type" value="1">Driver
				<input type="radio" name="type" value="2">Passenger<br>
				<input type="radio" name="sex" value="0">Man
				<input type="radio" name="sex" value="1">Woman<br>
				<input type="radio" name="isSmoke" value="1">Yes
				<input type="radio" name="isSmoke" value="0">No<br>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="JOIN" style="height: 100%; width: 100%"></td>
			</tr>
		</table>
		<br>
	</form>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>