
<!-- 승환 : 903 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 코어 : --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link href="/shoppingmall/css/style.css" rel="stylesheet"
	type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="/shoppingmall/member/modify.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
	</c:if>

	<div id="regForm" class="box">
		<ul>
			<li><p class="center">회원 정보 수정
			<li><label for="id">아이디</label> <input id="id" name="id"
				type="email" size="20" maxlength="50" value="${id}" readonly
				disabled>
			<li><label for="passwd">비밀번호</label> <input id="passwd"
				name="passwd" type="password" size="20" placeholder="6~16자 숫자/문자"
				maxlength="16"> <small class="cau">반드시 입력하세요.</small>
			<li><label for="name">이름</label> <input id="name" name="name"
				type="text" size="20" maxlength="10" value="${m.getName()}">
			<li><label for="address">주소</label> <input id="address"
				name="address" type="text" size="30" maxlength="50"
				value="${m.getAddress()}">
			<li><label for="tel">전화번호</label> <input id="tel" name="tel"
				type="tel" size="20" maxlength="20" value="${m.getTel()}">
			<li class="label2"><button id="modifyProcess">수정</button>
				<button id="cancle">취소</button>
		</ul>
	</div>
</body>
</html>