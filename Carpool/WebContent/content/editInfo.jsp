<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file="/header/header.jsp"%>
<body>
	<form action="${pageContext.request.contextPath }/MemberEditController" method="post" enctype="multipart/form-data">
	<br>
		<table id="editInfoTable">
			<tr>
				<td style="width: 150px">아이디<br>기존  비밀번호<br>새 비밀번호<br>이름<br>이메일<br>전화번호<br>사진(변경시 등록)<br>가입유형<br>성별<br>흡연여부</td>
				<td>
				<input type="text" name="id" value="${m.id }" readonly><br> 
				<input type="password" name="oldPwd" placeholder="입력 필요"><br> 
				<input type="password" name="newPwd" placeholder="변경할 경우에 입력"><br>  
				<input type="text" name="name" value="${m.name }" readonly><br> 
				<input type="email" name="email" value="${m.email }"><br>
				<input type="tel" name="tel" value="${m.tel }"><br>
				<input type="file" name="profile"><br>
				<c:if test="${m.type == 0}">관리자</c:if>
				<c:if test="${m.type == 1}">운전자</c:if>
				<c:if test="${m.type == 2}">탑승자</c:if><br>
				<c:if test="${m.sex == 0}">남성</c:if>
				<c:if test="${m.sex == 1}">여성</c:if><br>
				<input type="radio" name="isSmoke" value="1" <c:if test="${m.isSmoke == 1}">checked</c:if>>Yes
				<input type="radio" name="isSmoke" value="0" <c:if test="${m.isSmoke == 0}">checked</c:if>>No<br>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="EDIT" style="height: 100%; width: 100%"></td>
			</tr>
		</table>
	<br>
	</form>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>