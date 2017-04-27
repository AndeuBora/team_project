<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 보라 805 -->
<c:if test="${check==1 }">
	<!-- 관리자 인증성공 -->
	<c:set var="id" value="${id }" scope="session"/>
</c:if>