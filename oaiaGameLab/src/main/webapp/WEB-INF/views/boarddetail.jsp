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
	href="./resources/boarddetail.css">

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
				${userOradmin} <a href="createDB">DB생성</a>
			</div>
		</div>
	</header>
	<main>
		<div class="wrapper">
			<span class="mainTitle"> <a href="boardList"> <img
					src="./resources/image/talktoTalk_title2.png"></a>
			</span>
			<div class="content">
				<section class="wrap">
					<table class="contents">
						<tbody>
							<tr class="upper">
								<th>게시물번호</th>
								<td><span type="text" name="idx" value="${idx}">${idx }</span></td>
							</tr>
							<tr class="upper">
								<th>제목</th>
								<td><span type="text" name="user_title"
									value="${user_title}">${user_title}</span></td>
							</tr>
							<tr class="upper">
								<th>작성자</th>
								<td><span type="text" name="user_nickname"
									value="${user_nickname}">${user_nickname}</span></td>

							</tr>
							<tr class="upper">
								<th>작성시간</th>
								<td><span type="text" name="created" value="${created}">${created}</span></td>
							</tr>
							<tr class="bottom">
								<th>내용</th>
								<td><span type="text" name="user_content"
									value="${user_content}">${user_content}</span></td>
							</tr>
						</tbody>
					</table>
				</section>
			</div>
			<tr class="btnArea">
				<td class="insertBtn"><div class="boardInsertBtn">
						<a href='update?idx=${idx}'>수정하기</a><a href="delete?user_nickname='${user_nickname}'&idx=${idx}">삭제하기</a>
					</div></td>
			</tr>
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
