<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	// 천단위 콤마 
	function comma(num){
	    var len, point, str;  
	    num = num + "";  
	    point = num.length % 3 ;
	    len = num.length;  
	    str = num.substring(0, point);  
	    while (point < len) {  
	        if (str != "") str += ",";  
	        str += num.substring(point, point + 3);  
	        point += 3;  
	    }  
	    return str;
	}
	
	// 천단위 컴마 정규식
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	// 라디오 버튼으로 선택하거나 직접 입력한 값이 충전금액에 출력되도록 하는 함수
	function changeCoin(coin){
		//alert(coin);
		document.getElementById("chargeCoin").innerHTML=numberWithCommas(coin);
		document.frm.coin.value=coin;
	}
	
</script>	

</head>

<body>
<br>
<h1> 코인충전 </h1>
	<table border=3 class="table table-striped" style="width: 500px;height: 100px;">
		<tr>
			<th style="color:blue;text-align: center;font-size: 20px;">[ ${vo1.id } ] 님의 코인잔액</th>
			<!-- vo에 있는걸 불러오는게 좋을까, 세션에 있는걸 불러오는게 좋을까? 세션에서 불러오는 방법은? -->
		</tr>
		<tr>
			<td style="color:red;font-size: 30px;"><fmt:formatNumber value="${vo1.coin }"  /> 원</td>
		</tr>
	</table>
	<br>
	<form name="frm" class="form-inline" id="frm" method="post" action="coin.do?cmd=update">
	<input type="hidden" name="coin" value="10000" />
	<table border=3 class="table table-striped" style="width: 500px;height: 100px;">
		<tr>
			<td>
				<ul style="margin-left: 50px;">
					<li><input type="radio" name="cprice" value="10000" onclick="changeCoin(this.value)" checked/> 10,000원 </li> 
					<li><input type="radio" name="cprice" value="20000" onclick="changeCoin(this.value)"/> 20,000원  </li>
					<li><input type="radio" name="cprice" value="30000" onclick="changeCoin(this.value)"/> 30,000원  </li>
					<li><input type="radio" name="cprice" value="50000" onclick="changeCoin(this.value)"/> 50,000원  </li>
					<li><input type="radio" name="cprice" value="100000" onclick="changeCoin(this.value)"/> 100,000원 </li>
					<li style="margin-left: 20px;">
						<br>
						<label for="dcoin">직접입력</label> <input type="text" class="form-control" name="dcoin" id="dcoin" maxlength="6" value="10000" onkeyup="changeCoin(this.value);"  style="ime-mode:disabled;"> 원
					</li>
				</ul>
			</td>	
		</tr>
		<tr>
			<td> 충전금액 : <strong><span id="chargeCoin">10,000</span></strong>원</td>
		</tr>
	</table>
	<p align="center"><input type="submit" class="btn btn-warning" value="충전하기"></p>
	</form>
</body>
</html>	