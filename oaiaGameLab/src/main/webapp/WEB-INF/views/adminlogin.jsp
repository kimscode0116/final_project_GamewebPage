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
	href="./resources/login.css">
	<link rel="stylesheet" type="text/css" media="screen"
	href="./resources/adminlogin.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">


<!--CDN 링크 -->
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
</head>

<body>
	<header>
		<span class="logo"> <a href="/oaiaGameLab/"> <img
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
		<div class="loginWrap">
			<form class="adminloginForm" action="adminlogin_action" method="get">
				<div class="titleArea">
					<img src="./resources/image/mainLogo2.png">
					<h2>Only Administrater</h2>
				</div>
				<div class="idForm">
					<input type="text" name="admin_id" class="admin_id" placeholder="adminID" />
				</div>
				<div class="passForm">
					<input type="password" name="admin_pwd" class="admin_pwd"
						placeholder="adminPW">
				</div>
				<button type="submit" class="btn" onclick="button()">admin
					LOGIN</button>

				<!-- <div class="bottomText">
					Don't you have ID? <a href="#">sign up</a><br></br>
					Are you admin? <a href="adminlogin">Go admin</a>
				</div> -->
				<div class="bottomText">
					<p>
						This is admin Login Page<br> If you have any question,
						contact 'admin1'
					</p>
				</div>
			</form>
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
