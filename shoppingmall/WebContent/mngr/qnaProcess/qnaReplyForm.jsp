<!-- 839p 일현 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css"/>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="/shoppingmall/mngr/qnaProcess/qnawrite.js"></script>
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty sessionScope.id}">
<meta http-equiv="Refresh" content="0;url=/shoppingmall/mg/managerMain.do">
</c:if>

<input type="hidden" id="qna_writer" value="manager">
<input type="hidden" id="qna_id" value="${qna_id}">
<input type="hidden" id="book_id" value="${book_id}">
<input type="hidden" id="book_title" value="${book_title}">
<input type="hidden" id="qora" value="${qora}">

<div id="writeForm" class="box">
<ul>
<li><p>[${book_title}] 의QnA </p>
    <p>QnA내용:${qna_cotnent}</p>
</ul>


</div>


</body>
</html>