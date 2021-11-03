<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		table{
			border-collapse: collapse;
			border: 1px solid black;
			width: 600px;
		}
		td{
			border: 2px solid black;
		}
		
		table thead tr {
		 	background-color: #dedede;
		}
	</style>
</head>
<body>
	<header>
		<h1>행사목록</h1>
	</header>
	<article>
	<table>
	
	<colgroup>
		<col width="100px">
		<col width="*">
		<col width="100px">
		<col width="100px">
	</colgroup>
		<thead>
			<tr>
				<td>이미지</td>
				<td>제목</td>
				<td>주소</td>
				<td>전화번호</td>
			</tr>
		</thead>
	
	
		<tbody>
		<c:forEach var="vo" items="${list }"><!-- vo라는변수안에  list라는이르므로저장-->
			<tr>
				<td><img src="${vo.firstimage2 }" width="100px"/></td>
				<td>${vo.title }</td>
				<td>
				 <%--주소1에서 공백의 위치를 찾는다. --%>
				 <c:set var="idx" value="${fn:indexOf(vo.addr1, ' ') }"/>
				 	
					${fn:substring(vo.addr1,0,idx) }								
				</td>
				<td>${vo.tel }</td>
			</tr>
		</c:forEach>	
		</tbody>
		
	
	</table>
</article>
	
</body>
</html>