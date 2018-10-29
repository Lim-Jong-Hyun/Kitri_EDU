<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file="/header/header.jsp"%>
<body>
	<br>
	<br>
	<form id="f" action=""	method="post">
		<table id="board2">
			<tr>
				<td>글번호</td>
				<td style="text-align:left"><input type="hidden" name="num" value="${b.num }" readonly>${b.num }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td style="text-align:left"><input type="hidden" name="writer" value="${b.writer }" readonly>${b.writer }</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td style="text-align:left"><input type="hidden" name="w_date" value="${b.w_date }" readonly>${b.w_date }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${b.title }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea style="resize: none;" name="content" rows="15">${b.content }</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<c:choose>
						<c:when test="${sessionScope.m.id==b.writer}"><input type="button" value="수정" id="noticeEdit">
										<input type="button" value="삭제" id="noticeDel">
						</c:when>
					</c:choose>
					<input type="button" value="목록" id="noticeList">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<br>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>