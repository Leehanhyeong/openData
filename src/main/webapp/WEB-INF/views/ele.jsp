<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		table{
			border-collapse: collapse;
			border: 1px solid black;
			width: 680px;
			margin: auto;
			text-align: center;
		}
		td{
			border: 2px solid black;
		}
		
		table thead tr {
		 	background-color: #dedede;
		}
		
		
		h1{
			margin: auto;
			text-align: center;
		}
	</style>
</head>
<body>
	<header>
		<h1>전기자동차 충전소 정보</h1>
	</header>
	<article>
	<table>
	
	<colgroup>
		<col width="100px">
		<col width="200px">
		<col width="100px">
		<col width="100px">
		<col width="100px">
		<col width="100px">
	</colgroup>
		<thead>
			<tr >
				<td>충전소 명</td>
				<td>주소</td>
				<td>이용가능시간</td>
				<td>연락처</td>
				<td>이용자제한</td>
				<td>충전소안내</td>
			</tr>
		</thead>
	
	
		<tbody>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.statNm}</td>
				<td>${vo.addr}</td>
				<td>${vo.useTime }</td>
				<td>${vo.busiCall }</td>
				<td>${vo.limitYn }</td>
				<td>${vo.note}</td>
			</tr>
		</c:forEach>	
		</tbody>
		
	
	</table>
</article>
	
</body>
</html>