<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<html>

<head>
<meta charset="utf-8">
<title>Main</title>
<meta name="viewport" content="width-device-width, initial-scale=1">
<!-- <link rel="stylesheet" type="text/css" media="screen"
	href="./resources/main.css"> -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<!-- <link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css"> -->
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
					<li><a href="ranking">Ranking</a></li>
				</span>
				<span>
					<li><a href="boardList">TalktoTalk</a></li>
				</span>
				<span>
					<li><a href="question">Q&A</a></li>
				</span>
			</ul>
		</nav>
		<div class="dropdown">
			<button class="dropbtn">
				<a href="#"><i class="fas fa-bars"></i></a>
			</button>
			<div class="dropdown-content">
				<a href="login">로그인</a> <a href="signup">회원가입</a> <a href="createDB">DB생성</a>
			</div>
		</div>
	</header>
	<main>

		<div class="wrapper">
			<div class="content">
				<div class="oneCategory">
					<div class="wrapImageLogo">
						<img src="./resources/image/mainLogo2.png">
						<h2>
							회원가입을 환영합니다. <br> 하단 이용약관 동의를 해주셔야 회원가입이 진행됩니다.
						</h2>
					</div>
				</div>
				<form action="" id="joinForm">
					<ul class="join_box">
						<li class="checkBox check02">
							<ul class="clearfix">
								<li>이용약관 동의(필수)</li>
								<li class="checkBtn"><input type="checkbox" name="chk">
								</li>
							</ul> <textarea name="" id="">
Oaia 게임 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 당사의 사이트 서비스의 이용과 관련하여 웹 서비스를 제공하는 Oaia 주식회사(이하 ‘Oaia’)와 이를 이용하는 Oaia 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 Oaia 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       </textarea>
						</li>
					</ul>
				</form>
				<form method="POST" action="signup_action" role="form">
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="id">아이디</label>
						</h2>
						<span class="box int_id"> <input type="text" id="user_id"
							name="user_id" class="int" maxlength="20">
						</span> <span class="error_next_box"></span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="pswd1">비밀번호</label>
						</h2>
						<span class="box int_pass"> <input type="password"
							id="user_pwd1" name="user_pwd1" class="int" maxlength="25">
							<span id="alertTxt">사용불가</span> <span class="error_next_box"></span>
						</span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="pswd2">비밀번호 확인</label>
						</h2>
						<span class="box int_pass_check"> <input type="password"
							id="user_pwd2" name="user_pwd2" class="int" maxlength="25">
							<span id="alertTxt">사용불가</span> <span class="error_next_box"></span>
						</span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="name">이름</label>
						</h2>
						<span class="box int_name"> <input type="text"
							id="user_name" name="user_name" class="int" maxlength="50">
						</span> <span class="error_next_box"></span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="name">생년월일</label>
						</h2>
						<span class="box bir_wrap"> <input type="date"
							id="user_birth" name="user_birth" class="int" maxlength="50">
						</span> <span class="error_next_box"></span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="email">Email</label>
						</h2>
						<span class="box int_email"> <input type="email"
							id="user_email" name="user_email" class="int" maxlength="100"
							placeholder="선택입력">
						</span> <span class="error_next_box">이메일 주소를 다시 확인해주세요.</span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="nickName">닉네임</label>
						</h2>
						<span class="box int_nickName"> <input type="text"
							id="user_nickName" name="user_nickName" class="int"
							maxlength="100" placeholder="선택입력">
						</span> <span class="error_next_box"></span>
					</div>
					<div class="btn_area">
						<button type="submit" class="btn btn-info btn-block"
							id="signupBtn">가입하기</button>
					</div>
				</form>

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
