<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<html>

<head>
<meta charset="utf-8">
<title>Main</title>
<meta name="viewport" content="width-device-width, initial-scale=1">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="./resources/boardInsert.css">



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
					<li><a href="questionList">Q&A</a></li>
				</span>
			</ul>
		</nav>
		<div class="dropdown">
			<button class="dropbtn">
				<a href="#"><i class="fas fa-bars"></i></a>
			</button>
			<div class="dropdown-content">
				${userOradmin} 
				<!-- <a href="createDB">DB생성</a> -->
			</div>
		</div>
	</header>
	<main>
		<div class="wrapper">
			<span class="mainTitle"> <a href="boardList"> <img
					src="./resources/image/talktoTalk_title2.png"></a>
			</span>
			<div class="content">
				 <div class="officialMemo">
				<h2>WRITE HERE</h2>
				</div>
				<section class="data_insert">
					<form action="boardInsert_action" method="POST" class="totalForm">
						<table width="100%">
							<tr height="1" bgcolor="#dddddd">
								<td colspan="8"></td>
							</tr>
							<tr>
								<td><input type="text" name="user_title"
									placeholder="제목을 입력하세요"></td>
							</tr>
						

							<tr>
								<td><input type="text" name="user_nickname" value="${user_nickname}" class="default"
									readonly></td>
							</tr>

							
							<tr>
								<td id="bottom"><textarea name="user_content" cols="53" rows="100" placeholder="내용을 입력하세요" ></textarea></td>
							</tr>

							<tr height="1" bgcolor="#dddddd">
								<td colspan="4"></td>
							</tr>

						</table>
						<input type="submit" value="complete" class="submit_btn">

					</form>
				</section>
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
