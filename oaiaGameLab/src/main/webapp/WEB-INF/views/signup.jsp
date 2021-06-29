<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<html>

<head>
<meta charset="utf-8">
<title>Main</title>
<meta name="viewport" content="width-device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="./resources/main.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="./resources/message.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="./resources/signup.css">



<!--CDN 링크 -->
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
</head>

<body>
	<header>
		<span class="logo"> <a href="/oaiaGameLab/"><img
				src="./resources/image/mainLogo2.png"></a>
		</span>
		<nav>
			<ul class="menubar">
				<span>
					<li><a href="rank">Ranking</a></li>
				</span>
				<span>
					<li><a href="myList">TalktoTalk</a></li>
				</span>
				<span>
					<li><a href="question">Q&A</a></li>
				</span>
				<!-- <span>
        <li><a href="createDB">테이블생성(추후삭제)</a></li>
      </span> -->
			</ul>
		</nav>
		<div class="dropdown">
			<button class="dropbtn">
				<a href="#"><i class="fas fa-bars"></i></a>
			</button>
			<div class="dropdown-content">
				<a href="login">로그인</a> <a href="signup">회원가입</a> <a href="change">개인정보수정</a>
				<a href="createDB">DB생성</a>
			</div>
		</div>
		<script>
			function myFunction(x) {
				x.classList.toggle("change");
			}
		</script>
	</header>
	<main>
		<div class="main1">
			<div class="messageWrap">
				<div class="container">
					<div class="row">
						<div class="panel panel-primary">
							<div class="panel-body">
								<form method="POST" action="signup_action" role="form">
									<div class="form-group">
										<h2>회원가입</h2>
									</div>
									<div class="form-group">
										<label class="control-label" for="signupID">아이디</label> <input
											id="user_id" name="user_id" type="text" maxlength="50"
											class="form-control">
									</div>
									<div class="form-group">
										<label class="control-label" for="signupPassword">비밀번호</label>
										<input id="user_pwd1" name="user_pwd1" type="password"
											maxlength="25" class="form-control">
									</div>
									<div class="form-group">
										<label class="control-label" for="signupPasswordagain">비밀번호
											확인</label> <input id="user_pwd2" name="user_pwd2" type="password"
											maxlength="25" class="form-control">
									</div>
									<div class="form-group">
										<label class="control-label" for="signupName">이름</label> <input
											id="user_name" name="user_name" type="text" maxlength="50"
											class="form-control">
									</div>
									<div class="form-group">
										<label class="control-label" for="signupBirth">생년월일</label> <input
											id="user_birth" name="user_birth" type="date" maxlength="50"
											class="form-control">
									</div>
									<div class="form-group">
										<label class="control-label" for="signupEmail">Email</label> <input
											id="user_email" name="user_email" type="email" maxlength="50"
											class="form-control">
									</div>
									<div class="form-group">
										<label class="control-label" for="signupNickName">닉네임</label>
										<input id="user_nickName" name="user_nickName" type="text"
											maxlength="50" class="form-control">
									</div>
									<div class="form-group">
										<button id="signupBtn" type="submit"
											class="btn btn-info btn-block">가입하기</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</main>
	<footer class="footer-distributed">
		<div class="bottomWrap">
			<div class="bottomLogo">
				<img src="./resources/image/bottomLogo.gif">
			</div>
			<div class="footer-left">
				<p class="footer-links">
					<a class="link-1" href="#">Home</a> <a href="#">Blog</a> <a
						href="#">About</a> <a href="#">Contact Us</a>
				</p>
			</div>
			<div class="footer-right">

				<a href="#"><img src="./resources/image/fb.png"></a> <a
					href="#"><img src="./resources/image/is.png"></a> <a href="#"><img
					src="./resources/image/GitHub_Logo.png"></a> <a href="#"><img
					src="./resources/image/mail.png"></a>

			</div>
			<div class="snsLogo">
				<p>OaiaGameCompany &copy; 2021</p>
			</div>
		</div>
	</footer>
</body>
<script>
	$("#signupBtn").click(function() {
		if ($("#user_id").val() == "") {
			alert("아이디를 입력해 주세요");
			$("#user_id").focus();
			return false;
		} else if ($("#user_pwd1").val() == "") {
			alert("비밀번호를 입력해 주세요");
			$("#user_pwd1").focus();
			return false;
		} else if ($("#user_pwd1").val() != $("#user_pwd2").val()) {
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("#user_pwd2").focus();
			return false;
		} else if ($("#user_name").val() == "") {
			alert("이름을 입력해 주세요");
			$("#user_name").focus();
			return false;
		} else if ($("#user_birth").val() == "") {
			alert("생년월일을 선택해 주세요");
			$("#user_birth").focus();
			return false;
		} else if ($("#user_email").val() == "") {
			alert("이메일 주소를 입력해 주세요");
			$("#user_email").focus();
			return false;
		} else if ($("#user_nickName").val() == "") {
			alert("닉네임을 입력해 주세요");
			$("#user_nickName").focus();
			return false;
		}

	});
</script>

</html>
