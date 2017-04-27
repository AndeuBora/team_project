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
<script type="text/javascript" src="mlogin.js"></script>
<title>Insert title here</title>
</head>
<body>
<!-- 보라 802 -->
	<c:if test="${empty sessionScope.id }">
	<!-- 관리자 인증 안된경우 -->
		<div id="status">
			<ul>
				<li><label>아이디<input type="email" name="id" id="id"
						size="20" placeholder="example@kings.com" maxlength="50">
				</label> <label>비밀번호<input type="password" name="passwd" id="passwd" size="20"
						placeholder="6~16 문자/숫자" maxlength="16">
				</label>
				<button id="login">로그인</button>
				</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.id }">
	<!-- 관리자 인증 된경우 -->
		<div id="status">
			<ul>
				<li>관리자 로그인 성공↖⊙ㅅ⊙↗ <span style="color: purple;">(작업중입니다)</span>
				<button id="logout">로그아웃</button>
				</li>
			</ul>
		</div>
	</c:if>
</body>
</html>