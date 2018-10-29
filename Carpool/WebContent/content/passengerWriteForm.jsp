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
</script><body>
	<br><br>
	<form id="f" action="" method="post">
		<input type="hidden" id="spx" name="spx">
		<input type="hidden" id="spy" name="spy">
		<input type="hidden" id="epx" name="epx">
		<input type="hidden" id="epy" name="epy">
		<table id="board1">
			<tr>
				<td style="width:10%">유형</td>
				<td style="width:40%;text-align: left">
					<select name="cate" id="cate">
						<option value="출근" selected>출근</option>
						<option value="퇴근">퇴근</option>
						<option value="여가">여가</option>
						<option value="여행">여행</option>
						<option value="기타">기타</option>
					</select>
				</td>
				<td style="width:50%" rowspan="9"><div id=div1></div></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" placeholder="제목"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="content" style="resize: none" placeholder="EX) 즐거운 카풀 원해요"></textarea></td>
			</tr>
			<tr>
				<td>출발 위치</td>
				<td><input type="text" name="startPosi" id="sp1" placeholder="옆의 지도에서 선택하세요" readonly>
				</td>
			</tr>
			<tr>
				<td>도착 위치</td>
				<td>
				<input type="text" name="endPosi" id="ep1" placeholder="옆의 지도에서 선택하세요" readonly>
				</td>
			</tr>
			<tr>
				<td>출발 시간</td>
				<td><input type="text" class="datetimepicker" name="startTime"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" placeholder="원"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="passengerWrite" value="작성">
					<input type="button" id="passengerList" value="목록">
				</td>
			</tr>
		</table>
	</form>
	<br><br>
</body>
<%@ include file="/footer/footer.jsp"%>
</html>