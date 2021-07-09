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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">


<!--CDN 링크 -->
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
</head>

<body>
	<header>
		<span class="logo"> <a href="/oaiaGameLab/">
		<img src="./resources/image/mainLogo2.png"></a>
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
					<li><a href="questionList">Q&A</a></li>
				</span>
			</ul>
		</nav>
		<div class="dropdown">
			<button class="dropbtn">
				<a href="#"><i class="fas fa-bars"></i></a>
			</button>
			<div class="dropdown-content">
				<a href="login">로그인</a> <a href="signup">회원가입</a> 
				<!-- <a href="createDB">DB생성</a> -->

			</div>
		</div>
	</header>
	<main>
		<div class="loginWrap">
			<form class="loginForm" action="login_action" method="post">
				<div class="titleArea">
					<img src="./resources/image/mainLogo2.png">					
 					<h1>LOGIN</h1>
				</div>
				<div class="idForm">
					<input type="text"  name="user_id" class="id" placeholder="ID">
				</div>
				<div class="passForm">
					<input type="password" name="user_pwd" class="pw" placeholder="PW">
				</div>
				<button type="submit" class="btn" >LOG IN</button>
				<div class="bottomText">
					Don't you have ID? <a href="signup">sign up</a><br></br>
					Are you admin? <a href="adminlogin">Go admin</a>

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
					<a class="link-1" href="#">Home</a> <a href="Aboutus">About</a> <a href="contactus">Contact Us</a>

				</p>
			</div>
			<div class="footer-right">

				<a href="https://www.instagram.com/kkeehhh"><img
					src="./resources/image/is.png"></a> <a
					href="https://www.instagram.com/go_soojin_"><img
					src="./resources/image/is2.png"></a> <a
					href="https://github.com/0seony"><img
					src="./resources/image/GitHub_Logo.png"></a> <a
					href="https://github.com/dudpray0220"><img
					src="./resources/image/GitHub_Logo2.png"></a>

			</div>
			<div class="snsLogo">
				<p>OaiaGameCompany &copy; 2021</p>
			</div>
		</div>

	</footer>
</body>

</html>
