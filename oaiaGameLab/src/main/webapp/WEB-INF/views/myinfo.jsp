<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MyPage</title>
</head>
<body>
	<section class="wrap">
		<form action="update_action">
		<b>내정보</b>
			<label><input type="hidden" name="idx" value="${idx}" /></label><br>
      <label> 아이디 : ${usesr_id}</label><br>
      <label><input type="hidden" name="user_pwd1" value="${user_pwd}" /></label>
      <label> 비밀번호 변경 : <input type="password" name="user_pwd2" placeholder="변경하지 않을 경우 빈칸" /></label><br>
      <label> 이름 : <input type="text" name="user_name" value="${user_name}" /></label><br>
      <label> 생년월일 : <input type="date" name="user_birth" value="${user_birth}" /></label><br>
      <label> email : <input type="text" name="user_email" value="${user_email}" /></label><br>
      <label> 닉네임 : <input type="text" name="user_nickName" value="${user_nickName}" /></label><br>
      <input type="submit" value="수정" />
			<button id="noBtn" type="button">메인페이지로</button>
		</form>
	</section>
</body>
</html>
