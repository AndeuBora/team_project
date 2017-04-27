
<!-- 승환 : 901 페이지 -->

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
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
	</c:if>

	<div id="mStatus">
		<form id="uForm" method="post" action="/shoppingmall/modifyForm.do">
			<ul>
				<li><label for="passwd">비밀번호</label> <input id="passwd"
					name="passwd" type="password" size="20" placeholder="6~16자 숫자/문자"
					maxlength="16"> <input id="id" name="id" type="hidden"
					value="${sessionScope.id}"> <input type="submit"
					id="modify" value="정보수정">
			</ul>
		</form>

		<form id="dForm" method="post" action="/shoppingmall/deletePro.do">
			<ul>
				<li><label for="passwd">비밀번호</label> <input id="passwd"
					name="passwd" type="password" size="20" placeholder="6~16자 숫자/문자"
					maxlength="16"> <input id="id" name="id" type="hidden"
					value="${sessionScope.id}"> <input type="submit"
					id="delete" value="탈퇴"> <small class="cau">[탈퇴] 버튼을
						누르면 바로 탈퇴됨</small>
			</ul>
		</form>

		<button id="shopMain"
			onclick="window.location.href='/shoppingmall/index.do'">메인으로</button>
	</div>
</body>
</html>