<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file="/header/header.jsp"%>
<link rel="stylesheet" href="//mugifly.github.io/jquery-simple-datetimepicker/jquery.simple-dtpicker.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="//mugifly.github.io/jquery-simple-datetimepicker/jquery.simple-dtpicker.js"></script>	
<script type="text/javascript">
  $(function() {
    $('.datetimepicker').appendDtpicker({
      'locale' : 'ko'
    });
  });
</script>
<body>
	<c:if test="${!(sessionScope.m.id eq b.writer)}">
		<c:set var="readonly" value="readonly" />
	</c:if>
	<br><br>
	<form id="f" action="" method="post">
		<input type="hidden" name="num" value="${b.num }">
		<input type="hidden" name="writer" value="${b.writer }">
		<input type="hidden" name="driver" value="${b.driver }">
		<input type="hidden" id="sx" name="spx" value="${b.startX }">
		<input type="hidden" id="sy" name="spy" value="${b.startY }">
		<input type="hidden" id="ex" name="epx" value="${b.endX }">
		<input type="hidden" id="ey" name="epy" value="${b.endY }">
		<table id="board1">
			<tr>
				<td style="width:10%">작성자</td>
				<td style="width:40%;text-align: left">${b.writer }</td>
				<td style="width:50%" rowspan="10"><c:choose>
						<c:when test="${sessionScope.m.id eq b.writer}">
							<div id=div1></div>
						</c:when>
						<c:otherwise>
							<div id=div2></div>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td>유형</td>
				<td style="text-align: left"><c:choose>
						<c:when test="${sessionScope.m.id eq b.writer}">
							<select name="cate" id="cate">
								<c:choose>
									<c:when test="${b.cate eq '출근'}">
										<option value="출근" selected>출근</option>
										<option value="퇴근">퇴근</option>
										<option value="여가">여가</option>
										<option value="여행">여행</option>
										<option value="기타">기타</option>
									</c:when>
									<c:when test="${b.cate eq '퇴근'}">
										<option value="출근">출근</option>
										<option value="퇴근" selected>퇴근</option>
										<option value="여가">여가</option>
										<option value="여행">여행</option>
										<option value="기타">기타</option>
									</c:when>
									<c:when test="${b.cate eq '여가'}">
										<option value="출근">출근</option>
										<option value="퇴근">퇴근</option>
										<option value="여가" selected>여가</option>
										<option value="여행">여행</option>
										<option value="기타">기타</option>
									</c:when>
									<c:when test="${b.cate eq '여행'}">
										<option value="출근">출근</option>
										<option value="퇴근">퇴근</option>
										<option value="여가">여가</option>
										<option value="여행" selected>여행</option>
										<option value="기타">기타</option>
									</c:when>
									<c:when test="${b.cate eq '기타'}">
										<option value="출근">출근</option>
										<option value="퇴근">퇴근</option>
										<option value="여가">여가</option>
										<option value="여행">여행</option>
										<option value="기타" selected>기타</option>
									</c:when>
								</c:choose>
							</select>
						</c:when>
						<c:otherwise>
						${b.cate}
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${b.title }" placeholder="제목" ${readonly}></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="content"
						style="resize: none" placeholder="EX) 즐거운 카풀 원해요" ${readonly}>${b.content }</textarea></td>
			</tr>
			<tr>
				<td>출발 위치</td>
				<td><input type="text" name="startPosi" value="${b.startPosi }"	id="sp1" placeholder="옆의 지도에서 선택하세요" readonly>
				</td>
			</tr>
			<tr>
				<td>도착 위치</td>
				<td>
				<input type="text" name="endPosi" id="ep1" value="${b.endPosi }" placeholder="옆의 지도에서 선택하세요" readonly>
				</td>
			</tr>
			<tr>
				<td>출발 시간</td>
				<td><c:choose>
						<c:when test="${sessionScope.m.id eq b.writer}">
							<input type="text" class="datetimepicker" name="startTime"
								value="${b.startTime }">
						</c:when>
						<c:otherwise>
							<input type="hidden" class="datetimepicker" name="startTime"
								value="${b.startTime }">
							<input type="text" value="${b.startTime }" readonly>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" placeholder="원"
					value="${b.price }" ${readonly}></td>
			</tr>
			<tr>
				<td>신청자</td>
				<td style="text-align: left">
					${b.driver}
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:choose>
						<c:when test="${sessionScope.m.id eq b.writer}">
							<input type="button" id="passengerEdit" value="수정">
							<input type="button" id="passengerDel" value="삭제">
						</c:when>
						<c:when
							test="${(sessionScope.m.type eq 1) && (b.seat < b.maxSeat) && !(sessionScope.m.id eq b.driver)}">
							<input type="button" id="passengerRequestAdd" value="신청">
						</c:when>
						<c:when
							test="${ !empty sessionScope.m && sessionScope.m.id eq b.driver}">
							<input type="button" id="passengerRequestDel" value="신청취소">
						</c:when>
					</c:choose> <input type="button" id="passengerList" value="목록">
				</td>
			</tr>
		</table>
	</form>
	<br><br>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>