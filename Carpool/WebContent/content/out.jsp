<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file="/header/header.jsp"%>
<body>
	<form action="${pageContext.request.contextPath }/MemberOutController" method="post">
		<br>
		<table id="outTable">
			<tr>
				<td style="text-align:center">
				회원 탈퇴를 하시면 회원 가입시<br>
				입력하신 모든 정보가 삭제됩니다.<br><br>
				그와 동시에 KITRI Carpool 을 사용하면서<br>
				작성하신 게시글 내역 또한 함께 삭제됩니다.<br><br>
				<input type="password" name="pwd" placeholder="비밀번호"><br><br>
				위 안내사항을 모두 확인하였으며, 이에 동의하시면 탈퇴 진행을 위하여 "OUT" 버튼을 눌러주세요.<br><br><br>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="OUT" style="height: 100%; width: 100%"></td>
			</tr>
		</table>
		<br>
	</form>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>