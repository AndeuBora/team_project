
<!-- 승환 : 907 페이지 -->

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
</head>
<body>
	<c:if test="${check == 1}">
		<c:remove var="id" scope="session" />
	</c:if>
	<meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
</body>
</html>