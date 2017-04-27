<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/shoppingmall/mngr/managerMain.js"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- 보라 800page~ -->
	<c:if test="${empty sessionScope.id }">
		<!-- 관리자 인증 id가 null값일때 -->
		<div id="mList">
			<p>로그인하세요ㅠㅠ</p>
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.id }">
		<!-- 관리자 인증 id가 null값이 아닐때 -->
		<div id="mList">
			<ul>
				<li>상품관련 작업</li>
				<li><button id="registProduct">상품등록</button></li>
				<li><button id="updateProduct">상품수정/삭제</button></li>
			</ul>
			<ul>
				<li>구매된 상품관련 작업</li>
				<li><button id="orderedProduct">전체구매목록 확인</button></li>
			</ul>
			<ul>
				<li>상품 QnA작업</li>
				<li><button id="qua">상품 QnA답변</button></li>
			</ul>
		</div>
	</c:if>
</body>
</html>