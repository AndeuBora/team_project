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
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="header">
		<div id="logo" class="box">
			<img src="/shoppingmall/images/mollalogo3.png" class="noborder"
				id="logo" />
		</div>
		<div id="auth" class="box">
			<c:if test="${type == 0}">
				<jsp:include page="mngr/logon/mLoginForm.jsp" />
			</c:if>
			<jsp:include page="member/loginForm.jsp" />
		</div>
	</div>
	<div id="content" class="box2">
		<jsp:include page="${cont}" />
	</div>
</body>