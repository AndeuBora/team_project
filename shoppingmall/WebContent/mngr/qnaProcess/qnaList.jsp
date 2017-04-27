<!-- 836p~837p  일현-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link rel="stylesheet" href="/shoppingmall/css/style.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="/shoppingmall/mngr/qnaProcess/qnalist.js"></script>

<c:if test="${empty sessionScope.id}">
<meta http-equiv="Refresh" content="0;url=/shoppingmall/mg/mangerMain.do">
</c:if>

<div id="qnaHeader">
<button id="bookMain">관리자 메인으로</button>
</div>
<c:if test="${count==0}">
<p>등록된 Qna가 없습니다.
</c:if>

<c:if test="${count>0}">
<div id ="anaList">

<c:forEach var="qna" items="${qnaList}">

<ul>
<c:if test="${qna.getQora()==1 }">
<li><p>[${qna.getBook_title()}] 상품에 대한 QnA</p>
<p>${qna.getQna_writer()}<small class="date">
(${qna.getReg_date( )}
</small></p>
<p>${qna.getQna_content()}</p>
</c:if>
<c:if test="${na.getReply()==0 }">
<p><button id="reply" name="${qna.getQna_id()}"
onclick="reply(this)">답변하기 </button></p>
</c:if>
<c:if test="${qna.getQora()==2}">
<li class="re">
<p>${qna.getQna_content()}</p>
<p><c:if test="${qna.getQna_writer()=='manager'}" >관리자</c:if>
<small class="date">(${qna.getReg_date()})</small></p>
<p><button id="ediReply" name="${qna.getQna_id }" onclick="edit(this)">
수정</button></p>

</c:if>


</ul>
<hr>
</c:forEach>
</div>
</c:if>