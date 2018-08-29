<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
2018-08-29	코인충전 페이지 작성(윤우현)
 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addcoin.jsp</title>
<style> table{margin:auto;text-align:center}
	li{list-style: none;float: left;}
</style>
<script type="text/javascript">
	var xhr = null;
	function priceCheck(tmp) {
		document.getElementById("chargeCoin");
		document.frm.amount.value = tmp;
	}
	
</script>	

</head>

<body>
<br>
<h1 align="center"> 코인충전 </h1>
	<table border=1 width=500 height=100>
		<tr>
			<th style="color:blue">[ ${vo1.id } ] 님의 코인잔액</th>
			<!-- vo에 있는걸 불러오는게 좋을까, 세션에 있는걸 불러오는게 좋을까? 세션에서 불러오는 방법은? -->
		</tr>
		<tr>
			<td style="color:red">${vo1.coin }원</td>
		</tr>
	</table>
	<br>
	<form name="frm" id="frm" method="post">
	<table border=1 width=500 height=100>
		<input type="hidden" name="amount" value="10000">
		<tr>
			<td>
				<ul>
					<li><input type="radio" name="cprice" > 10,000원 </li> 
					<li><input type="radio" name="cprice" > 20,000원  </li>
					<li><input type="radio" name="cprice" > 30,000원  </li>
					<li><input type="radio" name="cprice" > 50,000원  </li>
					<li><input type="radio" name="cprice" > 100,000원 </li>
					<li>
					<br>
						직접입력 <input type="text" name="dcoin" id="dcoin" maxlength="6" value="10000" style="ime-mode:disabled;"> 원
					</li>
				</ul>
			</td>	
		</tr>
		<tr>
			<td> 충전금액 : <strong><span id="chargeCoin">10,000</span></strong>원</td>
		</tr>
	</table>
	<p align="center"><input type="submit" value="충전하기"></p>
	</form>
</body>
</html>	