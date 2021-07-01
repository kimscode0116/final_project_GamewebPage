<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<html>

<head>
<meta charset="utf-8">
<title>개인정보수정</title>
<meta name="viewport" content="width-device-width, initial-scale=1">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
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
				<a href="logout">로그아웃</a><a href="myinfo_pwd">개인정보수정</a> <a
					href="createDB">DB생성</a>
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
							회원정보 수정페이지입니다. <br> 변경을 원하시는 회원정보를 수정 후, 하단 수정완료를 클릭해주세요.
						</h2>
					</div>
				</div>
				<form method="POST" action="update_action" role="form">
					<label><input type="hidden" name="idx" value="${idx}" /></label>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="id">아이디</label>
						</h2>
						<span class="box_readonly int_id"> <!-- <input type="text" id="user_id"
							name="user_id" class="int" maxlength="20"> --> <span
							class="readOnly">${usesr_id}</span>
						</span> <span class="error_next_box"></span>
					</div>
					<label><input type="hidden" name="user_pwd1"
						value="${user_pwd}" /></label>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="pswd2">새로운 비밀번호</label>
						</h2>
						<span class="box int_pass_check"> <input type="password"
							id="user_pwd2" name="user_pwd2" placeholder="변경하지 않을 경우 빈칸"
							class="int" maxlength="25"> <span class="error_next_box"></span>
						</span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="name">이름</label>
						</h2>
						<span class="box int_name"> <input type="text"
							name="user_name" class="int" maxlength="50" value="${user_name}" />
						</span> <span class="error_next_box"></span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="name">생년월일</label>
						</h2>
						<span class="box bir_wrap"> <input type="date"
							id="user_birth" name="user_birth" class="int" maxlength="50"
							value="${user_birth}">
						</span> <span class="error_next_box"></span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="email">Email</label>
						</h2>
						<span class="box int_email"> <input type="email"
							id="user_email" name="user_email" class="int" maxlength="100"
							value="${user_email}">
						</span> <span class="error_next_box">이메일 주소를 다시 확인해주세요.</span>
					</div>
					<div class="oneCategory">
						<h2 class="join_title">
							<label for="nickName">닉네임</label>
						</h2>
						<span class="box int_nickName"> <input type="text"
							id="user_nickName" name="user_nickName" class="int"
							maxlength="100" value="${user_nickName}">
						</span> <span class="error_next_box"></span>
					</div>
					<div class="btn_area">
						<button type="submit" class="btn btn-info btn-block"
							id="signupBtn">수정완료</button>
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

</html>
