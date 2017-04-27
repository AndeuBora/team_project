// 보라 803page~
$(function() {
	// [로그인] 버튼클릭시
	$("#login").click(function() {
		var query = {
			id : $("#id").val(),
			passwd : $("#passwd").val()
		};
		$.ajax({
			type:"post",
			url:"/shoppingmall/mg/managerLoginPro.do",
			data:query,
			success:function(data){
				window.location.href="/shoppingmall/mg/managerMain.do";
			}
		});
	});
	
	// [로그아웃] 버튼클릭시
	$("#logout").click(function() {
		$.ajax({
			type:"post",
			url:"/shoppingmall/mg/managerLogout.do",
			data:query,
			success:function(data){
				window.location.href="/shoppingmall/mg/managerMain.do";
			}
		});
	});
});