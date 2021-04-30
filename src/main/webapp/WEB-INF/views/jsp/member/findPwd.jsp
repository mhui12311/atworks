<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/jsp/main/header.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<script>
$(function() {
			var authNum = "";
			var idPattern = /^[A-Za-z0-9]{1}[A-Za-z0-9]{3,19}$/;
			var namePattern = /^[가-힣]{2,6}$/;
			var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
			$("#u_id").blur(function() {
				if (idPattern.test($(this).val())) {
						console.log(idPattern.test($(this).val()));
						$("#id_check").text('');
				} else {
					$('#id_check').text('아이디를 확인해주세요 (4자 이상)');
					$('#id_check').css('color', 'red');
				}
			});
			
			$("#u_pwd").blur(function() {
				if (pwdPattern.test($(this).val())) {
						console.log(pwdPattern.test($(this).val()));
						$("#pwd_check").text('');
				} else {
					$('#pwd_check').text('비밀번호를 확인해주세요 (8자 이상, 영문&특수문자사용)');
					$('#pwd_check').css('color', 'red');
				}
			});
			
			$("#u_pwd2").blur(function() {
				if (pwdPattern.test($(this).val())) {
						console.log(pwdPattern.test($(this).val()));
						$("#pwd_check2").text('');
				} else {
					$('#pwd_check2').text('비밀번호를 확인해주세요');
					$('#pwd_check2').css('color', 'red');
				}
			});
			
			
			$("#u_name").blur(function() {
				if (namePattern.test($(this).val())) {
						console.log(namePattern.test($(this).val()));
						$("#name_check").text('');
				} else {
					$('#name_check').text('이름을 확인해주세요');
					$('#name_check').css('color', 'red');
				}
			});
			
			$("#u_email").blur(function() {
				if (emailPattern.test($(this).val())) {
						console.log(emailPattern.test($(this).val()));
						$("#email_check").text('');
				} else {
					$('#email_check').text('이메일을 확인해주세요');
					$('#email_check').css('color', 'red');
				}
			}); 
			
	 	<%-- $("#findPwd").click(function(){
			if($.trim($("#u_name").val()) !== $("#u_name").val() || $.trim($("#u_email").val()) !== $("#u_email").val() || $.trim($("#u_id").val()) !== $("#u_id").val()){
				alert("공백은 입력이 불가능합니다.");
				return false;
			}else{
				$.ajax({
					url : "<%=request.getContextPath() %>member/findPwd",
					type : "POST",
					date : { u_id : $("#u_id").val(),
						u_name : $("#u_name").val(), 
						u_email : $("#u_email").val(), 
					},
					success : function(result) {
						alert(result)
					}
				});
			}
		}) --%> 	
})
		function goBack() {
			window.history.back();
		}
		
	
		
		function findPwd() {
			if($.trim($("#u_id").val()) !== $("#u_id").val() || $.trim($("#u_name").val()) !== $("#u_name").val() || $.trim($("#u_email").val()) !== $("#u_email").val()){
				alert("공백은 입력이 불가능합니다.");
				return false;
			}else{
				$.ajax({
					url : "<%=request.getContextPath()%>/member/find_pwd",
					type : "POST",
					date : { u_id : $("#u_id").val(), u_name : $("#u_name").val(), 
						u_email : $("#u_email").val()
					},
					success : function(result) {
						alert(result)
					}
				});
			}
		}
</script>

</head>
<br>
<br>
<br>
<br>
<br>
<div id="login_wrap">
	<div class='row' style="height: 400px">
		<div class='col-sm-3'></div>
		<div class='col-sm-9'>
			<h2 class="login_title">비밀번호 찾기</h2>
			<form name="loginForm" method="post" action="member/login_ok">
				<div class="mb-3 row">
					<label for="u_id" class="col-sm-2 col-form-label">아이디</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="u_id" name="u_id"
							placeholder="ID를 입력하세요">
					</div>
				</div>
				<div class="mb-3 row">
					<label for="u_name" class="col-sm-2 col-form-label">이름</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="u_name" name="u_name"
							placeholder="이름을 입력하세요">
					</div>
				</div>
				<div class="mb-3 row">
					<label for="u_email" class="col-sm-2 col-form-label">이메일</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="u_email"
							name="u_email" placeholder="이메일을 입력하세요">
					</div>
				</div>
				<div class="mb-3 row">
					<label for="u_name" class="col-sm-2 col-form-label"></label>
					<div class="col-sm-5">
						<input type="button" value="확인" class="input_button"
							onclick="findPwd()" />
					</div>
				</div>
				<div class='mb-3 row'>
					<div id="login_menu">
						<input type="button" value="뒤로가기" class="input_button"
							onclick="goBack();" />
					</div>
				</div>
			</form>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/jsp/main/footer.jsp"%>