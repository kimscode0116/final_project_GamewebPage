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
<link rel="stylesheet" type="text/css" media="screen"
	href="./resources/ranking.css">


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
				${userOradmin} <a href="createDB">DB생성</a>
			</div>
		</div>
	</header>
	<main>
		<div class="wrapper">
			<section class="wrap">
				<div class="rankingPng">
					<img src="./resources/image/ranking123.png">
				</div>
				<table class="ranking123">
					<colgroup>
						<col width="30%" />
						<col width="30%" />
						<col width="30%" />
					</colgroup>
					<thead>
						<tr class="title123">
							<th class="raning1">1등</th>
							<th class="raning2">2등</th>
							<th class="raning3">3등</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="raning1">김씨는 나야</td>
							<td class="raning2">김씨는 나야</td>
							<td class="raning3">김씨는 나야</td>
						</tr>
						<tr>
							<td class="raning1">16</td>
							<td class="raning2">15</td>
							<td class="raning3">14</td>
						</tr>
					</tbody>
				</table>
				<table class="allranking">
					<thead>
						<tr>
							<th>랭킹</th>
							<th>닉네임</th>
							<th>점수</th>
						</tr>
					</thead>
					<tbody>${rankList }
					</tbody>
				</table>
				<br>
			</section>
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
